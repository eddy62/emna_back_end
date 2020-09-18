package fr.insy2s.web.rest;

import fr.insy2s.service.DevisService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.DevisDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.Devis}.
 */
@RestController
@RequestMapping("/api")
public class DevisResource {

    private final Logger log = LoggerFactory.getLogger(DevisResource.class);

    private static final String ENTITY_NAME = "devis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DevisService devisService;

    public DevisResource(DevisService devisService) {
        this.devisService = devisService;
    }

    /**
     * {@code POST  /devis} : Create a new devis.
     *
     * @param devisDTO the devisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new devisDTO, or with status {@code 400 (Bad Request)} if the devis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/devis")
    public ResponseEntity<DevisDTO> createDevis(@RequestBody DevisDTO devisDTO) throws URISyntaxException {
        log.debug("REST request to save Devis : {}", devisDTO);
        if (devisDTO.getId() != null) {
            throw new BadRequestAlertException("A new devis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DevisDTO result = devisService.save(devisDTO);
        return ResponseEntity.created(new URI("/api/devis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /devis} : Updates an existing devis.
     *
     * @param devisDTO the devisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated devisDTO,
     * or with status {@code 400 (Bad Request)} if the devisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the devisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/devis")
    public ResponseEntity<DevisDTO> updateDevis(@RequestBody DevisDTO devisDTO) throws URISyntaxException {
        log.debug("REST request to update Devis : {}", devisDTO);
        if (devisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DevisDTO result = devisService.save(devisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, devisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /devis} : get all the devis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of devis in body.
     */
    @GetMapping("/devis")
    public List<DevisDTO> getAllDevis() {
        log.debug("REST request to get all Devis");
        return devisService.findAll();
    }

    /**
     * {@code GET  /devis/:id} : get the "id" devis.
     *
     * @param id the id of the devisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the devisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/devis/{id}")
    public ResponseEntity<DevisDTO> getDevis(@PathVariable Long id) {
        log.debug("REST request to get Devis : {}", id);
        Optional<DevisDTO> devisDTO = devisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(devisDTO);
    }

    /**
     * {@code DELETE  /devis/:id} : delete the "id" devis.
     *
     * @param id the id of the devisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/devis/{id}")
    public ResponseEntity<Void> deleteDevis(@PathVariable Long id) {
        log.debug("REST request to delete Devis : {}", id);
        devisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET /devis/societe/:id} : get all the quotes by society id.
     *
     * @param idSociete the id of the society
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quotes in body.
     */
    @GetMapping("/devis/societe/{idSociete}")
    public List<DevisDTO> getAllQuotesBySocietyId(@PathVariable Long idSociete) {
        log.debug("REST request to get all quotes of a society");
        return devisService.findAllQuotesBySocietyId(idSociete);
    }
}
