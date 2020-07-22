package fr.insy2s.web.rest;

import fr.insy2s.service.EtatReleveService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.EtatReleveDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.EtatReleve}.
 */
@RestController
@RequestMapping("/api")
public class EtatReleveResource {

    private final Logger log = LoggerFactory.getLogger(EtatReleveResource.class);

    private static final String ENTITY_NAME = "etatReleve";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtatReleveService etatReleveService;

    public EtatReleveResource(EtatReleveService etatReleveService) {
        this.etatReleveService = etatReleveService;
    }

    /**
     * {@code POST  /etat-releves} : Create a new etatReleve.
     *
     * @param etatReleveDTO the etatReleveDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etatReleveDTO, or with status {@code 400 (Bad Request)} if the etatReleve has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etat-releves")
    public ResponseEntity<EtatReleveDTO> createEtatReleve(@RequestBody EtatReleveDTO etatReleveDTO) throws URISyntaxException {
        log.debug("REST request to save EtatReleve : {}", etatReleveDTO);
        if (etatReleveDTO.getId() != null) {
            throw new BadRequestAlertException("A new etatReleve cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtatReleveDTO result = etatReleveService.save(etatReleveDTO);
        return ResponseEntity.created(new URI("/api/etat-releves/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etat-releves} : Updates an existing etatReleve.
     *
     * @param etatReleveDTO the etatReleveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etatReleveDTO,
     * or with status {@code 400 (Bad Request)} if the etatReleveDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etatReleveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etat-releves")
    public ResponseEntity<EtatReleveDTO> updateEtatReleve(@RequestBody EtatReleveDTO etatReleveDTO) throws URISyntaxException {
        log.debug("REST request to update EtatReleve : {}", etatReleveDTO);
        if (etatReleveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtatReleveDTO result = etatReleveService.save(etatReleveDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etatReleveDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etat-releves} : get all the etatReleves.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etatReleves in body.
     */
    @GetMapping("/etat-releves")
    public List<EtatReleveDTO> getAllEtatReleves() {
        log.debug("REST request to get all EtatReleves");
        return etatReleveService.findAll();
    }

    /**
     * {@code GET  /etat-releves/:id} : get the "id" etatReleve.
     *
     * @param id the id of the etatReleveDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etatReleveDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etat-releves/{id}")
    public ResponseEntity<EtatReleveDTO> getEtatReleve(@PathVariable Long id) {
        log.debug("REST request to get EtatReleve : {}", id);
        Optional<EtatReleveDTO> etatReleveDTO = etatReleveService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etatReleveDTO);
    }

    /**
     * {@code DELETE  /etat-releves/:id} : delete the "id" etatReleve.
     *
     * @param id the id of the etatReleveDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etat-releves/{id}")
    public ResponseEntity<Void> deleteEtatReleve(@PathVariable Long id) {
        log.debug("REST request to delete EtatReleve : {}", id);
        etatReleveService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
