package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.Dpae;
import fr.insy2s.repository.DpaeRepository;
import fr.insy2s.service.DpaeService;
import fr.insy2s.service.dto.DpaeDTO;
import fr.insy2s.service.mapper.DpaeMapper;

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
 * Integration tests for the {@link DpaeResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DpaeResourceIT {

    private static final String DEFAULT_LIEU = "AAAAAAAAAA";
    private static final String UPDATED_LIEU = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LIEN_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_LIEN_DOCUMENT = "BBBBBBBBBB";

    @Autowired
    private DpaeRepository dpaeRepository;

    @Autowired
    private DpaeMapper dpaeMapper;

    @Autowired
    private DpaeService dpaeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDpaeMockMvc;

    private Dpae dpae;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dpae createEntity(EntityManager em) {
        Dpae dpae = new Dpae()
            .lieu(DEFAULT_LIEU)
            .date(DEFAULT_DATE)
            .lienDocument(DEFAULT_LIEN_DOCUMENT);
        return dpae;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dpae createUpdatedEntity(EntityManager em) {
        Dpae dpae = new Dpae()
            .lieu(UPDATED_LIEU)
            .date(UPDATED_DATE)
            .lienDocument(UPDATED_LIEN_DOCUMENT);
        return dpae;
    }

    @BeforeEach
    public void initTest() {
        dpae = createEntity(em);
    }

    @Test
    @Transactional
    public void createDpae() throws Exception {
        int databaseSizeBeforeCreate = dpaeRepository.findAll().size();
        // Create the Dpae
        DpaeDTO dpaeDTO = dpaeMapper.toDto(dpae);
        restDpaeMockMvc.perform(post("/api/dpaes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpaeDTO)))
            .andExpect(status().isCreated());

        // Validate the Dpae in the database
        List<Dpae> dpaeList = dpaeRepository.findAll();
        assertThat(dpaeList).hasSize(databaseSizeBeforeCreate + 1);
        Dpae testDpae = dpaeList.get(dpaeList.size() - 1);
        assertThat(testDpae.getLieu()).isEqualTo(DEFAULT_LIEU);
        assertThat(testDpae.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testDpae.getLienDocument()).isEqualTo(DEFAULT_LIEN_DOCUMENT);
    }

    @Test
    @Transactional
    public void createDpaeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dpaeRepository.findAll().size();

        // Create the Dpae with an existing ID
        dpae.setId(1L);
        DpaeDTO dpaeDTO = dpaeMapper.toDto(dpae);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDpaeMockMvc.perform(post("/api/dpaes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpaeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dpae in the database
        List<Dpae> dpaeList = dpaeRepository.findAll();
        assertThat(dpaeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLieuIsRequired() throws Exception {
        int databaseSizeBeforeTest = dpaeRepository.findAll().size();
        // set the field null
        dpae.setLieu(null);

        // Create the Dpae, which fails.
        DpaeDTO dpaeDTO = dpaeMapper.toDto(dpae);


        restDpaeMockMvc.perform(post("/api/dpaes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpaeDTO)))
            .andExpect(status().isBadRequest());

        List<Dpae> dpaeList = dpaeRepository.findAll();
        assertThat(dpaeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = dpaeRepository.findAll().size();
        // set the field null
        dpae.setDate(null);

        // Create the Dpae, which fails.
        DpaeDTO dpaeDTO = dpaeMapper.toDto(dpae);


        restDpaeMockMvc.perform(post("/api/dpaes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpaeDTO)))
            .andExpect(status().isBadRequest());

        List<Dpae> dpaeList = dpaeRepository.findAll();
        assertThat(dpaeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLienDocumentIsRequired() throws Exception {
        int databaseSizeBeforeTest = dpaeRepository.findAll().size();
        // set the field null
        dpae.setLienDocument(null);

        // Create the Dpae, which fails.
        DpaeDTO dpaeDTO = dpaeMapper.toDto(dpae);


        restDpaeMockMvc.perform(post("/api/dpaes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpaeDTO)))
            .andExpect(status().isBadRequest());

        List<Dpae> dpaeList = dpaeRepository.findAll();
        assertThat(dpaeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDpaes() throws Exception {
        // Initialize the database
        dpaeRepository.saveAndFlush(dpae);

        // Get all the dpaeList
        restDpaeMockMvc.perform(get("/api/dpaes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dpae.getId().intValue())))
            .andExpect(jsonPath("$.[*].lieu").value(hasItem(DEFAULT_LIEU)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].lienDocument").value(hasItem(DEFAULT_LIEN_DOCUMENT)));
    }
    
    @Test
    @Transactional
    public void getDpae() throws Exception {
        // Initialize the database
        dpaeRepository.saveAndFlush(dpae);

        // Get the dpae
        restDpaeMockMvc.perform(get("/api/dpaes/{id}", dpae.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dpae.getId().intValue()))
            .andExpect(jsonPath("$.lieu").value(DEFAULT_LIEU))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.lienDocument").value(DEFAULT_LIEN_DOCUMENT));
    }
    @Test
    @Transactional
    public void getNonExistingDpae() throws Exception {
        // Get the dpae
        restDpaeMockMvc.perform(get("/api/dpaes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDpae() throws Exception {
        // Initialize the database
        dpaeRepository.saveAndFlush(dpae);

        int databaseSizeBeforeUpdate = dpaeRepository.findAll().size();

        // Update the dpae
        Dpae updatedDpae = dpaeRepository.findById(dpae.getId()).get();
        // Disconnect from session so that the updates on updatedDpae are not directly saved in db
        em.detach(updatedDpae);
        updatedDpae
            .lieu(UPDATED_LIEU)
            .date(UPDATED_DATE)
            .lienDocument(UPDATED_LIEN_DOCUMENT);
        DpaeDTO dpaeDTO = dpaeMapper.toDto(updatedDpae);

        restDpaeMockMvc.perform(put("/api/dpaes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpaeDTO)))
            .andExpect(status().isOk());

        // Validate the Dpae in the database
        List<Dpae> dpaeList = dpaeRepository.findAll();
        assertThat(dpaeList).hasSize(databaseSizeBeforeUpdate);
        Dpae testDpae = dpaeList.get(dpaeList.size() - 1);
        assertThat(testDpae.getLieu()).isEqualTo(UPDATED_LIEU);
        assertThat(testDpae.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testDpae.getLienDocument()).isEqualTo(UPDATED_LIEN_DOCUMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingDpae() throws Exception {
        int databaseSizeBeforeUpdate = dpaeRepository.findAll().size();

        // Create the Dpae
        DpaeDTO dpaeDTO = dpaeMapper.toDto(dpae);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDpaeMockMvc.perform(put("/api/dpaes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpaeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dpae in the database
        List<Dpae> dpaeList = dpaeRepository.findAll();
        assertThat(dpaeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDpae() throws Exception {
        // Initialize the database
        dpaeRepository.saveAndFlush(dpae);

        int databaseSizeBeforeDelete = dpaeRepository.findAll().size();

        // Delete the dpae
        restDpaeMockMvc.perform(delete("/api/dpaes/{id}", dpae.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dpae> dpaeList = dpaeRepository.findAll();
        assertThat(dpaeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
