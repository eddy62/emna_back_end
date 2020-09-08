package fr.insy2s.web.rest;

import fr.insy2s.domain.Clause;
import fr.insy2s.repository.projection.IContratAllInfoProjection;
import fr.insy2s.repository.projection.IContratEmployerProjection;
import fr.insy2s.service.ClauseService;
import fr.insy2s.service.ContratService;
import fr.insy2s.service.dto.ClauseDTO;
import fr.insy2s.service.dto.ContratDTO;
import fr.insy2s.utils.files.PdfUtil;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.web.rest.vm.*;
import io.github.jhipster.web.util.HeaderUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.*;

/**
 * REST controller for managing {@link fr.insy2s.domain.Contrat}.
 */
@RestController
@RequestMapping("/api")
public class ContratResource {

    private final Logger log = LoggerFactory.getLogger(ContratResource.class);

    private static final String ENTITY_NAME = "contrat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContratService contratService;
    private final ClauseService clauseService;

    public ContratResource(ContratService contratService, ClauseService clauseService) {
        this.contratService = contratService;
        this.clauseService = clauseService;
    }

    /**
     * {@code POST  /contrats} : Create a new contrat.
     *
     * @param contratVM the contratVM to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contratDTO, or with status {@code 400 (Bad Request)} if the contrat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contrats")
    public ResponseEntity<ContratDTO> createContrat(@Valid @RequestBody ContratVM contratVM) throws URISyntaxException {

        log.debug("REST request to save Contrat : {}", contratVM);
        if (contratVM.getId() != null) {
            throw new BadRequestAlertException("A new contrat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        contratVM.setDateCreation(LocalDate.now());
        contratVM.setSigne(false);
        contratVM.setArchive(false);

        ContratDTO contratDTO = new ContratDTO();

        contratDTO.setSocieteId(contratVM.getSocieteId());
        contratDTO.setEmployeId(contratVM.getEmployeId());
        contratDTO.setArchive(contratVM.getArchive());
        contratDTO.setSigne(contratVM.getSigne());
        contratDTO.setDateCreation(contratVM.getDateCreation());
        contratDTO.setTitre(contratVM.getTitre());
        System.err.println("------------------        JE SAVE  MON CONTRA DTO         ------------------");
        ContratDTO result = contratService.save(contratDTO);
        System.err.println("-----------------------------------------------------------------------------");


        List<ClauseVm> listClauseVm = contratVM.getClauses();
        ClauseDTO clauseDto = null;
        if (!listClauseVm.isEmpty()) {
            for (ClauseVm clauseVm : listClauseVm) {
                System.err.println("------------------        JE RENTRE DANS LA BOUCLE         ------------------");
                System.err.println("------------------        JE FAIS APPEL AU CLAUSE SERVICE         ------------------");
                clauseDto = clauseService.findOne(clauseVm.getClauseId()).get();
                if (clauseDto.getId() != null) {
                    System.err.println("------------------        J'AJOUTE A LA LISTE DE CONTRAT LA LISTE DANS CLAUSEDTO       ------------------");
                    Set<ContratDTO> listeContratsDto = clauseDto.getListeContrats();
                    listeContratsDto.add(contratDTO);
                    clauseDto.setListeContrats(listeContratsDto);
                    System.err.println("------------------         CLAUSEDTO TO STRING()    ------------------");
                    System.err.println(clauseDto);
                    System.err.println("------------------        JE SAVE CLAUSEDTO        ------------------");
                }
            }
            clauseService.save(clauseDto);
        }
        System.err.println("------------------        JE GENERE LE PDF        ------------------");
        PdfUtil pdfUtil =new PdfUtil();
        String templateUrl = "C:\\Users\\LHono\\JaspersoftWorkspace\\TemplateContrat\\TemplateContrat.jrxml";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("titre", contratDTO.getTitre());
        List<Object> objects= new ArrayList<>();
        JRDataSource datasource = new JREmptyDataSource();
        objects.add(datasource);
       // pdfUtil.generatePdfReportAsBytes(templateUrl,params,objects);
        return ResponseEntity.created(new URI("/api/contrats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contrats} : Updates an existing contrat.
     *
     * @param contratDTO the contratDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contratDTO,
     * or with status {@code 400 (Bad Request)} if the contratDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contratDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contrats")
    public ResponseEntity<ContratDTO> updateContrat(@Valid @RequestBody ContratDTO contratDTO) throws URISyntaxException {
        log.debug("REST request to update Contrat : {}", contratDTO);
        if (contratDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContratDTO result = contratService.save(contratDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contratDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contrats} : get all the contrats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contrats in body.
     */
    @GetMapping("/contrats")
    public List<ContratDTO> getAllContrats() {
        log.debug("REST request to get all Contrats");
        return contratService.findAll();
    }

    /**
     * {@code GET  /contrats/:id} : get the "id" contrat.
     *
     * @param id the id of the contratDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contratDTO, or with status {@code 404 (Not Found)}.
     */
   /* @GetMapping("/contrats/{id}")
    public ResponseEntity<ContratDTO> getContrat(@PathVariable Long id) {
        log.debug("REST request to get Contrat : {}", id);
        Optional<ContratDTO> contratDTO = contratService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contratDTO);
    }*/

    /**
     * {@code DELETE  /contrats/:id} : delete the "id" contrat.
     *
     * @param id the id of the contratDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contrats/{id}")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        log.debug("REST request to delete Contrat : {}", id);
        contratService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/contrats/{id}")
    public ResponseEntity<ContratAllInfoVM> getTous(@PathVariable Long id) {
        log.debug("REST request to get details Contrat : {}", id);
        List<IContratAllInfoProjection> iContratAllInfoProjections = this.contratService.getContratAllInfos(id);
        Optional<ContratAllInfoVM> contratAllInfoVM = Optional.of(new ContratAllInfoVM());
        if(!iContratAllInfoProjections.isEmpty())
        contratAllInfoVM.get().setContratId(iContratAllInfoProjections.get(0).getContratId());
        return ResponseEntity.ok(contratAllInfoVM.get());
    }

    @GetMapping("/contrats/employer/{id}")
    public List<ContratEmployerVM> getAllContratEmployerByIdSociete(@PathVariable Long id) {
        List<ContratEmployerVM> contratEmployerVMS = new ArrayList<>();
        List<IContratEmployerProjection> iContratEmployerProjections = this.contratService.getAllContratEmployerById(id);
        for (IContratEmployerProjection iContratEmployerProjection: iContratEmployerProjections) {
            System.err.println(contratEmployerVMS);
            contratEmployerVMS.add(new ContratEmployerVM(iContratEmployerProjection.getEmployerId(), iContratEmployerProjection.getContratTitre(), iContratEmployerProjection.getEmployerNom(), iContratEmployerProjection.getEmployerPrenom(), iContratEmployerProjection.getContratSigner(), iContratEmployerProjection.getContratArchiver()));
        }
        return contratEmployerVMS;
    }
}
