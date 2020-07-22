package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.EtatFacture;
import fr.insy2s.repository.EtatFactureRepository;
import fr.insy2s.service.EtatFactureService;
import fr.insy2s.service.dto.EtatFactureDTO;
import fr.insy2s.service.mapper.EtatFactureMapper;

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
 * Integration tests for the {@link EtatFactureResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EtatFactureResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_REF = "AAAAAAAAAA";
    private static final String UPDATED_CODE_REF = "BBBBBBBBBB";

    @Autowired
    private EtatFactureRepository etatFactureRepository;

    @Autowired
    private EtatFactureMapper etatFactureMapper;

    @Autowired
    private EtatFactureService etatFactureService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEtatFactureMockMvc;

    private EtatFacture etatFacture;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatFacture createEntity(EntityManager em) {
        EtatFacture etatFacture = new EtatFacture()
            .libelle(DEFAULT_LIBELLE)
            .codeRef(DEFAULT_CODE_REF);
        return etatFacture;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatFacture createUpdatedEntity(EntityManager em) {
        EtatFacture etatFacture = new EtatFacture()
            .libelle(UPDATED_LIBELLE)
            .codeRef(UPDATED_CODE_REF);
        return etatFacture;
    }

    @BeforeEach
    public void initTest() {
        etatFacture = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtatFacture() throws Exception {
        int databaseSizeBeforeCreate = etatFactureRepository.findAll().size();
        // Create the EtatFacture
        EtatFactureDTO etatFactureDTO = etatFactureMapper.toDto(etatFacture);
        restEtatFactureMockMvc.perform(post("/api/etat-factures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatFactureDTO)))
            .andExpect(status().isCreated());

        // Validate the EtatFacture in the database
        List<EtatFacture> etatFactureList = etatFactureRepository.findAll();
        assertThat(etatFactureList).hasSize(databaseSizeBeforeCreate + 1);
        EtatFacture testEtatFacture = etatFactureList.get(etatFactureList.size() - 1);
        assertThat(testEtatFacture.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testEtatFacture.getCodeRef()).isEqualTo(DEFAULT_CODE_REF);
    }

    @Test
    @Transactional
    public void createEtatFactureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etatFactureRepository.findAll().size();

        // Create the EtatFacture with an existing ID
        etatFacture.setId(1L);
        EtatFactureDTO etatFactureDTO = etatFactureMapper.toDto(etatFacture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtatFactureMockMvc.perform(post("/api/etat-factures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatFactureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatFacture in the database
        List<EtatFacture> etatFactureList = etatFactureRepository.findAll();
        assertThat(etatFactureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = etatFactureRepository.findAll().size();
        // set the field null
        etatFacture.setLibelle(null);

        // Create the EtatFacture, which fails.
        EtatFactureDTO etatFactureDTO = etatFactureMapper.toDto(etatFacture);


        restEtatFactureMockMvc.perform(post("/api/etat-factures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatFactureDTO)))
            .andExpect(status().isBadRequest());

        List<EtatFacture> etatFactureList = etatFactureRepository.findAll();
        assertThat(etatFactureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = etatFactureRepository.findAll().size();
        // set the field null
        etatFacture.setCodeRef(null);

        // Create the EtatFacture, which fails.
        EtatFactureDTO etatFactureDTO = etatFactureMapper.toDto(etatFacture);


        restEtatFactureMockMvc.perform(post("/api/etat-factures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatFactureDTO)))
            .andExpect(status().isBadRequest());

        List<EtatFacture> etatFactureList = etatFactureRepository.findAll();
        assertThat(etatFactureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEtatFactures() throws Exception {
        // Initialize the database
        etatFactureRepository.saveAndFlush(etatFacture);

        // Get all the etatFactureList
        restEtatFactureMockMvc.perform(get("/api/etat-factures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etatFacture.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].codeRef").value(hasItem(DEFAULT_CODE_REF)));
    }
    
    @Test
    @Transactional
    public void getEtatFacture() throws Exception {
        // Initialize the database
        etatFactureRepository.saveAndFlush(etatFacture);

        // Get the etatFacture
        restEtatFactureMockMvc.perform(get("/api/etat-factures/{id}", etatFacture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(etatFacture.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.codeRef").value(DEFAULT_CODE_REF));
    }
    @Test
    @Transactional
    public void getNonExistingEtatFacture() throws Exception {
        // Get the etatFacture
        restEtatFactureMockMvc.perform(get("/api/etat-factures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtatFacture() throws Exception {
        // Initialize the database
        etatFactureRepository.saveAndFlush(etatFacture);

        int databaseSizeBeforeUpdate = etatFactureRepository.findAll().size();

        // Update the etatFacture
        EtatFacture updatedEtatFacture = etatFactureRepository.findById(etatFacture.getId()).get();
        // Disconnect from session so that the updates on updatedEtatFacture are not directly saved in db
        em.detach(updatedEtatFacture);
        updatedEtatFacture
            .libelle(UPDATED_LIBELLE)
            .codeRef(UPDATED_CODE_REF);
        EtatFactureDTO etatFactureDTO = etatFactureMapper.toDto(updatedEtatFacture);

        restEtatFactureMockMvc.perform(put("/api/etat-factures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatFactureDTO)))
            .andExpect(status().isOk());

        // Validate the EtatFacture in the database
        List<EtatFacture> etatFactureList = etatFactureRepository.findAll();
        assertThat(etatFactureList).hasSize(databaseSizeBeforeUpdate);
        EtatFacture testEtatFacture = etatFactureList.get(etatFactureList.size() - 1);
        assertThat(testEtatFacture.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testEtatFacture.getCodeRef()).isEqualTo(UPDATED_CODE_REF);
    }

    @Test
    @Transactional
    public void updateNonExistingEtatFacture() throws Exception {
        int databaseSizeBeforeUpdate = etatFactureRepository.findAll().size();

        // Create the EtatFacture
        EtatFactureDTO etatFactureDTO = etatFactureMapper.toDto(etatFacture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtatFactureMockMvc.perform(put("/api/etat-factures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatFactureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatFacture in the database
        List<EtatFacture> etatFactureList = etatFactureRepository.findAll();
        assertThat(etatFactureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtatFacture() throws Exception {
        // Initialize the database
        etatFactureRepository.saveAndFlush(etatFacture);

        int databaseSizeBeforeDelete = etatFactureRepository.findAll().size();

        // Delete the etatFacture
        restEtatFactureMockMvc.perform(delete("/api/etat-factures/{id}", etatFacture.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtatFacture> etatFactureList = etatFactureRepository.findAll();
        assertThat(etatFactureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
