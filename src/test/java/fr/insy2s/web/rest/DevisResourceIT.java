package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.Devis;
import fr.insy2s.repository.DevisRepository;
import fr.insy2s.service.DevisService;
import fr.insy2s.service.dto.DevisDTO;
import fr.insy2s.service.mapper.DevisMapper;

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
 * Integration tests for the {@link DevisResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DevisResourceIT {

    private static final Long DEFAULT_NUM_DEVIS = 1L;
    private static final Long UPDATED_NUM_DEVIS = 2L;

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_CREATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREATION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_LIMITE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_LIMITE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_PRIX_HT = 1D;
    private static final Double UPDATED_PRIX_HT = 2D;

    private static final Double DEFAULT_PRIX_TTC = 1D;
    private static final Double UPDATED_PRIX_TTC = 2D;

    private static final Float DEFAULT_TVA = 1F;
    private static final Float UPDATED_TVA = 2F;

    private static final String DEFAULT_CHEMIN_FICHIER = "AAAAAAAAAA";
    private static final String UPDATED_CHEMIN_FICHIER = "BBBBBBBBBB";

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private DevisMapper devisMapper;

    @Autowired
    private DevisService devisService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDevisMockMvc;

    private Devis devis;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Devis createEntity(EntityManager em) {
        Devis devis = new Devis()
            .numDevis(DEFAULT_NUM_DEVIS)
            .nom(DEFAULT_NOM)
            .message(DEFAULT_MESSAGE)
            .dateCreation(DEFAULT_DATE_CREATION)
            .dateLimite(DEFAULT_DATE_LIMITE);
            //.prixHT(DEFAULT_PRIX_HT)
            //.prixTTC(DEFAULT_PRIX_TTC)
            //.tva(DEFAULT_TVA)
            //.cheminFichier(DEFAULT_CHEMIN_FICHIER);
        return devis;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Devis createUpdatedEntity(EntityManager em) {
        Devis devis = new Devis()
            .numDevis(UPDATED_NUM_DEVIS)
            .nom(UPDATED_NOM)
            .message(UPDATED_MESSAGE)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateLimite(UPDATED_DATE_LIMITE);
            //.prixHT(UPDATED_PRIX_HT)
            //.prixTTC(UPDATED_PRIX_TTC)
            //.tva(UPDATED_TVA)
            //.cheminFichier(UPDATED_CHEMIN_FICHIER);
        return devis;
    }

    @BeforeEach
    public void initTest() {
        devis = createEntity(em);
    }

    @Test
    @Transactional
    public void createDevis() throws Exception {
        int databaseSizeBeforeCreate = devisRepository.findAll().size();
        // Create the Devis
        DevisDTO devisDTO = devisMapper.toDto(devis);
        restDevisMockMvc.perform(post("/api/devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devisDTO)))
            .andExpect(status().isCreated());

        // Validate the Devis in the database
        List<Devis> devisList = devisRepository.findAll();
        assertThat(devisList).hasSize(databaseSizeBeforeCreate + 1);
        Devis testDevis = devisList.get(devisList.size() - 1);
        assertThat(testDevis.getNumDevis()).isEqualTo(DEFAULT_NUM_DEVIS);
        assertThat(testDevis.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testDevis.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testDevis.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testDevis.getDateLimite()).isEqualTo(DEFAULT_DATE_LIMITE);

    }

    @Test
    @Transactional
    public void createDevisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = devisRepository.findAll().size();

        // Create the Devis with an existing ID
        devis.setId(1L);
        DevisDTO devisDTO = devisMapper.toDto(devis);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDevisMockMvc.perform(post("/api/devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Devis in the database
        List<Devis> devisList = devisRepository.findAll();
        assertThat(devisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDevis() throws Exception {
        // Initialize the database
        devisRepository.saveAndFlush(devis);

        // Get all the devisList
        restDevisMockMvc.perform(get("/api/devis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(devis.getId().intValue())))
            .andExpect(jsonPath("$.[*].numDevis").value(hasItem(DEFAULT_NUM_DEVIS.intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.toString())))
            .andExpect(jsonPath("$.[*].dateLimite").value(hasItem(DEFAULT_DATE_LIMITE.toString())))
            .andExpect(jsonPath("$.[*].prixHT").value(hasItem(DEFAULT_PRIX_HT.doubleValue())))
            .andExpect(jsonPath("$.[*].prixTTC").value(hasItem(DEFAULT_PRIX_TTC.doubleValue())))
            .andExpect(jsonPath("$.[*].tva").value(hasItem(DEFAULT_TVA.doubleValue())))
            .andExpect(jsonPath("$.[*].cheminFichier").value(hasItem(DEFAULT_CHEMIN_FICHIER)));
    }

    @Test
    @Transactional
    public void getDevis() throws Exception {
        // Initialize the database
        devisRepository.saveAndFlush(devis);

        // Get the devis
        restDevisMockMvc.perform(get("/api/devis/{id}", devis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(devis.getId().intValue()))
            .andExpect(jsonPath("$.numDevis").value(DEFAULT_NUM_DEVIS.intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION.toString()))
            .andExpect(jsonPath("$.dateLimite").value(DEFAULT_DATE_LIMITE.toString()))
            .andExpect(jsonPath("$.prixHT").value(DEFAULT_PRIX_HT.doubleValue()))
            .andExpect(jsonPath("$.prixTTC").value(DEFAULT_PRIX_TTC.doubleValue()))
            .andExpect(jsonPath("$.tva").value(DEFAULT_TVA.doubleValue()))
            .andExpect(jsonPath("$.cheminFichier").value(DEFAULT_CHEMIN_FICHIER));
    }
    @Test
    @Transactional
    public void getNonExistingDevis() throws Exception {
        // Get the devis
        restDevisMockMvc.perform(get("/api/devis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDevis() throws Exception {
        // Initialize the database
        devisRepository.saveAndFlush(devis);

        int databaseSizeBeforeUpdate = devisRepository.findAll().size();

        // Update the devis
        Devis updatedDevis = devisRepository.findById(devis.getId()).get();
        // Disconnect from session so that the updates on updatedDevis are not directly saved in db
        em.detach(updatedDevis);
        updatedDevis
            .numDevis(UPDATED_NUM_DEVIS)
            .nom(UPDATED_NOM)
            .message(UPDATED_MESSAGE)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateLimite(UPDATED_DATE_LIMITE)
           ;
        DevisDTO devisDTO = devisMapper.toDto(updatedDevis);

        restDevisMockMvc.perform(put("/api/devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devisDTO)))
            .andExpect(status().isOk());

        // Validate the Devis in the database
        List<Devis> devisList = devisRepository.findAll();
        assertThat(devisList).hasSize(databaseSizeBeforeUpdate);
        Devis testDevis = devisList.get(devisList.size() - 1);
        assertThat(testDevis.getNumDevis()).isEqualTo(UPDATED_NUM_DEVIS);
        assertThat(testDevis.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testDevis.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testDevis.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testDevis.getDateLimite()).isEqualTo(UPDATED_DATE_LIMITE);
    }

    @Test
    @Transactional
    public void updateNonExistingDevis() throws Exception {
        int databaseSizeBeforeUpdate = devisRepository.findAll().size();

        // Create the Devis
        DevisDTO devisDTO = devisMapper.toDto(devis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDevisMockMvc.perform(put("/api/devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Devis in the database
        List<Devis> devisList = devisRepository.findAll();
        assertThat(devisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDevis() throws Exception {
        // Initialize the database
        devisRepository.saveAndFlush(devis);

        int databaseSizeBeforeDelete = devisRepository.findAll().size();

        // Delete the devis
        restDevisMockMvc.perform(delete("/api/devis/{id}", devis.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Devis> devisList = devisRepository.findAll();
        assertThat(devisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
