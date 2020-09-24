package fr.insy2s.web.rest;

import fr.insy2s.domain.FichePaie;
import fr.insy2s.service.FichePaieService;
import fr.insy2s.utils.wrapper.WrapperVariablesPaie;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.FichePaieDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.FichePaie}.
 */
@RestController
@RequestMapping("/api")
public class FichePaieResource {

    private final Logger log = LoggerFactory.getLogger(FichePaieResource.class);

    private static final String ENTITY_NAME = "fichePaie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FichePaieService fichePaieService;

    public FichePaieResource(FichePaieService fichePaieService) {
        this.fichePaieService = fichePaieService;
    }

    /**
     * {@code POST  /fiche-paies} : Create a new fichePaie.
     *
     * @param fichePaieDTO the fichePaieDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fichePaieDTO, or with status {@code 400 (Bad Request)} if the fichePaie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fiche-paies")
    public ResponseEntity<FichePaieDTO> createFichePaie(@Valid @RequestBody FichePaieDTO fichePaieDTO) throws URISyntaxException {
        log.debug("REST request to save FichePaie : {}", fichePaieDTO);
        if (fichePaieDTO.getId() != null) {
            throw new BadRequestAlertException("A new fichePaie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FichePaieDTO result = fichePaieService.save(fichePaieDTO);
        return ResponseEntity.created(new URI("/api/fiche-paies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fiche-paies} : Updates an existing fichePaie.
     *
     * @param fichePaieDTO the fichePaieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fichePaieDTO,
     * or with status {@code 400 (Bad Request)} if the fichePaieDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fichePaieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fiche-paies")
    public ResponseEntity<FichePaieDTO> updateFichePaie(@Valid @RequestBody FichePaieDTO fichePaieDTO) throws URISyntaxException {
        log.debug("REST request to update FichePaie : {}", fichePaieDTO);
        if (fichePaieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FichePaieDTO result = fichePaieService.save(fichePaieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fichePaieDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fiche-paies} : get all the fichePaies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fichePaies in body.
     */
    @GetMapping("/fiche-paies")
    public List<FichePaieDTO> getAllFichePaies() {
        log.debug("REST request to get all FichePaies");
        return fichePaieService.findAll();
    }

    /**
     * {@code GET  /fiche-paies/:id} : get the "id" fichePaie.
     *
     * @param id the id of the fichePaieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fichePaieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fiche-paies/{id}")
    public ResponseEntity<FichePaieDTO> getFichePaie(@PathVariable Long id) {
        log.debug("REST request to get FichePaie : {}", id);
        Optional<FichePaieDTO> fichePaieDTO = fichePaieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fichePaieDTO);
    }

    /**
     * {@code GET /fiche-paies/employe/:idEmploye/annee/:year/moisDu/:monthStart/moisFin/:monthEnd} : get all the PaySlip by one employe, by one year and months.
     *
     * @param idEmploye id of the Employe in all PaySLip
     * @param year      year in all PaySLip
     * @param monthStart      min month in all PaySLip
     * @param monthEnd        max month in all PaySLip
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the wrapperVariablesPaie in body
     */
    @GetMapping("/fiche-paies/employe/{idEmploye}/annee/{year}/moisDu/{monthStart}/moisFin/{monthEnd}")
    public List<FichePaieDTO> getAllPayslipByEmployeIdMonthStartMonthEnd(@PathVariable Long idEmploye, @PathVariable Integer year, @PathVariable Integer monthStart , @PathVariable Integer monthEnd) {
        log.debug("REST request to get all PaySlip by employe:{}, annee:{}, moisDu:{}, moisFin:{}", idEmploye, year, monthStart, monthEnd);
        return fichePaieService.findAllByEmployeYearMonthStartMonthEnd(idEmploye, year, monthStart, monthEnd);
    }

    /**
     * {@code DELETE  /fiche-paies/:id} : delete the "id" fichePaie.
     *
     * @param id the id of the fichePaieDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fiche-paies/{id}")
    public ResponseEntity<Void> deleteFichePaie(@PathVariable Long id) {
        log.debug("REST request to delete FichePaie : {}", id);
        fichePaieService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
