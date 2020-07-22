package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.AutresVariable;
import fr.insy2s.repository.AutresVariableRepository;
import fr.insy2s.service.AutresVariableService;
import fr.insy2s.service.dto.AutresVariableDTO;
import fr.insy2s.service.mapper.AutresVariableMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AutresVariableResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AutresVariableResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_MONTANT = 1D;
    private static final Double UPDATED_MONTANT = 2D;

    private static final String DEFAULT_JUSTIFICATIF = "AAAAAAAAAA";
    private static final String UPDATED_JUSTIFICATIF = "BBBBBBBBBB";

    @Autowired
    private AutresVariableRepository autresVariableRepository;

    @Autowired
    private AutresVariableMapper autresVariableMapper;

    @Autowired
    private AutresVariableService autresVariableService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutresVariableMockMvc;

    private AutresVariable autresVariable;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AutresVariable createEntity(EntityManager em) {
        AutresVariable autresVariable = new AutresVariable()
            .description(DEFAULT_DESCRIPTION)
            .date(DEFAULT_DATE)
            .montant(DEFAULT_MONTANT)
            .justificatif(DEFAULT_JUSTIFICATIF);
        return autresVariable;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AutresVariable createUpdatedEntity(EntityManager em) {
        AutresVariable autresVariable = new AutresVariable()
            .description(UPDATED_DESCRIPTION)
            .date(UPDATED_DATE)
            .montant(UPDATED_MONTANT)
            .justificatif(UPDATED_JUSTIFICATIF);
        return autresVariable;
    }

    @BeforeEach
    public void initTest() {
        autresVariable = createEntity(em);
    }

    @Test
    @Transactional
    public void createAutresVariable() throws Exception {
        int databaseSizeBeforeCreate = autresVariableRepository.findAll().size();
        // Create the AutresVariable
        AutresVariableDTO autresVariableDTO = autresVariableMapper.toDto(autresVariable);
        restAutresVariableMockMvc.perform(post("/api/autres-variables")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autresVariableDTO)))
            .andExpect(status().isCreated());

        // Validate the AutresVariable in the database
        List<AutresVariable> autresVariableList = autresVariableRepository.findAll();
        assertThat(autresVariableList).hasSize(databaseSizeBeforeCreate + 1);
        AutresVariable testAutresVariable = autresVariableList.get(autresVariableList.size() - 1);
        assertThat(testAutresVariable.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAutresVariable.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testAutresVariable.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testAutresVariable.getJustificatif()).isEqualTo(DEFAULT_JUSTIFICATIF);
    }

    @Test
    @Transactional
    public void createAutresVariableWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = autresVariableRepository.findAll().size();

        // Create the AutresVariable with an existing ID
        autresVariable.setId(1L);
        AutresVariableDTO autresVariableDTO = autresVariableMapper.toDto(autresVariable);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutresVariableMockMvc.perform(post("/api/autres-variables")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autresVariableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AutresVariable in the database
        List<AutresVariable> autresVariableList = autresVariableRepository.findAll();
        assertThat(autresVariableList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAutresVariables() throws Exception {
        // Initialize the database
        autresVariableRepository.saveAndFlush(autresVariable);

        // Get all the autresVariableList
        restAutresVariableMockMvc.perform(get("/api/autres-variables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autresVariable.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.doubleValue())))
            .andExpect(jsonPath("$.[*].justificatif").value(hasItem(DEFAULT_JUSTIFICATIF)));
    }
    
    @Test
    @Transactional
    public void getAutresVariable() throws Exception {
        // Initialize the database
        autresVariableRepository.saveAndFlush(autresVariable);

        // Get the autresVariable
        restAutresVariableMockMvc.perform(get("/api/autres-variables/{id}", autresVariable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autresVariable.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.doubleValue()))
            .andExpect(jsonPath("$.justificatif").value(DEFAULT_JUSTIFICATIF));
    }
    @Test
    @Transactional
    public void getNonExistingAutresVariable() throws Exception {
        // Get the autresVariable
        restAutresVariableMockMvc.perform(get("/api/autres-variables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAutresVariable() throws Exception {
        // Initialize the database
        autresVariableRepository.saveAndFlush(autresVariable);

        int databaseSizeBeforeUpdate = autresVariableRepository.findAll().size();

        // Update the autresVariable
        AutresVariable updatedAutresVariable = autresVariableRepository.findById(autresVariable.getId()).get();
        // Disconnect from session so that the updates on updatedAutresVariable are not directly saved in db
        em.detach(updatedAutresVariable);
        updatedAutresVariable
            .description(UPDATED_DESCRIPTION)
            .date(UPDATED_DATE)
            .montant(UPDATED_MONTANT)
            .justificatif(UPDATED_JUSTIFICATIF);
        AutresVariableDTO autresVariableDTO = autresVariableMapper.toDto(updatedAutresVariable);

        restAutresVariableMockMvc.perform(put("/api/autres-variables")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autresVariableDTO)))
            .andExpect(status().isOk());

        // Validate the AutresVariable in the database
        List<AutresVariable> autresVariableList = autresVariableRepository.findAll();
        assertThat(autresVariableList).hasSize(databaseSizeBeforeUpdate);
        AutresVariable testAutresVariable = autresVariableList.get(autresVariableList.size() - 1);
        assertThat(testAutresVariable.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAutresVariable.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testAutresVariable.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testAutresVariable.getJustificatif()).isEqualTo(UPDATED_JUSTIFICATIF);
    }

    @Test
    @Transactional
    public void updateNonExistingAutresVariable() throws Exception {
        int databaseSizeBeforeUpdate = autresVariableRepository.findAll().size();

        // Create the AutresVariable
        AutresVariableDTO autresVariableDTO = autresVariableMapper.toDto(autresVariable);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutresVariableMockMvc.perform(put("/api/autres-variables")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autresVariableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AutresVariable in the database
        List<AutresVariable> autresVariableList = autresVariableRepository.findAll();
        assertThat(autresVariableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAutresVariable() throws Exception {
        // Initialize the database
        autresVariableRepository.saveAndFlush(autresVariable);

        int databaseSizeBeforeDelete = autresVariableRepository.findAll().size();

        // Delete the autresVariable
        restAutresVariableMockMvc.perform(delete("/api/autres-variables/{id}", autresVariable.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AutresVariable> autresVariableList = autresVariableRepository.findAll();
        assertThat(autresVariableList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
