package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.Comptable;
import fr.insy2s.domain.Adresse;
import fr.insy2s.repository.ComptableRepository;
import fr.insy2s.service.ComptableService;
import fr.insy2s.service.dto.ComptableDTO;
import fr.insy2s.service.mapper.ComptableMapper;

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
 * Integration tests for the {@link ComptableResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ComptableResourceIT {

    private static final String DEFAULT_CIVILITE = "AAAAAAAAAA";
    private static final String UPDATED_CIVILITE = "BBBBBBBBBB";

    @Autowired
    private ComptableRepository comptableRepository;

    @Autowired
    private ComptableMapper comptableMapper;

    @Autowired
    private ComptableService comptableService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restComptableMockMvc;

    private Comptable comptable;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comptable createEntity(EntityManager em) {
        Comptable comptable = new Comptable()
            .civilite(DEFAULT_CIVILITE);
        // Add required entity
        Adresse adresse;
        if (TestUtil.findAll(em, Adresse.class).isEmpty()) {
            adresse = AdresseResourceIT.createEntity(em);
            em.persist(adresse);
            em.flush();
        } else {
            adresse = TestUtil.findAll(em, Adresse.class).get(0);
        }
        comptable.setAdresse(adresse);
        return comptable;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comptable createUpdatedEntity(EntityManager em) {
        Comptable comptable = new Comptable()
            .civilite(UPDATED_CIVILITE);
        // Add required entity
        Adresse adresse;
        if (TestUtil.findAll(em, Adresse.class).isEmpty()) {
            adresse = AdresseResourceIT.createUpdatedEntity(em);
            em.persist(adresse);
            em.flush();
        } else {
            adresse = TestUtil.findAll(em, Adresse.class).get(0);
        }
        comptable.setAdresse(adresse);
        return comptable;
    }

    @BeforeEach
    public void initTest() {
        comptable = createEntity(em);
    }

    @Test
    @Transactional
    public void createComptable() throws Exception {
        int databaseSizeBeforeCreate = comptableRepository.findAll().size();
        // Create the Comptable
        ComptableDTO comptableDTO = comptableMapper.toDto(comptable);
        restComptableMockMvc.perform(post("/api/comptables")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comptableDTO)))
            .andExpect(status().isCreated());

        // Validate the Comptable in the database
        List<Comptable> comptableList = comptableRepository.findAll();
        assertThat(comptableList).hasSize(databaseSizeBeforeCreate + 1);
        Comptable testComptable = comptableList.get(comptableList.size() - 1);
        assertThat(testComptable.getCivilite()).isEqualTo(DEFAULT_CIVILITE);
    }

    @Test
    @Transactional
    public void createComptableWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = comptableRepository.findAll().size();

        // Create the Comptable with an existing ID
        comptable.setId(1L);
        ComptableDTO comptableDTO = comptableMapper.toDto(comptable);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComptableMockMvc.perform(post("/api/comptables")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comptableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Comptable in the database
        List<Comptable> comptableList = comptableRepository.findAll();
        assertThat(comptableList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllComptables() throws Exception {
        // Initialize the database
        comptableRepository.saveAndFlush(comptable);

        // Get all the comptableList
        restComptableMockMvc.perform(get("/api/comptables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comptable.getId().intValue())))
            .andExpect(jsonPath("$.[*].civilite").value(hasItem(DEFAULT_CIVILITE)));
    }
    
    @Test
    @Transactional
    public void getComptable() throws Exception {
        // Initialize the database
        comptableRepository.saveAndFlush(comptable);

        // Get the comptable
        restComptableMockMvc.perform(get("/api/comptables/{id}", comptable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(comptable.getId().intValue()))
            .andExpect(jsonPath("$.civilite").value(DEFAULT_CIVILITE));
    }
    @Test
    @Transactional
    public void getNonExistingComptable() throws Exception {
        // Get the comptable
        restComptableMockMvc.perform(get("/api/comptables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComptable() throws Exception {
        // Initialize the database
        comptableRepository.saveAndFlush(comptable);

        int databaseSizeBeforeUpdate = comptableRepository.findAll().size();

        // Update the comptable
        Comptable updatedComptable = comptableRepository.findById(comptable.getId()).get();
        // Disconnect from session so that the updates on updatedComptable are not directly saved in db
        em.detach(updatedComptable);
        updatedComptable
            .civilite(UPDATED_CIVILITE);
        ComptableDTO comptableDTO = comptableMapper.toDto(updatedComptable);

        restComptableMockMvc.perform(put("/api/comptables")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comptableDTO)))
            .andExpect(status().isOk());

        // Validate the Comptable in the database
        List<Comptable> comptableList = comptableRepository.findAll();
        assertThat(comptableList).hasSize(databaseSizeBeforeUpdate);
        Comptable testComptable = comptableList.get(comptableList.size() - 1);
        assertThat(testComptable.getCivilite()).isEqualTo(UPDATED_CIVILITE);
    }

    @Test
    @Transactional
    public void updateNonExistingComptable() throws Exception {
        int databaseSizeBeforeUpdate = comptableRepository.findAll().size();

        // Create the Comptable
        ComptableDTO comptableDTO = comptableMapper.toDto(comptable);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComptableMockMvc.perform(put("/api/comptables")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comptableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Comptable in the database
        List<Comptable> comptableList = comptableRepository.findAll();
        assertThat(comptableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComptable() throws Exception {
        // Initialize the database
        comptableRepository.saveAndFlush(comptable);

        int databaseSizeBeforeDelete = comptableRepository.findAll().size();

        // Delete the comptable
        restComptableMockMvc.perform(delete("/api/comptables/{id}", comptable.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Comptable> comptableList = comptableRepository.findAll();
        assertThat(comptableList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
