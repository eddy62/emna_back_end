package fr.insy2s.web.rest;

import fr.insy2s.service.TypeContratService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.TypeContratDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.TypeContrat}.
 */
@RestController
@RequestMapping("/api")
public class TypeContratResource {

    private final Logger log = LoggerFactory.getLogger(TypeContratResource.class);

    private static final String ENTITY_NAME = "typeContrat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeContratService typeContratService;

    public TypeContratResource(TypeContratService typeContratService) {
        this.typeContratService = typeContratService;
    }

    /**
     * {@code POST  /type-contrats} : Create a new typeContrat.
     *
     * @param typeContratDTO the typeContratDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeContratDTO, or with status {@code 400 (Bad Request)} if the typeContrat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-contrats")
    public ResponseEntity<TypeContratDTO> createTypeContrat(@Valid @RequestBody TypeContratDTO typeContratDTO) throws URISyntaxException {
        log.debug("REST request to save TypeContrat : {}", typeContratDTO);
        if (typeContratDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeContrat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeContratDTO result = typeContratService.save(typeContratDTO);
        return ResponseEntity.created(new URI("/api/type-contrats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-contrats} : Updates an existing typeContrat.
     *
     * @param typeContratDTO the typeContratDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeContratDTO,
     * or with status {@code 400 (Bad Request)} if the typeContratDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeContratDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-contrats")
    public ResponseEntity<TypeContratDTO> updateTypeContrat(@Valid @RequestBody TypeContratDTO typeContratDTO) throws URISyntaxException {
        log.debug("REST request to update TypeContrat : {}", typeContratDTO);
        if (typeContratDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeContratDTO result = typeContratService.save(typeContratDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeContratDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-contrats} : get all the typeContrats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeContrats in body.
     */
    @GetMapping("/type-contrats")
    public List<TypeContratDTO> getAllTypeContrats() {
        log.debug("REST request to get all TypeContrats");
        return typeContratService.findAll();
    }

    /**
     * {@code GET  /type-contrats/:id} : get the "id" typeContrat.
     *
     * @param id the id of the typeContratDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeContratDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-contrats/{id}")
    public ResponseEntity<TypeContratDTO> getTypeContrat(@PathVariable Long id) {
        log.debug("REST request to get TypeContrat : {}", id);
        Optional<TypeContratDTO> typeContratDTO = typeContratService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeContratDTO);
    }

    /**
     * {@code DELETE  /type-contrats/:id} : delete the "id" typeContrat.
     *
     * @param id the id of the typeContratDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-contrats/{id}")
    public ResponseEntity<Void> deleteTypeContrat(@PathVariable Long id) {
        log.debug("REST request to delete TypeContrat : {}", id);
        typeContratService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
