package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.FichePaie;
import fr.insy2s.repository.FichePaieRepository;
import fr.insy2s.service.FichePaieService;
import fr.insy2s.service.dto.FichePaieDTO;
import fr.insy2s.service.mapper.FichePaieMapper;

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
 * Integration tests for the {@link FichePaieResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FichePaieResourceIT {

    private static final LocalDate DEFAULT_DEBUT_PERIODE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEBUT_PERIODE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FIN_PERIODE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FIN_PERIODE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LIEN_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_LIEN_DOCUMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_MOIS = 1;
    private static final Integer UPDATED_MOIS = 2;

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    @Autowired
    private FichePaieRepository fichePaieRepository;

    @Autowired
    private FichePaieMapper fichePaieMapper;

    @Autowired
    private FichePaieService fichePaieService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFichePaieMockMvc;

    private FichePaie fichePaie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FichePaie createEntity(EntityManager em) {
        FichePaie fichePaie = new FichePaie()
            .debutPeriode(DEFAULT_DEBUT_PERIODE)
            .finPeriode(DEFAULT_FIN_PERIODE)
            .mois(DEFAULT_MOIS)
            .annee(DEFAULT_ANNEE);
        return fichePaie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FichePaie createUpdatedEntity(EntityManager em) {
        FichePaie fichePaie = new FichePaie()
            .debutPeriode(UPDATED_DEBUT_PERIODE)
            .finPeriode(UPDATED_FIN_PERIODE)
            .mois(UPDATED_MOIS)
            .annee(UPDATED_ANNEE);
        return fichePaie;
    }

    @BeforeEach
    public void initTest() {
        fichePaie = createEntity(em);
    }

    @Test
    @Transactional
    public void createFichePaie() throws Exception {
        int databaseSizeBeforeCreate = fichePaieRepository.findAll().size();
        // Create the FichePaie
        FichePaieDTO fichePaieDTO = fichePaieMapper.toDto(fichePaie);
        restFichePaieMockMvc.perform(post("/api/fiche-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fichePaieDTO)))
            .andExpect(status().isCreated());

        // Validate the FichePaie in the database
        List<FichePaie> fichePaieList = fichePaieRepository.findAll();
        assertThat(fichePaieList).hasSize(databaseSizeBeforeCreate + 1);
        FichePaie testFichePaie = fichePaieList.get(fichePaieList.size() - 1);
        assertThat(testFichePaie.getDebutPeriode()).isEqualTo(DEFAULT_DEBUT_PERIODE);
        assertThat(testFichePaie.getFinPeriode()).isEqualTo(DEFAULT_FIN_PERIODE);
        assertThat(testFichePaie.getMois()).isEqualTo(DEFAULT_MOIS);
        assertThat(testFichePaie.getAnnee()).isEqualTo(DEFAULT_ANNEE);
    }

    @Test
    @Transactional
    public void createFichePaieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fichePaieRepository.findAll().size();

        // Create the FichePaie with an existing ID
        fichePaie.setId(1L);
        FichePaieDTO fichePaieDTO = fichePaieMapper.toDto(fichePaie);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFichePaieMockMvc.perform(post("/api/fiche-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fichePaieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FichePaie in the database
        List<FichePaie> fichePaieList = fichePaieRepository.findAll();
        assertThat(fichePaieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDebutPeriodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fichePaieRepository.findAll().size();
        // set the field null
        fichePaie.setDebutPeriode(null);

        // Create the FichePaie, which fails.
        FichePaieDTO fichePaieDTO = fichePaieMapper.toDto(fichePaie);


        restFichePaieMockMvc.perform(post("/api/fiche-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fichePaieDTO)))
            .andExpect(status().isBadRequest());

        List<FichePaie> fichePaieList = fichePaieRepository.findAll();
        assertThat(fichePaieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFinPeriodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fichePaieRepository.findAll().size();
        // set the field null
        fichePaie.setFinPeriode(null);

        // Create the FichePaie, which fails.
        FichePaieDTO fichePaieDTO = fichePaieMapper.toDto(fichePaie);


        restFichePaieMockMvc.perform(post("/api/fiche-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fichePaieDTO)))
            .andExpect(status().isBadRequest());

        List<FichePaie> fichePaieList = fichePaieRepository.findAll();
        assertThat(fichePaieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLienDocumentIsRequired() throws Exception {
        int databaseSizeBeforeTest = fichePaieRepository.findAll().size();
        // set the field null

        // Create the FichePaie, which fails.
        FichePaieDTO fichePaieDTO = fichePaieMapper.toDto(fichePaie);


        restFichePaieMockMvc.perform(post("/api/fiche-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fichePaieDTO)))
            .andExpect(status().isBadRequest());

        List<FichePaie> fichePaieList = fichePaieRepository.findAll();
        assertThat(fichePaieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMoisIsRequired() throws Exception {
        int databaseSizeBeforeTest = fichePaieRepository.findAll().size();
        // set the field null
        fichePaie.setMois(null);

        // Create the FichePaie, which fails.
        FichePaieDTO fichePaieDTO = fichePaieMapper.toDto(fichePaie);


        restFichePaieMockMvc.perform(post("/api/fiche-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fichePaieDTO)))
            .andExpect(status().isBadRequest());

        List<FichePaie> fichePaieList = fichePaieRepository.findAll();
        assertThat(fichePaieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAnneeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fichePaieRepository.findAll().size();
        // set the field null
        fichePaie.setAnnee(null);

        // Create the FichePaie, which fails.
        FichePaieDTO fichePaieDTO = fichePaieMapper.toDto(fichePaie);


        restFichePaieMockMvc.perform(post("/api/fiche-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fichePaieDTO)))
            .andExpect(status().isBadRequest());

        List<FichePaie> fichePaieList = fichePaieRepository.findAll();
        assertThat(fichePaieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFichePaies() throws Exception {
        // Initialize the database
        fichePaieRepository.saveAndFlush(fichePaie);

        // Get all the fichePaieList
        restFichePaieMockMvc.perform(get("/api/fiche-paies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fichePaie.getId().intValue())))
            .andExpect(jsonPath("$.[*].debutPeriode").value(hasItem(DEFAULT_DEBUT_PERIODE.toString())))
            .andExpect(jsonPath("$.[*].finPeriode").value(hasItem(DEFAULT_FIN_PERIODE.toString())))
            .andExpect(jsonPath("$.[*].lienDocument").value(hasItem(DEFAULT_LIEN_DOCUMENT)))
            .andExpect(jsonPath("$.[*].mois").value(hasItem(DEFAULT_MOIS)))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)));
    }

    @Test
    @Transactional
    public void getFichePaie() throws Exception {
        // Initialize the database
        fichePaieRepository.saveAndFlush(fichePaie);

        // Get the fichePaie
        restFichePaieMockMvc.perform(get("/api/fiche-paies/{id}", fichePaie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fichePaie.getId().intValue()))
            .andExpect(jsonPath("$.debutPeriode").value(DEFAULT_DEBUT_PERIODE.toString()))
            .andExpect(jsonPath("$.finPeriode").value(DEFAULT_FIN_PERIODE.toString()))
            .andExpect(jsonPath("$.lienDocument").value(DEFAULT_LIEN_DOCUMENT))
            .andExpect(jsonPath("$.mois").value(DEFAULT_MOIS))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE));
    }
    @Test
    @Transactional
    public void getNonExistingFichePaie() throws Exception {
        // Get the fichePaie
        restFichePaieMockMvc.perform(get("/api/fiche-paies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFichePaie() throws Exception {
        // Initialize the database
        fichePaieRepository.saveAndFlush(fichePaie);

        int databaseSizeBeforeUpdate = fichePaieRepository.findAll().size();

        // Update the fichePaie
        FichePaie updatedFichePaie = fichePaieRepository.findById(fichePaie.getId()).get();
        // Disconnect from session so that the updates on updatedFichePaie are not directly saved in db
        em.detach(updatedFichePaie);
        updatedFichePaie
            .debutPeriode(UPDATED_DEBUT_PERIODE)
            .finPeriode(UPDATED_FIN_PERIODE)
            .mois(UPDATED_MOIS)
            .annee(UPDATED_ANNEE);
        FichePaieDTO fichePaieDTO = fichePaieMapper.toDto(updatedFichePaie);

        restFichePaieMockMvc.perform(put("/api/fiche-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fichePaieDTO)))
            .andExpect(status().isOk());

        // Validate the FichePaie in the database
        List<FichePaie> fichePaieList = fichePaieRepository.findAll();
        assertThat(fichePaieList).hasSize(databaseSizeBeforeUpdate);
        FichePaie testFichePaie = fichePaieList.get(fichePaieList.size() - 1);
        assertThat(testFichePaie.getDebutPeriode()).isEqualTo(UPDATED_DEBUT_PERIODE);
        assertThat(testFichePaie.getFinPeriode()).isEqualTo(UPDATED_FIN_PERIODE);
        assertThat(testFichePaie.getMois()).isEqualTo(UPDATED_MOIS);
        assertThat(testFichePaie.getAnnee()).isEqualTo(UPDATED_ANNEE);
    }

    @Test
    @Transactional
    public void updateNonExistingFichePaie() throws Exception {
        int databaseSizeBeforeUpdate = fichePaieRepository.findAll().size();

        // Create the FichePaie
        FichePaieDTO fichePaieDTO = fichePaieMapper.toDto(fichePaie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFichePaieMockMvc.perform(put("/api/fiche-paies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fichePaieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FichePaie in the database
        List<FichePaie> fichePaieList = fichePaieRepository.findAll();
        assertThat(fichePaieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFichePaie() throws Exception {
        // Initialize the database
        fichePaieRepository.saveAndFlush(fichePaie);

        int databaseSizeBeforeDelete = fichePaieRepository.findAll().size();

        // Delete the fichePaie
        restFichePaieMockMvc.perform(delete("/api/fiche-paies/{id}", fichePaie.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FichePaie> fichePaieList = fichePaieRepository.findAll();
        assertThat(fichePaieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
