package fr.insy2s.web.rest;

import fr.insy2s.service.ReleveService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.ReleveDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.Releve}.
 */
@RestController
@RequestMapping("/api")
public class ReleveResource {

    private final Logger log = LoggerFactory.getLogger(ReleveResource.class);

    private static final String ENTITY_NAME = "releve";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReleveService releveService;

    public ReleveResource(ReleveService releveService) {
        this.releveService = releveService;
    }

    /**
     * {@code POST  /releves} : Create a new releve.
     *
     * @param releveDTO the releveDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new releveDTO, or with status {@code 400 (Bad Request)} if the releve has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/releves")
    public ResponseEntity<ReleveDTO> createReleve(@RequestBody ReleveDTO releveDTO) throws URISyntaxException {
        log.debug("REST request to save Releve : {}", releveDTO);
        if (releveDTO.getId() != null) {
            throw new BadRequestAlertException("A new releve cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReleveDTO result = releveService.save(releveDTO);
        return ResponseEntity.created(new URI("/api/releves/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /releves} : Updates an existing releve.
     *
     * @param releveDTO the releveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated releveDTO,
     * or with status {@code 400 (Bad Request)} if the releveDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the releveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/releves")
    public ResponseEntity<ReleveDTO> updateReleve(@RequestBody ReleveDTO releveDTO) throws URISyntaxException {
        log.debug("REST request to update Releve : {}", releveDTO);
        if (releveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReleveDTO result = releveService.save(releveDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, releveDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /releves} : get all the releves.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of releves in body.
     */
    @GetMapping("/releves")
    public List<ReleveDTO> getAllReleves() {
        log.debug("REST request to get all Releves");
        return releveService.findAll();
    }

    /**
     * {@code GET  /releves/:id} : get the "id" releve.
     *
     * @param id the id of the releveDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the releveDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/releves/{id}")
    public ResponseEntity<ReleveDTO> getReleve(@PathVariable Long id) {
        log.debug("REST request to get Releve : {}", id);
        Optional<ReleveDTO> releveDTO = releveService.findOne(id);
        return ResponseUtil.wrapOrNotFound(releveDTO);
    }

    /**
     * {@code DELETE  /releves/:id} : delete the "id" releve.
     *
     * @param id the id of the releveDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/releves/{id}")
    public ResponseEntity<Void> deleteReleve(@PathVariable Long id) {
        log.debug("REST request to delete Releve : {}", id);
        releveService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
