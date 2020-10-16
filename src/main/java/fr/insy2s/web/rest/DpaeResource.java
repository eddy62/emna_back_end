package fr.insy2s.web.rest;

import fr.insy2s.security.AuthoritiesConstants;
import fr.insy2s.service.DpaeService;
import fr.insy2s.service.dto.DpaeDTO;
import fr.insy2s.utils.files.PdfUtil;
import fr.insy2s.utils.wrapper.WrapperDpae;
import fr.insy2s.utils.wrapper.WrapperPdfDpae;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.Dpae}.
 */
@RestController
@RequestMapping("/api")
public class DpaeResource {

    private final Logger log = LoggerFactory.getLogger(DpaeResource.class);

    private static final String ENTITY_NAME = "dpae";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DpaeService dpaeService;

    public DpaeResource(DpaeService dpaeService) {
        this.dpaeService = dpaeService;
    }

    /**
     * {@code POST  /dpaes} : Create a new dpae.
     *
     * @param dpaeDTO the dpaeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dpaeDTO, or with status {@code 400 (Bad Request)} if the dpae has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dpaes")
    public ResponseEntity<DpaeDTO> createDpae(@Valid @RequestBody DpaeDTO dpaeDTO) throws URISyntaxException {
        log.debug("REST request to save Dpae : {}", dpaeDTO);
        if (dpaeDTO.getId() != null) {
            throw new BadRequestAlertException("A new dpae cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DpaeDTO result = dpaeService.save(dpaeDTO);
        return ResponseEntity.created(new URI("/api/dpaes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dpaes} : Updates an existing dpae.
     *
     * @param dpaeDTO the dpaeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dpaeDTO,
     * or with status {@code 400 (Bad Request)} if the dpaeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dpaeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dpaes")
    public ResponseEntity<DpaeDTO> updateDpae(@Valid @RequestBody DpaeDTO dpaeDTO) throws URISyntaxException {
        log.debug("REST request to update Dpae : {}", dpaeDTO);
        if (dpaeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DpaeDTO result = dpaeService.save(dpaeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dpaeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dpaes} : get all the dpaes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dpaes in body.
     */
    @GetMapping("/dpaes")
    public List<DpaeDTO> getAllDpaes() {
        log.debug("REST request to get all Dpaes");
        return dpaeService.findAll();
    }

    /**
     * {@code GET  /dpaes/:id} : get the "id" dpae.
     *
     * @param id the id of the dpaeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dpaeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dpaes/{id}")
    public ResponseEntity<DpaeDTO> getDpae(@PathVariable Long id) {
        log.debug("REST request to get Dpae : {}", id);
        Optional<DpaeDTO> dpaeDTO = dpaeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dpaeDTO);
    }

    /**
     * {@code DELETE  /dpaes/:id} : delete the "id" dpae.
     *
     * @param id the id of the dpaeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dpaes/{id}")
    public ResponseEntity<Void> deleteDpae(@PathVariable Long id) {
        log.debug("REST request to delete Dpae : {}", id);
        dpaeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /dpae/pdf/:id} : get the pdf from a dpae.
     *
     * @param id the id of the dpae to process.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pdf, or with status {@code 404 (Not Found)}.
     */
    @Secured({
        AuthoritiesConstants.SOCIETY,
        AuthoritiesConstants.ADMIN,
        AuthoritiesConstants.ACCOUNTANT
    })
    @GetMapping("/dpae/pdf/{id}")
    public ResponseEntity<byte[]> generateDpaeAsBytes(@PathVariable Long id) throws JRException {
        log.debug("REST request to get dpae pdf: {}", id);
        WrapperPdfDpae wrapperPdfDpae = dpaeService.getWrapperPdfDpae(id);
        byte[] bytes    = PdfUtil.generateDpaeAsBytes(wrapperPdfDpae);
        String pdfName  = new Date().getTime()
            + "_DPAE_"
            + wrapperPdfDpae.getSurname() + "_"
            + wrapperPdfDpae.getId();
        return ResponseEntity.ok()
            .header("Content-Type", "application/pdf; charset=UTF-8")
            .header("Content-Disposition","attachment; filename=\"" + pdfName + ".pdf\"")
            .body(bytes);
    }

    /**
     * {@code GET /dpae/detail/{id}} : get one wrapperDpae by id of one dpae.
     *
     * @param id the id of the dpae to process
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the wrapperDpae in body
     */
    @GetMapping("/dpae/detail/{id}")
    public WrapperDpae getWrapperDpaeById(@PathVariable Long id) {
        log.debug("REST request to get one WrapperDpae by id:{}", id);
        return dpaeService.findWrapperDpaeById(id);
    }
}
