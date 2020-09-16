package fr.insy2s.web.rest;

import fr.insy2s.service.EtatDepenseService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.EtatDepenseDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.EtatDepense}.
 */
@RestController
@RequestMapping("/api")
public class EtatDepenseResource {

    private final Logger log = LoggerFactory.getLogger(EtatDepenseResource.class);

    private static final String ENTITY_NAME = "etatDepense";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtatDepenseService etatDepenseService;

    public EtatDepenseResource(EtatDepenseService etatDepenseService) {
        this.etatDepenseService = etatDepenseService;
    }

    /**
     * {@code POST  /etat-depenses} : Create a new etatDepense.
     *
     * @param etatDepenseDTO the etatDepenseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etatDepenseDTO, or with status {@code 400 (Bad Request)} if the etatDepense has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etat-depenses")
    public ResponseEntity<EtatDepenseDTO> createEtatDepense(@RequestBody EtatDepenseDTO etatDepenseDTO) throws URISyntaxException {
        log.debug("REST request to save EtatDepense : {}", etatDepenseDTO);
        if (etatDepenseDTO.getId() != null) {
            throw new BadRequestAlertException("A new etatDepense cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtatDepenseDTO result = etatDepenseService.save(etatDepenseDTO);
        return ResponseEntity.created(new URI("/api/etat-depenses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etat-depenses} : Updates an existing etatDepense.
     *
     * @param etatDepenseDTO the etatDepenseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etatDepenseDTO,
     * or with status {@code 400 (Bad Request)} if the etatDepenseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etatDepenseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etat-depenses")
    public ResponseEntity<EtatDepenseDTO> updateEtatDepense(@RequestBody EtatDepenseDTO etatDepenseDTO) throws URISyntaxException {
        log.debug("REST request to update EtatDepense : {}", etatDepenseDTO);
        if (etatDepenseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtatDepenseDTO result = etatDepenseService.save(etatDepenseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etatDepenseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etat-depenses} : get all the etatDepenses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etatDepenses in body.
     */
    @GetMapping("/etat-depenses")
    public List<EtatDepenseDTO> getAllEtatDepenses() {
        log.debug("REST request to get all EtatDepenses");
        return etatDepenseService.findAll();
    }

    /**
     * {@code GET  /etat-depenses/:id} : get the "id" etatDepense.
     *
     * @param id the id of the etatDepenseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etatDepenseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etat-depenses/{id}")
    public ResponseEntity<EtatDepenseDTO> getEtatDepense(@PathVariable Long id) {
        log.debug("REST request to get EtatDepense : {}", id);
        Optional<EtatDepenseDTO> etatDepenseDTO = etatDepenseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etatDepenseDTO);
    }

    /**
     * {@code DELETE  /etat-depenses/:id} : delete the "id" etatDepense.
     *
     * @param id the id of the etatDepenseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etat-depenses/{id}")
    public ResponseEntity<Void> deleteEtatDepense(@PathVariable Long id) {
        log.debug("REST request to delete EtatDepense : {}", id);
        etatDepenseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
