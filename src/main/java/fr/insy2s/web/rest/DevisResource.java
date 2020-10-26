package fr.insy2s.web.rest;

import fr.insy2s.security.AuthoritiesConstants;
import fr.insy2s.service.DevisService;
import fr.insy2s.utils.wrapper.WrapperQuote;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.DevisDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
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
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    @DeleteMapping("/devis/{id}")
    public ResponseEntity<Void> deleteDevis(@PathVariable Long id) {
        log.debug("REST request to delete Devis : {}", id);
        devisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET /devis/liste/societe/:id} : get all the Quotes Wrapper by society id.
     *
     * @param id the id of the society
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of WrapperQuotes in body.
     */
    @GetMapping("/devis/liste/societe/{id}")
    public List<WrapperQuote> getQuotesBySociety(@PathVariable Long id) {
        log.debug("Request to get all the WrapperQuotes by society id: {}", id);
        return devisService.findAllWrapperQuotes(id);
    }

    /**
     * {@code GET  /devis/detail/:id} : get the "id" quote.
     *
     * @param id the id of the wrapperQuote to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wrapperQuote, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("devis/detail/{id}")
    public WrapperQuote getQuote(@PathVariable Long id) {
        log.debug("REST request to get Quote : {}", id);
        return devisService.findQuote(id);
    }

    /**
     * {@code POST  /devis/nouveau} : Create a new quote.
     *
     * @param quote the wrapperQuote to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wrapperQuote, or with status {@code 400 (Bad Request)} if the wrapperQuote has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/devis/nouveau")
    public ResponseEntity<DevisDTO> createQuote(@RequestBody WrapperQuote quote) throws URISyntaxException {
        log.debug("REST request to save Quote : {}", quote);
        if (quote.getId() != null) {
            throw new BadRequestAlertException("A new quote cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DevisDTO result = devisService.saveQuote(quote);
        return ResponseEntity.created(new URI("/api/devis/nouveau" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /devis/nouveau/numero/:id} : get the "id" devis for get the number of the new quote .
     *
     * @param id the id of the devisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the devisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/devis/nouveau/numero/{id}")
    public Long getNewQuoteNumber(@PathVariable Long id) {
        log.debug("REST request to get the number of the new quote : {}", id);
        return devisService.findQuoteNumber(id);
    }
    /**
     * {@code PUT /quote/stateChange/{id}} : Change the state of the quote  .
     * @param id
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the devisDTO, or with status {@code 404 (Not Found)}
     */

    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")"+" || hasAuthority(\"" + AuthoritiesConstants.SOCIETY +"\")")
    @PutMapping("/quote/stateChange/{id}")
    public ResponseEntity<DevisDTO> updateStateQuote(@PathVariable Long id, Principal principal){
   System.out.println(principal.getName());

    	return ResponseUtil.wrapOrNotFound(devisService.changeState(id));
    }
}
