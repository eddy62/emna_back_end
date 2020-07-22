package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.Clause;
import fr.insy2s.repository.ClauseRepository;
import fr.insy2s.service.ClauseService;
import fr.insy2s.service.dto.ClauseDTO;
import fr.insy2s.service.mapper.ClauseMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ClauseResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ClauseResourceIT {

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ClauseRepository clauseRepository;

    @Mock
    private ClauseRepository clauseRepositoryMock;

    @Autowired
    private ClauseMapper clauseMapper;

    @Mock
    private ClauseService clauseServiceMock;

    @Autowired
    private ClauseService clauseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClauseMockMvc;

    private Clause clause;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Clause createEntity(EntityManager em) {
        Clause clause = new Clause()
            .reference(DEFAULT_REFERENCE)
            .description(DEFAULT_DESCRIPTION);
        return clause;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Clause createUpdatedEntity(EntityManager em) {
        Clause clause = new Clause()
            .reference(UPDATED_REFERENCE)
            .description(UPDATED_DESCRIPTION);
        return clause;
    }

    @BeforeEach
    public void initTest() {
        clause = createEntity(em);
    }

    @Test
    @Transactional
    public void createClause() throws Exception {
        int databaseSizeBeforeCreate = clauseRepository.findAll().size();
        // Create the Clause
        ClauseDTO clauseDTO = clauseMapper.toDto(clause);
        restClauseMockMvc.perform(post("/api/clauses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clauseDTO)))
            .andExpect(status().isCreated());

        // Validate the Clause in the database
        List<Clause> clauseList = clauseRepository.findAll();
        assertThat(clauseList).hasSize(databaseSizeBeforeCreate + 1);
        Clause testClause = clauseList.get(clauseList.size() - 1);
        assertThat(testClause.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testClause.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createClauseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clauseRepository.findAll().size();

        // Create the Clause with an existing ID
        clause.setId(1L);
        ClauseDTO clauseDTO = clauseMapper.toDto(clause);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClauseMockMvc.perform(post("/api/clauses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clauseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Clause in the database
        List<Clause> clauseList = clauseRepository.findAll();
        assertThat(clauseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkReferenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = clauseRepository.findAll().size();
        // set the field null
        clause.setReference(null);

        // Create the Clause, which fails.
        ClauseDTO clauseDTO = clauseMapper.toDto(clause);


        restClauseMockMvc.perform(post("/api/clauses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clauseDTO)))
            .andExpect(status().isBadRequest());

        List<Clause> clauseList = clauseRepository.findAll();
        assertThat(clauseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = clauseRepository.findAll().size();
        // set the field null
        clause.setDescription(null);

        // Create the Clause, which fails.
        ClauseDTO clauseDTO = clauseMapper.toDto(clause);


        restClauseMockMvc.perform(post("/api/clauses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clauseDTO)))
            .andExpect(status().isBadRequest());

        List<Clause> clauseList = clauseRepository.findAll();
        assertThat(clauseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllClauses() throws Exception {
        // Initialize the database
        clauseRepository.saveAndFlush(clause);

        // Get all the clauseList
        restClauseMockMvc.perform(get("/api/clauses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clause.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllClausesWithEagerRelationshipsIsEnabled() throws Exception {
        when(clauseServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restClauseMockMvc.perform(get("/api/clauses?eagerload=true"))
            .andExpect(status().isOk());

        verify(clauseServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllClausesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(clauseServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restClauseMockMvc.perform(get("/api/clauses?eagerload=true"))
            .andExpect(status().isOk());

        verify(clauseServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getClause() throws Exception {
        // Initialize the database
        clauseRepository.saveAndFlush(clause);

        // Get the clause
        restClauseMockMvc.perform(get("/api/clauses/{id}", clause.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clause.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingClause() throws Exception {
        // Get the clause
        restClauseMockMvc.perform(get("/api/clauses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClause() throws Exception {
        // Initialize the database
        clauseRepository.saveAndFlush(clause);

        int databaseSizeBeforeUpdate = clauseRepository.findAll().size();

        // Update the clause
        Clause updatedClause = clauseRepository.findById(clause.getId()).get();
        // Disconnect from session so that the updates on updatedClause are not directly saved in db
        em.detach(updatedClause);
        updatedClause
            .reference(UPDATED_REFERENCE)
            .description(UPDATED_DESCRIPTION);
        ClauseDTO clauseDTO = clauseMapper.toDto(updatedClause);

        restClauseMockMvc.perform(put("/api/clauses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clauseDTO)))
            .andExpect(status().isOk());

        // Validate the Clause in the database
        List<Clause> clauseList = clauseRepository.findAll();
        assertThat(clauseList).hasSize(databaseSizeBeforeUpdate);
        Clause testClause = clauseList.get(clauseList.size() - 1);
        assertThat(testClause.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testClause.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingClause() throws Exception {
        int databaseSizeBeforeUpdate = clauseRepository.findAll().size();

        // Create the Clause
        ClauseDTO clauseDTO = clauseMapper.toDto(clause);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClauseMockMvc.perform(put("/api/clauses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clauseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Clause in the database
        List<Clause> clauseList = clauseRepository.findAll();
        assertThat(clauseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClause() throws Exception {
        // Initialize the database
        clauseRepository.saveAndFlush(clause);

        int databaseSizeBeforeDelete = clauseRepository.findAll().size();

        // Delete the clause
        restClauseMockMvc.perform(delete("/api/clauses/{id}", clause.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Clause> clauseList = clauseRepository.findAll();
        assertThat(clauseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
