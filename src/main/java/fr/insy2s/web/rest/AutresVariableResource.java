package fr.insy2s.web.rest;

import fr.insy2s.service.AbsenceService;
import fr.insy2s.service.AutresVariableService;
import fr.insy2s.service.dto.AbsenceDTO;
import fr.insy2s.service.dto.NoteDeFraisDTO;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.AutresVariableDTO;

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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.AutresVariable}.
 */
@RestController
@RequestMapping("/api")
public class AutresVariableResource {

    private final Logger log = LoggerFactory.getLogger(AutresVariableResource.class);

    private static final String ENTITY_NAME = "autresVariable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutresVariableService autresVariableService;

    private final AbsenceService absenceService;

    public AutresVariableResource(AutresVariableService autresVariableService, AbsenceService absenceService) {
        this.autresVariableService = autresVariableService;
        this.absenceService = absenceService;
    }

    /**
     * {@code POST  /autres-variables} : Create a new autresVariable.
     *
     * @param autresVariableDTO the autresVariableDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autresVariableDTO, or with status {@code 400 (Bad Request)} if the autresVariable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/autres-variables")
    public ResponseEntity<AutresVariableDTO> createAutresVariable(@Valid @RequestBody AutresVariableDTO autresVariableDTO) throws URISyntaxException {
        log.debug("REST request to save AutresVariable : {}", autresVariableDTO);
        if (autresVariableDTO.getId() != null) {
            throw new BadRequestAlertException("A new autresVariable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Long idEmploye = autresVariableDTO.getEmployeId();
        LocalDate dateToCheck = autresVariableDTO.getDate();
        Optional<AbsenceDTO> dateExist = absenceService.findAbsenceExistByDate(idEmploye, dateToCheck);
        if(!dateExist.isPresent()) {
            AutresVariableDTO result = autresVariableService.save(autresVariableDTO);
            return ResponseEntity.status(201)/*.created(new URI("/api/autres-variables/" + result.getId()))*/
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
        }
        else {
            return ResponseEntity.status(208)
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, "Absence détectée à cette date"))
                .body(autresVariableDTO);
        }
    }

    /**
     * {@code PUT  /autres-variables} : Updates an existing autresVariable.
     *
     * @param autresVariableDTO the autresVariableDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autresVariableDTO,
     * or with status {@code 400 (Bad Request)} if the autresVariableDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autresVariableDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/autres-variables")
    public ResponseEntity<AutresVariableDTO> updateAutresVariable(@Valid @RequestBody AutresVariableDTO autresVariableDTO) throws URISyntaxException {
        log.debug("REST request to update AutresVariable : {}", autresVariableDTO);
        if (autresVariableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AutresVariableDTO result = autresVariableService.save(autresVariableDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, autresVariableDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /autres-variables} : get all the autresVariables.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autresVariables in body.
     */
    @GetMapping("/autres-variables")
    public List<AutresVariableDTO> getAllAutresVariables() {
        log.debug("REST request to get all AutresVariables");
        return autresVariableService.findAll();
    }

    /**
     * {@code GET  /autres-variables/:id} : get the "id" autresVariable.
     *
     * @param id the id of the autresVariableDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autresVariableDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/autres-variables/{id}")
    public ResponseEntity<AutresVariableDTO> getAutresVariable(@PathVariable Long id) {
        log.debug("REST request to get AutresVariable : {}", id);
        Optional<AutresVariableDTO> autresVariableDTO = autresVariableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(autresVariableDTO);
    }

    /**
     * {@code DELETE  /autres-variables/:id} : delete the "id" autresVariable.
     *
     * @param id the id of the autresVariableDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/autres-variables/{id}")
    public ResponseEntity<Void> deleteAutresVariable(@PathVariable Long id) {
        log.debug("REST request to delete AutresVariable : {}", id);
        autresVariableService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
