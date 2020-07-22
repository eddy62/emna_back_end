package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.EtatReleve;
import fr.insy2s.repository.EtatReleveRepository;
import fr.insy2s.service.EtatReleveService;
import fr.insy2s.service.dto.EtatReleveDTO;
import fr.insy2s.service.mapper.EtatReleveMapper;

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
 * Integration tests for the {@link EtatReleveResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EtatReleveResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_REF = "AAAAAAAAAA";
    private static final String UPDATED_CODE_REF = "BBBBBBBBBB";

    @Autowired
    private EtatReleveRepository etatReleveRepository;

    @Autowired
    private EtatReleveMapper etatReleveMapper;

    @Autowired
    private EtatReleveService etatReleveService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEtatReleveMockMvc;

    private EtatReleve etatReleve;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatReleve createEntity(EntityManager em) {
        EtatReleve etatReleve = new EtatReleve()
            .libelle(DEFAULT_LIBELLE)
            .codeRef(DEFAULT_CODE_REF);
        return etatReleve;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatReleve createUpdatedEntity(EntityManager em) {
        EtatReleve etatReleve = new EtatReleve()
            .libelle(UPDATED_LIBELLE)
            .codeRef(UPDATED_CODE_REF);
        return etatReleve;
    }

    @BeforeEach
    public void initTest() {
        etatReleve = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtatReleve() throws Exception {
        int databaseSizeBeforeCreate = etatReleveRepository.findAll().size();
        // Create the EtatReleve
        EtatReleveDTO etatReleveDTO = etatReleveMapper.toDto(etatReleve);
        restEtatReleveMockMvc.perform(post("/api/etat-releves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatReleveDTO)))
            .andExpect(status().isCreated());

        // Validate the EtatReleve in the database
        List<EtatReleve> etatReleveList = etatReleveRepository.findAll();
        assertThat(etatReleveList).hasSize(databaseSizeBeforeCreate + 1);
        EtatReleve testEtatReleve = etatReleveList.get(etatReleveList.size() - 1);
        assertThat(testEtatReleve.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testEtatReleve.getCodeRef()).isEqualTo(DEFAULT_CODE_REF);
    }

    @Test
    @Transactional
    public void createEtatReleveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etatReleveRepository.findAll().size();

        // Create the EtatReleve with an existing ID
        etatReleve.setId(1L);
        EtatReleveDTO etatReleveDTO = etatReleveMapper.toDto(etatReleve);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtatReleveMockMvc.perform(post("/api/etat-releves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatReleveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatReleve in the database
        List<EtatReleve> etatReleveList = etatReleveRepository.findAll();
        assertThat(etatReleveList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEtatReleves() throws Exception {
        // Initialize the database
        etatReleveRepository.saveAndFlush(etatReleve);

        // Get all the etatReleveList
        restEtatReleveMockMvc.perform(get("/api/etat-releves?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etatReleve.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].codeRef").value(hasItem(DEFAULT_CODE_REF)));
    }
    
    @Test
    @Transactional
    public void getEtatReleve() throws Exception {
        // Initialize the database
        etatReleveRepository.saveAndFlush(etatReleve);

        // Get the etatReleve
        restEtatReleveMockMvc.perform(get("/api/etat-releves/{id}", etatReleve.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(etatReleve.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.codeRef").value(DEFAULT_CODE_REF));
    }
    @Test
    @Transactional
    public void getNonExistingEtatReleve() throws Exception {
        // Get the etatReleve
        restEtatReleveMockMvc.perform(get("/api/etat-releves/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtatReleve() throws Exception {
        // Initialize the database
        etatReleveRepository.saveAndFlush(etatReleve);

        int databaseSizeBeforeUpdate = etatReleveRepository.findAll().size();

        // Update the etatReleve
        EtatReleve updatedEtatReleve = etatReleveRepository.findById(etatReleve.getId()).get();
        // Disconnect from session so that the updates on updatedEtatReleve are not directly saved in db
        em.detach(updatedEtatReleve);
        updatedEtatReleve
            .libelle(UPDATED_LIBELLE)
            .codeRef(UPDATED_CODE_REF);
        EtatReleveDTO etatReleveDTO = etatReleveMapper.toDto(updatedEtatReleve);

        restEtatReleveMockMvc.perform(put("/api/etat-releves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatReleveDTO)))
            .andExpect(status().isOk());

        // Validate the EtatReleve in the database
        List<EtatReleve> etatReleveList = etatReleveRepository.findAll();
        assertThat(etatReleveList).hasSize(databaseSizeBeforeUpdate);
        EtatReleve testEtatReleve = etatReleveList.get(etatReleveList.size() - 1);
        assertThat(testEtatReleve.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testEtatReleve.getCodeRef()).isEqualTo(UPDATED_CODE_REF);
    }

    @Test
    @Transactional
    public void updateNonExistingEtatReleve() throws Exception {
        int databaseSizeBeforeUpdate = etatReleveRepository.findAll().size();

        // Create the EtatReleve
        EtatReleveDTO etatReleveDTO = etatReleveMapper.toDto(etatReleve);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtatReleveMockMvc.perform(put("/api/etat-releves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatReleveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatReleve in the database
        List<EtatReleve> etatReleveList = etatReleveRepository.findAll();
        assertThat(etatReleveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtatReleve() throws Exception {
        // Initialize the database
        etatReleveRepository.saveAndFlush(etatReleve);

        int databaseSizeBeforeDelete = etatReleveRepository.findAll().size();

        // Delete the etatReleve
        restEtatReleveMockMvc.perform(delete("/api/etat-releves/{id}", etatReleve.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtatReleve> etatReleveList = etatReleveRepository.findAll();
        assertThat(etatReleveList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
