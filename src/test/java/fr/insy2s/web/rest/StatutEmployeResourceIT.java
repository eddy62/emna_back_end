package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.StatutEmploye;
import fr.insy2s.repository.StatutEmployeRepository;
import fr.insy2s.service.StatutEmployeService;
import fr.insy2s.service.dto.StatutEmployeDTO;
import fr.insy2s.service.mapper.StatutEmployeMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link StatutEmployeResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class StatutEmployeResourceIT {

    private static final String DEFAULT_CODE_REF = "AAAAAAAAAA";
    private static final String UPDATED_CODE_REF = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private StatutEmployeRepository statutEmployeRepository;

    @Autowired
    private StatutEmployeMapper statutEmployeMapper;

    @Autowired
    private StatutEmployeService statutEmployeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStatutEmployeMockMvc;

    private StatutEmploye statutEmploye;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatutEmploye createEntity(EntityManager em) {
        StatutEmploye statutEmploye = new StatutEmploye()
            .codeRef(DEFAULT_CODE_REF)
            .libelle(DEFAULT_LIBELLE);
        return statutEmploye;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatutEmploye createUpdatedEntity(EntityManager em) {
        StatutEmploye statutEmploye = new StatutEmploye()
            .codeRef(UPDATED_CODE_REF)
            .libelle(UPDATED_LIBELLE);
        return statutEmploye;
    }

    @BeforeEach
    public void initTest() {
        statutEmploye = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatutEmploye() throws Exception {
        int databaseSizeBeforeCreate = statutEmployeRepository.findAll().size();

        // Create the StatutEmploye
        StatutEmployeDTO statutEmployeDTO = statutEmployeMapper.toDto(statutEmploye);
        restStatutEmployeMockMvc.perform(post("/api/statut-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statutEmployeDTO)))
            .andExpect(status().isCreated());

        // Validate the StatutEmploye in the database
        List<StatutEmploye> statutEmployeList = statutEmployeRepository.findAll();
        assertThat(statutEmployeList).hasSize(databaseSizeBeforeCreate + 1);
        StatutEmploye testStatutEmploye = statutEmployeList.get(statutEmployeList.size() - 1);
        assertThat(testStatutEmploye.getCodeRef()).isEqualTo(DEFAULT_CODE_REF);
        assertThat(testStatutEmploye.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createStatutEmployeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statutEmployeRepository.findAll().size();

        // Create the StatutEmploye with an existing ID
        statutEmploye.setId(1L);
        StatutEmployeDTO statutEmployeDTO = statutEmployeMapper.toDto(statutEmploye);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatutEmployeMockMvc.perform(post("/api/statut-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statutEmployeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StatutEmploye in the database
        List<StatutEmploye> statutEmployeList = statutEmployeRepository.findAll();
        assertThat(statutEmployeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = statutEmployeRepository.findAll().size();
        // set the field null
        statutEmploye.setCodeRef(null);

        // Create the StatutEmploye, which fails.
        StatutEmployeDTO statutEmployeDTO = statutEmployeMapper.toDto(statutEmploye);

        restStatutEmployeMockMvc.perform(post("/api/statut-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statutEmployeDTO)))
            .andExpect(status().isBadRequest());

        List<StatutEmploye> statutEmployeList = statutEmployeRepository.findAll();
        assertThat(statutEmployeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = statutEmployeRepository.findAll().size();
        // set the field null
        statutEmploye.setLibelle(null);

        // Create the StatutEmploye, which fails.
        StatutEmployeDTO statutEmployeDTO = statutEmployeMapper.toDto(statutEmploye);

        restStatutEmployeMockMvc.perform(post("/api/statut-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statutEmployeDTO)))
            .andExpect(status().isBadRequest());

        List<StatutEmploye> statutEmployeList = statutEmployeRepository.findAll();
        assertThat(statutEmployeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStatutEmployes() throws Exception {
        // Initialize the database
        statutEmployeRepository.saveAndFlush(statutEmploye);

        // Get all the statutEmployeList
        restStatutEmployeMockMvc.perform(get("/api/statut-employes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statutEmploye.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeRef").value(hasItem(DEFAULT_CODE_REF)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getStatutEmploye() throws Exception {
        // Initialize the database
        statutEmployeRepository.saveAndFlush(statutEmploye);

        // Get the statutEmploye
        restStatutEmployeMockMvc.perform(get("/api/statut-employes/{id}", statutEmploye.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(statutEmploye.getId().intValue()))
            .andExpect(jsonPath("$.codeRef").value(DEFAULT_CODE_REF))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }

    @Test
    @Transactional
    public void getNonExistingStatutEmploye() throws Exception {
        // Get the statutEmploye
        restStatutEmployeMockMvc.perform(get("/api/statut-employes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatutEmploye() throws Exception {
        // Initialize the database
        statutEmployeRepository.saveAndFlush(statutEmploye);

        int databaseSizeBeforeUpdate = statutEmployeRepository.findAll().size();

        // Update the statutEmploye
        StatutEmploye updatedStatutEmploye = statutEmployeRepository.findById(statutEmploye.getId()).get();
        // Disconnect from session so that the updates on updatedStatutEmploye are not directly saved in db
        em.detach(updatedStatutEmploye);
        updatedStatutEmploye
            .codeRef(UPDATED_CODE_REF)
            .libelle(UPDATED_LIBELLE);
        StatutEmployeDTO statutEmployeDTO = statutEmployeMapper.toDto(updatedStatutEmploye);

        restStatutEmployeMockMvc.perform(put("/api/statut-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statutEmployeDTO)))
            .andExpect(status().isOk());

        // Validate the StatutEmploye in the database
        List<StatutEmploye> statutEmployeList = statutEmployeRepository.findAll();
        assertThat(statutEmployeList).hasSize(databaseSizeBeforeUpdate);
        StatutEmploye testStatutEmploye = statutEmployeList.get(statutEmployeList.size() - 1);
        assertThat(testStatutEmploye.getCodeRef()).isEqualTo(UPDATED_CODE_REF);
        assertThat(testStatutEmploye.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingStatutEmploye() throws Exception {
        int databaseSizeBeforeUpdate = statutEmployeRepository.findAll().size();

        // Create the StatutEmploye
        StatutEmployeDTO statutEmployeDTO = statutEmployeMapper.toDto(statutEmploye);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatutEmployeMockMvc.perform(put("/api/statut-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statutEmployeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StatutEmploye in the database
        List<StatutEmploye> statutEmployeList = statutEmployeRepository.findAll();
        assertThat(statutEmployeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatutEmploye() throws Exception {
        // Initialize the database
        statutEmployeRepository.saveAndFlush(statutEmploye);

        int databaseSizeBeforeDelete = statutEmployeRepository.findAll().size();

        // Delete the statutEmploye
        restStatutEmployeMockMvc.perform(delete("/api/statut-employes/{id}", statutEmploye.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StatutEmploye> statutEmployeList = statutEmployeRepository.findAll();
        assertThat(statutEmployeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
