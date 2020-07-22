package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.TypeAbsence;
import fr.insy2s.repository.TypeAbsenceRepository;
import fr.insy2s.service.TypeAbsenceService;
import fr.insy2s.service.dto.TypeAbsenceDTO;
import fr.insy2s.service.mapper.TypeAbsenceMapper;

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
 * Integration tests for the {@link TypeAbsenceResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeAbsenceResourceIT {

    private static final String DEFAULT_INTITULE = "AAAAAAAAAA";
    private static final String UPDATED_INTITULE = "BBBBBBBBBB";

    @Autowired
    private TypeAbsenceRepository typeAbsenceRepository;

    @Autowired
    private TypeAbsenceMapper typeAbsenceMapper;

    @Autowired
    private TypeAbsenceService typeAbsenceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeAbsenceMockMvc;

    private TypeAbsence typeAbsence;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeAbsence createEntity(EntityManager em) {
        TypeAbsence typeAbsence = new TypeAbsence()
            .intitule(DEFAULT_INTITULE);
        return typeAbsence;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeAbsence createUpdatedEntity(EntityManager em) {
        TypeAbsence typeAbsence = new TypeAbsence()
            .intitule(UPDATED_INTITULE);
        return typeAbsence;
    }

    @BeforeEach
    public void initTest() {
        typeAbsence = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeAbsence() throws Exception {
        int databaseSizeBeforeCreate = typeAbsenceRepository.findAll().size();
        // Create the TypeAbsence
        TypeAbsenceDTO typeAbsenceDTO = typeAbsenceMapper.toDto(typeAbsence);
        restTypeAbsenceMockMvc.perform(post("/api/type-absences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeAbsenceDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeAbsence in the database
        List<TypeAbsence> typeAbsenceList = typeAbsenceRepository.findAll();
        assertThat(typeAbsenceList).hasSize(databaseSizeBeforeCreate + 1);
        TypeAbsence testTypeAbsence = typeAbsenceList.get(typeAbsenceList.size() - 1);
        assertThat(testTypeAbsence.getIntitule()).isEqualTo(DEFAULT_INTITULE);
    }

    @Test
    @Transactional
    public void createTypeAbsenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeAbsenceRepository.findAll().size();

        // Create the TypeAbsence with an existing ID
        typeAbsence.setId(1L);
        TypeAbsenceDTO typeAbsenceDTO = typeAbsenceMapper.toDto(typeAbsence);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeAbsenceMockMvc.perform(post("/api/type-absences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeAbsenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeAbsence in the database
        List<TypeAbsence> typeAbsenceList = typeAbsenceRepository.findAll();
        assertThat(typeAbsenceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIntituleIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeAbsenceRepository.findAll().size();
        // set the field null
        typeAbsence.setIntitule(null);

        // Create the TypeAbsence, which fails.
        TypeAbsenceDTO typeAbsenceDTO = typeAbsenceMapper.toDto(typeAbsence);


        restTypeAbsenceMockMvc.perform(post("/api/type-absences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeAbsenceDTO)))
            .andExpect(status().isBadRequest());

        List<TypeAbsence> typeAbsenceList = typeAbsenceRepository.findAll();
        assertThat(typeAbsenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeAbsences() throws Exception {
        // Initialize the database
        typeAbsenceRepository.saveAndFlush(typeAbsence);

        // Get all the typeAbsenceList
        restTypeAbsenceMockMvc.perform(get("/api/type-absences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeAbsence.getId().intValue())))
            .andExpect(jsonPath("$.[*].intitule").value(hasItem(DEFAULT_INTITULE)));
    }
    
    @Test
    @Transactional
    public void getTypeAbsence() throws Exception {
        // Initialize the database
        typeAbsenceRepository.saveAndFlush(typeAbsence);

        // Get the typeAbsence
        restTypeAbsenceMockMvc.perform(get("/api/type-absences/{id}", typeAbsence.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeAbsence.getId().intValue()))
            .andExpect(jsonPath("$.intitule").value(DEFAULT_INTITULE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeAbsence() throws Exception {
        // Get the typeAbsence
        restTypeAbsenceMockMvc.perform(get("/api/type-absences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeAbsence() throws Exception {
        // Initialize the database
        typeAbsenceRepository.saveAndFlush(typeAbsence);

        int databaseSizeBeforeUpdate = typeAbsenceRepository.findAll().size();

        // Update the typeAbsence
        TypeAbsence updatedTypeAbsence = typeAbsenceRepository.findById(typeAbsence.getId()).get();
        // Disconnect from session so that the updates on updatedTypeAbsence are not directly saved in db
        em.detach(updatedTypeAbsence);
        updatedTypeAbsence
            .intitule(UPDATED_INTITULE);
        TypeAbsenceDTO typeAbsenceDTO = typeAbsenceMapper.toDto(updatedTypeAbsence);

        restTypeAbsenceMockMvc.perform(put("/api/type-absences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeAbsenceDTO)))
            .andExpect(status().isOk());

        // Validate the TypeAbsence in the database
        List<TypeAbsence> typeAbsenceList = typeAbsenceRepository.findAll();
        assertThat(typeAbsenceList).hasSize(databaseSizeBeforeUpdate);
        TypeAbsence testTypeAbsence = typeAbsenceList.get(typeAbsenceList.size() - 1);
        assertThat(testTypeAbsence.getIntitule()).isEqualTo(UPDATED_INTITULE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeAbsence() throws Exception {
        int databaseSizeBeforeUpdate = typeAbsenceRepository.findAll().size();

        // Create the TypeAbsence
        TypeAbsenceDTO typeAbsenceDTO = typeAbsenceMapper.toDto(typeAbsence);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeAbsenceMockMvc.perform(put("/api/type-absences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeAbsenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeAbsence in the database
        List<TypeAbsence> typeAbsenceList = typeAbsenceRepository.findAll();
        assertThat(typeAbsenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeAbsence() throws Exception {
        // Initialize the database
        typeAbsenceRepository.saveAndFlush(typeAbsence);

        int databaseSizeBeforeDelete = typeAbsenceRepository.findAll().size();

        // Delete the typeAbsence
        restTypeAbsenceMockMvc.perform(delete("/api/type-absences/{id}", typeAbsence.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeAbsence> typeAbsenceList = typeAbsenceRepository.findAll();
        assertThat(typeAbsenceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
