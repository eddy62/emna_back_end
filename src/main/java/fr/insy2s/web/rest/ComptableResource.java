package fr.insy2s.web.rest;

import fr.insy2s.service.ComptableService;
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
import java.net.URI;
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

    public ComptableResource(ComptableService comptableService) {
        this.comptableService = comptableService;
    }

    /**
     * {@code POST  /comptables} : Create a new comptable.
     *
     * @param comptableDTO the comptableDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new comptableDTO, or with status {@code 400 (Bad Request)} if the comptable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comptables")
    public ResponseEntity<ComptableDTO> createComptable(@Valid @RequestBody ComptableDTO comptableDTO) throws URISyntaxException {
        log.debug("REST request to save Comptable : {}", comptableDTO);
        if (comptableDTO.getId() != null) {
            throw new BadRequestAlertException("A new comptable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComptableDTO result = comptableService.save(comptableDTO);
        return ResponseEntity.created(new URI("/api/comptables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /comptables} : Updates an existing comptable.
     *
     * @param comptableDTO the comptableDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comptableDTO,
     * or with status {@code 400 (Bad Request)} if the comptableDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the comptableDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
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
    @GetMapping("/comptables/{id}")
    public ResponseEntity<ComptableDTO> getComptable(@PathVariable Long id) {
        log.debug("REST request to get Comptable : {}", id);
        Optional<ComptableDTO> comptableDTO = comptableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(comptableDTO);
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
}
