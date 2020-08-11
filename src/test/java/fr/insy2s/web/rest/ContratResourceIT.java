package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.Contrat;
import fr.insy2s.repository.ContratRepository;
import fr.insy2s.service.ContratService;
import fr.insy2s.service.dto.ContratDTO;
import fr.insy2s.service.mapper.ContratMapper;

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
 * Integration tests for the {@link ContratResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ContratResourceIT {

    private static final String DEFAULT_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_CREATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREATION = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_SIGNE = false;
    private static final Boolean UPDATED_SIGNE = true;

    private static final Boolean DEFAULT_ARCHIVE = false;
    private static final Boolean UPDATED_ARCHIVE = true;

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private ContratMapper contratMapper;

    @Autowired
    private ContratService contratService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContratMockMvc;

    private Contrat contrat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contrat createEntity(EntityManager em) {
        Contrat contrat = new Contrat()
            .titre(DEFAULT_TITRE)
            .dateCreation(DEFAULT_DATE_CREATION)
            .signe(DEFAULT_SIGNE)
            .archive(DEFAULT_ARCHIVE);
        return contrat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contrat createUpdatedEntity(EntityManager em) {
        Contrat contrat = new Contrat()
            .titre(UPDATED_TITRE)
            .dateCreation(UPDATED_DATE_CREATION)
            .signe(UPDATED_SIGNE)
            .archive(UPDATED_ARCHIVE);
        return contrat;
    }

    @BeforeEach
    public void initTest() {
        contrat = createEntity(em);
    }

    @Test
    @Transactional
    public void createContrat() throws Exception {
        int databaseSizeBeforeCreate = contratRepository.findAll().size();

        // Create the Contrat
        ContratDTO contratDTO = contratMapper.toDto(contrat);
        restContratMockMvc.perform(post("/api/contrats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contratDTO)))
            .andExpect(status().isCreated());

        // Validate the Contrat in the database
        List<Contrat> contratList = contratRepository.findAll();
        assertThat(contratList).hasSize(databaseSizeBeforeCreate + 1);
        Contrat testContrat = contratList.get(contratList.size() - 1);
        assertThat(testContrat.getTitre()).isEqualTo(DEFAULT_TITRE);
        assertThat(testContrat.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testContrat.isSigne()).isEqualTo(DEFAULT_SIGNE);
        assertThat(testContrat.isArchive()).isEqualTo(DEFAULT_ARCHIVE);
    }

    @Test
    @Transactional
    public void createContratWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contratRepository.findAll().size();

        // Create the Contrat with an existing ID
        contrat.setId(1L);
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContratMockMvc.perform(post("/api/contrats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contrat in the database
        List<Contrat> contratList = contratRepository.findAll();
        assertThat(contratList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTitreIsRequired() throws Exception {
        int databaseSizeBeforeTest = contratRepository.findAll().size();
        // set the field null
        contrat.setTitre(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc.perform(post("/api/contrats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        List<Contrat> contratList = contratRepository.findAll();
        assertThat(contratList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateCreationIsRequired() throws Exception {
        int databaseSizeBeforeTest = contratRepository.findAll().size();
        // set the field null
        contrat.setDateCreation(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc.perform(post("/api/contrats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        List<Contrat> contratList = contratRepository.findAll();
        assertThat(contratList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSigneIsRequired() throws Exception {
        int databaseSizeBeforeTest = contratRepository.findAll().size();
        // set the field null
        contrat.setSigne(null);

        // Create the Contrat, which fails.
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        restContratMockMvc.perform(post("/api/contrats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        List<Contrat> contratList = contratRepository.findAll();
        assertThat(contratList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllContrats() throws Exception {
        // Initialize the database
        contratRepository.saveAndFlush(contrat);

        // Get all the contratList
        restContratMockMvc.perform(get("/api/contrats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contrat.getId().intValue())))
            .andExpect(jsonPath("$.[*].titre").value(hasItem(DEFAULT_TITRE)))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.toString())))
            .andExpect(jsonPath("$.[*].signe").value(hasItem(DEFAULT_SIGNE.booleanValue())))
            .andExpect(jsonPath("$.[*].archive").value(hasItem(DEFAULT_ARCHIVE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getContrat() throws Exception {
        // Initialize the database
        contratRepository.saveAndFlush(contrat);

        // Get the contrat
        restContratMockMvc.perform(get("/api/contrats/{id}", contrat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contrat.getId().intValue()))
            .andExpect(jsonPath("$.titre").value(DEFAULT_TITRE))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION.toString()))
            .andExpect(jsonPath("$.signe").value(DEFAULT_SIGNE.booleanValue()))
            .andExpect(jsonPath("$.archive").value(DEFAULT_ARCHIVE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingContrat() throws Exception {
        // Get the contrat
        restContratMockMvc.perform(get("/api/contrats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContrat() throws Exception {
        // Initialize the database
        contratRepository.saveAndFlush(contrat);

        int databaseSizeBeforeUpdate = contratRepository.findAll().size();

        // Update the contrat
        Contrat updatedContrat = contratRepository.findById(contrat.getId()).get();
        // Disconnect from session so that the updates on updatedContrat are not directly saved in db
        em.detach(updatedContrat);
        updatedContrat
            .titre(UPDATED_TITRE)
            .dateCreation(UPDATED_DATE_CREATION)
            .signe(UPDATED_SIGNE)
            .archive(UPDATED_ARCHIVE);
        ContratDTO contratDTO = contratMapper.toDto(updatedContrat);

        restContratMockMvc.perform(put("/api/contrats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contratDTO)))
            .andExpect(status().isOk());

        // Validate the Contrat in the database
        List<Contrat> contratList = contratRepository.findAll();
        assertThat(contratList).hasSize(databaseSizeBeforeUpdate);
        Contrat testContrat = contratList.get(contratList.size() - 1);
        assertThat(testContrat.getTitre()).isEqualTo(UPDATED_TITRE);
        assertThat(testContrat.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testContrat.isSigne()).isEqualTo(UPDATED_SIGNE);
        assertThat(testContrat.isArchive()).isEqualTo(UPDATED_ARCHIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingContrat() throws Exception {
        int databaseSizeBeforeUpdate = contratRepository.findAll().size();

        // Create the Contrat
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContratMockMvc.perform(put("/api/contrats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contrat in the database
        List<Contrat> contratList = contratRepository.findAll();
        assertThat(contratList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContrat() throws Exception {
        // Initialize the database
        contratRepository.saveAndFlush(contrat);

        int databaseSizeBeforeDelete = contratRepository.findAll().size();

        // Delete the contrat
        restContratMockMvc.perform(delete("/api/contrats/{id}", contrat.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Contrat> contratList = contratRepository.findAll();
        assertThat(contratList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
