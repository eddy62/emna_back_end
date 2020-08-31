package fr.insy2s.web.rest;

import fr.insy2s.service.LigneProduitService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.LigneProduitDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.LigneProduit}.
 */
@RestController
@RequestMapping("/api")
public class LigneProduitResource {

    private final Logger log = LoggerFactory.getLogger(LigneProduitResource.class);

    private static final String ENTITY_NAME = "ligneProduit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LigneProduitService ligneProduitService;

    public LigneProduitResource(LigneProduitService ligneProduitService) {
        this.ligneProduitService = ligneProduitService;
    }

    /**
     * {@code POST  /ligne-produits} : Create a new ligneProduit.
     *
     * @param ligneProduitDTO the ligneProduitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ligneProduitDTO, or with status {@code 400 (Bad Request)} if the ligneProduit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ligne-produits")
    public ResponseEntity<LigneProduitDTO> createLigneProduit(@Valid @RequestBody LigneProduitDTO ligneProduitDTO) throws URISyntaxException {
        log.debug("REST request to save LigneProduit : {}", ligneProduitDTO);
        if (ligneProduitDTO.getId() != null) {
            throw new BadRequestAlertException("A new ligneProduit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LigneProduitDTO result = ligneProduitService.save(ligneProduitDTO);
        return ResponseEntity.created(new URI("/api/ligne-produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ligne-produits} : Updates an existing ligneProduit.
     *
     * @param ligneProduitDTO the ligneProduitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ligneProduitDTO,
     * or with status {@code 400 (Bad Request)} if the ligneProduitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ligneProduitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ligne-produits")
    public ResponseEntity<LigneProduitDTO> updateLigneProduit(@Valid @RequestBody LigneProduitDTO ligneProduitDTO) throws URISyntaxException {
        log.debug("REST request to update LigneProduit : {}", ligneProduitDTO);
        if (ligneProduitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LigneProduitDTO result = ligneProduitService.save(ligneProduitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ligneProduitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ligne-produits} : get all the ligneProduits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ligneProduits in body.
     */
    @GetMapping("/ligne-produits")
    public List<LigneProduitDTO> getAllLigneProduits() {
        log.debug("REST request to get all LigneProduits");
        return ligneProduitService.findAll();
    }

    /**
     * {@code GET  /ligne-produits/:id} : get the "id" ligneProduit.
     *
     * @param id the id of the ligneProduitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ligneProduitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ligne-produits/{id}")
    public ResponseEntity<LigneProduitDTO> getLigneProduit(@PathVariable Long id) {
        log.debug("REST request to get LigneProduit : {}", id);
        Optional<LigneProduitDTO> ligneProduitDTO = ligneProduitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ligneProduitDTO);
    }

    /**
     * {@code DELETE  /ligne-produits/:id} : delete the "id" ligneProduit.
     *
     * @param id the id of the ligneProduitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ligne-produits/{id}")
    public ResponseEntity<Void> deleteLigneProduit(@PathVariable Long id) {
        log.debug("REST request to delete LigneProduit : {}", id);
        ligneProduitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
