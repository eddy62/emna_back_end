package fr.insy2s.web.rest;

import fr.insy2s.service.NoteDeFraisService;
import fr.insy2s.service.dto.NoteDeFraisDTO;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link fr.insy2s.domain.NoteDeFrais}.
 */
@RestController
@RequestMapping("/api")
public class NoteDeFraisResource {

    private final Logger log = LoggerFactory.getLogger(NoteDeFraisResource.class);

    private static final String ENTITY_NAME = "noteDeFrais";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NoteDeFraisService noteDeFraisService;

    public NoteDeFraisResource(NoteDeFraisService noteDeFraisService) {
        this.noteDeFraisService = noteDeFraisService;
    }

    /**
     * {@code POST  /note-de-frais} : Create a new noteDeFrais.
     *
     * @param noteDeFraisDTO the noteDeFraisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new noteDeFraisDTO, or with status {@code 400 (Bad Request)} if the noteDeFrais has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/note-de-frais")
    public ResponseEntity<NoteDeFraisDTO> createNoteDeFrais(@Valid @RequestBody NoteDeFraisDTO noteDeFraisDTO) throws URISyntaxException {
        log.debug("REST request to save NoteDeFrais : {}", noteDeFraisDTO);
        if (noteDeFraisDTO.getId() != null) {
            throw new BadRequestAlertException("A new noteDeFrais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoteDeFraisDTO result = noteDeFraisService.save(noteDeFraisDTO);
        return ResponseEntity.created(new URI("/api/note-de-frais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /note-de-frais} : Updates an existing noteDeFrais.
     *
     * @param noteDeFraisDTO the noteDeFraisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noteDeFraisDTO,
     * or with status {@code 400 (Bad Request)} if the noteDeFraisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the noteDeFraisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/note-de-frais")
    public ResponseEntity<NoteDeFraisDTO> updateNoteDeFrais(@Valid @RequestBody NoteDeFraisDTO noteDeFraisDTO) throws URISyntaxException {
        log.debug("REST request to update NoteDeFrais : {}", noteDeFraisDTO);
        if (noteDeFraisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NoteDeFraisDTO result = noteDeFraisService.save(noteDeFraisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, noteDeFraisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /note-de-frais} : get all the noteDeFrais.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of noteDeFrais in body.
     */
    @GetMapping("/note-de-frais")
    public List<NoteDeFraisDTO> getAllNoteDeFrais() {
        log.debug("REST request to get all NoteDeFrais");
        return noteDeFraisService.findAll();
    }

    /**
     * {@code GET  /note-de-frais/:id} : get the "id" noteDeFrais.
     *
     * @param id the id of the noteDeFraisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the noteDeFraisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/note-de-frais/{id}")
    public ResponseEntity<NoteDeFraisDTO> getNoteDeFrais(@PathVariable Long id) {
        log.debug("REST request to get NoteDeFrais : {}", id);
        Optional<NoteDeFraisDTO> noteDeFraisDTO = noteDeFraisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noteDeFraisDTO);
    }

    /**
     * {@code DELETE  /note-de-frais/:id} : delete the "id" noteDeFrais.
     *
     * @param id the id of the noteDeFraisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/note-de-frais/{id}")
    public ResponseEntity<Void> deleteNoteDeFrais(@PathVariable Long id) {
        log.debug("REST request to delete NoteDeFrais : {}", id);
        noteDeFraisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/note-de-frais/employe/{idEmploye}/annee/{annee}/mois/{mois}")
    public List<NoteDeFraisDTO> getAllNoteDeFraisByIdEmployeAndAnneeAndMois(@PathVariable Long idEmploye, @PathVariable Integer annee, @PathVariable Integer mois) {
        log.debug("REST request to get all NoteDeFrais by employe {}, annee {}, mois {}", idEmploye, annee, mois);
        return noteDeFraisService.findAllNoteDeFraisByIdEmployeAndAnneeAndMois(idEmploye, annee, mois);
    }
}
