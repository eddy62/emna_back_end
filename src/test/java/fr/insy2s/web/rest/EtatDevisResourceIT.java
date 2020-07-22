package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.EtatDevis;
import fr.insy2s.repository.EtatDevisRepository;
import fr.insy2s.service.EtatDevisService;
import fr.insy2s.service.dto.EtatDevisDTO;
import fr.insy2s.service.mapper.EtatDevisMapper;

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
 * Integration tests for the {@link EtatDevisResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EtatDevisResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_REF = "AAAAAAAAAA";
    private static final String UPDATED_CODE_REF = "BBBBBBBBBB";

    @Autowired
    private EtatDevisRepository etatDevisRepository;

    @Autowired
    private EtatDevisMapper etatDevisMapper;

    @Autowired
    private EtatDevisService etatDevisService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEtatDevisMockMvc;

    private EtatDevis etatDevis;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatDevis createEntity(EntityManager em) {
        EtatDevis etatDevis = new EtatDevis()
            .libelle(DEFAULT_LIBELLE)
            .codeRef(DEFAULT_CODE_REF);
        return etatDevis;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatDevis createUpdatedEntity(EntityManager em) {
        EtatDevis etatDevis = new EtatDevis()
            .libelle(UPDATED_LIBELLE)
            .codeRef(UPDATED_CODE_REF);
        return etatDevis;
    }

    @BeforeEach
    public void initTest() {
        etatDevis = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtatDevis() throws Exception {
        int databaseSizeBeforeCreate = etatDevisRepository.findAll().size();
        // Create the EtatDevis
        EtatDevisDTO etatDevisDTO = etatDevisMapper.toDto(etatDevis);
        restEtatDevisMockMvc.perform(post("/api/etat-devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatDevisDTO)))
            .andExpect(status().isCreated());

        // Validate the EtatDevis in the database
        List<EtatDevis> etatDevisList = etatDevisRepository.findAll();
        assertThat(etatDevisList).hasSize(databaseSizeBeforeCreate + 1);
        EtatDevis testEtatDevis = etatDevisList.get(etatDevisList.size() - 1);
        assertThat(testEtatDevis.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testEtatDevis.getCodeRef()).isEqualTo(DEFAULT_CODE_REF);
    }

    @Test
    @Transactional
    public void createEtatDevisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etatDevisRepository.findAll().size();

        // Create the EtatDevis with an existing ID
        etatDevis.setId(1L);
        EtatDevisDTO etatDevisDTO = etatDevisMapper.toDto(etatDevis);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtatDevisMockMvc.perform(post("/api/etat-devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatDevisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatDevis in the database
        List<EtatDevis> etatDevisList = etatDevisRepository.findAll();
        assertThat(etatDevisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = etatDevisRepository.findAll().size();
        // set the field null
        etatDevis.setLibelle(null);

        // Create the EtatDevis, which fails.
        EtatDevisDTO etatDevisDTO = etatDevisMapper.toDto(etatDevis);


        restEtatDevisMockMvc.perform(post("/api/etat-devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatDevisDTO)))
            .andExpect(status().isBadRequest());

        List<EtatDevis> etatDevisList = etatDevisRepository.findAll();
        assertThat(etatDevisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = etatDevisRepository.findAll().size();
        // set the field null
        etatDevis.setCodeRef(null);

        // Create the EtatDevis, which fails.
        EtatDevisDTO etatDevisDTO = etatDevisMapper.toDto(etatDevis);


        restEtatDevisMockMvc.perform(post("/api/etat-devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatDevisDTO)))
            .andExpect(status().isBadRequest());

        List<EtatDevis> etatDevisList = etatDevisRepository.findAll();
        assertThat(etatDevisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEtatDevis() throws Exception {
        // Initialize the database
        etatDevisRepository.saveAndFlush(etatDevis);

        // Get all the etatDevisList
        restEtatDevisMockMvc.perform(get("/api/etat-devis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etatDevis.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].codeRef").value(hasItem(DEFAULT_CODE_REF)));
    }
    
    @Test
    @Transactional
    public void getEtatDevis() throws Exception {
        // Initialize the database
        etatDevisRepository.saveAndFlush(etatDevis);

        // Get the etatDevis
        restEtatDevisMockMvc.perform(get("/api/etat-devis/{id}", etatDevis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(etatDevis.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.codeRef").value(DEFAULT_CODE_REF));
    }
    @Test
    @Transactional
    public void getNonExistingEtatDevis() throws Exception {
        // Get the etatDevis
        restEtatDevisMockMvc.perform(get("/api/etat-devis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtatDevis() throws Exception {
        // Initialize the database
        etatDevisRepository.saveAndFlush(etatDevis);

        int databaseSizeBeforeUpdate = etatDevisRepository.findAll().size();

        // Update the etatDevis
        EtatDevis updatedEtatDevis = etatDevisRepository.findById(etatDevis.getId()).get();
        // Disconnect from session so that the updates on updatedEtatDevis are not directly saved in db
        em.detach(updatedEtatDevis);
        updatedEtatDevis
            .libelle(UPDATED_LIBELLE)
            .codeRef(UPDATED_CODE_REF);
        EtatDevisDTO etatDevisDTO = etatDevisMapper.toDto(updatedEtatDevis);

        restEtatDevisMockMvc.perform(put("/api/etat-devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatDevisDTO)))
            .andExpect(status().isOk());

        // Validate the EtatDevis in the database
        List<EtatDevis> etatDevisList = etatDevisRepository.findAll();
        assertThat(etatDevisList).hasSize(databaseSizeBeforeUpdate);
        EtatDevis testEtatDevis = etatDevisList.get(etatDevisList.size() - 1);
        assertThat(testEtatDevis.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testEtatDevis.getCodeRef()).isEqualTo(UPDATED_CODE_REF);
    }

    @Test
    @Transactional
    public void updateNonExistingEtatDevis() throws Exception {
        int databaseSizeBeforeUpdate = etatDevisRepository.findAll().size();

        // Create the EtatDevis
        EtatDevisDTO etatDevisDTO = etatDevisMapper.toDto(etatDevis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtatDevisMockMvc.perform(put("/api/etat-devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatDevisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatDevis in the database
        List<EtatDevis> etatDevisList = etatDevisRepository.findAll();
        assertThat(etatDevisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtatDevis() throws Exception {
        // Initialize the database
        etatDevisRepository.saveAndFlush(etatDevis);

        int databaseSizeBeforeDelete = etatDevisRepository.findAll().size();

        // Delete the etatDevis
        restEtatDevisMockMvc.perform(delete("/api/etat-devis/{id}", etatDevis.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtatDevis> etatDevisList = etatDevisRepository.findAll();
        assertThat(etatDevisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
