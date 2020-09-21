package fr.insy2s.web.rest;

import fr.insy2s.service.SaisieArticleService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.SaisieArticleDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.SaisieArticle}.
 */
@RestController
@RequestMapping("/api")
public class SaisieArticleResource {

    private final Logger log = LoggerFactory.getLogger(SaisieArticleResource.class);

    private static final String ENTITY_NAME = "saisieArticle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SaisieArticleService saisieArticleService;

    public SaisieArticleResource(SaisieArticleService saisieArticleService) {
        this.saisieArticleService = saisieArticleService;
    }

    /**
     * {@code POST  /saisie-articles} : Create a new saisieArticle.
     *
     * @param saisieArticleDTO the saisieArticleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new saisieArticleDTO, or with status {@code 400 (Bad Request)} if the saisieArticle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/saisie-articles")
    public ResponseEntity<SaisieArticleDTO> createSaisieArticle(@Valid @RequestBody SaisieArticleDTO saisieArticleDTO) throws URISyntaxException {
        log.debug("REST request to save SaisieArticle : {}", saisieArticleDTO);
        if (saisieArticleDTO.getId() != null) {
            throw new BadRequestAlertException("A new saisieArticle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SaisieArticleDTO result = saisieArticleService.save(saisieArticleDTO);
        return ResponseEntity.created(new URI("/api/saisie-articles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /saisie-articles} : Updates an existing saisieArticle.
     *
     * @param saisieArticleDTO the saisieArticleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated saisieArticleDTO,
     * or with status {@code 400 (Bad Request)} if the saisieArticleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the saisieArticleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/saisie-articles")
    public ResponseEntity<SaisieArticleDTO> updateSaisieArticle(@Valid @RequestBody SaisieArticleDTO saisieArticleDTO) throws URISyntaxException {
        log.debug("REST request to update SaisieArticle : {}", saisieArticleDTO);
        if (saisieArticleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SaisieArticleDTO result = saisieArticleService.save(saisieArticleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, saisieArticleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /saisie-articles} : get all the saisieArticles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of saisieArticles in body.
     */
    @GetMapping("/saisie-articles")
    public List<SaisieArticleDTO> getAllSaisieArticles() {
        log.debug("REST request to get all SaisieArticles");
        return saisieArticleService.findAll();
    }

    /**
     * {@code GET  /saisie-articles/:id} : get the "id" saisieArticle.
     *
     * @param id the id of the saisieArticleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the saisieArticleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/saisie-articles/{id}")
    public ResponseEntity<SaisieArticleDTO> getSaisieArticle(@PathVariable Long id) {
        log.debug("REST request to get SaisieArticle : {}", id);
        Optional<SaisieArticleDTO> saisieArticleDTO = saisieArticleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(saisieArticleDTO);
    }

    /**
     * {@code DELETE  /saisie-articles/:id} : delete the "id" saisieArticle.
     *
     * @param id the id of the saisieArticleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/saisie-articles/{id}")
    public ResponseEntity<Void> deleteSaisieArticle(@PathVariable Long id) {
        log.debug("REST request to delete SaisieArticle : {}", id);
        saisieArticleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
