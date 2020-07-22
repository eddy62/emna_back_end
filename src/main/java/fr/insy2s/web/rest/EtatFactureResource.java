package fr.insy2s.web.rest;

import fr.insy2s.service.EtatFactureService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.EtatFactureDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.EtatFacture}.
 */
@RestController
@RequestMapping("/api")
public class EtatFactureResource {

    private final Logger log = LoggerFactory.getLogger(EtatFactureResource.class);

    private static final String ENTITY_NAME = "etatFacture";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtatFactureService etatFactureService;

    public EtatFactureResource(EtatFactureService etatFactureService) {
        this.etatFactureService = etatFactureService;
    }

    /**
     * {@code POST  /etat-factures} : Create a new etatFacture.
     *
     * @param etatFactureDTO the etatFactureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etatFactureDTO, or with status {@code 400 (Bad Request)} if the etatFacture has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etat-factures")
    public ResponseEntity<EtatFactureDTO> createEtatFacture(@Valid @RequestBody EtatFactureDTO etatFactureDTO) throws URISyntaxException {
        log.debug("REST request to save EtatFacture : {}", etatFactureDTO);
        if (etatFactureDTO.getId() != null) {
            throw new BadRequestAlertException("A new etatFacture cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtatFactureDTO result = etatFactureService.save(etatFactureDTO);
        return ResponseEntity.created(new URI("/api/etat-factures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etat-factures} : Updates an existing etatFacture.
     *
     * @param etatFactureDTO the etatFactureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etatFactureDTO,
     * or with status {@code 400 (Bad Request)} if the etatFactureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etatFactureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etat-factures")
    public ResponseEntity<EtatFactureDTO> updateEtatFacture(@Valid @RequestBody EtatFactureDTO etatFactureDTO) throws URISyntaxException {
        log.debug("REST request to update EtatFacture : {}", etatFactureDTO);
        if (etatFactureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtatFactureDTO result = etatFactureService.save(etatFactureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etatFactureDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etat-factures} : get all the etatFactures.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etatFactures in body.
     */
    @GetMapping("/etat-factures")
    public List<EtatFactureDTO> getAllEtatFactures() {
        log.debug("REST request to get all EtatFactures");
        return etatFactureService.findAll();
    }

    /**
     * {@code GET  /etat-factures/:id} : get the "id" etatFacture.
     *
     * @param id the id of the etatFactureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etatFactureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etat-factures/{id}")
    public ResponseEntity<EtatFactureDTO> getEtatFacture(@PathVariable Long id) {
        log.debug("REST request to get EtatFacture : {}", id);
        Optional<EtatFactureDTO> etatFactureDTO = etatFactureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etatFactureDTO);
    }

    /**
     * {@code DELETE  /etat-factures/:id} : delete the "id" etatFacture.
     *
     * @param id the id of the etatFactureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etat-factures/{id}")
    public ResponseEntity<Void> deleteEtatFacture(@PathVariable Long id) {
        log.debug("REST request to delete EtatFacture : {}", id);
        etatFactureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
