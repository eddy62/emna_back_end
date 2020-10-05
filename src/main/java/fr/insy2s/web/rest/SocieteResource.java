package fr.insy2s.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import fr.insy2s.domain.Employe;
import fr.insy2s.service.dto.ComptableInfoEntrepriseAdresseUserDTO;
import fr.insy2s.service.dto.EmployeDTO;
import fr.insy2s.service.dto.SocieteInfoEntrepriseAdresseUserDTO;
import fr.insy2s.utils.wrapper.WrapperComptable;
import fr.insy2s.utils.wrapper.WrapperEmploye;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insy2s.service.SocieteService;
import fr.insy2s.service.dto.SocieteDTO;
import fr.insy2s.utils.wrapper.WrapperSociete;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;

/**
 * REST controller for managing {@link fr.insy2s.domain.Societe}.
 */
@RestController
@RequestMapping("/api")
public class SocieteResource {

    private final Logger log = LoggerFactory.getLogger(SocieteResource.class);

    private static final String ENTITY_NAME = "societe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SocieteService societeService;

    public SocieteResource(SocieteService societeService) {
        this.societeService = societeService;
    }



    /**
     * {@code POST  /societes} : Create a new societe.
     *
     * @param societeDTO the societeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new societeDTO, or with status {@code 400 (Bad Request)} if the societe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/societes")
    public ResponseEntity<SocieteDTO> createSociete(@RequestBody SocieteDTO societeDTO) throws URISyntaxException {
        log.debug("REST request to save Societe : {}", societeDTO);
        if (societeDTO.getId() != null) {
            throw new BadRequestAlertException("A new societe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SocieteDTO result = societeService.save(societeDTO);
        return ResponseEntity.created(new URI("/api/societes/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    /**
     * {@code POST  /wrappersociete/add} : Create a new comptable, adresse, infoEntreprise et user.
     *
     * @param societeInfoEntrepriseAdresseUserDTO the wrapperComptable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wrapperSociete, or with status {@code 400 (Bad Request)} if the societe, Adresse, User,infoEntreprise has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wrappersociete/add")
    public ResponseEntity<WrapperSociete> createWrapperSociete(@Valid @RequestBody SocieteInfoEntrepriseAdresseUserDTO societeInfoEntrepriseAdresseUserDTO) throws URISyntaxException {
        log.debug("REST request to save Comptable : {}", societeInfoEntrepriseAdresseUserDTO);
        verificationsSociete(societeInfoEntrepriseAdresseUserDTO,"create");
        WrapperSociete result = societeService.creerOuModifierSociete(societeInfoEntrepriseAdresseUserDTO, "create");
        if (result == null) {
            throw new BadRequestAlertException("Le comptable n'a pas pu être enregistrée.", ENTITY_NAME, "annonce non enregistree");
        } else {
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
        }
    }


    /**
     * {@code PUT  /societes} : Updates an existing societe.
     *
     * @param societeDTO the societeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated societeDTO, or with status {@code 400 (Bad Request)} if the societeDTO is not valid, or with status
     * {@code 500 (Internal Server Error)} if the societeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/societes")
    public ResponseEntity<SocieteDTO> updateSociete(@RequestBody SocieteDTO societeDTO) throws URISyntaxException {
        log.debug("REST request to update Societe : {}", societeDTO);
        if (societeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SocieteDTO result = societeService.save(societeDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, societeDTO.getId().toString())).body(result);
    }


    /**
     * {@code PUT  /wrappersociete/edit} : Updates an existing comptable.
     *
     * @param societeInfoEntrepriseAdresseUserDTO the wrapperComptable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated societeInfoEntrepriseAdresseUserDTO ,
     * or with status {@code 400 (Bad Request)} if the societe, Adreese, User, or InfoEntreprise is not valid,
     * or with status {@code 500 (Internal Server Error)} if the societeInfoEntrepriseAdresseUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wrappersociete/edit")
    public ResponseEntity<WrapperSociete> updateWrapperSociete(@Valid @RequestBody SocieteInfoEntrepriseAdresseUserDTO societeInfoEntrepriseAdresseUserDTO) throws URISyntaxException {
        log.debug("REST request to update Comptable : {}", societeInfoEntrepriseAdresseUserDTO);
        verificationsSociete(societeInfoEntrepriseAdresseUserDTO,"update");
        WrapperSociete result = societeService.creerOuModifierSociete(societeInfoEntrepriseAdresseUserDTO,"update");
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    /**
     * {@code GET  /societes} : get all the societes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of societes in body.
     */
    @GetMapping("/societes")
    public List<SocieteDTO> getAllSocietes() {
        log.debug("REST request to get all Societes");
        return societeService.findAll();
    }


    /**
     * {@code GET  /societes/:id} : get the "id" societe.
     *
     * @param id the id of the societeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the societeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/societes/{id}")
    public ResponseEntity<SocieteDTO> getSociete(@PathVariable Long id) {
        log.debug("REST request to get Societe : {}", id);
        Optional<SocieteDTO> societeDTO = societeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(societeDTO);
    }


    /**
     * {@code GET  /wrappersociete/:id} : get the "id" comptable.
     *
     * @param id the id of the wrapperSociete to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wrapperSociete, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wrappersociete/{id}")
    public ResponseEntity<WrapperSociete> getWrapperSociete(@PathVariable Long id) {
        log.debug("REST request to get Comptable : {}", id);
        Optional<WrapperSociete> result = Optional.ofNullable(societeService.getSociete(id));
        return ResponseUtil.wrapOrNotFound(result);
    }


    @GetMapping("/societes/comptable/{id}")
    public List<SocieteDTO> getAllSocietesByComptableId(@PathVariable Long id) {
        log.debug("REST request to get all Societes of a specific Comptable : {}", id);
        List<SocieteDTO> list = societeService.findAllByComptableId(id);
        return list;
    }

    @GetMapping("/societes/user/{id}")
    public ResponseEntity<SocieteDTO> getSocieteByUserId(@PathVariable Long id) {
        log.debug("REST request to get Societe from user ID : {}", id);
        Optional<SocieteDTO> societeDTO = societeService.findByUser(id);
        return ResponseUtil.wrapOrNotFound(societeDTO);
    }


    /**
     * {@code DELETE  /societes/:id} : delete the "id" societe.
     *
     * @param id the id of the societeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/societes/{id}")
    public ResponseEntity<Void> deleteSociete(@PathVariable Long id) {
        log.debug("REST request to delete Societe : {}", id);
        societeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /*
    /**
     * {@code GET  /wrapperSociete/:id} : get the "id" wrapperSociete.
     *
     * @param id the id of the wrapperSociete to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wrapperSociete, or with status {@code 404 (Not Found)}.
     */
    /*
    @GetMapping("/wrappersociete/{id}")
    public ResponseEntity<WrapperSociete> getWrapperSociete(@PathVariable Long id) {
        log.debug("REST request to get WrapperSociete : {}", id);
        Optional<WrapperSociete> wrapperSociete = societeService.findById(id);
        return ResponseUtil.wrapOrNotFound(wrapperSociete);
    }
    /*
     */

    private void verificationsSociete(SocieteInfoEntrepriseAdresseUserDTO societe, String callingMethode) {
        if (callingMethode.equals("create")) {
            if (societe.getId() != null) {
                throw new BadRequestAlertException("A new comptable cannot already have an ID", ENTITY_NAME, "idexists");
            }
            if (societe.getIdAdresse() != null) {
                throw new BadRequestAlertException("A new adress cannot already have an ID", ENTITY_NAME, "idexists");
            }
            if (societe.getIdInfoEntreprise() != null) {
                throw new BadRequestAlertException("A new infoEntreprise cannot already have an ID", ENTITY_NAME, "idexists");
            }
            if (societe.getIdUser() != null) {
                throw new BadRequestAlertException("A new user cannot already have an ID", ENTITY_NAME, "idexists");
            }
        }
        if (callingMethode.equals("update")) {
            if (societe.getId() == null) {
                throw new BadRequestAlertException("The comptable ID must not be NULL!", ENTITY_NAME, "id not exist");
            }
            if (societe.getIdAdresse() == null) {
                throw new BadRequestAlertException("The adresse ID must not be NULL!", ENTITY_NAME, "id not exist");
            }
            if (societe.getIdInfoEntreprise() == null) {
                throw new BadRequestAlertException("The infoEntreprise ID must not be NULL!", ENTITY_NAME, "id not exist");
            }
            if (societe.getIdUser() == null) {
                throw new BadRequestAlertException("The user ID must not be NULL!", ENTITY_NAME, "id not exist");
            }
        }
        if (callingMethode.equals("create") || callingMethode.equals("update")) {

            if (societe.getAuthorities() == null || "".equals(societe.getAuthorities())) {
                throw new BadRequestAlertException("Authorities is empty !", ENTITY_NAME, "Authorities null");
            }
            if (societe.getLogin() == null || "".equals(societe.getLogin())) {
                throw new BadRequestAlertException("Login is empty !", ENTITY_NAME, "Login null");
            }
            if (societe.getEmail() == null || "".equals(societe.getEmail())) {
                throw new BadRequestAlertException("Email is empty !", ENTITY_NAME, "Email null");
            }
            if (societe.getFirstName() == null || "".equals(societe.getFirstName())) {
                throw new BadRequestAlertException("FirstName is empty! ", ENTITY_NAME, "FirstName  null");
            }
            if (societe.getLastName() == null || "".equals(societe.getLastName())) {
                throw new BadRequestAlertException("LastName is empty", ENTITY_NAME, "LastName null");
            }
            if (societe.getLangKey() == null || "".equals(societe.getLangKey())) {
                throw new BadRequestAlertException("LangKey is empty", ENTITY_NAME, "LangKey null");
            }
            if (societe.getCivilite() == null || "".equals(societe.getCivilite())) {
                throw new BadRequestAlertException("Civilite is empty", ENTITY_NAME, "Civilite null");
            }
            if (societe.getNomRue() == null || "".equals(societe.getNomRue())) {
                throw new BadRequestAlertException("NomRue is empty", ENTITY_NAME, "NomRue null");
            }
            if (societe.getNumeroRue() == null || "".equals(societe.getNumeroRue())) {
                throw new BadRequestAlertException("NumeroRue is empty", ENTITY_NAME, "NumeroRue null");
            }
            if (societe.getVille() == null || "".equals(societe.getVille())) {
                throw new BadRequestAlertException("Ville is empty", ENTITY_NAME, "Ville null");
            }
            if (societe.getCodePostal() == null || "".equals(societe.getCodePostal())) {
                throw new BadRequestAlertException("CodePostal is empty", ENTITY_NAME, "CodePostal null");
            }

            if (societe.getSiren() == null || "".equals(societe.getSiren())) {
                throw new BadRequestAlertException("Siren is empty", ENTITY_NAME, "Siren null");
            }
            if (societe.getRaisonSociale() == null || "".equals(societe.getRaisonSociale())) {
                throw new BadRequestAlertException("RaisonSociale is empty", ENTITY_NAME, "CodePostal null");
            }

        }
    }

    /**
     * {@code GET /employes/society/:id} : get all the wrapperEmployes by society.
     *
     * @param id of the society
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of society's Employes in body.
     */
    @GetMapping("/employes/society/{id}")
    public List<EmployeDTO> getAllEmployesBySociety(@PathVariable Long id) {
        log.debug("REST request to get all Employes by society : {}", id);
        List<EmployeDTO> list = societeService.findAllEmployeBySociete(id);
        return list;
    }
}
