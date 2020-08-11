package fr.insy2s.web.rest;

import fr.insy2s.domain.Comptable;
import fr.insy2s.repository.ComptableRepository;
import fr.insy2s.service.ComptableService;
import fr.insy2s.service.dto.ComptableInfoEntrepriseAdresseUserDTO;
import fr.insy2s.service.mapper.ComptableMapper;
import fr.insy2s.utils.wrapper.WrapperComptable;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.ComptableDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.Comptable}.
 */
@RestController
@RequestMapping("/api")
public class ComptableResource {

    private final Logger log = LoggerFactory.getLogger(ComptableResource.class);

    private static final String ENTITY_NAME = "comptable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComptableService comptableService;
    private  final  ComptableRepository comptableRepository;
    private  final ComptableMapper comptableMapper;

    public ComptableResource(ComptableService comptableService, ComptableRepository comptableRepository, ComptableMapper comptableMapper) {
        this.comptableService = comptableService;
        this.comptableRepository = comptableRepository;
        this.comptableMapper = comptableMapper;
    }

/*    *//**
     * {@code POST  /comptables} : Create a new comptable.
     *
     * @param comptableDTO the comptableDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new comptableDTO, or with status {@code 400 (Bad Request)} if the comptable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*
    @PostMapping("/comptables/add")
    public ResponseEntity<ComptableDTO> createComptable(@Valid @RequestBody ComptableDTO comptableDTO) throws URISyntaxException {
        log.debug("REST request to save Comptable : {}", comptableDTO);
        if (comptableDTO.getId() != null) {
            throw new BadRequestAlertException("A new comptable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComptableDTO result = comptableService.save(comptableDTO);
        return ResponseEntity.created(new URI("/api/comptables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }*/

    /**
     * {@code POST  /comptables} : Create a new comptable.
     *
     * @param comptableInfoEntrepriseAdresseUserDTO the comptableDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new comptableDTO, or with status {@code 400 (Bad Request)} if the comptable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comptables/add")
    public ResponseEntity<WrapperComptable> createComptable(@Valid @RequestBody ComptableInfoEntrepriseAdresseUserDTO comptableInfoEntrepriseAdresseUserDTO) throws URISyntaxException {
        log.debug("REST request to save Comptable : {}", comptableInfoEntrepriseAdresseUserDTO);
        verificationsComptable(comptableInfoEntrepriseAdresseUserDTO,"create");
        WrapperComptable result = comptableService.creerOuModifierComptable(comptableInfoEntrepriseAdresseUserDTO,"create");
        if (result == null) {
            throw new BadRequestAlertException("Le comptable n'a pas pu être enregistrée.", ENTITY_NAME, "annonce non enregistree");
        } else {
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
        }
    }



    /**
     * {@code PUT  /comptables} : Updates an existing comptable.
     *
     * @param comptableInfoEntrepriseAdresseUserDTO the comptableDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comptableDTO,
     * or with status {@code 400 (Bad Request)} if the comptableDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the comptableDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/comptables/edit")
    public ResponseEntity<WrapperComptable> updateComptable(@Valid @RequestBody ComptableInfoEntrepriseAdresseUserDTO comptableInfoEntrepriseAdresseUserDTO) throws URISyntaxException {
        log.debug("REST request to update Comptable : {}", comptableInfoEntrepriseAdresseUserDTO);
        verificationsComptable(comptableInfoEntrepriseAdresseUserDTO,"update");
        WrapperComptable result = comptableService.creerOuModifierComptable(comptableInfoEntrepriseAdresseUserDTO,"update");
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /*
        @PutMapping("/comptables")
        public ResponseEntity<ComptableDTO> updateComptable(@Valid @RequestBody ComptableDTO comptableDTO) throws URISyntaxException {
        log.debug("REST request to update Comptable : {}", comptableDTO);
        if (comptableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComptableDTO result = comptableService.save(comptableDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, comptableDTO.getId().toString()))
            .body(result);
    }
     */
    /**
     * {@code GET  /comptables} : get all the comptables.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comptables in body.
     */
    @GetMapping("/comptables")
    public List<ComptableDTO> getAllComptables() {
        log.debug("REST request to get all Comptables");
        return comptableService.findAll();
    }

    /**
     * {@code GET  /comptables/:id} : get the "id" comptable.
     *
     * @param id the id of the comptableDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the comptableDTO, or with status {@code 404 (Not Found)}.
     */
   /* @GetMapping("/comptables/{id}")
    public ResponseEntity<ComptableDTO> getComptable(@PathVariable Long id) {
        log.debug("REST request to get Comptable : {}", id);
        Optional<ComptableDTO> comptableDTO = comptableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(comptableDTO);
    } */

    @GetMapping("/comptables/{id}")
    public ResponseEntity<WrapperComptable> getComptable(@PathVariable Long id) {
        log.debug("REST request to get Comptable : {}", id);
        Optional<WrapperComptable> result = Optional.ofNullable(comptableService.getComptable(id));
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     * {@code DELETE  /comptables/:id} : delete the "id" comptable.
     *
     * @param id the id of the comptableDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/comptables/{id}")
    public ResponseEntity<Void> deleteComptable(@PathVariable Long id) {
        log.debug("REST request to delete Comptable : {}", id);
        comptableService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/comptables/refuser/{id}")
    public ResponseEntity<ComptableDTO> getComptableByUserID(@PathVariable Long id) {
        log.debug("REST request to get Comptable : {}", id);
        Optional<Comptable> result = comptableRepository.findComptableByUserId(id);
        ComptableDTO res = comptableMapper.toDto(result.get());
        Optional<ComptableDTO> resS = Optional.ofNullable(res);
        return ResponseUtil.wrapOrNotFound(resS);
    }

    private void verificationsComptable(ComptableInfoEntrepriseAdresseUserDTO comptable, String callingMethode ) {
        if(callingMethode.equals("create")){
            if (comptable.getId() != null) {
                throw new BadRequestAlertException("A new comptable cannot already have an ID", ENTITY_NAME, "idexists");
            }
            if (comptable.getIdAdresse() != null) {
                throw new BadRequestAlertException("A new adress cannot already have an ID", ENTITY_NAME, "idexists");
            }
            if (comptable.getIdInfoEntreprise() != null) {
                throw new BadRequestAlertException("A new infoEntreprise cannot already have an ID", ENTITY_NAME, "idexists");
            }
            if (comptable.getIdUser() != null) {
                throw new BadRequestAlertException("A new user cannot already have an ID", ENTITY_NAME, "idexists");
            }
        }
        if(callingMethode.equals("update")){
            if (comptable.getId() == null) {
                throw new BadRequestAlertException("The comptable ID must not be NULL!", ENTITY_NAME, "id not exist");
            }
            if (comptable.getIdAdresse() == null) {
                throw new BadRequestAlertException("The adresse ID must not be NULL!", ENTITY_NAME, "id not exist");
            }
            if (comptable.getIdInfoEntreprise() == null) {
                throw new BadRequestAlertException("The infoEntreprise ID must not be NULL!", ENTITY_NAME, "id not exist");
            }
            if (comptable.getIdUser() == null) {
                throw new BadRequestAlertException("The user ID must not be NULL!", ENTITY_NAME, "id not exist");
            }
        }
        if(callingMethode.equals("create") || callingMethode.equals("update")){

            if (comptable.getAuthorities() == null || "".equals(comptable.getAuthorities())) {
                throw new BadRequestAlertException("Authorities is empty !", ENTITY_NAME, "Authorities null");
            }
            if (comptable.getLogin() == null || "".equals(comptable.getLogin())) {
                throw new BadRequestAlertException("Login is empty !", ENTITY_NAME, "Login null");
            }
            if (comptable.getEmail() == null || "".equals(comptable.getEmail())) {
                throw new BadRequestAlertException("Email is empty !", ENTITY_NAME, "Email null");
            }
            if (comptable.getFirstName() == null || "".equals(comptable.getFirstName())) {
                throw new BadRequestAlertException("FirstName is empty! ", ENTITY_NAME, "FirstName  null");
            }
            if (comptable.getLastName() == null || "".equals(comptable.getLastName())) {
                throw new BadRequestAlertException("LastName is empty", ENTITY_NAME, "LastName null");
            }
            if (comptable.getLangKey() == null || "".equals(comptable.getLangKey())) {
                throw new BadRequestAlertException("LangKey is empty", ENTITY_NAME, "LangKey null");
            }
            if (comptable.getCivilite() == null || "".equals(comptable.getCivilite())) {
                throw new BadRequestAlertException("Civilite is empty", ENTITY_NAME, "Civilite null");
            }
            if (comptable.getNomRue() == null || "".equals(comptable.getNomRue())) {
                throw new BadRequestAlertException("NomRue is empty", ENTITY_NAME, "NomRue null");
            }
            if (comptable.getNumeroRue() == null || "".equals(comptable.getNumeroRue())) {
                throw new BadRequestAlertException("NumeroRue is empty", ENTITY_NAME, "NumeroRue null");
            }
            if (comptable.getVille() == null || "".equals(comptable.getVille())) {
                throw new BadRequestAlertException("Ville is empty", ENTITY_NAME, "Ville null");
            }
            if (comptable.getCodePostal() == null || "".equals(comptable.getCodePostal())) {
                throw new BadRequestAlertException("CodePostal is empty", ENTITY_NAME, "CodePostal null");
            }

            if (comptable.getSiren() == null || "".equals(comptable.getSiren())) {
                throw new BadRequestAlertException("Siren is empty", ENTITY_NAME, "Siren null");
            }
            if (comptable.getRaisonSociale() == null || "".equals(comptable.getRaisonSociale())) {
                throw new BadRequestAlertException("RaisonSociale is empty", ENTITY_NAME, "CodePostal null");
            }





        }


    }

}
