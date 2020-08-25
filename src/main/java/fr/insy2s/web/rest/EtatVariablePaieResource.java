package fr.insy2s.web.rest;

import fr.insy2s.service.EtatVariablePaieService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.EtatVariablePaieDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.EtatVariablePaie}.
 */
@RestController
@RequestMapping("/api")
public class EtatVariablePaieResource {

    private final Logger log = LoggerFactory.getLogger(EtatVariablePaieResource.class);

    private static final String ENTITY_NAME = "etatVariablePaie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtatVariablePaieService etatVariablePaieService;

    public EtatVariablePaieResource(EtatVariablePaieService etatVariablePaieService) {
        this.etatVariablePaieService = etatVariablePaieService;
    }

    /**
     * {@code POST  /etat-variable-paies} : Create a new etatVariablePaie.
     *
     * @param etatVariablePaieDTO the etatVariablePaieDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etatVariablePaieDTO, or with status {@code 400 (Bad Request)} if the etatVariablePaie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etat-variable-paies")
    public ResponseEntity<EtatVariablePaieDTO> createEtatVariablePaie(@Valid @RequestBody EtatVariablePaieDTO etatVariablePaieDTO) throws URISyntaxException {
        log.debug("REST request to save EtatVariablePaie : {}", etatVariablePaieDTO);
        if (etatVariablePaieDTO.getId() != null) {
            throw new BadRequestAlertException("A new etatVariablePaie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtatVariablePaieDTO result = etatVariablePaieService.save(etatVariablePaieDTO);
        return ResponseEntity.created(new URI("/api/etat-variable-paies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etat-variable-paies} : Updates an existing etatVariablePaie.
     *
     * @param etatVariablePaieDTO the etatVariablePaieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etatVariablePaieDTO,
     * or with status {@code 400 (Bad Request)} if the etatVariablePaieDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etatVariablePaieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etat-variable-paies")
    public ResponseEntity<EtatVariablePaieDTO> updateEtatVariablePaie(@Valid @RequestBody EtatVariablePaieDTO etatVariablePaieDTO) throws URISyntaxException {
        log.debug("REST request to update EtatVariablePaie : {}", etatVariablePaieDTO);
        if (etatVariablePaieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtatVariablePaieDTO result = etatVariablePaieService.save(etatVariablePaieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etatVariablePaieDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etat-variable-paies} : get all the etatVariablePaies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etatVariablePaies in body.
     */
    @GetMapping("/etat-variable-paies")
    public List<EtatVariablePaieDTO> getAllEtatVariablePaies() {
        log.debug("REST request to get all EtatVariablePaies");
        return etatVariablePaieService.findAll();
    }

    /**
     * {@code GET  /etat-variable-paies/:id} : get the "id" etatVariablePaie.
     *
     * @param id the id of the etatVariablePaieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etatVariablePaieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etat-variable-paies/{id}")
    public ResponseEntity<EtatVariablePaieDTO> getEtatVariablePaie(@PathVariable Long id) {
        log.debug("REST request to get EtatVariablePaie : {}", id);
        Optional<EtatVariablePaieDTO> etatVariablePaieDTO = etatVariablePaieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etatVariablePaieDTO);
    }

    /**
     * {@code DELETE  /etat-variable-paies/:id} : delete the "id" etatVariablePaie.
     *
     * @param id the id of the etatVariablePaieDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etat-variable-paies/{id}")
    public ResponseEntity<Void> deleteEtatVariablePaie(@PathVariable Long id) {
        log.debug("REST request to delete EtatVariablePaie : {}", id);
        etatVariablePaieService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
