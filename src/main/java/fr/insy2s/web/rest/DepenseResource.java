package fr.insy2s.web.rest;

import fr.insy2s.security.AuthoritiesConstants;
import fr.insy2s.service.DepenseService;
import fr.insy2s.service.dto.DepenseDTO;
import fr.insy2s.service.dto.DepenseTemp;
import fr.insy2s.utils.wrapper.WrapperDepense;
import fr.insy2s.utils.wrapper.WrapperListeDepense;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.Depense}.
 */
@RestController
@RequestMapping("/api")
public class DepenseResource {

    private final Logger log = LoggerFactory.getLogger(DepenseResource.class);

    private static final String ENTITY_NAME = "depense";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepenseService depenseService;

    public DepenseResource(DepenseService depenseService) {
        this.depenseService = depenseService;
    }

    /**
     * {@code POST  /depenses} : Create a new depense.
     *
     * @param depenseDTO the depenseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new depenseDTO, or with status {@code 400 (Bad Request)} if the depense has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/depenses")
    public ResponseEntity<DepenseDTO> createDepense(@Valid @RequestBody DepenseDTO depenseDTO) throws URISyntaxException {
        log.debug("REST request to save Depense : {}", depenseDTO);
        if (depenseDTO.getId() != null) {
            throw new BadRequestAlertException("A new depense cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepenseDTO result = depenseService.save(depenseDTO);
        return ResponseEntity.created(new URI("/api/depenses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code POST  /wrapperdepense} : Create a new depense.
     *
     * @param wrapperdepense the depenseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new depenseDTO, or with status {@code 400 (Bad Request)} if the depense has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.SOCIETY})
    @PostMapping("/wrapperdepense")
    public ResponseEntity<DepenseDTO> createDepense(@Valid @RequestBody WrapperDepense wrapperdepense) throws URISyntaxException {
        log.debug("REST request to save Depense from wrapper : {}", wrapperdepense);
        if (wrapperdepense.getId() != null) {
            throw new BadRequestAlertException("A new depense cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepenseDTO result = depenseService.createFromWrapperDepense(wrapperdepense);
        return ResponseEntity.created(new URI("/api/depenses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /depenses} : Updates an existing depense.
     *
     * @param depenseDTO the depenseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated depenseDTO,
     * or with status {@code 400 (Bad Request)} if the depenseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the depenseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/depenses")
    public ResponseEntity<DepenseDTO> updateDepense(@Valid @RequestBody DepenseDTO depenseDTO) throws URISyntaxException {
        log.debug("REST request to update Depense : {}", depenseDTO);
        if (depenseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DepenseDTO result = depenseService.save(depenseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, depenseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /depenses} : Updates an existing depense.
     *
     * @param wrapperDepense the depenseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated depenseDTO,
     * or with status {@code 400 (Bad Request)} if the depenseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the depenseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.SOCIETY})
    @PutMapping("/wrapperdepenses")
    public ResponseEntity<DepenseDTO> updateDepenseFromWrapper(@Valid @RequestBody WrapperDepense wrapperDepense) throws URISyntaxException {
        log.debug("REST request to update Depense from wrapper: {}", wrapperDepense);
        if (wrapperDepense.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DepenseDTO result = depenseService.save(WrapperDepense.toDTO(wrapperDepense));
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, wrapperDepense.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /depenses} : get all the depenses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of depenses in body.
     */
    @GetMapping("/depenses")
    public List<DepenseDTO> getAllDepenses() {
        log.debug("REST request to get all Depenses");
        return depenseService.findAll();
    }

    /**
     * {@code GET  /depenses/:id} : get the "id" depense.
     *
     * @param id the id of the depenseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the depenseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/depenses/{id}")
    public ResponseEntity<WrapperDepense> getDepense(@PathVariable Long id) {
        log.debug("REST request to get Depense : {}", id);
      Optional<WrapperDepense> depenseDTO = depenseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(depenseDTO);
    }

    /**
     * {@code DELETE  /depenses/:id} : delete the "id" depense.
     *
     * @param id the id of the depenseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/depenses/{id}")
    public ResponseEntity<Void> deleteDepense(@PathVariable Long id) {
        log.debug("REST request to delete Depense : {}", id);
        depenseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/depenses/societe/{id}")
    public List<WrapperListeDepense> getAllFactureAchatBySocieteId(@PathVariable Long id) {
        log.debug("REST request to get all Factures By User");
        return depenseService.findAllDepenseBySocieteId(id);
    }

    @PostMapping("/depense/new")
    public ResponseEntity<DepenseDTO> createDepenseForSociete(@ModelAttribute DepenseTemp depenseTemp) throws URISyntaxException, IOException {
        log.debug("REST request to save Facture : {}", depenseTemp);
        DepenseDTO result = depenseService.postDepenseWithFile(depenseTemp);
        return ResponseEntity.created(new URI("/api/factures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
