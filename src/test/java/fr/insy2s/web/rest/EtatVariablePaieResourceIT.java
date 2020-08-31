package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.EtatVariablePaie;
import fr.insy2s.repository.EtatVariablePaieRepository;
import fr.insy2s.service.EtatVariablePaieService;
import fr.insy2s.service.dto.EtatVariablePaieDTO;
import fr.insy2s.service.mapper.EtatVariablePaieMapper;

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
 * Integration tests for the {@link EtatVariablePaieResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EtatVariablePaieResourceIT {

    private static final String DEFAULT_CODE_REF = "AAAAAAAAAA";
    private static final String UPDATED_CODE_REF = "BBBBBBBBBB";

    private static final String DEFAULT_INTITULE = "AAAAAAAAAA";
    private static final String UPDATED_INTITULE = "BBBBBBBBBB";

    @Autowired
    private EtatVariablePaieRepository etatVariablePaieRepository;

    @Autowired
    private EtatVariablePaieMapper etatVariablePaieMapper;

    @Autowired
    private EtatVariablePaieService etatVariablePaieService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEtatVariablePaieMockMvc;

    private EtatVariablePaie etatVariablePaie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatVariablePaie createEntity(EntityManager em) {
        EtatVariablePaie etatVariablePaie = new EtatVariablePaie()
            .codeRef(DEFAULT_CODE_REF)
            .intitule(DEFAULT_INTITULE);
        return etatVariablePaie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatVariablePaie createUpdatedEntity(EntityManager em) {
        EtatVariablePaie etatVariablePaie = new EtatVariablePaie()
            .codeRef(UPDATED_CODE_REF)
            .intitule(UPDATED_INTITULE);
        return etatVariablePaie;
    }

    @BeforeEach
    public void initTest() {
        etatVariablePaie = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtatVariablePaie() throws Exception {
        int databaseSizeBeforeCreate = etatVariablePaieRepository.findAll().size();
        // Create the EtatVariablePaie
        EtatVariablePaieDTO etatVariablePaieDTO = etatVariablePaieMapper.toDto(etatVariablePaie);
        restEtatVariablePaieMockMvc.perform(post("/api/etat-variable-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatVariablePaieDTO)))
            .andExpect(status().isCreated());

        // Validate the EtatVariablePaie in the database
        List<EtatVariablePaie> etatVariablePaieList = etatVariablePaieRepository.findAll();
        assertThat(etatVariablePaieList).hasSize(databaseSizeBeforeCreate + 1);
        EtatVariablePaie testEtatVariablePaie = etatVariablePaieList.get(etatVariablePaieList.size() - 1);
        assertThat(testEtatVariablePaie.getCodeRef()).isEqualTo(DEFAULT_CODE_REF);
        assertThat(testEtatVariablePaie.getIntitule()).isEqualTo(DEFAULT_INTITULE);
    }

    @Test
    @Transactional
    public void createEtatVariablePaieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etatVariablePaieRepository.findAll().size();

        // Create the EtatVariablePaie with an existing ID
        etatVariablePaie.setId(1L);
        EtatVariablePaieDTO etatVariablePaieDTO = etatVariablePaieMapper.toDto(etatVariablePaie);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtatVariablePaieMockMvc.perform(post("/api/etat-variable-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatVariablePaieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatVariablePaie in the database
        List<EtatVariablePaie> etatVariablePaieList = etatVariablePaieRepository.findAll();
        assertThat(etatVariablePaieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = etatVariablePaieRepository.findAll().size();
        // set the field null
        etatVariablePaie.setCodeRef(null);

        // Create the EtatVariablePaie, which fails.
        EtatVariablePaieDTO etatVariablePaieDTO = etatVariablePaieMapper.toDto(etatVariablePaie);


        restEtatVariablePaieMockMvc.perform(post("/api/etat-variable-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatVariablePaieDTO)))
            .andExpect(status().isBadRequest());

        List<EtatVariablePaie> etatVariablePaieList = etatVariablePaieRepository.findAll();
        assertThat(etatVariablePaieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIntituleIsRequired() throws Exception {
        int databaseSizeBeforeTest = etatVariablePaieRepository.findAll().size();
        // set the field null
        etatVariablePaie.setIntitule(null);

        // Create the EtatVariablePaie, which fails.
        EtatVariablePaieDTO etatVariablePaieDTO = etatVariablePaieMapper.toDto(etatVariablePaie);


        restEtatVariablePaieMockMvc.perform(post("/api/etat-variable-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatVariablePaieDTO)))
            .andExpect(status().isBadRequest());

        List<EtatVariablePaie> etatVariablePaieList = etatVariablePaieRepository.findAll();
        assertThat(etatVariablePaieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEtatVariablePaies() throws Exception {
        // Initialize the database
        etatVariablePaieRepository.saveAndFlush(etatVariablePaie);

        // Get all the etatVariablePaieList
        restEtatVariablePaieMockMvc.perform(get("/api/etat-variable-paies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etatVariablePaie.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeRef").value(hasItem(DEFAULT_CODE_REF)))
            .andExpect(jsonPath("$.[*].intitule").value(hasItem(DEFAULT_INTITULE)));
    }
    
    @Test
    @Transactional
    public void getEtatVariablePaie() throws Exception {
        // Initialize the database
        etatVariablePaieRepository.saveAndFlush(etatVariablePaie);

        // Get the etatVariablePaie
        restEtatVariablePaieMockMvc.perform(get("/api/etat-variable-paies/{id}", etatVariablePaie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(etatVariablePaie.getId().intValue()))
            .andExpect(jsonPath("$.codeRef").value(DEFAULT_CODE_REF))
            .andExpect(jsonPath("$.intitule").value(DEFAULT_INTITULE));
    }
    @Test
    @Transactional
    public void getNonExistingEtatVariablePaie() throws Exception {
        // Get the etatVariablePaie
        restEtatVariablePaieMockMvc.perform(get("/api/etat-variable-paies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtatVariablePaie() throws Exception {
        // Initialize the database
        etatVariablePaieRepository.saveAndFlush(etatVariablePaie);

        int databaseSizeBeforeUpdate = etatVariablePaieRepository.findAll().size();

        // Update the etatVariablePaie
        EtatVariablePaie updatedEtatVariablePaie = etatVariablePaieRepository.findById(etatVariablePaie.getId()).get();
        // Disconnect from session so that the updates on updatedEtatVariablePaie are not directly saved in db
        em.detach(updatedEtatVariablePaie);
        updatedEtatVariablePaie
            .codeRef(UPDATED_CODE_REF)
            .intitule(UPDATED_INTITULE);
        EtatVariablePaieDTO etatVariablePaieDTO = etatVariablePaieMapper.toDto(updatedEtatVariablePaie);

        restEtatVariablePaieMockMvc.perform(put("/api/etat-variable-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatVariablePaieDTO)))
            .andExpect(status().isOk());

        // Validate the EtatVariablePaie in the database
        List<EtatVariablePaie> etatVariablePaieList = etatVariablePaieRepository.findAll();
        assertThat(etatVariablePaieList).hasSize(databaseSizeBeforeUpdate);
        EtatVariablePaie testEtatVariablePaie = etatVariablePaieList.get(etatVariablePaieList.size() - 1);
        assertThat(testEtatVariablePaie.getCodeRef()).isEqualTo(UPDATED_CODE_REF);
        assertThat(testEtatVariablePaie.getIntitule()).isEqualTo(UPDATED_INTITULE);
    }

    @Test
    @Transactional
    public void updateNonExistingEtatVariablePaie() throws Exception {
        int databaseSizeBeforeUpdate = etatVariablePaieRepository.findAll().size();

        // Create the EtatVariablePaie
        EtatVariablePaieDTO etatVariablePaieDTO = etatVariablePaieMapper.toDto(etatVariablePaie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtatVariablePaieMockMvc.perform(put("/api/etat-variable-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatVariablePaieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatVariablePaie in the database
        List<EtatVariablePaie> etatVariablePaieList = etatVariablePaieRepository.findAll();
        assertThat(etatVariablePaieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtatVariablePaie() throws Exception {
        // Initialize the database
        etatVariablePaieRepository.saveAndFlush(etatVariablePaie);

        int databaseSizeBeforeDelete = etatVariablePaieRepository.findAll().size();

        // Delete the etatVariablePaie
        restEtatVariablePaieMockMvc.perform(delete("/api/etat-variable-paies/{id}", etatVariablePaie.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtatVariablePaie> etatVariablePaieList = etatVariablePaieRepository.findAll();
        assertThat(etatVariablePaieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
