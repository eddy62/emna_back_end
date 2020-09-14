package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.NoteDeFrais;
import fr.insy2s.repository.NoteDeFraisRepository;
import fr.insy2s.service.NoteDeFraisService;
import fr.insy2s.service.dto.NoteDeFraisDTO;
import fr.insy2s.service.mapper.NoteDeFraisMapper;

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
 * Integration tests for the {@link NoteDeFraisResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NoteDeFraisResourceIT {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_MONTANT = 1D;
    private static final Double UPDATED_MONTANT = 2D;

    private static final String DEFAULT_JUSTIFICATIF = "AAAAAAAAAA";
    private static final String UPDATED_JUSTIFICATIF = "BBBBBBBBBB";

    private static final Integer DEFAULT_MOIS = 1;
    private static final Integer UPDATED_MOIS = 2;

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    @Autowired
    private NoteDeFraisRepository noteDeFraisRepository;

    @Autowired
    private NoteDeFraisMapper noteDeFraisMapper;

    @Autowired
    private NoteDeFraisService noteDeFraisService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNoteDeFraisMockMvc;

    private NoteDeFrais noteDeFrais;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoteDeFrais createEntity(EntityManager em) {
        NoteDeFrais noteDeFrais = new NoteDeFrais()
            .designation(DEFAULT_DESIGNATION)
            .date(DEFAULT_DATE)
            .montant(DEFAULT_MONTANT)
            .justificatif(DEFAULT_JUSTIFICATIF)
            .mois(DEFAULT_MOIS)
            .annee(DEFAULT_ANNEE);
        return noteDeFrais;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoteDeFrais createUpdatedEntity(EntityManager em) {
        NoteDeFrais noteDeFrais = new NoteDeFrais()
            .designation(UPDATED_DESIGNATION)
            .date(UPDATED_DATE)
            .montant(UPDATED_MONTANT)
            .justificatif(UPDATED_JUSTIFICATIF)
            .mois(UPDATED_MOIS)
            .annee(UPDATED_ANNEE);
        return noteDeFrais;
    }

    @BeforeEach
    public void initTest() {
        noteDeFrais = createEntity(em);
    }

    @Test
    @Transactional
    public void createNoteDeFrais() throws Exception {
        int databaseSizeBeforeCreate = noteDeFraisRepository.findAll().size();
        // Create the NoteDeFrais
        NoteDeFraisDTO noteDeFraisDTO = noteDeFraisMapper.toDto(noteDeFrais);
        restNoteDeFraisMockMvc.perform(post("/api/note-de-frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noteDeFraisDTO)))
            .andExpect(status().isCreated());

        // Validate the NoteDeFrais in the database
        List<NoteDeFrais> noteDeFraisList = noteDeFraisRepository.findAll();
        assertThat(noteDeFraisList).hasSize(databaseSizeBeforeCreate + 1);
        NoteDeFrais testNoteDeFrais = noteDeFraisList.get(noteDeFraisList.size() - 1);
        assertThat(testNoteDeFrais.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testNoteDeFrais.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testNoteDeFrais.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testNoteDeFrais.getJustificatif()).isEqualTo(DEFAULT_JUSTIFICATIF);
        assertThat(testNoteDeFrais.getMois()).isEqualTo(DEFAULT_MOIS);
        assertThat(testNoteDeFrais.getAnnee()).isEqualTo(DEFAULT_ANNEE);
    }

    @Test
    @Transactional
    public void createNoteDeFraisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = noteDeFraisRepository.findAll().size();

        // Create the NoteDeFrais with an existing ID
        noteDeFrais.setId(1L);
        NoteDeFraisDTO noteDeFraisDTO = noteDeFraisMapper.toDto(noteDeFrais);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoteDeFraisMockMvc.perform(post("/api/note-de-frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noteDeFraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NoteDeFrais in the database
        List<NoteDeFrais> noteDeFraisList = noteDeFraisRepository.findAll();
        assertThat(noteDeFraisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDesignationIsRequired() throws Exception {
        int databaseSizeBeforeTest = noteDeFraisRepository.findAll().size();
        // set the field null
        noteDeFrais.setDesignation(null);

        // Create the NoteDeFrais, which fails.
        NoteDeFraisDTO noteDeFraisDTO = noteDeFraisMapper.toDto(noteDeFrais);


        restNoteDeFraisMockMvc.perform(post("/api/note-de-frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noteDeFraisDTO)))
            .andExpect(status().isBadRequest());

        List<NoteDeFrais> noteDeFraisList = noteDeFraisRepository.findAll();
        assertThat(noteDeFraisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = noteDeFraisRepository.findAll().size();
        // set the field null
        noteDeFrais.setDate(null);

        // Create the NoteDeFrais, which fails.
        NoteDeFraisDTO noteDeFraisDTO = noteDeFraisMapper.toDto(noteDeFrais);


        restNoteDeFraisMockMvc.perform(post("/api/note-de-frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noteDeFraisDTO)))
            .andExpect(status().isBadRequest());

        List<NoteDeFrais> noteDeFraisList = noteDeFraisRepository.findAll();
        assertThat(noteDeFraisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantIsRequired() throws Exception {
        int databaseSizeBeforeTest = noteDeFraisRepository.findAll().size();
        // set the field null
        noteDeFrais.setMontant(null);

        // Create the NoteDeFrais, which fails.
        NoteDeFraisDTO noteDeFraisDTO = noteDeFraisMapper.toDto(noteDeFrais);


        restNoteDeFraisMockMvc.perform(post("/api/note-de-frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noteDeFraisDTO)))
            .andExpect(status().isBadRequest());

        List<NoteDeFrais> noteDeFraisList = noteDeFraisRepository.findAll();
        assertThat(noteDeFraisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMoisIsRequired() throws Exception {
        int databaseSizeBeforeTest = noteDeFraisRepository.findAll().size();
        // set the field null
        noteDeFrais.setMois(null);

        // Create the NoteDeFrais, which fails.
        NoteDeFraisDTO noteDeFraisDTO = noteDeFraisMapper.toDto(noteDeFrais);


        restNoteDeFraisMockMvc.perform(post("/api/note-de-frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noteDeFraisDTO)))
            .andExpect(status().isBadRequest());

        List<NoteDeFrais> noteDeFraisList = noteDeFraisRepository.findAll();
        assertThat(noteDeFraisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAnneeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noteDeFraisRepository.findAll().size();
        // set the field null
        noteDeFrais.setAnnee(null);

        // Create the NoteDeFrais, which fails.
        NoteDeFraisDTO noteDeFraisDTO = noteDeFraisMapper.toDto(noteDeFrais);


        restNoteDeFraisMockMvc.perform(post("/api/note-de-frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noteDeFraisDTO)))
            .andExpect(status().isBadRequest());

        List<NoteDeFrais> noteDeFraisList = noteDeFraisRepository.findAll();
        assertThat(noteDeFraisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNoteDeFrais() throws Exception {
        // Initialize the database
        noteDeFraisRepository.saveAndFlush(noteDeFrais);

        // Get all the noteDeFraisList
        restNoteDeFraisMockMvc.perform(get("/api/note-de-frais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noteDeFrais.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.doubleValue())))
            .andExpect(jsonPath("$.[*].justificatif").value(hasItem(DEFAULT_JUSTIFICATIF)))
            .andExpect(jsonPath("$.[*].mois").value(hasItem(DEFAULT_MOIS)))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)));
    }
    
    @Test
    @Transactional
    public void getNoteDeFrais() throws Exception {
        // Initialize the database
        noteDeFraisRepository.saveAndFlush(noteDeFrais);

        // Get the noteDeFrais
        restNoteDeFraisMockMvc.perform(get("/api/note-de-frais/{id}", noteDeFrais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(noteDeFrais.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.doubleValue()))
            .andExpect(jsonPath("$.justificatif").value(DEFAULT_JUSTIFICATIF))
            .andExpect(jsonPath("$.mois").value(DEFAULT_MOIS))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE));
    }
    @Test
    @Transactional
    public void getNonExistingNoteDeFrais() throws Exception {
        // Get the noteDeFrais
        restNoteDeFraisMockMvc.perform(get("/api/note-de-frais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNoteDeFrais() throws Exception {
        // Initialize the database
        noteDeFraisRepository.saveAndFlush(noteDeFrais);

        int databaseSizeBeforeUpdate = noteDeFraisRepository.findAll().size();

        // Update the noteDeFrais
        NoteDeFrais updatedNoteDeFrais = noteDeFraisRepository.findById(noteDeFrais.getId()).get();
        // Disconnect from session so that the updates on updatedNoteDeFrais are not directly saved in db
        em.detach(updatedNoteDeFrais);
        updatedNoteDeFrais
            .designation(UPDATED_DESIGNATION)
            .date(UPDATED_DATE)
            .montant(UPDATED_MONTANT)
            .justificatif(UPDATED_JUSTIFICATIF)
            .mois(UPDATED_MOIS)
            .annee(UPDATED_ANNEE);
        NoteDeFraisDTO noteDeFraisDTO = noteDeFraisMapper.toDto(updatedNoteDeFrais);

        restNoteDeFraisMockMvc.perform(put("/api/note-de-frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noteDeFraisDTO)))
            .andExpect(status().isOk());

        // Validate the NoteDeFrais in the database
        List<NoteDeFrais> noteDeFraisList = noteDeFraisRepository.findAll();
        assertThat(noteDeFraisList).hasSize(databaseSizeBeforeUpdate);
        NoteDeFrais testNoteDeFrais = noteDeFraisList.get(noteDeFraisList.size() - 1);
        assertThat(testNoteDeFrais.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testNoteDeFrais.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testNoteDeFrais.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testNoteDeFrais.getJustificatif()).isEqualTo(UPDATED_JUSTIFICATIF);
        assertThat(testNoteDeFrais.getMois()).isEqualTo(UPDATED_MOIS);
        assertThat(testNoteDeFrais.getAnnee()).isEqualTo(UPDATED_ANNEE);
    }

    @Test
    @Transactional
    public void updateNonExistingNoteDeFrais() throws Exception {
        int databaseSizeBeforeUpdate = noteDeFraisRepository.findAll().size();

        // Create the NoteDeFrais
        NoteDeFraisDTO noteDeFraisDTO = noteDeFraisMapper.toDto(noteDeFrais);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoteDeFraisMockMvc.perform(put("/api/note-de-frais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noteDeFraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NoteDeFrais in the database
        List<NoteDeFrais> noteDeFraisList = noteDeFraisRepository.findAll();
        assertThat(noteDeFraisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNoteDeFrais() throws Exception {
        // Initialize the database
        noteDeFraisRepository.saveAndFlush(noteDeFrais);

        int databaseSizeBeforeDelete = noteDeFraisRepository.findAll().size();

        // Delete the noteDeFrais
        restNoteDeFraisMockMvc.perform(delete("/api/note-de-frais/{id}", noteDeFrais.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NoteDeFrais> noteDeFraisList = noteDeFraisRepository.findAll();
        assertThat(noteDeFraisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
