package fr.insy2s.web.rest;

import fr.insy2s.security.AuthoritiesConstants;
import fr.insy2s.service.AvenantService;
import fr.insy2s.service.DocumentService;
import fr.insy2s.service.FilesStorageService;
import fr.insy2s.service.dto.AvenantDTO;
import fr.insy2s.service.dto.DocumentDTO;
import fr.insy2s.service.dto.SaisieArticleDTO;
import fr.insy2s.utils.TypeDocumentConstants;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.Avenant}.
 */
@RestController
@RequestMapping("/api")
public class AvenantResource {

    private final Logger log = LoggerFactory.getLogger(AvenantResource.class);

    private static final String ENTITY_NAME = "avenant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    FilesStorageService storageService;
    @Autowired
    DocumentService documentService;

    private final AvenantService avenantService;

    public AvenantResource(AvenantService avenantService) {
        this.avenantService = avenantService;
    }

    /**
     * {@code POST  /avenants} : Create a new avenant.
     *
     * @param avenantDTO the avenantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new avenantDTO, or with status {@code 400 (Bad Request)} if the avenant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/avenants")
    public ResponseEntity<AvenantDTO> createAvenant(@Valid @RequestBody AvenantDTO avenantDTO) throws URISyntaxException {
        log.debug("REST request to save Avenant : {}", avenantDTO);
        if (avenantDTO.getId() != null) {
            throw new BadRequestAlertException("A new avenant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AvenantDTO result = avenantService.save(avenantDTO);
        return ResponseEntity.created(new URI("/api/avenants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/avenants/add/new")
    public ResponseEntity<AvenantDTO> createAvenant(@Valid @RequestBody List<SaisieArticleDTO> listSaisieArticle) throws URISyntaxException {
        log.debug("REST request to save Avenant : {}", listSaisieArticle);
//        if (listSaisieArticle.getId() != null) {
//            throw new BadRequestAlertException("A new avenant cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        AvenantDTO result = avenantService.saveFromListeSaisieArticle(listSaisieArticle);
        return ResponseEntity.created(new URI("/api/avenants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /avenants} : Updates an existing avenant.
     *
     * @param avenantDTO the avenantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated avenantDTO,
     * or with status {@code 400 (Bad Request)} if the avenantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the avenantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/avenants")
    public ResponseEntity<AvenantDTO> updateAvenant(@Valid @RequestBody AvenantDTO avenantDTO) throws URISyntaxException {
        log.debug("REST request to update Avenant : {}", avenantDTO);
        if (avenantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AvenantDTO result = avenantService.save(avenantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, avenantDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /avenants} : get all the avenants.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of avenants in body.
     */
    @GetMapping("/avenants")
    public List<AvenantDTO> getAllAvenants() {
        log.debug("REST request to get all Avenants");
        return avenantService.findAll();
    }

    /**
     * {@code GET  /avenants/:id} : get the "id" avenant.
     *
     * @param id the id of the avenantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the avenantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/avenants/{id}")
    public ResponseEntity<AvenantDTO> getAvenant(@PathVariable Long id) {
        log.debug("REST request to get Avenant : {}", id);
        Optional<AvenantDTO> avenantDTO = avenantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(avenantDTO);
    }

    /**
     * {@code DELETE  /avenants/:id} : delete the "id" avenant.
     *
     * @param id the id of the avenantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/avenants/{id}")
    public ResponseEntity<Void> deleteAvenant(@PathVariable Long id) {
        log.debug("REST request to delete Avenant : {}", id);
        avenantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /avenants/:idContrat} : Récupère tout les avenants du contrat passer en id
     *
     * @param idContract id du contrat pour récupèré tout les avenants de celui-ci
     * @return {@link ResponseEntity} avec le status {@code 200 (OK)} et la liste des avenants.
     */
    @GetMapping("avenants/contrats/{idContract}")
    public List<AvenantDTO> getAllAmendmentByContractId(@PathVariable long idContract) {
        log.debug("REST request to get all Amendement by contract id ", idContract);
        return avenantService.getAllAmendmentByContractId(idContract);
    }

    /**
     * {@code GET  /avenant/pdf/:idAmendment} : get the pdf from a releve.
     *
     * @param idAmendment the id of the contract to process.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pdf, or with status {@code 404 (Not Found)}.
     */
    @Secured({
        AuthoritiesConstants.SOCIETY,
        AuthoritiesConstants.ADMIN,
        AuthoritiesConstants.ACCOUNTANT
    })
    @GetMapping("/avenant/pdf/{idAmendment}")
    public ResponseEntity<byte[]> getPDFAmendment(@PathVariable Long idAmendment) throws JRException {
        log.debug("REST request to get avenant by the id ", idAmendment);
        byte[] bytes = avenantService.getPDFAmendment(idAmendment);
        return ResponseEntity.ok()
            .header("Content-Type", "application/pdf; charset=UTF-8")
            .body(bytes);
    }

    @PostMapping("/upload/avenant/{id}/{idContrat}")
    public ResponseEntity<String> uploadSignedAvenant(@RequestParam("files") MultipartFile files, @PathVariable Long id, @PathVariable Long idContrat) {
        String message = "";
        Optional<AvenantDTO> optionalAvenantDTO = avenantService.findOne(id);
        if (optionalAvenantDTO.isEmpty())
            throw new BadRequestAlertException("The contract does not exist", "avenant", "avenantNonExistant");
        AvenantDTO avenantDTO = optionalAvenantDTO.get();
        if (avenantDTO.isSigne())
            throw new BadRequestAlertException("The amendment is already signed", "avenant", "alreadySigned");

        try {
            Path documentPath = storageService.saveAmendment(files, "avenant",idContrat, id);
            try {
                DocumentDTO documentDTO = new DocumentDTO();
                documentDTO.setCheminFichier("./"+documentPath.toString().replace("\\\"", "\""));
                documentDTO.setNom(documentPath.getFileName().toString());
                documentDTO.setContratId(idContrat);
                documentDTO.setAvenantId(id);
                documentDTO.setEmployeId(id);
                documentDTO.setTypeDocumentId(TypeDocumentConstants.AVENANT);
                documentService.save(documentDTO);
                avenantService.signAmendment(id);
                message = "File uploaded with success: " + files.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Error: could not create the entity Document linked to: " + files.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

