package fr.insy2s.web.rest;

import fr.insy2s.security.AuthoritiesConstants;
import fr.insy2s.service.OperationService;
import fr.insy2s.service.ReleveService;
import fr.insy2s.service.dto.OperationDTO;
import fr.insy2s.service.dto.ReleveDTO;
import fr.insy2s.utils.CheckUtil;
import fr.insy2s.utils.wrapper.WrapperReleveSolde;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.Operation}.
 */
@RestController
@RequestMapping("/api")
public class OperationResource {

    private final Logger log = LoggerFactory.getLogger(OperationResource.class);

    private static final String ENTITY_NAME = "operation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperationService operationService;

    private final ReleveService releveService;

    public OperationResource(OperationService operationService, ReleveService releveService) {
        this.operationService = operationService;
        this.releveService = releveService;
    }

    /**
     * {@code POST  /operations} : Create a new operation.
     *
     * @param operationDTO the operationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operationDTO, or with status {@code 400 (Bad Request)} if the operation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.SOCIETY})
    @PostMapping("/operations")
    public ResponseEntity<OperationDTO> createOperation(@RequestBody OperationDTO operationDTO) throws URISyntaxException {
        log.debug("REST request to save Operation : {}", operationDTO);
        if (operationDTO.getId() != null) {
            throw new BadRequestAlertException("A new operation cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Optional<WrapperReleveSolde> optionalReleveDTO = releveService.findOne(operationDTO.getReleveId());

        if (optionalReleveDTO.isPresent()) {
            WrapperReleveSolde releve = optionalReleveDTO.get();
            if (operationDTO.getDate() == null || !CheckUtil.isDateBetween(
                    operationDTO.getDate(),
                    releve.getDateDebut(),
                    releve.getDateFin())
            ) {
                throw new BadRequestAlertException("La date de l'opération est invalide", ENTITY_NAME, "dateOutOfRange");
            }
        }

        OperationDTO result = operationService.save(operationDTO);
        return ResponseEntity.created(new URI("/api/operations/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /operations} : Updates an existing operation.
     *
     * @param operationDTO the operationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operationDTO,
     * or with status {@code 400 (Bad Request)} if the operationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Secured({AuthoritiesConstants.ADMIN,AuthoritiesConstants.SOCIETY})
    @PutMapping("/operations")
    public ResponseEntity<OperationDTO> updateOperation(@RequestBody OperationDTO operationDTO) throws URISyntaxException {
        log.debug("REST request to update Operation : {}", operationDTO);
        if (operationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OperationDTO result = operationService.save(operationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operationDTO.getId().toString()))
            .body(result);
    }

    @PutMapping("/operations/{idOperation}")
    public void updateRapprochementOperation(@PathVariable Long idOperation){
        log.debug("REST request to update rapprochement operation by id operation");
        operationService.updateRapprochementOperation(idOperation);
    }

    /**
     * {@code GET  /operations} : get all the operations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operations in body.
     */
    @GetMapping("/operations")
    public List<OperationDTO> getAllOperations() {
        log.debug("REST request to get all Operations");
        return operationService.findAll();
    }

    /**
     * {@code GET  /operations/:id} : get the "id" operation.
     *
     * @param id the id of the operationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/operations/{id}")
    public ResponseEntity<OperationDTO> getOperation(@PathVariable Long id) {
        log.debug("REST request to get Operation : {}", id);
        Optional<OperationDTO> operationDTO = operationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(operationDTO);
    }

    /**
     * {@code DELETE  /operations/:id} : delete the "id" operation.
     *
     * @param id the id of the operationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Secured({AuthoritiesConstants.SOCIETY, AuthoritiesConstants.ADMIN})
    @DeleteMapping("/operations/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable Long id) {
        log.debug("REST request to delete Operation : {}", id);
        operationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @GetMapping("/operations/releve/{id}")
    public List<OperationDTO> getAllOperationsByReleveId(@PathVariable Long id) {
        log.debug("REST request to get all Operations by Releve id ");
        return operationService.findAllByReleveId(id);
    }


}
