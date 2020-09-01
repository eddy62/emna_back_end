package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.HeuresSupplementaires;
import fr.insy2s.repository.HeuresSupplementairesRepository;
import fr.insy2s.service.HeuresSupplementairesService;
import fr.insy2s.service.dto.HeuresSupplementairesDTO;
import fr.insy2s.service.mapper.HeuresSupplementairesMapper;

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
 * Integration tests for the {@link HeuresSupplementairesResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class HeuresSupplementairesResourceIT {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NOMBRE_HEURE = 1;
    private static final Integer UPDATED_NOMBRE_HEURE = 2;

    private static final String DEFAULT_JUSTIFICATIF = "AAAAAAAAAA";
    private static final String UPDATED_JUSTIFICATIF = "BBBBBBBBBB";

    private static final Integer DEFAULT_MOIS = 1;
    private static final Integer UPDATED_MOIS = 2;

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    @Autowired
    private HeuresSupplementairesRepository heuresSupplementairesRepository;

    @Autowired
    private HeuresSupplementairesMapper heuresSupplementairesMapper;

    @Autowired
    private HeuresSupplementairesService heuresSupplementairesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHeuresSupplementairesMockMvc;

    private HeuresSupplementaires heuresSupplementaires;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HeuresSupplementaires createEntity(EntityManager em) {
        HeuresSupplementaires heuresSupplementaires = new HeuresSupplementaires()
            .date(DEFAULT_DATE)
            .nombreHeure(DEFAULT_NOMBRE_HEURE)
            .justificatif(DEFAULT_JUSTIFICATIF)
            .mois(DEFAULT_MOIS)
            .annee(DEFAULT_ANNEE);
        return heuresSupplementaires;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HeuresSupplementaires createUpdatedEntity(EntityManager em) {
        HeuresSupplementaires heuresSupplementaires = new HeuresSupplementaires()
            .date(UPDATED_DATE)
            .nombreHeure(UPDATED_NOMBRE_HEURE)
            .justificatif(UPDATED_JUSTIFICATIF)
            .mois(UPDATED_MOIS)
            .annee(UPDATED_ANNEE);
        return heuresSupplementaires;
    }

    @BeforeEach
    public void initTest() {
        heuresSupplementaires = createEntity(em);
    }

    @Test
    @Transactional
    public void createHeuresSupplementaires() throws Exception {
        int databaseSizeBeforeCreate = heuresSupplementairesRepository.findAll().size();
        // Create the HeuresSupplementaires
        HeuresSupplementairesDTO heuresSupplementairesDTO = heuresSupplementairesMapper.toDto(heuresSupplementaires);
        restHeuresSupplementairesMockMvc.perform(post("/api/heures-supplementaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(heuresSupplementairesDTO)))
            .andExpect(status().isCreated());

        // Validate the HeuresSupplementaires in the database
        List<HeuresSupplementaires> heuresSupplementairesList = heuresSupplementairesRepository.findAll();
        assertThat(heuresSupplementairesList).hasSize(databaseSizeBeforeCreate + 1);
        HeuresSupplementaires testHeuresSupplementaires = heuresSupplementairesList.get(heuresSupplementairesList.size() - 1);
        assertThat(testHeuresSupplementaires.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testHeuresSupplementaires.getNombreHeure()).isEqualTo(DEFAULT_NOMBRE_HEURE);
        assertThat(testHeuresSupplementaires.getJustificatif()).isEqualTo(DEFAULT_JUSTIFICATIF);
        assertThat(testHeuresSupplementaires.getMois()).isEqualTo(DEFAULT_MOIS);
        assertThat(testHeuresSupplementaires.getAnnee()).isEqualTo(DEFAULT_ANNEE);
    }

    @Test
    @Transactional
    public void createHeuresSupplementairesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = heuresSupplementairesRepository.findAll().size();

        // Create the HeuresSupplementaires with an existing ID
        heuresSupplementaires.setId(1L);
        HeuresSupplementairesDTO heuresSupplementairesDTO = heuresSupplementairesMapper.toDto(heuresSupplementaires);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHeuresSupplementairesMockMvc.perform(post("/api/heures-supplementaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(heuresSupplementairesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HeuresSupplementaires in the database
        List<HeuresSupplementaires> heuresSupplementairesList = heuresSupplementairesRepository.findAll();
        assertThat(heuresSupplementairesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = heuresSupplementairesRepository.findAll().size();
        // set the field null
        heuresSupplementaires.setDate(null);

        // Create the HeuresSupplementaires, which fails.
        HeuresSupplementairesDTO heuresSupplementairesDTO = heuresSupplementairesMapper.toDto(heuresSupplementaires);


        restHeuresSupplementairesMockMvc.perform(post("/api/heures-supplementaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(heuresSupplementairesDTO)))
            .andExpect(status().isBadRequest());

        List<HeuresSupplementaires> heuresSupplementairesList = heuresSupplementairesRepository.findAll();
        assertThat(heuresSupplementairesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNombreHeureIsRequired() throws Exception {
        int databaseSizeBeforeTest = heuresSupplementairesRepository.findAll().size();
        // set the field null
        heuresSupplementaires.setNombreHeure(null);

        // Create the HeuresSupplementaires, which fails.
        HeuresSupplementairesDTO heuresSupplementairesDTO = heuresSupplementairesMapper.toDto(heuresSupplementaires);


        restHeuresSupplementairesMockMvc.perform(post("/api/heures-supplementaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(heuresSupplementairesDTO)))
            .andExpect(status().isBadRequest());

        List<HeuresSupplementaires> heuresSupplementairesList = heuresSupplementairesRepository.findAll();
        assertThat(heuresSupplementairesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMoisIsRequired() throws Exception {
        int databaseSizeBeforeTest = heuresSupplementairesRepository.findAll().size();
        // set the field null
        heuresSupplementaires.setMois(null);

        // Create the HeuresSupplementaires, which fails.
        HeuresSupplementairesDTO heuresSupplementairesDTO = heuresSupplementairesMapper.toDto(heuresSupplementaires);


        restHeuresSupplementairesMockMvc.perform(post("/api/heures-supplementaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(heuresSupplementairesDTO)))
            .andExpect(status().isBadRequest());

        List<HeuresSupplementaires> heuresSupplementairesList = heuresSupplementairesRepository.findAll();
        assertThat(heuresSupplementairesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAnneeIsRequired() throws Exception {
        int databaseSizeBeforeTest = heuresSupplementairesRepository.findAll().size();
        // set the field null
        heuresSupplementaires.setAnnee(null);

        // Create the HeuresSupplementaires, which fails.
        HeuresSupplementairesDTO heuresSupplementairesDTO = heuresSupplementairesMapper.toDto(heuresSupplementaires);


        restHeuresSupplementairesMockMvc.perform(post("/api/heures-supplementaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(heuresSupplementairesDTO)))
            .andExpect(status().isBadRequest());

        List<HeuresSupplementaires> heuresSupplementairesList = heuresSupplementairesRepository.findAll();
        assertThat(heuresSupplementairesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHeuresSupplementaires() throws Exception {
        // Initialize the database
        heuresSupplementairesRepository.saveAndFlush(heuresSupplementaires);

        // Get all the heuresSupplementairesList
        restHeuresSupplementairesMockMvc.perform(get("/api/heures-supplementaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(heuresSupplementaires.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].nombreHeure").value(hasItem(DEFAULT_NOMBRE_HEURE)))
            .andExpect(jsonPath("$.[*].justificatif").value(hasItem(DEFAULT_JUSTIFICATIF)))
            .andExpect(jsonPath("$.[*].mois").value(hasItem(DEFAULT_MOIS)))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)));
    }
    
    @Test
    @Transactional
    public void getHeuresSupplementaires() throws Exception {
        // Initialize the database
        heuresSupplementairesRepository.saveAndFlush(heuresSupplementaires);

        // Get the heuresSupplementaires
        restHeuresSupplementairesMockMvc.perform(get("/api/heures-supplementaires/{id}", heuresSupplementaires.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(heuresSupplementaires.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.nombreHeure").value(DEFAULT_NOMBRE_HEURE))
            .andExpect(jsonPath("$.justificatif").value(DEFAULT_JUSTIFICATIF))
            .andExpect(jsonPath("$.mois").value(DEFAULT_MOIS))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE));
    }
    @Test
    @Transactional
    public void getNonExistingHeuresSupplementaires() throws Exception {
        // Get the heuresSupplementaires
        restHeuresSupplementairesMockMvc.perform(get("/api/heures-supplementaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHeuresSupplementaires() throws Exception {
        // Initialize the database
        heuresSupplementairesRepository.saveAndFlush(heuresSupplementaires);

        int databaseSizeBeforeUpdate = heuresSupplementairesRepository.findAll().size();

        // Update the heuresSupplementaires
        HeuresSupplementaires updatedHeuresSupplementaires = heuresSupplementairesRepository.findById(heuresSupplementaires.getId()).get();
        // Disconnect from session so that the updates on updatedHeuresSupplementaires are not directly saved in db
        em.detach(updatedHeuresSupplementaires);
        updatedHeuresSupplementaires
            .date(UPDATED_DATE)
            .nombreHeure(UPDATED_NOMBRE_HEURE)
            .justificatif(UPDATED_JUSTIFICATIF)
            .mois(UPDATED_MOIS)
            .annee(UPDATED_ANNEE);
        HeuresSupplementairesDTO heuresSupplementairesDTO = heuresSupplementairesMapper.toDto(updatedHeuresSupplementaires);

        restHeuresSupplementairesMockMvc.perform(put("/api/heures-supplementaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(heuresSupplementairesDTO)))
            .andExpect(status().isOk());

        // Validate the HeuresSupplementaires in the database
        List<HeuresSupplementaires> heuresSupplementairesList = heuresSupplementairesRepository.findAll();
        assertThat(heuresSupplementairesList).hasSize(databaseSizeBeforeUpdate);
        HeuresSupplementaires testHeuresSupplementaires = heuresSupplementairesList.get(heuresSupplementairesList.size() - 1);
        assertThat(testHeuresSupplementaires.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testHeuresSupplementaires.getNombreHeure()).isEqualTo(UPDATED_NOMBRE_HEURE);
        assertThat(testHeuresSupplementaires.getJustificatif()).isEqualTo(UPDATED_JUSTIFICATIF);
        assertThat(testHeuresSupplementaires.getMois()).isEqualTo(UPDATED_MOIS);
        assertThat(testHeuresSupplementaires.getAnnee()).isEqualTo(UPDATED_ANNEE);
    }

    @Test
    @Transactional
    public void updateNonExistingHeuresSupplementaires() throws Exception {
        int databaseSizeBeforeUpdate = heuresSupplementairesRepository.findAll().size();

        // Create the HeuresSupplementaires
        HeuresSupplementairesDTO heuresSupplementairesDTO = heuresSupplementairesMapper.toDto(heuresSupplementaires);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHeuresSupplementairesMockMvc.perform(put("/api/heures-supplementaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(heuresSupplementairesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HeuresSupplementaires in the database
        List<HeuresSupplementaires> heuresSupplementairesList = heuresSupplementairesRepository.findAll();
        assertThat(heuresSupplementairesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHeuresSupplementaires() throws Exception {
        // Initialize the database
        heuresSupplementairesRepository.saveAndFlush(heuresSupplementaires);

        int databaseSizeBeforeDelete = heuresSupplementairesRepository.findAll().size();

        // Delete the heuresSupplementaires
        restHeuresSupplementairesMockMvc.perform(delete("/api/heures-supplementaires/{id}", heuresSupplementaires.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HeuresSupplementaires> heuresSupplementairesList = heuresSupplementairesRepository.findAll();
        assertThat(heuresSupplementairesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
