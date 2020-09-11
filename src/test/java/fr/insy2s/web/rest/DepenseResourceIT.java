package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.Depense;
import fr.insy2s.repository.DepenseRepository;
import fr.insy2s.service.DepenseService;
import fr.insy2s.service.dto.DepenseDTO;
import fr.insy2s.service.mapper.DepenseMapper;

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
 * Integration tests for the {@link DepenseResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DepenseResourceIT {

    private static final Long DEFAULT_NUMERO = 1L;
    private static final Long UPDATED_NUMERO = 2L;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_PRIX = 1D;
    private static final Double UPDATED_PRIX = 2D;

    private static final String DEFAULT_MOYEN_DE_PAIEMENT = "AAAAAAAAAA";
    private static final String UPDATED_MOYEN_DE_PAIEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_RAISON = "AAAAAAAAAA";
    private static final String UPDATED_RAISON = "BBBBBBBBBB";

    @Autowired
    private DepenseRepository depenseRepository;

    @Autowired
    private DepenseMapper depenseMapper;

    @Autowired
    private DepenseService depenseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDepenseMockMvc;

    private Depense depense;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Depense createEntity(EntityManager em) {
        Depense depense = new Depense()
            .numero(DEFAULT_NUMERO)
            .date(DEFAULT_DATE)
            .prix(DEFAULT_PRIX)
            .moyenDePaiement(DEFAULT_MOYEN_DE_PAIEMENT)
            .raison(DEFAULT_RAISON);
        return depense;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Depense createUpdatedEntity(EntityManager em) {
        Depense depense = new Depense()
            .numero(UPDATED_NUMERO)
            .date(UPDATED_DATE)
            .prix(UPDATED_PRIX)
            .moyenDePaiement(UPDATED_MOYEN_DE_PAIEMENT)
            .raison(UPDATED_RAISON);
        return depense;
    }

    @BeforeEach
    public void initTest() {
        depense = createEntity(em);
    }

    @Test
    @Transactional
    public void createDepense() throws Exception {
        int databaseSizeBeforeCreate = depenseRepository.findAll().size();
        // Create the Depense
        DepenseDTO depenseDTO = depenseMapper.toDto(depense);
        restDepenseMockMvc.perform(post("/api/depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depenseDTO)))
            .andExpect(status().isCreated());

        // Validate the Depense in the database
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeCreate + 1);
        Depense testDepense = depenseList.get(depenseList.size() - 1);
        assertThat(testDepense.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testDepense.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testDepense.getPrix()).isEqualTo(DEFAULT_PRIX);
        assertThat(testDepense.getMoyenDePaiement()).isEqualTo(DEFAULT_MOYEN_DE_PAIEMENT);
        assertThat(testDepense.getRaison()).isEqualTo(DEFAULT_RAISON);
    }

    @Test
    @Transactional
    public void createDepenseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = depenseRepository.findAll().size();

        // Create the Depense with an existing ID
        depense.setId(1L);
        DepenseDTO depenseDTO = depenseMapper.toDto(depense);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepenseMockMvc.perform(post("/api/depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depenseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Depense in the database
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = depenseRepository.findAll().size();
        // set the field null
        depense.setNumero(null);

        // Create the Depense, which fails.
        DepenseDTO depenseDTO = depenseMapper.toDto(depense);


        restDepenseMockMvc.perform(post("/api/depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depenseDTO)))
            .andExpect(status().isBadRequest());

        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDepenses() throws Exception {
        // Initialize the database
        depenseRepository.saveAndFlush(depense);

        // Get all the depenseList
        restDepenseMockMvc.perform(get("/api/depenses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(depense.getId().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].prix").value(hasItem(DEFAULT_PRIX.doubleValue())))
            .andExpect(jsonPath("$.[*].moyenDePaiement").value(hasItem(DEFAULT_MOYEN_DE_PAIEMENT)))
            .andExpect(jsonPath("$.[*].raison").value(hasItem(DEFAULT_RAISON)));
    }
    
    @Test
    @Transactional
    public void getDepense() throws Exception {
        // Initialize the database
        depenseRepository.saveAndFlush(depense);

        // Get the depense
        restDepenseMockMvc.perform(get("/api/depenses/{id}", depense.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(depense.getId().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.prix").value(DEFAULT_PRIX.doubleValue()))
            .andExpect(jsonPath("$.moyenDePaiement").value(DEFAULT_MOYEN_DE_PAIEMENT))
            .andExpect(jsonPath("$.raison").value(DEFAULT_RAISON));
    }
    @Test
    @Transactional
    public void getNonExistingDepense() throws Exception {
        // Get the depense
        restDepenseMockMvc.perform(get("/api/depenses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepense() throws Exception {
        // Initialize the database
        depenseRepository.saveAndFlush(depense);

        int databaseSizeBeforeUpdate = depenseRepository.findAll().size();

        // Update the depense
        Depense updatedDepense = depenseRepository.findById(depense.getId()).get();
        // Disconnect from session so that the updates on updatedDepense are not directly saved in db
        em.detach(updatedDepense);
        updatedDepense
            .numero(UPDATED_NUMERO)
            .date(UPDATED_DATE)
            .prix(UPDATED_PRIX)
            .moyenDePaiement(UPDATED_MOYEN_DE_PAIEMENT)
            .raison(UPDATED_RAISON);
        DepenseDTO depenseDTO = depenseMapper.toDto(updatedDepense);

        restDepenseMockMvc.perform(put("/api/depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depenseDTO)))
            .andExpect(status().isOk());

        // Validate the Depense in the database
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeUpdate);
        Depense testDepense = depenseList.get(depenseList.size() - 1);
        assertThat(testDepense.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testDepense.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testDepense.getPrix()).isEqualTo(UPDATED_PRIX);
        assertThat(testDepense.getMoyenDePaiement()).isEqualTo(UPDATED_MOYEN_DE_PAIEMENT);
        assertThat(testDepense.getRaison()).isEqualTo(UPDATED_RAISON);
    }

    @Test
    @Transactional
    public void updateNonExistingDepense() throws Exception {
        int databaseSizeBeforeUpdate = depenseRepository.findAll().size();

        // Create the Depense
        DepenseDTO depenseDTO = depenseMapper.toDto(depense);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepenseMockMvc.perform(put("/api/depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depenseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Depense in the database
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDepense() throws Exception {
        // Initialize the database
        depenseRepository.saveAndFlush(depense);

        int databaseSizeBeforeDelete = depenseRepository.findAll().size();

        // Delete the depense
        restDepenseMockMvc.perform(delete("/api/depenses/{id}", depense.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
