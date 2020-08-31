package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.AvanceRappelSalaire;
import fr.insy2s.repository.AvanceRappelSalaireRepository;
import fr.insy2s.service.AvanceRappelSalaireService;
import fr.insy2s.service.dto.AvanceRappelSalaireDTO;
import fr.insy2s.service.mapper.AvanceRappelSalaireMapper;

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
 * Integration tests for the {@link AvanceRappelSalaireResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AvanceRappelSalaireResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DEBUT_PERIODE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEBUT_PERIODE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FIN_PERIODE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FIN_PERIODE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_MONTANT = 1D;
    private static final Double UPDATED_MONTANT = 2D;

    @Autowired
    private AvanceRappelSalaireRepository avanceRappelSalaireRepository;

    @Autowired
    private AvanceRappelSalaireMapper avanceRappelSalaireMapper;

    @Autowired
    private AvanceRappelSalaireService avanceRappelSalaireService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAvanceRappelSalaireMockMvc;

    private AvanceRappelSalaire avanceRappelSalaire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AvanceRappelSalaire createEntity(EntityManager em) {
        AvanceRappelSalaire avanceRappelSalaire = new AvanceRappelSalaire()
            .type(DEFAULT_TYPE)
            .debutPeriode(DEFAULT_DEBUT_PERIODE)
            .finPeriode(DEFAULT_FIN_PERIODE)
            .montant(DEFAULT_MONTANT);
        return avanceRappelSalaire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AvanceRappelSalaire createUpdatedEntity(EntityManager em) {
        AvanceRappelSalaire avanceRappelSalaire = new AvanceRappelSalaire()
            .type(UPDATED_TYPE)
            .debutPeriode(UPDATED_DEBUT_PERIODE)
            .finPeriode(UPDATED_FIN_PERIODE)
            .montant(UPDATED_MONTANT);
        return avanceRappelSalaire;
    }

    @BeforeEach
    public void initTest() {
        avanceRappelSalaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createAvanceRappelSalaire() throws Exception {
        int databaseSizeBeforeCreate = avanceRappelSalaireRepository.findAll().size();
        // Create the AvanceRappelSalaire
        AvanceRappelSalaireDTO avanceRappelSalaireDTO = avanceRappelSalaireMapper.toDto(avanceRappelSalaire);
        restAvanceRappelSalaireMockMvc.perform(post("/api/avance-rappel-salaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avanceRappelSalaireDTO)))
            .andExpect(status().isCreated());

        // Validate the AvanceRappelSalaire in the database
        List<AvanceRappelSalaire> avanceRappelSalaireList = avanceRappelSalaireRepository.findAll();
        assertThat(avanceRappelSalaireList).hasSize(databaseSizeBeforeCreate + 1);
        AvanceRappelSalaire testAvanceRappelSalaire = avanceRappelSalaireList.get(avanceRappelSalaireList.size() - 1);
        assertThat(testAvanceRappelSalaire.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAvanceRappelSalaire.getDebutPeriode()).isEqualTo(DEFAULT_DEBUT_PERIODE);
        assertThat(testAvanceRappelSalaire.getFinPeriode()).isEqualTo(DEFAULT_FIN_PERIODE);
        assertThat(testAvanceRappelSalaire.getMontant()).isEqualTo(DEFAULT_MONTANT);
    }

    @Test
    @Transactional
    public void createAvanceRappelSalaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = avanceRappelSalaireRepository.findAll().size();

        // Create the AvanceRappelSalaire with an existing ID
        avanceRappelSalaire.setId(1L);
        AvanceRappelSalaireDTO avanceRappelSalaireDTO = avanceRappelSalaireMapper.toDto(avanceRappelSalaire);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAvanceRappelSalaireMockMvc.perform(post("/api/avance-rappel-salaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avanceRappelSalaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AvanceRappelSalaire in the database
        List<AvanceRappelSalaire> avanceRappelSalaireList = avanceRappelSalaireRepository.findAll();
        assertThat(avanceRappelSalaireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = avanceRappelSalaireRepository.findAll().size();
        // set the field null
        avanceRappelSalaire.setType(null);

        // Create the AvanceRappelSalaire, which fails.
        AvanceRappelSalaireDTO avanceRappelSalaireDTO = avanceRappelSalaireMapper.toDto(avanceRappelSalaire);


        restAvanceRappelSalaireMockMvc.perform(post("/api/avance-rappel-salaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avanceRappelSalaireDTO)))
            .andExpect(status().isBadRequest());

        List<AvanceRappelSalaire> avanceRappelSalaireList = avanceRappelSalaireRepository.findAll();
        assertThat(avanceRappelSalaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDebutPeriodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = avanceRappelSalaireRepository.findAll().size();
        // set the field null
        avanceRappelSalaire.setDebutPeriode(null);

        // Create the AvanceRappelSalaire, which fails.
        AvanceRappelSalaireDTO avanceRappelSalaireDTO = avanceRappelSalaireMapper.toDto(avanceRappelSalaire);


        restAvanceRappelSalaireMockMvc.perform(post("/api/avance-rappel-salaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avanceRappelSalaireDTO)))
            .andExpect(status().isBadRequest());

        List<AvanceRappelSalaire> avanceRappelSalaireList = avanceRappelSalaireRepository.findAll();
        assertThat(avanceRappelSalaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFinPeriodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = avanceRappelSalaireRepository.findAll().size();
        // set the field null
        avanceRappelSalaire.setFinPeriode(null);

        // Create the AvanceRappelSalaire, which fails.
        AvanceRappelSalaireDTO avanceRappelSalaireDTO = avanceRappelSalaireMapper.toDto(avanceRappelSalaire);


        restAvanceRappelSalaireMockMvc.perform(post("/api/avance-rappel-salaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avanceRappelSalaireDTO)))
            .andExpect(status().isBadRequest());

        List<AvanceRappelSalaire> avanceRappelSalaireList = avanceRappelSalaireRepository.findAll();
        assertThat(avanceRappelSalaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantIsRequired() throws Exception {
        int databaseSizeBeforeTest = avanceRappelSalaireRepository.findAll().size();
        // set the field null
        avanceRappelSalaire.setMontant(null);

        // Create the AvanceRappelSalaire, which fails.
        AvanceRappelSalaireDTO avanceRappelSalaireDTO = avanceRappelSalaireMapper.toDto(avanceRappelSalaire);


        restAvanceRappelSalaireMockMvc.perform(post("/api/avance-rappel-salaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avanceRappelSalaireDTO)))
            .andExpect(status().isBadRequest());

        List<AvanceRappelSalaire> avanceRappelSalaireList = avanceRappelSalaireRepository.findAll();
        assertThat(avanceRappelSalaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAvanceRappelSalaires() throws Exception {
        // Initialize the database
        avanceRappelSalaireRepository.saveAndFlush(avanceRappelSalaire);

        // Get all the avanceRappelSalaireList
        restAvanceRappelSalaireMockMvc.perform(get("/api/avance-rappel-salaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(avanceRappelSalaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].debutPeriode").value(hasItem(DEFAULT_DEBUT_PERIODE.toString())))
            .andExpect(jsonPath("$.[*].finPeriode").value(hasItem(DEFAULT_FIN_PERIODE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getAvanceRappelSalaire() throws Exception {
        // Initialize the database
        avanceRappelSalaireRepository.saveAndFlush(avanceRappelSalaire);

        // Get the avanceRappelSalaire
        restAvanceRappelSalaireMockMvc.perform(get("/api/avance-rappel-salaires/{id}", avanceRappelSalaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(avanceRappelSalaire.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.debutPeriode").value(DEFAULT_DEBUT_PERIODE.toString()))
            .andExpect(jsonPath("$.finPeriode").value(DEFAULT_FIN_PERIODE.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAvanceRappelSalaire() throws Exception {
        // Get the avanceRappelSalaire
        restAvanceRappelSalaireMockMvc.perform(get("/api/avance-rappel-salaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAvanceRappelSalaire() throws Exception {
        // Initialize the database
        avanceRappelSalaireRepository.saveAndFlush(avanceRappelSalaire);

        int databaseSizeBeforeUpdate = avanceRappelSalaireRepository.findAll().size();

        // Update the avanceRappelSalaire
        AvanceRappelSalaire updatedAvanceRappelSalaire = avanceRappelSalaireRepository.findById(avanceRappelSalaire.getId()).get();
        // Disconnect from session so that the updates on updatedAvanceRappelSalaire are not directly saved in db
        em.detach(updatedAvanceRappelSalaire);
        updatedAvanceRappelSalaire
            .type(UPDATED_TYPE)
            .debutPeriode(UPDATED_DEBUT_PERIODE)
            .finPeriode(UPDATED_FIN_PERIODE)
            .montant(UPDATED_MONTANT);
        AvanceRappelSalaireDTO avanceRappelSalaireDTO = avanceRappelSalaireMapper.toDto(updatedAvanceRappelSalaire);

        restAvanceRappelSalaireMockMvc.perform(put("/api/avance-rappel-salaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avanceRappelSalaireDTO)))
            .andExpect(status().isOk());

        // Validate the AvanceRappelSalaire in the database
        List<AvanceRappelSalaire> avanceRappelSalaireList = avanceRappelSalaireRepository.findAll();
        assertThat(avanceRappelSalaireList).hasSize(databaseSizeBeforeUpdate);
        AvanceRappelSalaire testAvanceRappelSalaire = avanceRappelSalaireList.get(avanceRappelSalaireList.size() - 1);
        assertThat(testAvanceRappelSalaire.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAvanceRappelSalaire.getDebutPeriode()).isEqualTo(UPDATED_DEBUT_PERIODE);
        assertThat(testAvanceRappelSalaire.getFinPeriode()).isEqualTo(UPDATED_FIN_PERIODE);
        assertThat(testAvanceRappelSalaire.getMontant()).isEqualTo(UPDATED_MONTANT);
    }

    @Test
    @Transactional
    public void updateNonExistingAvanceRappelSalaire() throws Exception {
        int databaseSizeBeforeUpdate = avanceRappelSalaireRepository.findAll().size();

        // Create the AvanceRappelSalaire
        AvanceRappelSalaireDTO avanceRappelSalaireDTO = avanceRappelSalaireMapper.toDto(avanceRappelSalaire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAvanceRappelSalaireMockMvc.perform(put("/api/avance-rappel-salaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avanceRappelSalaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AvanceRappelSalaire in the database
        List<AvanceRappelSalaire> avanceRappelSalaireList = avanceRappelSalaireRepository.findAll();
        assertThat(avanceRappelSalaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAvanceRappelSalaire() throws Exception {
        // Initialize the database
        avanceRappelSalaireRepository.saveAndFlush(avanceRappelSalaire);

        int databaseSizeBeforeDelete = avanceRappelSalaireRepository.findAll().size();

        // Delete the avanceRappelSalaire
        restAvanceRappelSalaireMockMvc.perform(delete("/api/avance-rappel-salaires/{id}", avanceRappelSalaire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AvanceRappelSalaire> avanceRappelSalaireList = avanceRappelSalaireRepository.findAll();
        assertThat(avanceRappelSalaireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
