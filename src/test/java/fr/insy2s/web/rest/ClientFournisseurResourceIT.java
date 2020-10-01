package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.ClientFournisseur;
import fr.insy2s.repository.ClientFournisseurRepository;
import fr.insy2s.service.ClientFournisseurService;
import fr.insy2s.service.dto.ClientFournisseurDTO;
import fr.insy2s.service.mapper.ClientFournisseurMapper;

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
 * Integration tests for the {@link ClientFournisseurResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ClientFournisseurResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_SIREN = "aasasa";
    private static final String UPDATED_SIREN = "sSSXSQX";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private ClientFournisseurRepository clientFournisseurRepository;

    @Autowired
    private ClientFournisseurMapper clientFournisseurMapper;

    @Autowired
    private ClientFournisseurService clientFournisseurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClientFournisseurMockMvc;

    private ClientFournisseur clientFournisseur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientFournisseur createEntity(EntityManager em) {
        ClientFournisseur clientFournisseur = new ClientFournisseur()
            .nom(DEFAULT_NOM)
            .siret(DEFAULT_SIREN)
            .telephone(DEFAULT_TELEPHONE)
            .email(DEFAULT_EMAIL);
        return clientFournisseur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientFournisseur createUpdatedEntity(EntityManager em) {
        ClientFournisseur clientFournisseur = new ClientFournisseur()
            .nom(UPDATED_NOM)
            .siret(UPDATED_SIREN)
            .telephone(UPDATED_TELEPHONE)
            .email(UPDATED_EMAIL);
        return clientFournisseur;
    }

    @BeforeEach
    public void initTest() {
        clientFournisseur = createEntity(em);
    }

    @Test
    @Transactional
    public void createClientFournisseur() throws Exception {
        int databaseSizeBeforeCreate = clientFournisseurRepository.findAll().size();
        // Create the ClientFournisseur
        ClientFournisseurDTO clientFournisseurDTO = clientFournisseurMapper.toDto(clientFournisseur);
        restClientFournisseurMockMvc.perform(post("/api/client-fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientFournisseurDTO)))
            .andExpect(status().isCreated());

        // Validate the ClientFournisseur in the database
        List<ClientFournisseur> clientFournisseurList = clientFournisseurRepository.findAll();
        assertThat(clientFournisseurList).hasSize(databaseSizeBeforeCreate + 1);
        ClientFournisseur testClientFournisseur = clientFournisseurList.get(clientFournisseurList.size() - 1);
        assertThat(testClientFournisseur.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testClientFournisseur.getSiret()).isEqualTo(DEFAULT_SIREN);
        assertThat(testClientFournisseur.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testClientFournisseur.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createClientFournisseurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clientFournisseurRepository.findAll().size();

        // Create the ClientFournisseur with an existing ID
        clientFournisseur.setId(1L);
        ClientFournisseurDTO clientFournisseurDTO = clientFournisseurMapper.toDto(clientFournisseur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientFournisseurMockMvc.perform(post("/api/client-fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientFournisseurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClientFournisseur in the database
        List<ClientFournisseur> clientFournisseurList = clientFournisseurRepository.findAll();
        assertThat(clientFournisseurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClientFournisseurs() throws Exception {
        // Initialize the database
        clientFournisseurRepository.saveAndFlush(clientFournisseur);

        // Get all the clientFournisseurList
        restClientFournisseurMockMvc.perform(get("/api/client-fournisseurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientFournisseur.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].siren").value(hasItem(DEFAULT_SIREN)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }

    @Test
    @Transactional
    public void getClientFournisseur() throws Exception {
        // Initialize the database
        clientFournisseurRepository.saveAndFlush(clientFournisseur);

        // Get the clientFournisseur
        restClientFournisseurMockMvc.perform(get("/api/client-fournisseurs/{id}", clientFournisseur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clientFournisseur.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.siren").value(DEFAULT_SIREN))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }
    @Test
    @Transactional
    public void getNonExistingClientFournisseur() throws Exception {
        // Get the clientFournisseur
        restClientFournisseurMockMvc.perform(get("/api/client-fournisseurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClientFournisseur() throws Exception {
        // Initialize the database
        clientFournisseurRepository.saveAndFlush(clientFournisseur);

        int databaseSizeBeforeUpdate = clientFournisseurRepository.findAll().size();

        // Update the clientFournisseur
        ClientFournisseur updatedClientFournisseur = clientFournisseurRepository.findById(clientFournisseur.getId()).get();
        // Disconnect from session so that the updates on updatedClientFournisseur are not directly saved in db
        em.detach(updatedClientFournisseur);
        updatedClientFournisseur
            .nom(UPDATED_NOM)
            .siret(UPDATED_SIREN)
            .telephone(UPDATED_TELEPHONE)
            .email(UPDATED_EMAIL);
        ClientFournisseurDTO clientFournisseurDTO = clientFournisseurMapper.toDto(updatedClientFournisseur);

        restClientFournisseurMockMvc.perform(put("/api/client-fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientFournisseurDTO)))
            .andExpect(status().isOk());

        // Validate the ClientFournisseur in the database
        List<ClientFournisseur> clientFournisseurList = clientFournisseurRepository.findAll();
        assertThat(clientFournisseurList).hasSize(databaseSizeBeforeUpdate);
        ClientFournisseur testClientFournisseur = clientFournisseurList.get(clientFournisseurList.size() - 1);
        assertThat(testClientFournisseur.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testClientFournisseur.getSiret()).isEqualTo(UPDATED_SIREN);
        assertThat(testClientFournisseur.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testClientFournisseur.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingClientFournisseur() throws Exception {
        int databaseSizeBeforeUpdate = clientFournisseurRepository.findAll().size();

        // Create the ClientFournisseur
        ClientFournisseurDTO clientFournisseurDTO = clientFournisseurMapper.toDto(clientFournisseur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientFournisseurMockMvc.perform(put("/api/client-fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientFournisseurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClientFournisseur in the database
        List<ClientFournisseur> clientFournisseurList = clientFournisseurRepository.findAll();
        assertThat(clientFournisseurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClientFournisseur() throws Exception {
        // Initialize the database
        clientFournisseurRepository.saveAndFlush(clientFournisseur);

        int databaseSizeBeforeDelete = clientFournisseurRepository.findAll().size();

        // Delete the clientFournisseur
        restClientFournisseurMockMvc.perform(delete("/api/client-fournisseurs/{id}", clientFournisseur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClientFournisseur> clientFournisseurList = clientFournisseurRepository.findAll();
        assertThat(clientFournisseurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
