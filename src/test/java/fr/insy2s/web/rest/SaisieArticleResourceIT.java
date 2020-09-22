package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.SaisieArticle;
import fr.insy2s.repository.SaisieArticleRepository;
import fr.insy2s.service.SaisieArticleService;
import fr.insy2s.service.dto.SaisieArticleDTO;
import fr.insy2s.service.mapper.SaisieArticleMapper;

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
 * Integration tests for the {@link SaisieArticleResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SaisieArticleResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private SaisieArticleRepository saisieArticleRepository;

    @Autowired
    private SaisieArticleMapper saisieArticleMapper;

    @Autowired
    private SaisieArticleService saisieArticleService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSaisieArticleMockMvc;

    private SaisieArticle saisieArticle;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SaisieArticle createEntity(EntityManager em) {
        SaisieArticle saisieArticle = new SaisieArticle()
            .libelle(DEFAULT_LIBELLE);
        return saisieArticle;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SaisieArticle createUpdatedEntity(EntityManager em) {
        SaisieArticle saisieArticle = new SaisieArticle()
            .libelle(UPDATED_LIBELLE);
        return saisieArticle;
    }

    @BeforeEach
    public void initTest() {
        saisieArticle = createEntity(em);
    }

    @Test
    @Transactional
    public void createSaisieArticle() throws Exception {
        int databaseSizeBeforeCreate = saisieArticleRepository.findAll().size();
        // Create the SaisieArticle
        SaisieArticleDTO saisieArticleDTO = saisieArticleMapper.toDto(saisieArticle);
        restSaisieArticleMockMvc.perform(post("/api/saisie-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(saisieArticleDTO)))
            .andExpect(status().isCreated());

        // Validate the SaisieArticle in the database
        List<SaisieArticle> saisieArticleList = saisieArticleRepository.findAll();
        assertThat(saisieArticleList).hasSize(databaseSizeBeforeCreate + 1);
        SaisieArticle testSaisieArticle = saisieArticleList.get(saisieArticleList.size() - 1);
        assertThat(testSaisieArticle.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createSaisieArticleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = saisieArticleRepository.findAll().size();

        // Create the SaisieArticle with an existing ID
        saisieArticle.setId(1L);
        SaisieArticleDTO saisieArticleDTO = saisieArticleMapper.toDto(saisieArticle);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSaisieArticleMockMvc.perform(post("/api/saisie-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(saisieArticleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SaisieArticle in the database
        List<SaisieArticle> saisieArticleList = saisieArticleRepository.findAll();
        assertThat(saisieArticleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = saisieArticleRepository.findAll().size();
        // set the field null
        saisieArticle.setLibelle(null);

        // Create the SaisieArticle, which fails.
        SaisieArticleDTO saisieArticleDTO = saisieArticleMapper.toDto(saisieArticle);


        restSaisieArticleMockMvc.perform(post("/api/saisie-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(saisieArticleDTO)))
            .andExpect(status().isBadRequest());

        List<SaisieArticle> saisieArticleList = saisieArticleRepository.findAll();
        assertThat(saisieArticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSaisieArticles() throws Exception {
        // Initialize the database
        saisieArticleRepository.saveAndFlush(saisieArticle);

        // Get all the saisieArticleList
        restSaisieArticleMockMvc.perform(get("/api/saisie-articles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(saisieArticle.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getSaisieArticle() throws Exception {
        // Initialize the database
        saisieArticleRepository.saveAndFlush(saisieArticle);

        // Get the saisieArticle
        restSaisieArticleMockMvc.perform(get("/api/saisie-articles/{id}", saisieArticle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(saisieArticle.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingSaisieArticle() throws Exception {
        // Get the saisieArticle
        restSaisieArticleMockMvc.perform(get("/api/saisie-articles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSaisieArticle() throws Exception {
        // Initialize the database
        saisieArticleRepository.saveAndFlush(saisieArticle);

        int databaseSizeBeforeUpdate = saisieArticleRepository.findAll().size();

        // Update the saisieArticle
        SaisieArticle updatedSaisieArticle = saisieArticleRepository.findById(saisieArticle.getId()).get();
        // Disconnect from session so that the updates on updatedSaisieArticle are not directly saved in db
        em.detach(updatedSaisieArticle);
        updatedSaisieArticle
            .libelle(UPDATED_LIBELLE);
        SaisieArticleDTO saisieArticleDTO = saisieArticleMapper.toDto(updatedSaisieArticle);

        restSaisieArticleMockMvc.perform(put("/api/saisie-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(saisieArticleDTO)))
            .andExpect(status().isOk());

        // Validate the SaisieArticle in the database
        List<SaisieArticle> saisieArticleList = saisieArticleRepository.findAll();
        assertThat(saisieArticleList).hasSize(databaseSizeBeforeUpdate);
        SaisieArticle testSaisieArticle = saisieArticleList.get(saisieArticleList.size() - 1);
        assertThat(testSaisieArticle.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingSaisieArticle() throws Exception {
        int databaseSizeBeforeUpdate = saisieArticleRepository.findAll().size();

        // Create the SaisieArticle
        SaisieArticleDTO saisieArticleDTO = saisieArticleMapper.toDto(saisieArticle);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSaisieArticleMockMvc.perform(put("/api/saisie-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(saisieArticleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SaisieArticle in the database
        List<SaisieArticle> saisieArticleList = saisieArticleRepository.findAll();
        assertThat(saisieArticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSaisieArticle() throws Exception {
        // Initialize the database
        saisieArticleRepository.saveAndFlush(saisieArticle);

        int databaseSizeBeforeDelete = saisieArticleRepository.findAll().size();

        // Delete the saisieArticle
        restSaisieArticleMockMvc.perform(delete("/api/saisie-articles/{id}", saisieArticle.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SaisieArticle> saisieArticleList = saisieArticleRepository.findAll();
        assertThat(saisieArticleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
