package fr.insy2s.web.rest;

import fr.insy2s.service.InfoEntrepriseService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.InfoEntrepriseDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.InfoEntreprise}.
 */
@RestController
@RequestMapping("/api")
public class InfoEntrepriseResource {

    private final Logger log = LoggerFactory.getLogger(InfoEntrepriseResource.class);

    private static final String ENTITY_NAME = "infoEntreprise";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InfoEntrepriseService infoEntrepriseService;

    public InfoEntrepriseResource(InfoEntrepriseService infoEntrepriseService) {
        this.infoEntrepriseService = infoEntrepriseService;
    }

    /**
     * {@code POST  /info-entreprises} : Create a new infoEntreprise.
     *
     * @param infoEntrepriseDTO the infoEntrepriseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new infoEntrepriseDTO, or with status {@code 400 (Bad Request)} if the infoEntreprise has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/info-entreprises")
    public ResponseEntity<InfoEntrepriseDTO> createInfoEntreprise(@RequestBody InfoEntrepriseDTO infoEntrepriseDTO) throws URISyntaxException {
        log.debug("REST request to save InfoEntreprise : {}", infoEntrepriseDTO);
        if (infoEntrepriseDTO.getId() != null) {
            throw new BadRequestAlertException("A new infoEntreprise cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InfoEntrepriseDTO result = infoEntrepriseService.save(infoEntrepriseDTO);
        return ResponseEntity.created(new URI("/api/info-entreprises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /info-entreprises} : Updates an existing infoEntreprise.
     *
     * @param infoEntrepriseDTO the infoEntrepriseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated infoEntrepriseDTO,
     * or with status {@code 400 (Bad Request)} if the infoEntrepriseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the infoEntrepriseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/info-entreprises")
    public ResponseEntity<InfoEntrepriseDTO> updateInfoEntreprise(@RequestBody InfoEntrepriseDTO infoEntrepriseDTO) throws URISyntaxException {
        log.debug("REST request to update InfoEntreprise : {}", infoEntrepriseDTO);
        if (infoEntrepriseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InfoEntrepriseDTO result = infoEntrepriseService.save(infoEntrepriseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, infoEntrepriseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /info-entreprises} : get all the infoEntreprises.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of infoEntreprises in body.
     */
    @GetMapping("/info-entreprises")
    public List<InfoEntrepriseDTO> getAllInfoEntreprises() {
        log.debug("REST request to get all InfoEntreprises");
        return infoEntrepriseService.findAll();
    }

    /**
     * {@code GET  /info-entreprises/:id} : get the "id" infoEntreprise.
     *
     * @param id the id of the infoEntrepriseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the infoEntrepriseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/info-entreprises/{id}")
    public ResponseEntity<InfoEntrepriseDTO> getInfoEntreprise(@PathVariable Long id) {
        log.debug("REST request to get InfoEntreprise : {}", id);
        Optional<InfoEntrepriseDTO> infoEntrepriseDTO = infoEntrepriseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(infoEntrepriseDTO);
    }

    /**
     * {@code DELETE  /info-entreprises/:id} : delete the "id" infoEntreprise.
     *
     * @param id the id of the infoEntrepriseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/info-entreprises/{id}")
    public ResponseEntity<Void> deleteInfoEntreprise(@PathVariable Long id) {
        log.debug("REST request to delete InfoEntreprise : {}", id);
        infoEntrepriseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
