package fr.insy2s.web.rest;

import fr.insy2s.service.EtatDevisService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.EtatDevisDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.EtatDevis}.
 */
@RestController
@RequestMapping("/api")
public class EtatDevisResource {

    private final Logger log = LoggerFactory.getLogger(EtatDevisResource.class);

    private static final String ENTITY_NAME = "etatDevis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtatDevisService etatDevisService;

    public EtatDevisResource(EtatDevisService etatDevisService) {
        this.etatDevisService = etatDevisService;
    }

    /**
     * {@code POST  /etat-devis} : Create a new etatDevis.
     *
     * @param etatDevisDTO the etatDevisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etatDevisDTO, or with status {@code 400 (Bad Request)} if the etatDevis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etat-devis")
    public ResponseEntity<EtatDevisDTO> createEtatDevis(@Valid @RequestBody EtatDevisDTO etatDevisDTO) throws URISyntaxException {
        log.debug("REST request to save EtatDevis : {}", etatDevisDTO);
        if (etatDevisDTO.getId() != null) {
            throw new BadRequestAlertException("A new etatDevis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtatDevisDTO result = etatDevisService.save(etatDevisDTO);
        return ResponseEntity.created(new URI("/api/etat-devis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etat-devis} : Updates an existing etatDevis.
     *
     * @param etatDevisDTO the etatDevisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etatDevisDTO,
     * or with status {@code 400 (Bad Request)} if the etatDevisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etatDevisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etat-devis")
    public ResponseEntity<EtatDevisDTO> updateEtatDevis(@Valid @RequestBody EtatDevisDTO etatDevisDTO) throws URISyntaxException {
        log.debug("REST request to update EtatDevis : {}", etatDevisDTO);
        if (etatDevisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtatDevisDTO result = etatDevisService.save(etatDevisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etatDevisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etat-devis} : get all the etatDevis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etatDevis in body.
     */
    @GetMapping("/etat-devis")
    public List<EtatDevisDTO> getAllEtatDevis() {
        log.debug("REST request to get all EtatDevis");
        return etatDevisService.findAll();
    }

    /**
     * {@code GET  /etat-devis/:id} : get the "id" etatDevis.
     *
     * @param id the id of the etatDevisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etatDevisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etat-devis/{id}")
    public ResponseEntity<EtatDevisDTO> getEtatDevis(@PathVariable Long id) {
        log.debug("REST request to get EtatDevis : {}", id);
        Optional<EtatDevisDTO> etatDevisDTO = etatDevisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etatDevisDTO);
    }

    /**
     * {@code DELETE  /etat-devis/:id} : delete the "id" etatDevis.
     *
     * @param id the id of the etatDevisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etat-devis/{id}")
    public ResponseEntity<Void> deleteEtatDevis(@PathVariable Long id) {
        log.debug("REST request to delete EtatDevis : {}", id);
        etatDevisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
