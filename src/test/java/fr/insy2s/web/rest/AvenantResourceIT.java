package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.Avenant;
import fr.insy2s.repository.AvenantRepository;
import fr.insy2s.service.AvenantService;
import fr.insy2s.service.dto.AvenantDTO;
import fr.insy2s.service.mapper.AvenantMapper;

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
 * Integration tests for the {@link AvenantResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AvenantResourceIT {

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SIGNE = false;
    private static final Boolean UPDATED_SIGNE = true;

    @Autowired
    private AvenantRepository avenantRepository;

    @Autowired
    private AvenantMapper avenantMapper;

    @Autowired
    private AvenantService avenantService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAvenantMockMvc;

    private Avenant avenant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Avenant createEntity(EntityManager em) {
        Avenant avenant = new Avenant()
            .reference(DEFAULT_REFERENCE)
            .signe(DEFAULT_SIGNE);
        return avenant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Avenant createUpdatedEntity(EntityManager em) {
        Avenant avenant = new Avenant()
            .reference(UPDATED_REFERENCE)
            .signe(UPDATED_SIGNE);
        return avenant;
    }

    @BeforeEach
    public void initTest() {
        avenant = createEntity(em);
    }

    @Test
    @Transactional
    public void createAvenant() throws Exception {
        int databaseSizeBeforeCreate = avenantRepository.findAll().size();
        // Create the Avenant
        AvenantDTO avenantDTO = avenantMapper.toDto(avenant);
        restAvenantMockMvc.perform(post("/api/avenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avenantDTO)))
            .andExpect(status().isCreated());

        // Validate the Avenant in the database
        List<Avenant> avenantList = avenantRepository.findAll();
        assertThat(avenantList).hasSize(databaseSizeBeforeCreate + 1);
        Avenant testAvenant = avenantList.get(avenantList.size() - 1);
        assertThat(testAvenant.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testAvenant.isSigne()).isEqualTo(DEFAULT_SIGNE);
    }

    @Test
    @Transactional
    public void createAvenantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = avenantRepository.findAll().size();

        // Create the Avenant with an existing ID
        avenant.setId(1L);
        AvenantDTO avenantDTO = avenantMapper.toDto(avenant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAvenantMockMvc.perform(post("/api/avenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avenantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Avenant in the database
        List<Avenant> avenantList = avenantRepository.findAll();
        assertThat(avenantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkReferenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = avenantRepository.findAll().size();
        // set the field null
        avenant.setReference(null);

        // Create the Avenant, which fails.
        AvenantDTO avenantDTO = avenantMapper.toDto(avenant);


        restAvenantMockMvc.perform(post("/api/avenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avenantDTO)))
            .andExpect(status().isBadRequest());

        List<Avenant> avenantList = avenantRepository.findAll();
        assertThat(avenantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSigneIsRequired() throws Exception {
        int databaseSizeBeforeTest = avenantRepository.findAll().size();
        // set the field null
        avenant.setSigne(null);

        // Create the Avenant, which fails.
        AvenantDTO avenantDTO = avenantMapper.toDto(avenant);


        restAvenantMockMvc.perform(post("/api/avenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avenantDTO)))
            .andExpect(status().isBadRequest());

        List<Avenant> avenantList = avenantRepository.findAll();
        assertThat(avenantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAvenants() throws Exception {
        // Initialize the database
        avenantRepository.saveAndFlush(avenant);

        // Get all the avenantList
        restAvenantMockMvc.perform(get("/api/avenants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(avenant.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].signe").value(hasItem(DEFAULT_SIGNE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAvenant() throws Exception {
        // Initialize the database
        avenantRepository.saveAndFlush(avenant);

        // Get the avenant
        restAvenantMockMvc.perform(get("/api/avenants/{id}", avenant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(avenant.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
            .andExpect(jsonPath("$.signe").value(DEFAULT_SIGNE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAvenant() throws Exception {
        // Get the avenant
        restAvenantMockMvc.perform(get("/api/avenants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAvenant() throws Exception {
        // Initialize the database
        avenantRepository.saveAndFlush(avenant);

        int databaseSizeBeforeUpdate = avenantRepository.findAll().size();

        // Update the avenant
        Avenant updatedAvenant = avenantRepository.findById(avenant.getId()).get();
        // Disconnect from session so that the updates on updatedAvenant are not directly saved in db
        em.detach(updatedAvenant);
        updatedAvenant
            .reference(UPDATED_REFERENCE)
            .signe(UPDATED_SIGNE);
        AvenantDTO avenantDTO = avenantMapper.toDto(updatedAvenant);

        restAvenantMockMvc.perform(put("/api/avenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avenantDTO)))
            .andExpect(status().isOk());

        // Validate the Avenant in the database
        List<Avenant> avenantList = avenantRepository.findAll();
        assertThat(avenantList).hasSize(databaseSizeBeforeUpdate);
        Avenant testAvenant = avenantList.get(avenantList.size() - 1);
        assertThat(testAvenant.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testAvenant.isSigne()).isEqualTo(UPDATED_SIGNE);
    }

    @Test
    @Transactional
    public void updateNonExistingAvenant() throws Exception {
        int databaseSizeBeforeUpdate = avenantRepository.findAll().size();

        // Create the Avenant
        AvenantDTO avenantDTO = avenantMapper.toDto(avenant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAvenantMockMvc.perform(put("/api/avenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avenantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Avenant in the database
        List<Avenant> avenantList = avenantRepository.findAll();
        assertThat(avenantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAvenant() throws Exception {
        // Initialize the database
        avenantRepository.saveAndFlush(avenant);

        int databaseSizeBeforeDelete = avenantRepository.findAll().size();

        // Delete the avenant
        restAvenantMockMvc.perform(delete("/api/avenants/{id}", avenant.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Avenant> avenantList = avenantRepository.findAll();
        assertThat(avenantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
