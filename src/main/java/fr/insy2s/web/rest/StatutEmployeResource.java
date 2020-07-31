package fr.insy2s.web.rest;

import fr.insy2s.service.StatutEmployeService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.StatutEmployeDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.StatutEmploye}.
 */
@RestController
@RequestMapping("/api")
public class StatutEmployeResource {

    private final Logger log = LoggerFactory.getLogger(StatutEmployeResource.class);

    private static final String ENTITY_NAME = "statutEmploye";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StatutEmployeService statutEmployeService;

    public StatutEmployeResource(StatutEmployeService statutEmployeService) {
        this.statutEmployeService = statutEmployeService;
    }

    /**
     * {@code POST  /statut-employes} : Create a new statutEmploye.
     *
     * @param statutEmployeDTO the statutEmployeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new statutEmployeDTO, or with status {@code 400 (Bad Request)} if the statutEmploye has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/statut-employes")
    public ResponseEntity<StatutEmployeDTO> createStatutEmploye(@Valid @RequestBody StatutEmployeDTO statutEmployeDTO) throws URISyntaxException {
        log.debug("REST request to save StatutEmploye : {}", statutEmployeDTO);
        if (statutEmployeDTO.getId() != null) {
            throw new BadRequestAlertException("A new statutEmploye cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatutEmployeDTO result = statutEmployeService.save(statutEmployeDTO);
        return ResponseEntity.created(new URI("/api/statut-employes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /statut-employes} : Updates an existing statutEmploye.
     *
     * @param statutEmployeDTO the statutEmployeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated statutEmployeDTO,
     * or with status {@code 400 (Bad Request)} if the statutEmployeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the statutEmployeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/statut-employes")
    public ResponseEntity<StatutEmployeDTO> updateStatutEmploye(@Valid @RequestBody StatutEmployeDTO statutEmployeDTO) throws URISyntaxException {
        log.debug("REST request to update StatutEmploye : {}", statutEmployeDTO);
        if (statutEmployeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StatutEmployeDTO result = statutEmployeService.save(statutEmployeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, statutEmployeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /statut-employes} : get all the statutEmployes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statutEmployes in body.
     */
    @GetMapping("/statut-employes")
    public List<StatutEmployeDTO> getAllStatutEmployes() {
        log.debug("REST request to get all StatutEmployes");
        return statutEmployeService.findAll();
    }

    /**
     * {@code GET  /statut-employes/:id} : get the "id" statutEmploye.
     *
     * @param id the id of the statutEmployeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statutEmployeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/statut-employes/{id}")
    public ResponseEntity<StatutEmployeDTO> getStatutEmploye(@PathVariable Long id) {
        log.debug("REST request to get StatutEmploye : {}", id);
        Optional<StatutEmployeDTO> statutEmployeDTO = statutEmployeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statutEmployeDTO);
    }

    /**
     * {@code DELETE  /statut-employes/:id} : delete the "id" statutEmploye.
     *
     * @param id the id of the statutEmployeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/statut-employes/{id}")
    public ResponseEntity<Void> deleteStatutEmploye(@PathVariable Long id) {
        log.debug("REST request to delete StatutEmploye : {}", id);
        statutEmployeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
