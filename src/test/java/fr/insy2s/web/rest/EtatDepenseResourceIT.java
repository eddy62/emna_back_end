package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.EtatDepense;
import fr.insy2s.repository.EtatDepenseRepository;
import fr.insy2s.service.EtatDepenseService;
import fr.insy2s.service.dto.EtatDepenseDTO;
import fr.insy2s.service.mapper.EtatDepenseMapper;

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
 * Integration tests for the {@link EtatDepenseResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EtatDepenseResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_REF = "AAAAAAAAAA";
    private static final String UPDATED_CODE_REF = "BBBBBBBBBB";

    @Autowired
    private EtatDepenseRepository etatDepenseRepository;

    @Autowired
    private EtatDepenseMapper etatDepenseMapper;

    @Autowired
    private EtatDepenseService etatDepenseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEtatDepenseMockMvc;

    private EtatDepense etatDepense;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatDepense createEntity(EntityManager em) {
        EtatDepense etatDepense = new EtatDepense()
            .libelle(DEFAULT_LIBELLE)
            .codeRef(DEFAULT_CODE_REF);
        return etatDepense;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatDepense createUpdatedEntity(EntityManager em) {
        EtatDepense etatDepense = new EtatDepense()
            .libelle(UPDATED_LIBELLE)
            .codeRef(UPDATED_CODE_REF);
        return etatDepense;
    }

    @BeforeEach
    public void initTest() {
        etatDepense = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtatDepense() throws Exception {
        int databaseSizeBeforeCreate = etatDepenseRepository.findAll().size();
        // Create the EtatDepense
        EtatDepenseDTO etatDepenseDTO = etatDepenseMapper.toDto(etatDepense);
        restEtatDepenseMockMvc.perform(post("/api/etat-depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatDepenseDTO)))
            .andExpect(status().isCreated());

        // Validate the EtatDepense in the database
        List<EtatDepense> etatDepenseList = etatDepenseRepository.findAll();
        assertThat(etatDepenseList).hasSize(databaseSizeBeforeCreate + 1);
        EtatDepense testEtatDepense = etatDepenseList.get(etatDepenseList.size() - 1);
        assertThat(testEtatDepense.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testEtatDepense.getCodeRef()).isEqualTo(DEFAULT_CODE_REF);
    }

    @Test
    @Transactional
    public void createEtatDepenseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etatDepenseRepository.findAll().size();

        // Create the EtatDepense with an existing ID
        etatDepense.setId(1L);
        EtatDepenseDTO etatDepenseDTO = etatDepenseMapper.toDto(etatDepense);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtatDepenseMockMvc.perform(post("/api/etat-depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatDepenseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatDepense in the database
        List<EtatDepense> etatDepenseList = etatDepenseRepository.findAll();
        assertThat(etatDepenseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEtatDepenses() throws Exception {
        // Initialize the database
        etatDepenseRepository.saveAndFlush(etatDepense);

        // Get all the etatDepenseList
        restEtatDepenseMockMvc.perform(get("/api/etat-depenses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etatDepense.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].codeRef").value(hasItem(DEFAULT_CODE_REF)));
    }
    
    @Test
    @Transactional
    public void getEtatDepense() throws Exception {
        // Initialize the database
        etatDepenseRepository.saveAndFlush(etatDepense);

        // Get the etatDepense
        restEtatDepenseMockMvc.perform(get("/api/etat-depenses/{id}", etatDepense.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(etatDepense.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.codeRef").value(DEFAULT_CODE_REF));
    }
    @Test
    @Transactional
    public void getNonExistingEtatDepense() throws Exception {
        // Get the etatDepense
        restEtatDepenseMockMvc.perform(get("/api/etat-depenses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtatDepense() throws Exception {
        // Initialize the database
        etatDepenseRepository.saveAndFlush(etatDepense);

        int databaseSizeBeforeUpdate = etatDepenseRepository.findAll().size();

        // Update the etatDepense
        EtatDepense updatedEtatDepense = etatDepenseRepository.findById(etatDepense.getId()).get();
        // Disconnect from session so that the updates on updatedEtatDepense are not directly saved in db
        em.detach(updatedEtatDepense);
        updatedEtatDepense
            .libelle(UPDATED_LIBELLE)
            .codeRef(UPDATED_CODE_REF);
        EtatDepenseDTO etatDepenseDTO = etatDepenseMapper.toDto(updatedEtatDepense);

        restEtatDepenseMockMvc.perform(put("/api/etat-depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatDepenseDTO)))
            .andExpect(status().isOk());

        // Validate the EtatDepense in the database
        List<EtatDepense> etatDepenseList = etatDepenseRepository.findAll();
        assertThat(etatDepenseList).hasSize(databaseSizeBeforeUpdate);
        EtatDepense testEtatDepense = etatDepenseList.get(etatDepenseList.size() - 1);
        assertThat(testEtatDepense.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testEtatDepense.getCodeRef()).isEqualTo(UPDATED_CODE_REF);
    }

    @Test
    @Transactional
    public void updateNonExistingEtatDepense() throws Exception {
        int databaseSizeBeforeUpdate = etatDepenseRepository.findAll().size();

        // Create the EtatDepense
        EtatDepenseDTO etatDepenseDTO = etatDepenseMapper.toDto(etatDepense);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtatDepenseMockMvc.perform(put("/api/etat-depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatDepenseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatDepense in the database
        List<EtatDepense> etatDepenseList = etatDepenseRepository.findAll();
        assertThat(etatDepenseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtatDepense() throws Exception {
        // Initialize the database
        etatDepenseRepository.saveAndFlush(etatDepense);

        int databaseSizeBeforeDelete = etatDepenseRepository.findAll().size();

        // Delete the etatDepense
        restEtatDepenseMockMvc.perform(delete("/api/etat-depenses/{id}", etatDepense.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtatDepense> etatDepenseList = etatDepenseRepository.findAll();
        assertThat(etatDepenseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
