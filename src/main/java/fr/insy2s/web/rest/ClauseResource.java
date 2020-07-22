package fr.insy2s.web.rest;

import fr.insy2s.service.ClauseService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.ClauseDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.Clause}.
 */
@RestController
@RequestMapping("/api")
public class ClauseResource {

    private final Logger log = LoggerFactory.getLogger(ClauseResource.class);

    private static final String ENTITY_NAME = "clause";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClauseService clauseService;

    public ClauseResource(ClauseService clauseService) {
        this.clauseService = clauseService;
    }

    /**
     * {@code POST  /clauses} : Create a new clause.
     *
     * @param clauseDTO the clauseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clauseDTO, or with status {@code 400 (Bad Request)} if the clause has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/clauses")
    public ResponseEntity<ClauseDTO> createClause(@Valid @RequestBody ClauseDTO clauseDTO) throws URISyntaxException {
        log.debug("REST request to save Clause : {}", clauseDTO);
        if (clauseDTO.getId() != null) {
            throw new BadRequestAlertException("A new clause cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClauseDTO result = clauseService.save(clauseDTO);
        return ResponseEntity.created(new URI("/api/clauses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /clauses} : Updates an existing clause.
     *
     * @param clauseDTO the clauseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clauseDTO,
     * or with status {@code 400 (Bad Request)} if the clauseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clauseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/clauses")
    public ResponseEntity<ClauseDTO> updateClause(@Valid @RequestBody ClauseDTO clauseDTO) throws URISyntaxException {
        log.debug("REST request to update Clause : {}", clauseDTO);
        if (clauseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClauseDTO result = clauseService.save(clauseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clauseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /clauses} : get all the clauses.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clauses in body.
     */
    @GetMapping("/clauses")
    public List<ClauseDTO> getAllClauses(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Clauses");
        return clauseService.findAll();
    }

    /**
     * {@code GET  /clauses/:id} : get the "id" clause.
     *
     * @param id the id of the clauseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clauseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/clauses/{id}")
    public ResponseEntity<ClauseDTO> getClause(@PathVariable Long id) {
        log.debug("REST request to get Clause : {}", id);
        Optional<ClauseDTO> clauseDTO = clauseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clauseDTO);
    }

    /**
     * {@code DELETE  /clauses/:id} : delete the "id" clause.
     *
     * @param id the id of the clauseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/clauses/{id}")
    public ResponseEntity<Void> deleteClause(@PathVariable Long id) {
        log.debug("REST request to delete Clause : {}", id);
        clauseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
