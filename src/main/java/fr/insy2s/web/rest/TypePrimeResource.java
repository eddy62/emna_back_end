package fr.insy2s.web.rest;

import fr.insy2s.service.TypePrimeService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.TypePrimeDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.TypePrime}.
 */
@RestController
@RequestMapping("/api")
public class TypePrimeResource {

    private final Logger log = LoggerFactory.getLogger(TypePrimeResource.class);

    private static final String ENTITY_NAME = "typePrime";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypePrimeService typePrimeService;

    public TypePrimeResource(TypePrimeService typePrimeService) {
        this.typePrimeService = typePrimeService;
    }

    /**
     * {@code POST  /type-primes} : Create a new typePrime.
     *
     * @param typePrimeDTO the typePrimeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typePrimeDTO, or with status {@code 400 (Bad Request)} if the typePrime has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-primes")
    public ResponseEntity<TypePrimeDTO> createTypePrime(@Valid @RequestBody TypePrimeDTO typePrimeDTO) throws URISyntaxException {
        log.debug("REST request to save TypePrime : {}", typePrimeDTO);
        if (typePrimeDTO.getId() != null) {
            throw new BadRequestAlertException("A new typePrime cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypePrimeDTO result = typePrimeService.save(typePrimeDTO);
        return ResponseEntity.created(new URI("/api/type-primes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-primes} : Updates an existing typePrime.
     *
     * @param typePrimeDTO the typePrimeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typePrimeDTO,
     * or with status {@code 400 (Bad Request)} if the typePrimeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typePrimeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-primes")
    public ResponseEntity<TypePrimeDTO> updateTypePrime(@Valid @RequestBody TypePrimeDTO typePrimeDTO) throws URISyntaxException {
        log.debug("REST request to update TypePrime : {}", typePrimeDTO);
        if (typePrimeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypePrimeDTO result = typePrimeService.save(typePrimeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typePrimeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-primes} : get all the typePrimes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typePrimes in body.
     */
    @GetMapping("/type-primes")
    public List<TypePrimeDTO> getAllTypePrimes() {
        log.debug("REST request to get all TypePrimes");
        return typePrimeService.findAll();
    }

    /**
     * {@code GET  /type-primes/:id} : get the "id" typePrime.
     *
     * @param id the id of the typePrimeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typePrimeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-primes/{id}")
    public ResponseEntity<TypePrimeDTO> getTypePrime(@PathVariable Long id) {
        log.debug("REST request to get TypePrime : {}", id);
        Optional<TypePrimeDTO> typePrimeDTO = typePrimeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typePrimeDTO);
    }

    /**
     * {@code DELETE  /type-primes/:id} : delete the "id" typePrime.
     *
     * @param id the id of the typePrimeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-primes/{id}")
    public ResponseEntity<Void> deleteTypePrime(@PathVariable Long id) {
        log.debug("REST request to delete TypePrime : {}", id);
        typePrimeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
