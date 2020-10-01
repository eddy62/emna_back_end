package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.domain.Employe;
import fr.insy2s.domain.StatutEmploye;
import fr.insy2s.domain.Adresse;
import fr.insy2s.domain.TypeContrat;
import fr.insy2s.repository.EmployeRepository;
import fr.insy2s.service.EmployeService;
import fr.insy2s.service.dto.EmployeDTO;
import fr.insy2s.service.mapper.EmployeMapper;

import liquibase.pro.packaged.B;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EmployeResource} REST controller.
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EmployeResourceIT {

    private static final String DEFAULT_MATRICULE = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE = "BBBBBBBBBB";

    private static final String DEFAULT_CIVILITE = "AAAAAAAAAA";
    private static final String UPDATED_CIVILITE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_NAISSANCE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_NAISSANCE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_USAGE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_USAGE = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_NAISSANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NAISSANCE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_VILLE_NAISSANCE = "AAAAAAAAAA";
    private static final String UPDATED_VILLE_NAISSANCE = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTEMENT_NAISSANCE = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTEMENT_NAISSANCE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYS_NAISANCE = "AAAAAAAAAA";
    private static final String UPDATED_PAYS_NAISANCE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_SECURITE_SOCIALE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_SECURITE_SOCIALE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE_FIX = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_FIX = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE_PORTABLE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_PORTABLE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SALAIRE_HORAIRE = BigDecimal.valueOf(1D);
    private static final BigDecimal UPDATED_SALAIRE_HORAIRE = BigDecimal.valueOf(2D);

    private static final BigDecimal DEFAULT_SALAIRE_BRUT_MENSUELLE = BigDecimal.valueOf(1D);
    private static final BigDecimal UPDATED_SALAIRE_BRUT_MENSUELLE = BigDecimal.valueOf(2D);

    private static final BigDecimal DEFAULT_HEURES_MENSUELLE = BigDecimal.valueOf(1D);
    private static final BigDecimal UPDATED_HEURES_MENSUELLE = BigDecimal.valueOf(2D);

    private static final String DEFAULT_CATEGORIE = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIE = "BBBBBBBBBB";

    private static final String DEFAULT_POSTE = "AAAAAAAAAA";
    private static final String UPDATED_POSTE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_EMBAUCHE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EMBAUCHE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_SORTIE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_SORTIE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_PERIODE_ESSAI = 1D;
    private static final Double UPDATED_PERIODE_ESSAI = 2D;

    private static final String DEFAULT_SITUATION_FAMILIALE = "AAAAAAAAAA";
    private static final String UPDATED_SITUATION_FAMILIALE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ENFANTS_A_CHARGE = 1;
    private static final Integer UPDATED_ENFANTS_A_CHARGE = 2;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EmployeMapper employeMapper;

    @Autowired
    private EmployeService employeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmployeMockMvc;

    private Employe employe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employe createEntity(EntityManager em) {
        Employe employe = new Employe()
            .matricule(DEFAULT_MATRICULE)
            .civilite(DEFAULT_CIVILITE)
            .nomNaissance(DEFAULT_NOM_NAISSANCE)
            .nomUsage(DEFAULT_NOM_USAGE)
            .prenom(DEFAULT_PRENOM)
            .dateNaissance(DEFAULT_DATE_NAISSANCE)
            .villeNaissance(DEFAULT_VILLE_NAISSANCE)
            .departementNaissance(DEFAULT_DEPARTEMENT_NAISSANCE)
            .paysNaisance(DEFAULT_PAYS_NAISANCE)
            .numeroSecuriteSociale(DEFAULT_NUMERO_SECURITE_SOCIALE)
            .email(DEFAULT_EMAIL)
            .telephoneFix(DEFAULT_TELEPHONE_FIX)
            .telephonePortable(DEFAULT_TELEPHONE_PORTABLE)
            .fax(DEFAULT_FAX)
            .salaireHoraire(DEFAULT_SALAIRE_HORAIRE)
            .salaireBrutMensuelle(DEFAULT_SALAIRE_BRUT_MENSUELLE)
            .heuresMensuelle(DEFAULT_HEURES_MENSUELLE)
            .categorie(DEFAULT_CATEGORIE)
            .poste(DEFAULT_POSTE)
            .dateEmbauche(DEFAULT_DATE_EMBAUCHE)
            .dateSortie(DEFAULT_DATE_SORTIE)
            .periodeEssai(DEFAULT_PERIODE_ESSAI)
            .situationFamiliale(DEFAULT_SITUATION_FAMILIALE)
            .enfantsACharge(DEFAULT_ENFANTS_A_CHARGE);
        // Add required entity
        StatutEmploye statutEmploye;
        if (TestUtil.findAll(em, StatutEmploye.class).isEmpty()) {
            statutEmploye = StatutEmployeResourceIT.createEntity(em);
            em.persist(statutEmploye);
            em.flush();
        } else {
            statutEmploye = TestUtil.findAll(em, StatutEmploye.class).get(0);
        }
        employe.setStatutEmploye(statutEmploye);
        // Add required entity
        Adresse adresse;
        if (TestUtil.findAll(em, Adresse.class).isEmpty()) {
            adresse = AdresseResourceIT.createEntity(em);
            em.persist(adresse);
            em.flush();
        } else {
            adresse = TestUtil.findAll(em, Adresse.class).get(0);
        }
        employe.setAdresse(adresse);
        // Add required entity
        TypeContrat typeContrat;
        if (TestUtil.findAll(em, TypeContrat.class).isEmpty()) {
            typeContrat = TypeContratResourceIT.createEntity(em);
            em.persist(typeContrat);
            em.flush();
        } else {
            typeContrat = TestUtil.findAll(em, TypeContrat.class).get(0);
        }
        //employe.setTypeContrat(typeContrat);
        return employe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employe createUpdatedEntity(EntityManager em) {
        Employe employe = new Employe()
            .matricule(UPDATED_MATRICULE)
            .civilite(UPDATED_CIVILITE)
            .nomNaissance(UPDATED_NOM_NAISSANCE)
            .nomUsage(UPDATED_NOM_USAGE)
            .prenom(UPDATED_PRENOM)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .villeNaissance(UPDATED_VILLE_NAISSANCE)
            .departementNaissance(UPDATED_DEPARTEMENT_NAISSANCE)
            .paysNaisance(UPDATED_PAYS_NAISANCE)
            .numeroSecuriteSociale(UPDATED_NUMERO_SECURITE_SOCIALE)
            .email(UPDATED_EMAIL)
            .telephoneFix(UPDATED_TELEPHONE_FIX)
            .telephonePortable(UPDATED_TELEPHONE_PORTABLE)
            .fax(UPDATED_FAX)
            .salaireHoraire(UPDATED_SALAIRE_HORAIRE)
            .salaireBrutMensuelle(UPDATED_SALAIRE_BRUT_MENSUELLE)
            .heuresMensuelle(UPDATED_HEURES_MENSUELLE)
            .categorie(UPDATED_CATEGORIE)
            .poste(UPDATED_POSTE)
            .dateEmbauche(UPDATED_DATE_EMBAUCHE)
            .dateSortie(UPDATED_DATE_SORTIE)
            .periodeEssai(UPDATED_PERIODE_ESSAI)
            .situationFamiliale(UPDATED_SITUATION_FAMILIALE)
            .enfantsACharge(UPDATED_ENFANTS_A_CHARGE);
        // Add required entity
        StatutEmploye statutEmploye;
        if (TestUtil.findAll(em, StatutEmploye.class).isEmpty()) {
            statutEmploye = StatutEmployeResourceIT.createUpdatedEntity(em);
            em.persist(statutEmploye);
            em.flush();
        } else {
            statutEmploye = TestUtil.findAll(em, StatutEmploye.class).get(0);
        }
        employe.setStatutEmploye(statutEmploye);
        // Add required entity
        Adresse adresse;
        if (TestUtil.findAll(em, Adresse.class).isEmpty()) {
            adresse = AdresseResourceIT.createUpdatedEntity(em);
            em.persist(adresse);
            em.flush();
        } else {
            adresse = TestUtil.findAll(em, Adresse.class).get(0);
        }
        employe.setAdresse(adresse);
        // Add required entity
        TypeContrat typeContrat;
        if (TestUtil.findAll(em, TypeContrat.class).isEmpty()) {
            typeContrat = TypeContratResourceIT.createUpdatedEntity(em);
            em.persist(typeContrat);
            em.flush();
        } else {
            typeContrat = TestUtil.findAll(em, TypeContrat.class).get(0);
        }
        //employe.setTypeContrat(typeContrat);
        return employe;
    }

    @BeforeEach
    public void initTest() {
        employe = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmploye() throws Exception {
        int databaseSizeBeforeCreate = employeRepository.findAll().size();
        // Create the Employe
        EmployeDTO employeDTO = employeMapper.toDto(employe);
        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isCreated());

        // Validate the Employe in the database
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeCreate + 1);
        Employe testEmploye = employeList.get(employeList.size() - 1);
        assertThat(testEmploye.getMatricule()).isEqualTo(DEFAULT_MATRICULE);
        assertThat(testEmploye.getCivilite()).isEqualTo(DEFAULT_CIVILITE);
        assertThat(testEmploye.getNomNaissance()).isEqualTo(DEFAULT_NOM_NAISSANCE);
        assertThat(testEmploye.getNomUsage()).isEqualTo(DEFAULT_NOM_USAGE);
        assertThat(testEmploye.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testEmploye.getDateNaissance()).isEqualTo(DEFAULT_DATE_NAISSANCE);
        assertThat(testEmploye.getVilleNaissance()).isEqualTo(DEFAULT_VILLE_NAISSANCE);
        assertThat(testEmploye.getDepartementNaissance()).isEqualTo(DEFAULT_DEPARTEMENT_NAISSANCE);
        assertThat(testEmploye.getPaysNaisance()).isEqualTo(DEFAULT_PAYS_NAISANCE);
        assertThat(testEmploye.getNumeroSecuriteSociale()).isEqualTo(DEFAULT_NUMERO_SECURITE_SOCIALE);
        assertThat(testEmploye.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEmploye.getTelephoneFix()).isEqualTo(DEFAULT_TELEPHONE_FIX);
        assertThat(testEmploye.getTelephonePortable()).isEqualTo(DEFAULT_TELEPHONE_PORTABLE);
        assertThat(testEmploye.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testEmploye.getSalaireHoraire()).isEqualTo(DEFAULT_SALAIRE_HORAIRE);
        assertThat(testEmploye.getSalaireBrutMensuelle()).isEqualTo(DEFAULT_SALAIRE_BRUT_MENSUELLE);
        assertThat(testEmploye.getHeuresMensuelle()).isEqualTo(DEFAULT_HEURES_MENSUELLE);
        assertThat(testEmploye.getCategorie()).isEqualTo(DEFAULT_CATEGORIE);
        assertThat(testEmploye.getPoste()).isEqualTo(DEFAULT_POSTE);
        assertThat(testEmploye.getDateEmbauche()).isEqualTo(DEFAULT_DATE_EMBAUCHE);
        assertThat(testEmploye.getDateSortie()).isEqualTo(DEFAULT_DATE_SORTIE);
        assertThat(testEmploye.getPeriodeEssai()).isEqualTo(DEFAULT_PERIODE_ESSAI);
        assertThat(testEmploye.getSituationFamiliale()).isEqualTo(DEFAULT_SITUATION_FAMILIALE);
        assertThat(testEmploye.getEnfantsACharge()).isEqualTo(DEFAULT_ENFANTS_A_CHARGE);
    }

    @Test
    @Transactional
    public void createEmployeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeRepository.findAll().size();

        // Create the Employe with an existing ID
        employe.setId(1L);
        EmployeDTO employeDTO = employeMapper.toDto(employe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employe in the database
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMatriculeIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setMatricule(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCiviliteIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setCivilite(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomUsageIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setNomUsage(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setPrenom(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateNaissanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setDateNaissance(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVilleNaissanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setVilleNaissance(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaysNaisanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setPaysNaisance(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroSecuriteSocialeIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setNumeroSecuriteSociale(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setEmail(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelephonePortableIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setTelephonePortable(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setFax(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSalaireHoraireIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setSalaireHoraire(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSalaireBrutMensuelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setSalaireBrutMensuelle(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeuresMensuelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setHeuresMensuelle(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategorieIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setCategorie(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosteIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setPoste(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateEmbaucheIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setDateEmbauche(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateSortieIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setDateSortie(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPeriodeEssaiIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setPeriodeEssai(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSituationFamilialeIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setSituationFamiliale(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnfantsAChargeIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setEnfantsACharge(null);

        // Create the Employe, which fails.
        EmployeDTO employeDTO = employeMapper.toDto(employe);


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmployes() throws Exception {
        // Initialize the database
        employeRepository.saveAndFlush(employe);

        // Get all the employeList
        restEmployeMockMvc.perform(get("/api/employes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employe.getId().intValue())))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)))
            .andExpect(jsonPath("$.[*].civilite").value(hasItem(DEFAULT_CIVILITE)))
            .andExpect(jsonPath("$.[*].nomNaissance").value(hasItem(DEFAULT_NOM_NAISSANCE)))
            .andExpect(jsonPath("$.[*].nomUsage").value(hasItem(DEFAULT_NOM_USAGE)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].dateNaissance").value(hasItem(DEFAULT_DATE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].villeNaissance").value(hasItem(DEFAULT_VILLE_NAISSANCE)))
            .andExpect(jsonPath("$.[*].departementNaissance").value(hasItem(DEFAULT_DEPARTEMENT_NAISSANCE)))
            .andExpect(jsonPath("$.[*].paysNaisance").value(hasItem(DEFAULT_PAYS_NAISANCE)))
            .andExpect(jsonPath("$.[*].numeroSecuriteSociale").value(hasItem(DEFAULT_NUMERO_SECURITE_SOCIALE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].telephoneFix").value(hasItem(DEFAULT_TELEPHONE_FIX)))
            .andExpect(jsonPath("$.[*].telephonePortable").value(hasItem(DEFAULT_TELEPHONE_PORTABLE)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].salaireHoraire").value(hasItem(DEFAULT_SALAIRE_HORAIRE.doubleValue())))
            .andExpect(jsonPath("$.[*].salaireBrutMensuelle").value(hasItem(DEFAULT_SALAIRE_BRUT_MENSUELLE.doubleValue())))
            .andExpect(jsonPath("$.[*].heuresMensuelle").value(hasItem(DEFAULT_HEURES_MENSUELLE.doubleValue())))
            .andExpect(jsonPath("$.[*].categorie").value(hasItem(DEFAULT_CATEGORIE)))
            .andExpect(jsonPath("$.[*].poste").value(hasItem(DEFAULT_POSTE)))
            .andExpect(jsonPath("$.[*].dateEmbauche").value(hasItem(DEFAULT_DATE_EMBAUCHE.toString())))
            .andExpect(jsonPath("$.[*].dateSortie").value(hasItem(DEFAULT_DATE_SORTIE.toString())))
            .andExpect(jsonPath("$.[*].periodeEssai").value(hasItem(DEFAULT_PERIODE_ESSAI.doubleValue())))
            .andExpect(jsonPath("$.[*].situationFamiliale").value(hasItem(DEFAULT_SITUATION_FAMILIALE)))
            .andExpect(jsonPath("$.[*].enfantsACharge").value(hasItem(DEFAULT_ENFANTS_A_CHARGE)));
    }

    @Test
    @Transactional
    public void getEmploye() throws Exception {
        // Initialize the database
        employeRepository.saveAndFlush(employe);

        // Get the employe
        restEmployeMockMvc.perform(get("/api/employes/{id}", employe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(employe.getId().intValue()))
            .andExpect(jsonPath("$.matricule").value(DEFAULT_MATRICULE))
            .andExpect(jsonPath("$.civilite").value(DEFAULT_CIVILITE))
            .andExpect(jsonPath("$.nomNaissance").value(DEFAULT_NOM_NAISSANCE))
            .andExpect(jsonPath("$.nomUsage").value(DEFAULT_NOM_USAGE))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.dateNaissance").value(DEFAULT_DATE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.villeNaissance").value(DEFAULT_VILLE_NAISSANCE))
            .andExpect(jsonPath("$.departementNaissance").value(DEFAULT_DEPARTEMENT_NAISSANCE))
            .andExpect(jsonPath("$.paysNaisance").value(DEFAULT_PAYS_NAISANCE))
            .andExpect(jsonPath("$.numeroSecuriteSociale").value(DEFAULT_NUMERO_SECURITE_SOCIALE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.telephoneFix").value(DEFAULT_TELEPHONE_FIX))
            .andExpect(jsonPath("$.telephonePortable").value(DEFAULT_TELEPHONE_PORTABLE))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.salaireHoraire").value(DEFAULT_SALAIRE_HORAIRE.doubleValue()))
            .andExpect(jsonPath("$.salaireBrutMensuelle").value(DEFAULT_SALAIRE_BRUT_MENSUELLE.doubleValue()))
            .andExpect(jsonPath("$.heuresMensuelle").value(DEFAULT_HEURES_MENSUELLE.doubleValue()))
            .andExpect(jsonPath("$.categorie").value(DEFAULT_CATEGORIE))
            .andExpect(jsonPath("$.poste").value(DEFAULT_POSTE))
            .andExpect(jsonPath("$.dateEmbauche").value(DEFAULT_DATE_EMBAUCHE.toString()))
            .andExpect(jsonPath("$.dateSortie").value(DEFAULT_DATE_SORTIE.toString()))
            .andExpect(jsonPath("$.periodeEssai").value(DEFAULT_PERIODE_ESSAI.doubleValue()))
            .andExpect(jsonPath("$.situationFamiliale").value(DEFAULT_SITUATION_FAMILIALE))
            .andExpect(jsonPath("$.enfantsACharge").value(DEFAULT_ENFANTS_A_CHARGE));
    }
    @Test
    @Transactional
    public void getNonExistingEmploye() throws Exception {
        // Get the employe
        restEmployeMockMvc.perform(get("/api/employes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmploye() throws Exception {
        // Initialize the database
        employeRepository.saveAndFlush(employe);

        int databaseSizeBeforeUpdate = employeRepository.findAll().size();

        // Update the employe
        Employe updatedEmploye = employeRepository.findById(employe.getId()).get();
        // Disconnect from session so that the updates on updatedEmploye are not directly saved in db
        em.detach(updatedEmploye);
        updatedEmploye
            .matricule(UPDATED_MATRICULE)
            .civilite(UPDATED_CIVILITE)
            .nomNaissance(UPDATED_NOM_NAISSANCE)
            .nomUsage(UPDATED_NOM_USAGE)
            .prenom(UPDATED_PRENOM)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .villeNaissance(UPDATED_VILLE_NAISSANCE)
            .departementNaissance(UPDATED_DEPARTEMENT_NAISSANCE)
            .paysNaisance(UPDATED_PAYS_NAISANCE)
            .numeroSecuriteSociale(UPDATED_NUMERO_SECURITE_SOCIALE)
            .email(UPDATED_EMAIL)
            .telephoneFix(UPDATED_TELEPHONE_FIX)
            .telephonePortable(UPDATED_TELEPHONE_PORTABLE)
            .fax(UPDATED_FAX)
            .salaireHoraire(UPDATED_SALAIRE_HORAIRE)
            .salaireBrutMensuelle(UPDATED_SALAIRE_BRUT_MENSUELLE)
            .heuresMensuelle(UPDATED_HEURES_MENSUELLE)
            .categorie(UPDATED_CATEGORIE)
            .poste(UPDATED_POSTE)
            .dateEmbauche(UPDATED_DATE_EMBAUCHE)
            .dateSortie(UPDATED_DATE_SORTIE)
            .periodeEssai(UPDATED_PERIODE_ESSAI)
            .situationFamiliale(UPDATED_SITUATION_FAMILIALE)
            .enfantsACharge(UPDATED_ENFANTS_A_CHARGE);
        EmployeDTO employeDTO = employeMapper.toDto(updatedEmploye);

        restEmployeMockMvc.perform(put("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isOk());

        // Validate the Employe in the database
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeUpdate);
        Employe testEmploye = employeList.get(employeList.size() - 1);
        assertThat(testEmploye.getMatricule()).isEqualTo(UPDATED_MATRICULE);
        assertThat(testEmploye.getCivilite()).isEqualTo(UPDATED_CIVILITE);
        assertThat(testEmploye.getNomNaissance()).isEqualTo(UPDATED_NOM_NAISSANCE);
        assertThat(testEmploye.getNomUsage()).isEqualTo(UPDATED_NOM_USAGE);
        assertThat(testEmploye.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testEmploye.getDateNaissance()).isEqualTo(UPDATED_DATE_NAISSANCE);
        assertThat(testEmploye.getVilleNaissance()).isEqualTo(UPDATED_VILLE_NAISSANCE);
        assertThat(testEmploye.getDepartementNaissance()).isEqualTo(UPDATED_DEPARTEMENT_NAISSANCE);
        assertThat(testEmploye.getPaysNaisance()).isEqualTo(UPDATED_PAYS_NAISANCE);
        assertThat(testEmploye.getNumeroSecuriteSociale()).isEqualTo(UPDATED_NUMERO_SECURITE_SOCIALE);
        assertThat(testEmploye.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEmploye.getTelephoneFix()).isEqualTo(UPDATED_TELEPHONE_FIX);
        assertThat(testEmploye.getTelephonePortable()).isEqualTo(UPDATED_TELEPHONE_PORTABLE);
        assertThat(testEmploye.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testEmploye.getSalaireHoraire()).isEqualTo(UPDATED_SALAIRE_HORAIRE);
        assertThat(testEmploye.getSalaireBrutMensuelle()).isEqualTo(UPDATED_SALAIRE_BRUT_MENSUELLE);
        assertThat(testEmploye.getHeuresMensuelle()).isEqualTo(UPDATED_HEURES_MENSUELLE);
        assertThat(testEmploye.getCategorie()).isEqualTo(UPDATED_CATEGORIE);
        assertThat(testEmploye.getPoste()).isEqualTo(UPDATED_POSTE);
        assertThat(testEmploye.getDateEmbauche()).isEqualTo(UPDATED_DATE_EMBAUCHE);
        assertThat(testEmploye.getDateSortie()).isEqualTo(UPDATED_DATE_SORTIE);
        assertThat(testEmploye.getPeriodeEssai()).isEqualTo(UPDATED_PERIODE_ESSAI);
        assertThat(testEmploye.getSituationFamiliale()).isEqualTo(UPDATED_SITUATION_FAMILIALE);
        assertThat(testEmploye.getEnfantsACharge()).isEqualTo(UPDATED_ENFANTS_A_CHARGE);
    }

    @Test
    @Transactional
    public void updateNonExistingEmploye() throws Exception {
        int databaseSizeBeforeUpdate = employeRepository.findAll().size();

        // Create the Employe
        EmployeDTO employeDTO = employeMapper.toDto(employe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeMockMvc.perform(put("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employe in the database
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmploye() throws Exception {
        // Initialize the database
        employeRepository.saveAndFlush(employe);

        int databaseSizeBeforeDelete = employeRepository.findAll().size();

        // Delete the employe
        restEmployeMockMvc.perform(delete("/api/employes/{id}", employe.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
