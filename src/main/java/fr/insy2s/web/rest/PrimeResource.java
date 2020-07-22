package fr.insy2s.web.rest;

import fr.insy2s.service.PrimeService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.PrimeDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.Prime}.
 */
@RestController
@RequestMapping("/api")
public class PrimeResource {

    private final Logger log = LoggerFactory.getLogger(PrimeResource.class);

    private static final String ENTITY_NAME = "prime";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrimeService primeService;

    public PrimeResource(PrimeService primeService) {
        this.primeService = primeService;
    }

    /**
     * {@code POST  /primes} : Create a new prime.
     *
     * @param primeDTO the primeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new primeDTO, or with status {@code 400 (Bad Request)} if the prime has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/primes")
    public ResponseEntity<PrimeDTO> createPrime(@Valid @RequestBody PrimeDTO primeDTO) throws URISyntaxException {
        log.debug("REST request to save Prime : {}", primeDTO);
        if (primeDTO.getId() != null) {
            throw new BadRequestAlertException("A new prime cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrimeDTO result = primeService.save(primeDTO);
        return ResponseEntity.created(new URI("/api/primes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /primes} : Updates an existing prime.
     *
     * @param primeDTO the primeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated primeDTO,
     * or with status {@code 400 (Bad Request)} if the primeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the primeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/primes")
    public ResponseEntity<PrimeDTO> updatePrime(@Valid @RequestBody PrimeDTO primeDTO) throws URISyntaxException {
        log.debug("REST request to update Prime : {}", primeDTO);
        if (primeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrimeDTO result = primeService.save(primeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, primeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /primes} : get all the primes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of primes in body.
     */
    @GetMapping("/primes")
    public List<PrimeDTO> getAllPrimes() {
        log.debug("REST request to get all Primes");
        return primeService.findAll();
    }

    /**
     * {@code GET  /primes/:id} : get the "id" prime.
     *
     * @param id the id of the primeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the primeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/primes/{id}")
    public ResponseEntity<PrimeDTO> getPrime(@PathVariable Long id) {
        log.debug("REST request to get Prime : {}", id);
        Optional<PrimeDTO> primeDTO = primeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(primeDTO);
    }

    /**
     * {@code DELETE  /primes/:id} : delete the "id" prime.
     *
     * @param id the id of the primeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/primes/{id}")
    public ResponseEntity<Void> deletePrime(@PathVariable Long id) {
        log.debug("REST request to delete Prime : {}", id);
        primeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
