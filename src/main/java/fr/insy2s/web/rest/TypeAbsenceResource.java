package fr.insy2s.web.rest;

import fr.insy2s.service.TypeAbsenceService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.TypeAbsenceDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.TypeAbsence}.
 */
@RestController
@RequestMapping("/api")
public class TypeAbsenceResource {

    private final Logger log = LoggerFactory.getLogger(TypeAbsenceResource.class);

    private static final String ENTITY_NAME = "typeAbsence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeAbsenceService typeAbsenceService;

    public TypeAbsenceResource(TypeAbsenceService typeAbsenceService) {
        this.typeAbsenceService = typeAbsenceService;
    }

    /**
     * {@code POST  /type-absences} : Create a new typeAbsence.
     *
     * @param typeAbsenceDTO the typeAbsenceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeAbsenceDTO, or with status {@code 400 (Bad Request)} if the typeAbsence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-absences")
    public ResponseEntity<TypeAbsenceDTO> createTypeAbsence(@Valid @RequestBody TypeAbsenceDTO typeAbsenceDTO) throws URISyntaxException {
        log.debug("REST request to save TypeAbsence : {}", typeAbsenceDTO);
        if (typeAbsenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeAbsence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeAbsenceDTO result = typeAbsenceService.save(typeAbsenceDTO);
        return ResponseEntity.created(new URI("/api/type-absences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-absences} : Updates an existing typeAbsence.
     *
     * @param typeAbsenceDTO the typeAbsenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeAbsenceDTO,
     * or with status {@code 400 (Bad Request)} if the typeAbsenceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeAbsenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-absences")
    public ResponseEntity<TypeAbsenceDTO> updateTypeAbsence(@Valid @RequestBody TypeAbsenceDTO typeAbsenceDTO) throws URISyntaxException {
        log.debug("REST request to update TypeAbsence : {}", typeAbsenceDTO);
        if (typeAbsenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeAbsenceDTO result = typeAbsenceService.save(typeAbsenceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeAbsenceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-absences} : get all the typeAbsences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeAbsences in body.
     */
    @GetMapping("/type-absences")
    public List<TypeAbsenceDTO> getAllTypeAbsences() {
        log.debug("REST request to get all TypeAbsences");
        return typeAbsenceService.findAll();
    }

    /**
     * {@code GET  /type-absences/:id} : get the "id" typeAbsence.
     *
     * @param id the id of the typeAbsenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeAbsenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-absences/{id}")
    public ResponseEntity<TypeAbsenceDTO> getTypeAbsence(@PathVariable Long id) {
        log.debug("REST request to get TypeAbsence : {}", id);
        Optional<TypeAbsenceDTO> typeAbsenceDTO = typeAbsenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeAbsenceDTO);
    }

    /**
     * {@code DELETE  /type-absences/:id} : delete the "id" typeAbsence.
     *
     * @param id the id of the typeAbsenceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-absences/{id}")
    public ResponseEntity<Void> deleteTypeAbsence(@PathVariable Long id) {
        log.debug("REST request to delete TypeAbsence : {}", id);
        typeAbsenceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
