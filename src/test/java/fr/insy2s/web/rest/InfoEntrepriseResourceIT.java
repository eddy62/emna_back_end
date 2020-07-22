package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.InfoEntreprise;
import fr.insy2s.repository.InfoEntrepriseRepository;
import fr.insy2s.service.InfoEntrepriseService;
import fr.insy2s.service.dto.InfoEntrepriseDTO;
import fr.insy2s.service.mapper.InfoEntrepriseMapper;

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
 * Integration tests for the {@link InfoEntrepriseResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InfoEntrepriseResourceIT {

    private static final String DEFAULT_RAISON_SOCIALE = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_SOCIALE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_FORME_JURIDIQUE = "AAAAAAAAAA";
    private static final String UPDATED_FORME_JURIDIQUE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_DE_CREATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DE_CREATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SIREN = "AAAAAAAAAA";
    private static final String UPDATED_SIREN = "BBBBBBBBBB";

    private static final String DEFAULT_SIRET = "AAAAAAAAAA";
    private static final String UPDATED_SIRET = "BBBBBBBBBB";

    private static final String DEFAULT_DOMAINE_DACTIVITE = "AAAAAAAAAA";
    private static final String UPDATED_DOMAINE_DACTIVITE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private InfoEntrepriseRepository infoEntrepriseRepository;

    @Autowired
    private InfoEntrepriseMapper infoEntrepriseMapper;

    @Autowired
    private InfoEntrepriseService infoEntrepriseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInfoEntrepriseMockMvc;

    private InfoEntreprise infoEntreprise;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InfoEntreprise createEntity(EntityManager em) {
        InfoEntreprise infoEntreprise = new InfoEntreprise()
            .raisonSociale(DEFAULT_RAISON_SOCIALE)
            .telephone(DEFAULT_TELEPHONE)
            .fax(DEFAULT_FAX)
            .formeJuridique(DEFAULT_FORME_JURIDIQUE)
            .dateDeCreation(DEFAULT_DATE_DE_CREATION)
            .siren(DEFAULT_SIREN)
            .siret(DEFAULT_SIRET)
            .domaineDactivite(DEFAULT_DOMAINE_DACTIVITE)
            .description(DEFAULT_DESCRIPTION)
            .email(DEFAULT_EMAIL);
        return infoEntreprise;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InfoEntreprise createUpdatedEntity(EntityManager em) {
        InfoEntreprise infoEntreprise = new InfoEntreprise()
            .raisonSociale(UPDATED_RAISON_SOCIALE)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .formeJuridique(UPDATED_FORME_JURIDIQUE)
            .dateDeCreation(UPDATED_DATE_DE_CREATION)
            .siren(UPDATED_SIREN)
            .siret(UPDATED_SIRET)
            .domaineDactivite(UPDATED_DOMAINE_DACTIVITE)
            .description(UPDATED_DESCRIPTION)
            .email(UPDATED_EMAIL);
        return infoEntreprise;
    }

    @BeforeEach
    public void initTest() {
        infoEntreprise = createEntity(em);
    }

    @Test
    @Transactional
    public void createInfoEntreprise() throws Exception {
        int databaseSizeBeforeCreate = infoEntrepriseRepository.findAll().size();
        // Create the InfoEntreprise
        InfoEntrepriseDTO infoEntrepriseDTO = infoEntrepriseMapper.toDto(infoEntreprise);
        restInfoEntrepriseMockMvc.perform(post("/api/info-entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infoEntrepriseDTO)))
            .andExpect(status().isCreated());

        // Validate the InfoEntreprise in the database
        List<InfoEntreprise> infoEntrepriseList = infoEntrepriseRepository.findAll();
        assertThat(infoEntrepriseList).hasSize(databaseSizeBeforeCreate + 1);
        InfoEntreprise testInfoEntreprise = infoEntrepriseList.get(infoEntrepriseList.size() - 1);
        assertThat(testInfoEntreprise.getRaisonSociale()).isEqualTo(DEFAULT_RAISON_SOCIALE);
        assertThat(testInfoEntreprise.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testInfoEntreprise.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testInfoEntreprise.getFormeJuridique()).isEqualTo(DEFAULT_FORME_JURIDIQUE);
        assertThat(testInfoEntreprise.getDateDeCreation()).isEqualTo(DEFAULT_DATE_DE_CREATION);
        assertThat(testInfoEntreprise.getSiren()).isEqualTo(DEFAULT_SIREN);
        assertThat(testInfoEntreprise.getSiret()).isEqualTo(DEFAULT_SIRET);
        assertThat(testInfoEntreprise.getDomaineDactivite()).isEqualTo(DEFAULT_DOMAINE_DACTIVITE);
        assertThat(testInfoEntreprise.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testInfoEntreprise.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createInfoEntrepriseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = infoEntrepriseRepository.findAll().size();

        // Create the InfoEntreprise with an existing ID
        infoEntreprise.setId(1L);
        InfoEntrepriseDTO infoEntrepriseDTO = infoEntrepriseMapper.toDto(infoEntreprise);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInfoEntrepriseMockMvc.perform(post("/api/info-entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infoEntrepriseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InfoEntreprise in the database
        List<InfoEntreprise> infoEntrepriseList = infoEntrepriseRepository.findAll();
        assertThat(infoEntrepriseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInfoEntreprises() throws Exception {
        // Initialize the database
        infoEntrepriseRepository.saveAndFlush(infoEntreprise);

        // Get all the infoEntrepriseList
        restInfoEntrepriseMockMvc.perform(get("/api/info-entreprises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(infoEntreprise.getId().intValue())))
            .andExpect(jsonPath("$.[*].raisonSociale").value(hasItem(DEFAULT_RAISON_SOCIALE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].formeJuridique").value(hasItem(DEFAULT_FORME_JURIDIQUE)))
            .andExpect(jsonPath("$.[*].dateDeCreation").value(hasItem(DEFAULT_DATE_DE_CREATION.toString())))
            .andExpect(jsonPath("$.[*].siren").value(hasItem(DEFAULT_SIREN)))
            .andExpect(jsonPath("$.[*].siret").value(hasItem(DEFAULT_SIRET)))
            .andExpect(jsonPath("$.[*].domaineDactivite").value(hasItem(DEFAULT_DOMAINE_DACTIVITE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }
    
    @Test
    @Transactional
    public void getInfoEntreprise() throws Exception {
        // Initialize the database
        infoEntrepriseRepository.saveAndFlush(infoEntreprise);

        // Get the infoEntreprise
        restInfoEntrepriseMockMvc.perform(get("/api/info-entreprises/{id}", infoEntreprise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(infoEntreprise.getId().intValue()))
            .andExpect(jsonPath("$.raisonSociale").value(DEFAULT_RAISON_SOCIALE))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.formeJuridique").value(DEFAULT_FORME_JURIDIQUE))
            .andExpect(jsonPath("$.dateDeCreation").value(DEFAULT_DATE_DE_CREATION.toString()))
            .andExpect(jsonPath("$.siren").value(DEFAULT_SIREN))
            .andExpect(jsonPath("$.siret").value(DEFAULT_SIRET))
            .andExpect(jsonPath("$.domaineDactivite").value(DEFAULT_DOMAINE_DACTIVITE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }
    @Test
    @Transactional
    public void getNonExistingInfoEntreprise() throws Exception {
        // Get the infoEntreprise
        restInfoEntrepriseMockMvc.perform(get("/api/info-entreprises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInfoEntreprise() throws Exception {
        // Initialize the database
        infoEntrepriseRepository.saveAndFlush(infoEntreprise);

        int databaseSizeBeforeUpdate = infoEntrepriseRepository.findAll().size();

        // Update the infoEntreprise
        InfoEntreprise updatedInfoEntreprise = infoEntrepriseRepository.findById(infoEntreprise.getId()).get();
        // Disconnect from session so that the updates on updatedInfoEntreprise are not directly saved in db
        em.detach(updatedInfoEntreprise);
        updatedInfoEntreprise
            .raisonSociale(UPDATED_RAISON_SOCIALE)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .formeJuridique(UPDATED_FORME_JURIDIQUE)
            .dateDeCreation(UPDATED_DATE_DE_CREATION)
            .siren(UPDATED_SIREN)
            .siret(UPDATED_SIRET)
            .domaineDactivite(UPDATED_DOMAINE_DACTIVITE)
            .description(UPDATED_DESCRIPTION)
            .email(UPDATED_EMAIL);
        InfoEntrepriseDTO infoEntrepriseDTO = infoEntrepriseMapper.toDto(updatedInfoEntreprise);

        restInfoEntrepriseMockMvc.perform(put("/api/info-entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infoEntrepriseDTO)))
            .andExpect(status().isOk());

        // Validate the InfoEntreprise in the database
        List<InfoEntreprise> infoEntrepriseList = infoEntrepriseRepository.findAll();
        assertThat(infoEntrepriseList).hasSize(databaseSizeBeforeUpdate);
        InfoEntreprise testInfoEntreprise = infoEntrepriseList.get(infoEntrepriseList.size() - 1);
        assertThat(testInfoEntreprise.getRaisonSociale()).isEqualTo(UPDATED_RAISON_SOCIALE);
        assertThat(testInfoEntreprise.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testInfoEntreprise.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testInfoEntreprise.getFormeJuridique()).isEqualTo(UPDATED_FORME_JURIDIQUE);
        assertThat(testInfoEntreprise.getDateDeCreation()).isEqualTo(UPDATED_DATE_DE_CREATION);
        assertThat(testInfoEntreprise.getSiren()).isEqualTo(UPDATED_SIREN);
        assertThat(testInfoEntreprise.getSiret()).isEqualTo(UPDATED_SIRET);
        assertThat(testInfoEntreprise.getDomaineDactivite()).isEqualTo(UPDATED_DOMAINE_DACTIVITE);
        assertThat(testInfoEntreprise.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testInfoEntreprise.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingInfoEntreprise() throws Exception {
        int databaseSizeBeforeUpdate = infoEntrepriseRepository.findAll().size();

        // Create the InfoEntreprise
        InfoEntrepriseDTO infoEntrepriseDTO = infoEntrepriseMapper.toDto(infoEntreprise);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfoEntrepriseMockMvc.perform(put("/api/info-entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infoEntrepriseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InfoEntreprise in the database
        List<InfoEntreprise> infoEntrepriseList = infoEntrepriseRepository.findAll();
        assertThat(infoEntrepriseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInfoEntreprise() throws Exception {
        // Initialize the database
        infoEntrepriseRepository.saveAndFlush(infoEntreprise);

        int databaseSizeBeforeDelete = infoEntrepriseRepository.findAll().size();

        // Delete the infoEntreprise
        restInfoEntrepriseMockMvc.perform(delete("/api/info-entreprises/{id}", infoEntreprise.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InfoEntreprise> infoEntrepriseList = infoEntrepriseRepository.findAll();
        assertThat(infoEntrepriseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
