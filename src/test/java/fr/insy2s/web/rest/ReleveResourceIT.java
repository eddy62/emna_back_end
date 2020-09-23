package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.Releve;
import fr.insy2s.repository.ReleveRepository;
import fr.insy2s.service.ReleveService;
import fr.insy2s.service.dto.ReleveDTO;
import fr.insy2s.service.mapper.ReleveMapper;

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
 * Integration tests for the {@link ReleveResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ReleveResourceIT {

    private static final LocalDate DEFAULT_DATE_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BANQUE = "AAAAAAAAAA";
    private static final String UPDATED_BANQUE = "BBBBBBBBBB";

    @Autowired
    private ReleveRepository releveRepository;

    @Autowired
    private ReleveMapper releveMapper;

    @Autowired
    private ReleveService releveService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReleveMockMvc;

    private Releve releve;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Releve createEntity(EntityManager em) {
        Releve releve = new Releve()
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .banque(DEFAULT_BANQUE);
        return releve;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Releve createUpdatedEntity(EntityManager em) {
        Releve releve = new Releve()
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .banque(UPDATED_BANQUE);
        return releve;
    }

    @BeforeEach
    public void initTest() {
        releve = createEntity(em);
    }

    @Test
    @Transactional
    public void createReleve() throws Exception {
        int databaseSizeBeforeCreate = releveRepository.findAll().size();
        // Create the Releve
        ReleveDTO releveDTO = releveMapper.toDto(releve);
        restReleveMockMvc.perform(post("/api/releves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(releveDTO)))
            .andExpect(status().isCreated());

        // Validate the Releve in the database
        List<Releve> releveList = releveRepository.findAll();
        assertThat(releveList).hasSize(databaseSizeBeforeCreate + 1);
        Releve testReleve = releveList.get(releveList.size() - 1);
        assertThat(testReleve.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testReleve.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testReleve.getBanque()).isEqualTo(DEFAULT_BANQUE);
    }

    @Test
    @Transactional
    public void createReleveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = releveRepository.findAll().size();

        // Create the Releve with an existing ID
        releve.setId(1L);
        ReleveDTO releveDTO = releveMapper.toDto(releve);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReleveMockMvc.perform(post("/api/releves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(releveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Releve in the database
        List<Releve> releveList = releveRepository.findAll();
        assertThat(releveList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReleves() throws Exception {
        // Initialize the database
        releveRepository.saveAndFlush(releve);

        // Get all the releveList
        restReleveMockMvc.perform(get("/api/releves?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(releve.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].banque").value(hasItem(DEFAULT_BANQUE)));
    }
    
    @Test
    @Transactional
    public void getReleve() throws Exception {
        // Initialize the database
        releveRepository.saveAndFlush(releve);

        // Get the releve
        restReleveMockMvc.perform(get("/api/releves/{id}", releve.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(releve.getId().intValue()))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.banque").value(DEFAULT_BANQUE));
    }
    @Test
    @Transactional
    public void getNonExistingReleve() throws Exception {
        // Get the releve
        restReleveMockMvc.perform(get("/api/releves/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReleve() throws Exception {
        // Initialize the database
        releveRepository.saveAndFlush(releve);

        int databaseSizeBeforeUpdate = releveRepository.findAll().size();

        // Update the releve
        Releve updatedReleve = releveRepository.findById(releve.getId()).get();
        // Disconnect from session so that the updates on updatedReleve are not directly saved in db
        em.detach(updatedReleve);
        updatedReleve
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .banque(UPDATED_BANQUE);
        ReleveDTO releveDTO = releveMapper.toDto(updatedReleve);

        restReleveMockMvc.perform(put("/api/releves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(releveDTO)))
            .andExpect(status().isOk());

        // Validate the Releve in the database
        List<Releve> releveList = releveRepository.findAll();
        assertThat(releveList).hasSize(databaseSizeBeforeUpdate);
        Releve testReleve = releveList.get(releveList.size() - 1);
        assertThat(testReleve.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testReleve.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testReleve.getBanque()).isEqualTo(UPDATED_BANQUE);
    }

    @Test
    @Transactional
    public void updateNonExistingReleve() throws Exception {
        int databaseSizeBeforeUpdate = releveRepository.findAll().size();

        // Create the Releve
        ReleveDTO releveDTO = releveMapper.toDto(releve);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReleveMockMvc.perform(put("/api/releves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(releveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Releve in the database
        List<Releve> releveList = releveRepository.findAll();
        assertThat(releveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReleve() throws Exception {
        // Initialize the database
        releveRepository.saveAndFlush(releve);

        int databaseSizeBeforeDelete = releveRepository.findAll().size();

        // Delete the releve
        restReleveMockMvc.perform(delete("/api/releves/{id}", releve.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Releve> releveList = releveRepository.findAll();
        assertThat(releveList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
