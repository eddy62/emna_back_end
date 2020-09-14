package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.TypeContrat;
import fr.insy2s.repository.TypeContratRepository;
import fr.insy2s.service.TypeContratService;
import fr.insy2s.service.dto.TypeContratDTO;
import fr.insy2s.service.mapper.TypeContratMapper;

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
 * Integration tests for the {@link TypeContratResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeContratResourceIT {

    private static final String DEFAULT_CODE_REF = "AAAAAAAAAA";
    private static final String UPDATED_CODE_REF = "BBBBBBBBBB";

    private static final String DEFAULT_INTITULE = "AAAAAAAAAA";
    private static final String UPDATED_INTITULE = "BBBBBBBBBB";

    @Autowired
    private TypeContratRepository typeContratRepository;

    @Autowired
    private TypeContratMapper typeContratMapper;

    @Autowired
    private TypeContratService typeContratService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeContratMockMvc;

    private TypeContrat typeContrat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeContrat createEntity(EntityManager em) {
        TypeContrat typeContrat = new TypeContrat()
            .codeRef(DEFAULT_CODE_REF)
            .intitule(DEFAULT_INTITULE);
        return typeContrat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeContrat createUpdatedEntity(EntityManager em) {
        TypeContrat typeContrat = new TypeContrat()
            .codeRef(UPDATED_CODE_REF)
            .intitule(UPDATED_INTITULE);
        return typeContrat;
    }

    @BeforeEach
    public void initTest() {
        typeContrat = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeContrat() throws Exception {
        int databaseSizeBeforeCreate = typeContratRepository.findAll().size();
        // Create the TypeContrat
        TypeContratDTO typeContratDTO = typeContratMapper.toDto(typeContrat);
        restTypeContratMockMvc.perform(post("/api/type-contrats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeContratDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeContrat in the database
        List<TypeContrat> typeContratList = typeContratRepository.findAll();
        assertThat(typeContratList).hasSize(databaseSizeBeforeCreate + 1);
        TypeContrat testTypeContrat = typeContratList.get(typeContratList.size() - 1);
        assertThat(testTypeContrat.getCodeRef()).isEqualTo(DEFAULT_CODE_REF);
        assertThat(testTypeContrat.getIntitule()).isEqualTo(DEFAULT_INTITULE);
    }

    @Test
    @Transactional
    public void createTypeContratWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeContratRepository.findAll().size();

        // Create the TypeContrat with an existing ID
        typeContrat.setId(1L);
        TypeContratDTO typeContratDTO = typeContratMapper.toDto(typeContrat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeContratMockMvc.perform(post("/api/type-contrats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeContratDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeContrat in the database
        List<TypeContrat> typeContratList = typeContratRepository.findAll();
        assertThat(typeContratList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeContratRepository.findAll().size();
        // set the field null
        typeContrat.setCodeRef(null);

        // Create the TypeContrat, which fails.
        TypeContratDTO typeContratDTO = typeContratMapper.toDto(typeContrat);


        restTypeContratMockMvc.perform(post("/api/type-contrats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeContratDTO)))
            .andExpect(status().isBadRequest());

        List<TypeContrat> typeContratList = typeContratRepository.findAll();
        assertThat(typeContratList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIntituleIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeContratRepository.findAll().size();
        // set the field null
        typeContrat.setIntitule(null);

        // Create the TypeContrat, which fails.
        TypeContratDTO typeContratDTO = typeContratMapper.toDto(typeContrat);


        restTypeContratMockMvc.perform(post("/api/type-contrats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeContratDTO)))
            .andExpect(status().isBadRequest());

        List<TypeContrat> typeContratList = typeContratRepository.findAll();
        assertThat(typeContratList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeContrats() throws Exception {
        // Initialize the database
        typeContratRepository.saveAndFlush(typeContrat);

        // Get all the typeContratList
        restTypeContratMockMvc.perform(get("/api/type-contrats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeContrat.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeRef").value(hasItem(DEFAULT_CODE_REF)))
            .andExpect(jsonPath("$.[*].intitule").value(hasItem(DEFAULT_INTITULE)));
    }
    
    @Test
    @Transactional
    public void getTypeContrat() throws Exception {
        // Initialize the database
        typeContratRepository.saveAndFlush(typeContrat);

        // Get the typeContrat
        restTypeContratMockMvc.perform(get("/api/type-contrats/{id}", typeContrat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeContrat.getId().intValue()))
            .andExpect(jsonPath("$.codeRef").value(DEFAULT_CODE_REF))
            .andExpect(jsonPath("$.intitule").value(DEFAULT_INTITULE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeContrat() throws Exception {
        // Get the typeContrat
        restTypeContratMockMvc.perform(get("/api/type-contrats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeContrat() throws Exception {
        // Initialize the database
        typeContratRepository.saveAndFlush(typeContrat);

        int databaseSizeBeforeUpdate = typeContratRepository.findAll().size();

        // Update the typeContrat
        TypeContrat updatedTypeContrat = typeContratRepository.findById(typeContrat.getId()).get();
        // Disconnect from session so that the updates on updatedTypeContrat are not directly saved in db
        em.detach(updatedTypeContrat);
        updatedTypeContrat
            .codeRef(UPDATED_CODE_REF)
            .intitule(UPDATED_INTITULE);
        TypeContratDTO typeContratDTO = typeContratMapper.toDto(updatedTypeContrat);

        restTypeContratMockMvc.perform(put("/api/type-contrats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeContratDTO)))
            .andExpect(status().isOk());

        // Validate the TypeContrat in the database
        List<TypeContrat> typeContratList = typeContratRepository.findAll();
        assertThat(typeContratList).hasSize(databaseSizeBeforeUpdate);
        TypeContrat testTypeContrat = typeContratList.get(typeContratList.size() - 1);
        assertThat(testTypeContrat.getCodeRef()).isEqualTo(UPDATED_CODE_REF);
        assertThat(testTypeContrat.getIntitule()).isEqualTo(UPDATED_INTITULE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeContrat() throws Exception {
        int databaseSizeBeforeUpdate = typeContratRepository.findAll().size();

        // Create the TypeContrat
        TypeContratDTO typeContratDTO = typeContratMapper.toDto(typeContrat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeContratMockMvc.perform(put("/api/type-contrats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeContratDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeContrat in the database
        List<TypeContrat> typeContratList = typeContratRepository.findAll();
        assertThat(typeContratList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeContrat() throws Exception {
        // Initialize the database
        typeContratRepository.saveAndFlush(typeContrat);

        int databaseSizeBeforeDelete = typeContratRepository.findAll().size();

        // Delete the typeContrat
        restTypeContratMockMvc.perform(delete("/api/type-contrats/{id}", typeContrat.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeContrat> typeContratList = typeContratRepository.findAll();
        assertThat(typeContratList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
