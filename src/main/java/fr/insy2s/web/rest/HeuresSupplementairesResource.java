package fr.insy2s.web.rest;

import fr.insy2s.service.HeuresSupplementairesService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.HeuresSupplementairesDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.HeuresSupplementaires}.
 */
@RestController
@RequestMapping("/api")
public class HeuresSupplementairesResource {

    private final Logger log = LoggerFactory.getLogger(HeuresSupplementairesResource.class);

    private static final String ENTITY_NAME = "heuresSupplementaires";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HeuresSupplementairesService heuresSupplementairesService;

    public HeuresSupplementairesResource(HeuresSupplementairesService heuresSupplementairesService) {
        this.heuresSupplementairesService = heuresSupplementairesService;
    }

    /**
     * {@code POST  /heures-supplementaires} : Create a new heuresSupplementaires.
     *
     * @param heuresSupplementairesDTO the heuresSupplementairesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new heuresSupplementairesDTO, or with status {@code 400 (Bad Request)} if the heuresSupplementaires has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/heures-supplementaires")
    public ResponseEntity<HeuresSupplementairesDTO> createHeuresSupplementaires(@Valid @RequestBody HeuresSupplementairesDTO heuresSupplementairesDTO) throws URISyntaxException {
        log.debug("REST request to save HeuresSupplementaires : {}", heuresSupplementairesDTO);
        if (heuresSupplementairesDTO.getId() != null) {
            throw new BadRequestAlertException("A new heuresSupplementaires cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HeuresSupplementairesDTO result = heuresSupplementairesService.save(heuresSupplementairesDTO);
        return ResponseEntity.created(new URI("/api/heures-supplementaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /heures-supplementaires} : Updates an existing heuresSupplementaires.
     *
     * @param heuresSupplementairesDTO the heuresSupplementairesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated heuresSupplementairesDTO,
     * or with status {@code 400 (Bad Request)} if the heuresSupplementairesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the heuresSupplementairesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/heures-supplementaires")
    public ResponseEntity<HeuresSupplementairesDTO> updateHeuresSupplementaires(@Valid @RequestBody HeuresSupplementairesDTO heuresSupplementairesDTO) throws URISyntaxException {
        log.debug("REST request to update HeuresSupplementaires : {}", heuresSupplementairesDTO);
        if (heuresSupplementairesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HeuresSupplementairesDTO result = heuresSupplementairesService.save(heuresSupplementairesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, heuresSupplementairesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /heures-supplementaires} : get all the heuresSupplementaires.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of heuresSupplementaires in body.
     */
    @GetMapping("/heures-supplementaires")
    public List<HeuresSupplementairesDTO> getAllHeuresSupplementaires() {
        log.debug("REST request to get all HeuresSupplementaires");
        return heuresSupplementairesService.findAll();
    }

    /**
     * {@code GET  /heures-supplementaires/:id} : get the "id" heuresSupplementaires.
     *
     * @param id the id of the heuresSupplementairesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the heuresSupplementairesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/heures-supplementaires/{id}")
    public ResponseEntity<HeuresSupplementairesDTO> getHeuresSupplementaires(@PathVariable Long id) {
        log.debug("REST request to get HeuresSupplementaires : {}", id);
        Optional<HeuresSupplementairesDTO> heuresSupplementairesDTO = heuresSupplementairesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(heuresSupplementairesDTO);
    }

    /**
     * {@code DELETE  /heures-supplementaires/:id} : delete the "id" heuresSupplementaires.
     *
     * @param id the id of the heuresSupplementairesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/heures-supplementaires/{id}")
    public ResponseEntity<Void> deleteHeuresSupplementaires(@PathVariable Long id) {
        log.debug("REST request to delete HeuresSupplementaires : {}", id);
        heuresSupplementairesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
