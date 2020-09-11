package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.Prime;
import fr.insy2s.domain.TypePrime;
import fr.insy2s.repository.PrimeRepository;
import fr.insy2s.service.PrimeService;
import fr.insy2s.service.dto.PrimeDTO;
import fr.insy2s.service.mapper.PrimeMapper;

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
 * Integration tests for the {@link PrimeResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrimeResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Double DEFAULT_MONTANT = 1D;
    private static final Double UPDATED_MONTANT = 2D;

    private static final Integer DEFAULT_MOIS = 1;
    private static final Integer UPDATED_MOIS = 2;

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    @Autowired
    private PrimeRepository primeRepository;

    @Autowired
    private PrimeMapper primeMapper;

    @Autowired
    private PrimeService primeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrimeMockMvc;

    private Prime prime;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prime createEntity(EntityManager em) {
        Prime prime = new Prime()
            .montant(DEFAULT_MONTANT)
            .mois(DEFAULT_MOIS)
            .annee(DEFAULT_ANNEE);
        // Add required entity
        TypePrime typePrime;
        if (TestUtil.findAll(em, TypePrime.class).isEmpty()) {
            typePrime = TypePrimeResourceIT.createEntity(em);
            em.persist(typePrime);
            em.flush();
        } else {
            typePrime = TestUtil.findAll(em, TypePrime.class).get(0);
        }
        prime.setTypePrime(typePrime);
        return prime;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prime createUpdatedEntity(EntityManager em) {
        Prime prime = new Prime()
            .montant(UPDATED_MONTANT)
            .mois(UPDATED_MOIS)
            .annee(UPDATED_ANNEE);
        // Add required entity
        TypePrime typePrime;
        if (TestUtil.findAll(em, TypePrime.class).isEmpty()) {
            typePrime = TypePrimeResourceIT.createUpdatedEntity(em);
            em.persist(typePrime);
            em.flush();
        } else {
            typePrime = TestUtil.findAll(em, TypePrime.class).get(0);
        }
        prime.setTypePrime(typePrime);
        return prime;
    }

    @BeforeEach
    public void initTest() {
        prime = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrime() throws Exception {
        int databaseSizeBeforeCreate = primeRepository.findAll().size();
        // Create the Prime
        PrimeDTO primeDTO = primeMapper.toDto(prime);
        restPrimeMockMvc.perform(post("/api/primes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(primeDTO)))
            .andExpect(status().isCreated());

        // Validate the Prime in the database
        List<Prime> primeList = primeRepository.findAll();
        assertThat(primeList).hasSize(databaseSizeBeforeCreate + 1);
        Prime testPrime = primeList.get(primeList.size() - 1);
        assertThat(testPrime.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testPrime.getMois()).isEqualTo(DEFAULT_MOIS);
        assertThat(testPrime.getAnnee()).isEqualTo(DEFAULT_ANNEE);
    }

    @Test
    @Transactional
    public void createPrimeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = primeRepository.findAll().size();

        // Create the Prime with an existing ID
        prime.setId(1L);
        PrimeDTO primeDTO = primeMapper.toDto(prime);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrimeMockMvc.perform(post("/api/primes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(primeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prime in the database
        List<Prime> primeList = primeRepository.findAll();
        assertThat(primeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = primeRepository.findAll().size();

        // Create the Prime, which fails.
        PrimeDTO primeDTO = primeMapper.toDto(prime);


        restPrimeMockMvc.perform(post("/api/primes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(primeDTO)))
            .andExpect(status().isBadRequest());

        List<Prime> primeList = primeRepository.findAll();
        assertThat(primeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantIsRequired() throws Exception {
        int databaseSizeBeforeTest = primeRepository.findAll().size();
        // set the field null
        prime.setMontant(null);

        // Create the Prime, which fails.
        PrimeDTO primeDTO = primeMapper.toDto(prime);


        restPrimeMockMvc.perform(post("/api/primes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(primeDTO)))
            .andExpect(status().isBadRequest());

        List<Prime> primeList = primeRepository.findAll();
        assertThat(primeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMoisIsRequired() throws Exception {
        int databaseSizeBeforeTest = primeRepository.findAll().size();
        // set the field null
        prime.setMois(null);

        // Create the Prime, which fails.
        PrimeDTO primeDTO = primeMapper.toDto(prime);


        restPrimeMockMvc.perform(post("/api/primes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(primeDTO)))
            .andExpect(status().isBadRequest());

        List<Prime> primeList = primeRepository.findAll();
        assertThat(primeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAnneeIsRequired() throws Exception {
        int databaseSizeBeforeTest = primeRepository.findAll().size();
        // set the field null
        prime.setAnnee(null);

        // Create the Prime, which fails.
        PrimeDTO primeDTO = primeMapper.toDto(prime);


        restPrimeMockMvc.perform(post("/api/primes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(primeDTO)))
            .andExpect(status().isBadRequest());

        List<Prime> primeList = primeRepository.findAll();
        assertThat(primeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPrimes() throws Exception {
        // Initialize the database
        primeRepository.saveAndFlush(prime);

        // Get all the primeList
        restPrimeMockMvc.perform(get("/api/primes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prime.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.doubleValue())))
            .andExpect(jsonPath("$.[*].mois").value(hasItem(DEFAULT_MOIS)))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)));
    }

    @Test
    @Transactional
    public void getPrime() throws Exception {
        // Initialize the database
        primeRepository.saveAndFlush(prime);

        // Get the prime
        restPrimeMockMvc.perform(get("/api/primes/{id}", prime.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prime.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.doubleValue()))
            .andExpect(jsonPath("$.mois").value(DEFAULT_MOIS))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE));
    }
    @Test
    @Transactional
    public void getNonExistingPrime() throws Exception {
        // Get the prime
        restPrimeMockMvc.perform(get("/api/primes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrime() throws Exception {
        // Initialize the database
        primeRepository.saveAndFlush(prime);

        int databaseSizeBeforeUpdate = primeRepository.findAll().size();

        // Update the prime
        Prime updatedPrime = primeRepository.findById(prime.getId()).get();
        // Disconnect from session so that the updates on updatedPrime are not directly saved in db
        em.detach(updatedPrime);
        updatedPrime
            .montant(UPDATED_MONTANT)
            .mois(UPDATED_MOIS)
            .annee(UPDATED_ANNEE);
        PrimeDTO primeDTO = primeMapper.toDto(updatedPrime);

        restPrimeMockMvc.perform(put("/api/primes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(primeDTO)))
            .andExpect(status().isOk());

        // Validate the Prime in the database
        List<Prime> primeList = primeRepository.findAll();
        assertThat(primeList).hasSize(databaseSizeBeforeUpdate);
        Prime testPrime = primeList.get(primeList.size() - 1);
        assertThat(testPrime.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testPrime.getMois()).isEqualTo(UPDATED_MOIS);
        assertThat(testPrime.getAnnee()).isEqualTo(UPDATED_ANNEE);
    }

    @Test
    @Transactional
    public void updateNonExistingPrime() throws Exception {
        int databaseSizeBeforeUpdate = primeRepository.findAll().size();

        // Create the Prime
        PrimeDTO primeDTO = primeMapper.toDto(prime);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrimeMockMvc.perform(put("/api/primes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(primeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prime in the database
        List<Prime> primeList = primeRepository.findAll();
        assertThat(primeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrime() throws Exception {
        // Initialize the database
        primeRepository.saveAndFlush(prime);

        int databaseSizeBeforeDelete = primeRepository.findAll().size();

        // Delete the prime
        restPrimeMockMvc.perform(delete("/api/primes/{id}", prime.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Prime> primeList = primeRepository.findAll();
        assertThat(primeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
