package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.TypePrime;
import fr.insy2s.repository.TypePrimeRepository;
import fr.insy2s.service.TypePrimeService;
import fr.insy2s.service.dto.TypePrimeDTO;
import fr.insy2s.service.mapper.TypePrimeMapper;

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
 * Integration tests for the {@link TypePrimeResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypePrimeResourceIT {

    private static final String DEFAULT_INTITULE = "AAAAAAAAAA";
    private static final String UPDATED_INTITULE = "BBBBBBBBBB";

    @Autowired
    private TypePrimeRepository typePrimeRepository;

    @Autowired
    private TypePrimeMapper typePrimeMapper;

    @Autowired
    private TypePrimeService typePrimeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypePrimeMockMvc;

    private TypePrime typePrime;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypePrime createEntity(EntityManager em) {
        TypePrime typePrime = new TypePrime()
            .intitule(DEFAULT_INTITULE);
        return typePrime;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypePrime createUpdatedEntity(EntityManager em) {
        TypePrime typePrime = new TypePrime()
            .intitule(UPDATED_INTITULE);
        return typePrime;
    }

    @BeforeEach
    public void initTest() {
        typePrime = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypePrime() throws Exception {
        int databaseSizeBeforeCreate = typePrimeRepository.findAll().size();
        // Create the TypePrime
        TypePrimeDTO typePrimeDTO = typePrimeMapper.toDto(typePrime);
        restTypePrimeMockMvc.perform(post("/api/type-primes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typePrimeDTO)))
            .andExpect(status().isCreated());

        // Validate the TypePrime in the database
        List<TypePrime> typePrimeList = typePrimeRepository.findAll();
        assertThat(typePrimeList).hasSize(databaseSizeBeforeCreate + 1);
        TypePrime testTypePrime = typePrimeList.get(typePrimeList.size() - 1);
        assertThat(testTypePrime.getIntitule()).isEqualTo(DEFAULT_INTITULE);
    }

    @Test
    @Transactional
    public void createTypePrimeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typePrimeRepository.findAll().size();

        // Create the TypePrime with an existing ID
        typePrime.setId(1L);
        TypePrimeDTO typePrimeDTO = typePrimeMapper.toDto(typePrime);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypePrimeMockMvc.perform(post("/api/type-primes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typePrimeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypePrime in the database
        List<TypePrime> typePrimeList = typePrimeRepository.findAll();
        assertThat(typePrimeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIntituleIsRequired() throws Exception {
        int databaseSizeBeforeTest = typePrimeRepository.findAll().size();
        // set the field null
        typePrime.setIntitule(null);

        // Create the TypePrime, which fails.
        TypePrimeDTO typePrimeDTO = typePrimeMapper.toDto(typePrime);


        restTypePrimeMockMvc.perform(post("/api/type-primes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typePrimeDTO)))
            .andExpect(status().isBadRequest());

        List<TypePrime> typePrimeList = typePrimeRepository.findAll();
        assertThat(typePrimeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypePrimes() throws Exception {
        // Initialize the database
        typePrimeRepository.saveAndFlush(typePrime);

        // Get all the typePrimeList
        restTypePrimeMockMvc.perform(get("/api/type-primes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typePrime.getId().intValue())))
            .andExpect(jsonPath("$.[*].intitule").value(hasItem(DEFAULT_INTITULE)));
    }
    
    @Test
    @Transactional
    public void getTypePrime() throws Exception {
        // Initialize the database
        typePrimeRepository.saveAndFlush(typePrime);

        // Get the typePrime
        restTypePrimeMockMvc.perform(get("/api/type-primes/{id}", typePrime.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typePrime.getId().intValue()))
            .andExpect(jsonPath("$.intitule").value(DEFAULT_INTITULE));
    }
    @Test
    @Transactional
    public void getNonExistingTypePrime() throws Exception {
        // Get the typePrime
        restTypePrimeMockMvc.perform(get("/api/type-primes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypePrime() throws Exception {
        // Initialize the database
        typePrimeRepository.saveAndFlush(typePrime);

        int databaseSizeBeforeUpdate = typePrimeRepository.findAll().size();

        // Update the typePrime
        TypePrime updatedTypePrime = typePrimeRepository.findById(typePrime.getId()).get();
        // Disconnect from session so that the updates on updatedTypePrime are not directly saved in db
        em.detach(updatedTypePrime);
        updatedTypePrime
            .intitule(UPDATED_INTITULE);
        TypePrimeDTO typePrimeDTO = typePrimeMapper.toDto(updatedTypePrime);

        restTypePrimeMockMvc.perform(put("/api/type-primes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typePrimeDTO)))
            .andExpect(status().isOk());

        // Validate the TypePrime in the database
        List<TypePrime> typePrimeList = typePrimeRepository.findAll();
        assertThat(typePrimeList).hasSize(databaseSizeBeforeUpdate);
        TypePrime testTypePrime = typePrimeList.get(typePrimeList.size() - 1);
        assertThat(testTypePrime.getIntitule()).isEqualTo(UPDATED_INTITULE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypePrime() throws Exception {
        int databaseSizeBeforeUpdate = typePrimeRepository.findAll().size();

        // Create the TypePrime
        TypePrimeDTO typePrimeDTO = typePrimeMapper.toDto(typePrime);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypePrimeMockMvc.perform(put("/api/type-primes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typePrimeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypePrime in the database
        List<TypePrime> typePrimeList = typePrimeRepository.findAll();
        assertThat(typePrimeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypePrime() throws Exception {
        // Initialize the database
        typePrimeRepository.saveAndFlush(typePrime);

        int databaseSizeBeforeDelete = typePrimeRepository.findAll().size();

        // Delete the typePrime
        restTypePrimeMockMvc.perform(delete("/api/type-primes/{id}", typePrime.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypePrime> typePrimeList = typePrimeRepository.findAll();
        assertThat(typePrimeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
