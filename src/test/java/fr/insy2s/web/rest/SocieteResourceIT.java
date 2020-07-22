package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.Societe;
import fr.insy2s.repository.SocieteRepository;
import fr.insy2s.service.SocieteService;
import fr.insy2s.service.dto.SocieteDTO;
import fr.insy2s.service.mapper.SocieteMapper;

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
 * Integration tests for the {@link SocieteResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SocieteResourceIT {

    private static final String DEFAULT_CIVILITE = "AAAAAAAAAA";
    private static final String UPDATED_CIVILITE = "BBBBBBBBBB";

    @Autowired
    private SocieteRepository societeRepository;

    @Autowired
    private SocieteMapper societeMapper;

    @Autowired
    private SocieteService societeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSocieteMockMvc;

    private Societe societe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Societe createEntity(EntityManager em) {
        Societe societe = new Societe()
            .civilite(DEFAULT_CIVILITE);
        return societe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Societe createUpdatedEntity(EntityManager em) {
        Societe societe = new Societe()
            .civilite(UPDATED_CIVILITE);
        return societe;
    }

    @BeforeEach
    public void initTest() {
        societe = createEntity(em);
    }

    @Test
    @Transactional
    public void createSociete() throws Exception {
        int databaseSizeBeforeCreate = societeRepository.findAll().size();
        // Create the Societe
        SocieteDTO societeDTO = societeMapper.toDto(societe);
        restSocieteMockMvc.perform(post("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isCreated());

        // Validate the Societe in the database
        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeCreate + 1);
        Societe testSociete = societeList.get(societeList.size() - 1);
        assertThat(testSociete.getCivilite()).isEqualTo(DEFAULT_CIVILITE);
    }

    @Test
    @Transactional
    public void createSocieteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = societeRepository.findAll().size();

        // Create the Societe with an existing ID
        societe.setId(1L);
        SocieteDTO societeDTO = societeMapper.toDto(societe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSocieteMockMvc.perform(post("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Societe in the database
        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSocietes() throws Exception {
        // Initialize the database
        societeRepository.saveAndFlush(societe);

        // Get all the societeList
        restSocieteMockMvc.perform(get("/api/societes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(societe.getId().intValue())))
            .andExpect(jsonPath("$.[*].civilite").value(hasItem(DEFAULT_CIVILITE)));
    }
    
    @Test
    @Transactional
    public void getSociete() throws Exception {
        // Initialize the database
        societeRepository.saveAndFlush(societe);

        // Get the societe
        restSocieteMockMvc.perform(get("/api/societes/{id}", societe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(societe.getId().intValue()))
            .andExpect(jsonPath("$.civilite").value(DEFAULT_CIVILITE));
    }
    @Test
    @Transactional
    public void getNonExistingSociete() throws Exception {
        // Get the societe
        restSocieteMockMvc.perform(get("/api/societes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSociete() throws Exception {
        // Initialize the database
        societeRepository.saveAndFlush(societe);

        int databaseSizeBeforeUpdate = societeRepository.findAll().size();

        // Update the societe
        Societe updatedSociete = societeRepository.findById(societe.getId()).get();
        // Disconnect from session so that the updates on updatedSociete are not directly saved in db
        em.detach(updatedSociete);
        updatedSociete
            .civilite(UPDATED_CIVILITE);
        SocieteDTO societeDTO = societeMapper.toDto(updatedSociete);

        restSocieteMockMvc.perform(put("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isOk());

        // Validate the Societe in the database
        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeUpdate);
        Societe testSociete = societeList.get(societeList.size() - 1);
        assertThat(testSociete.getCivilite()).isEqualTo(UPDATED_CIVILITE);
    }

    @Test
    @Transactional
    public void updateNonExistingSociete() throws Exception {
        int databaseSizeBeforeUpdate = societeRepository.findAll().size();

        // Create the Societe
        SocieteDTO societeDTO = societeMapper.toDto(societe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSocieteMockMvc.perform(put("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Societe in the database
        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSociete() throws Exception {
        // Initialize the database
        societeRepository.saveAndFlush(societe);

        int databaseSizeBeforeDelete = societeRepository.findAll().size();

        // Delete the societe
        restSocieteMockMvc.perform(delete("/api/societes/{id}", societe.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
