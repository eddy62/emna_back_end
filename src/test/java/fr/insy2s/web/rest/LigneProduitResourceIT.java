package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.LigneProduit;
import fr.insy2s.domain.Produit;
import fr.insy2s.repository.LigneProduitRepository;
import fr.insy2s.service.LigneProduitService;
import fr.insy2s.service.dto.LigneProduitDTO;
import fr.insy2s.service.mapper.LigneProduitMapper;

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
 * Integration tests for the {@link LigneProduitResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LigneProduitResourceIT {

    private static final Integer DEFAULT_QUANTITE = 1;
    private static final Integer UPDATED_QUANTITE = 2;

    @Autowired
    private LigneProduitRepository ligneProduitRepository;

    @Autowired
    private LigneProduitMapper ligneProduitMapper;

    @Autowired
    private LigneProduitService ligneProduitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLigneProduitMockMvc;

    private LigneProduit ligneProduit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LigneProduit createEntity(EntityManager em) {
        LigneProduit ligneProduit = new LigneProduit()
            .quantite(DEFAULT_QUANTITE);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        ligneProduit.setProduit(produit);
        return ligneProduit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LigneProduit createUpdatedEntity(EntityManager em) {
        LigneProduit ligneProduit = new LigneProduit()
            .quantite(UPDATED_QUANTITE);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createUpdatedEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        ligneProduit.setProduit(produit);
        return ligneProduit;
    }

    @BeforeEach
    public void initTest() {
        ligneProduit = createEntity(em);
    }

    @Test
    @Transactional
    public void createLigneProduit() throws Exception {
        int databaseSizeBeforeCreate = ligneProduitRepository.findAll().size();
        // Create the LigneProduit
        LigneProduitDTO ligneProduitDTO = ligneProduitMapper.toDto(ligneProduit);
        restLigneProduitMockMvc.perform(post("/api/ligne-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneProduitDTO)))
            .andExpect(status().isCreated());

        // Validate the LigneProduit in the database
        List<LigneProduit> ligneProduitList = ligneProduitRepository.findAll();
        assertThat(ligneProduitList).hasSize(databaseSizeBeforeCreate + 1);
        LigneProduit testLigneProduit = ligneProduitList.get(ligneProduitList.size() - 1);
        assertThat(testLigneProduit.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
    }

    @Test
    @Transactional
    public void createLigneProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ligneProduitRepository.findAll().size();

        // Create the LigneProduit with an existing ID
        ligneProduit.setId(1L);
        LigneProduitDTO ligneProduitDTO = ligneProduitMapper.toDto(ligneProduit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLigneProduitMockMvc.perform(post("/api/ligne-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LigneProduit in the database
        List<LigneProduit> ligneProduitList = ligneProduitRepository.findAll();
        assertThat(ligneProduitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkQuantiteIsRequired() throws Exception {
        int databaseSizeBeforeTest = ligneProduitRepository.findAll().size();
        // set the field null
        ligneProduit.setQuantite(null);

        // Create the LigneProduit, which fails.
        LigneProduitDTO ligneProduitDTO = ligneProduitMapper.toDto(ligneProduit);


        restLigneProduitMockMvc.perform(post("/api/ligne-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneProduitDTO)))
            .andExpect(status().isBadRequest());

        List<LigneProduit> ligneProduitList = ligneProduitRepository.findAll();
        assertThat(ligneProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLigneProduits() throws Exception {
        // Initialize the database
        ligneProduitRepository.saveAndFlush(ligneProduit);

        // Get all the ligneProduitList
        restLigneProduitMockMvc.perform(get("/api/ligne-produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ligneProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE)));
    }
    
    @Test
    @Transactional
    public void getLigneProduit() throws Exception {
        // Initialize the database
        ligneProduitRepository.saveAndFlush(ligneProduit);

        // Get the ligneProduit
        restLigneProduitMockMvc.perform(get("/api/ligne-produits/{id}", ligneProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ligneProduit.getId().intValue()))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE));
    }
    @Test
    @Transactional
    public void getNonExistingLigneProduit() throws Exception {
        // Get the ligneProduit
        restLigneProduitMockMvc.perform(get("/api/ligne-produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLigneProduit() throws Exception {
        // Initialize the database
        ligneProduitRepository.saveAndFlush(ligneProduit);

        int databaseSizeBeforeUpdate = ligneProduitRepository.findAll().size();

        // Update the ligneProduit
        LigneProduit updatedLigneProduit = ligneProduitRepository.findById(ligneProduit.getId()).get();
        // Disconnect from session so that the updates on updatedLigneProduit are not directly saved in db
        em.detach(updatedLigneProduit);
        updatedLigneProduit
            .quantite(UPDATED_QUANTITE);
        LigneProduitDTO ligneProduitDTO = ligneProduitMapper.toDto(updatedLigneProduit);

        restLigneProduitMockMvc.perform(put("/api/ligne-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneProduitDTO)))
            .andExpect(status().isOk());

        // Validate the LigneProduit in the database
        List<LigneProduit> ligneProduitList = ligneProduitRepository.findAll();
        assertThat(ligneProduitList).hasSize(databaseSizeBeforeUpdate);
        LigneProduit testLigneProduit = ligneProduitList.get(ligneProduitList.size() - 1);
        assertThat(testLigneProduit.getQuantite()).isEqualTo(UPDATED_QUANTITE);
    }

    @Test
    @Transactional
    public void updateNonExistingLigneProduit() throws Exception {
        int databaseSizeBeforeUpdate = ligneProduitRepository.findAll().size();

        // Create the LigneProduit
        LigneProduitDTO ligneProduitDTO = ligneProduitMapper.toDto(ligneProduit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLigneProduitMockMvc.perform(put("/api/ligne-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LigneProduit in the database
        List<LigneProduit> ligneProduitList = ligneProduitRepository.findAll();
        assertThat(ligneProduitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLigneProduit() throws Exception {
        // Initialize the database
        ligneProduitRepository.saveAndFlush(ligneProduit);

        int databaseSizeBeforeDelete = ligneProduitRepository.findAll().size();

        // Delete the ligneProduit
        restLigneProduitMockMvc.perform(delete("/api/ligne-produits/{id}", ligneProduit.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LigneProduit> ligneProduitList = ligneProduitRepository.findAll();
        assertThat(ligneProduitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
