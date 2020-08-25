package fr.insy2s.web.rest;

import fr.insy2s.service.AvanceRappelSalaireService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.AvanceRappelSalaireDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.AvanceRappelSalaire}.
 */
@RestController
@RequestMapping("/api")
public class AvanceRappelSalaireResource {

    private final Logger log = LoggerFactory.getLogger(AvanceRappelSalaireResource.class);

    private static final String ENTITY_NAME = "avanceRappelSalaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AvanceRappelSalaireService avanceRappelSalaireService;

    public AvanceRappelSalaireResource(AvanceRappelSalaireService avanceRappelSalaireService) {
        this.avanceRappelSalaireService = avanceRappelSalaireService;
    }

    /**
     * {@code POST  /avance-rappel-salaires} : Create a new avanceRappelSalaire.
     *
     * @param avanceRappelSalaireDTO the avanceRappelSalaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new avanceRappelSalaireDTO, or with status {@code 400 (Bad Request)} if the avanceRappelSalaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/avance-rappel-salaires")
    public ResponseEntity<AvanceRappelSalaireDTO> createAvanceRappelSalaire(@Valid @RequestBody AvanceRappelSalaireDTO avanceRappelSalaireDTO) throws URISyntaxException {
        log.debug("REST request to save AvanceRappelSalaire : {}", avanceRappelSalaireDTO);
        if (avanceRappelSalaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new avanceRappelSalaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AvanceRappelSalaireDTO result = avanceRappelSalaireService.save(avanceRappelSalaireDTO);
        return ResponseEntity.created(new URI("/api/avance-rappel-salaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /avance-rappel-salaires} : Updates an existing avanceRappelSalaire.
     *
     * @param avanceRappelSalaireDTO the avanceRappelSalaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated avanceRappelSalaireDTO,
     * or with status {@code 400 (Bad Request)} if the avanceRappelSalaireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the avanceRappelSalaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/avance-rappel-salaires")
    public ResponseEntity<AvanceRappelSalaireDTO> updateAvanceRappelSalaire(@Valid @RequestBody AvanceRappelSalaireDTO avanceRappelSalaireDTO) throws URISyntaxException {
        log.debug("REST request to update AvanceRappelSalaire : {}", avanceRappelSalaireDTO);
        if (avanceRappelSalaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AvanceRappelSalaireDTO result = avanceRappelSalaireService.save(avanceRappelSalaireDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, avanceRappelSalaireDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /avance-rappel-salaires} : get all the avanceRappelSalaires.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of avanceRappelSalaires in body.
     */
    @GetMapping("/avance-rappel-salaires")
    public List<AvanceRappelSalaireDTO> getAllAvanceRappelSalaires() {
        log.debug("REST request to get all AvanceRappelSalaires");
        return avanceRappelSalaireService.findAll();
    }

    /**
     * {@code GET  /avance-rappel-salaires/:id} : get the "id" avanceRappelSalaire.
     *
     * @param id the id of the avanceRappelSalaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the avanceRappelSalaireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/avance-rappel-salaires/{id}")
    public ResponseEntity<AvanceRappelSalaireDTO> getAvanceRappelSalaire(@PathVariable Long id) {
        log.debug("REST request to get AvanceRappelSalaire : {}", id);
        Optional<AvanceRappelSalaireDTO> avanceRappelSalaireDTO = avanceRappelSalaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(avanceRappelSalaireDTO);
    }

    /**
     * {@code DELETE  /avance-rappel-salaires/:id} : delete the "id" avanceRappelSalaire.
     *
     * @param id the id of the avanceRappelSalaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/avance-rappel-salaires/{id}")
    public ResponseEntity<Void> deleteAvanceRappelSalaire(@PathVariable Long id) {
        log.debug("REST request to delete AvanceRappelSalaire : {}", id);
        avanceRappelSalaireService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
