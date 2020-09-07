package fr.insy2s.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insy2s.repository.projection.IEmployeContratProjection;
import fr.insy2s.service.EmployeService;
import fr.insy2s.service.dto.EmployeDTO;
import fr.insy2s.utils.wrapper.WrapperEmploye;
import fr.insy2s.utils.wrapper.WrapperVariablesPaie;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.web.rest.vm.ArticleVM;
import fr.insy2s.web.rest.vm.ClauseVm;
import fr.insy2s.web.rest.vm.EmployeEtArticleVM;
import fr.insy2s.web.rest.vm.EmployerVM;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link fr.insy2s.domain.Employe}.
 */
@RestController
@RequestMapping("/api")
public class EmployeResource {

    private final Logger         log         = LoggerFactory.getLogger(EmployeResource.class);

    private static final String  ENTITY_NAME = "employe";

    @Value("${jhipster.clientApp.name}")
    private String               applicationName;

    private final EmployeService employeService;

    public EmployeResource(EmployeService employeService) {
        this.employeService = employeService;
    }

    /**
     * {@code POST  /employes} : Create a new employe.
     *
     * @param employeDTO the employeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employeDTO, or with status {@code 400 (Bad Request)} if the employe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/employes")
    public ResponseEntity<EmployeDTO> createEmploye(@Valid @RequestBody EmployeDTO employeDTO) throws URISyntaxException {
        log.debug("REST request to save Employe : {}", employeDTO);
        if (employeDTO.getId() != null) {
            throw new BadRequestAlertException("A new employe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmployeDTO result = employeService.save(employeDTO);
        return ResponseEntity.created(new URI("/api/employes/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
    }

    /**
     * {@code PUT  /employes} : Updates an existing employe.
     *
     * @param employeDTO the employeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employeDTO, or with status {@code 400 (Bad Request)} if the employeDTO is not valid, or with status
     *         {@code 500 (Internal Server Error)} if the employeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/employes")
    public ResponseEntity<EmployeDTO> updateEmploye(@Valid @RequestBody EmployeDTO employeDTO) throws URISyntaxException {
        log.debug("REST request to update Employe : {}", employeDTO);
        if (employeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmployeDTO result = employeService.save(employeDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employeDTO.getId().toString())).body(result);
    }

    /**
     * {@code GET  /employes} : get all the employes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employes in body.
     */
    @GetMapping("/employes")
    public List<EmployeDTO> getAllEmployes() {
        log.debug("REST request to get all Employes");
        return employeService.findAll();
    }

    /**
     * {@code GET  /employes/:id} : get the "id" employe.
     *
     * @param id the id of the employeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/employes/{id}")
    public ResponseEntity<EmployeDTO> getEmploye(@PathVariable Long id) {
        log.debug("REST request to get Employe : {}", id);
        Optional<EmployeDTO> employeDTO = employeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(employeDTO);
    }

    /**
     * {@code DELETE  /employes/:id} : delete the "id" employe.
     *
     * @param id the id of the employeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/employes/{id}")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Long id) {
        log.debug("REST request to delete Employe : {}", id);
        employeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/employer/article/clause/societe/{id}")
    public EmployeEtArticleVM getAllEmployeArticleClauseBySocieteId(@PathVariable Long id) {
        EmployeEtArticleVM employeEtArticleVM = new EmployeEtArticleVM();
        List<EmployerVM> listEmployer = new ArrayList<>();
        List<IEmployeContratProjection> listIEmployeContratProjections = this.employeService.getAllEmployeArticleClauseBySocieteId(id);

        List<ArticleVM> listArticle = new ArrayList<>();
        List<ClauseVm> listClause = new ArrayList<>();

        int index = 1;
        boolean present = false;
        boolean presentEmploye = false;
        for (IEmployeContratProjection iEmployeContratProjection : listIEmployeContratProjections) { //*19
            present = false;
            presentEmploye = false;
            ArticleVM articleVM = new ArticleVM();
            ClauseVm clauseVm = new ClauseVm();
            EmployerVM employerVM = new EmployerVM();
            employerVM.setEmployerId(iEmployeContratProjection.getEmployerId());
            employerVM.setEmployerNom(iEmployeContratProjection.getEmployerNom());
            employerVM.setEmployerPrenom(iEmployeContratProjection.getEmployerPrenom());
            employerVM.setSocieteId(iEmployeContratProjection.getSocieteId());
            for (EmployerVM employe : listEmployer) {
                if (employe.getEmployerId() == iEmployeContratProjection.getEmployerId()) {
                    presentEmploye = true;
                }
            }
            if (!presentEmploye) {
                listEmployer.add(employerVM);
            }
            articleVM.setArticleId(iEmployeContratProjection.getArticleId());
            articleVM.setArticleTitre(iEmployeContratProjection.getArticleTitre());
            articleVM.setArticleDescription(iEmployeContratProjection.getArticleDescription());
            articleVM.setArticleReference(iEmployeContratProjection.getArticleReference());
            articleVM.setListClauses(new ArrayList<>());
            if (articleVM.getArticleId() == index) {
                listArticle.add(articleVM);
                index++;
            }
            for (ClauseVm clause : listClause) {
                if (iEmployeContratProjection.getClauseId() == clause.getClauseId()) {
                    present = true;
                }
            }
            if (!present) {
                clauseVm.setArticleId(iEmployeContratProjection.getArticleId());
                clauseVm.setClauseId(iEmployeContratProjection.getClauseId());
                clauseVm.setClauseDesciption(iEmployeContratProjection.getClauseDescription());

                listClause.add(clauseVm);

            }
        }

        for (ClauseVm clause : listClause) {
            int integ = Math.toIntExact(clause.getArticleId());
            listArticle.get(integ - 1).getListClauses().add(clause);
        }

        employeEtArticleVM.setArticleVMList(listArticle);
        employeEtArticleVM.setEmployerVMList(listEmployer);

        return employeEtArticleVM;

    }

    /**
     * {@code GET  /wrapperemployes} : get all the wrapperEmployes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wrapperEmployes in body.
     */
    @GetMapping("/wrapperemployes")
    public List<WrapperEmploye> getAllWrapperEmployes() {
        log.debug("REST request to get all WrapperEmployes");
        List<WrapperEmploye> list = employeService.findAllWrapperEmploye();
        return list;
    }

    /**
     * {@code GET /wrapperemployes/society/:id} : get all the wrapperEmployes by society.
     *
     * @param id of the society
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of society's wrapperEmployes in body.
     */
    @GetMapping("/wrapperemployes/society/{id}")
    public List<WrapperEmploye> getAllWrapperEmployesBySociety(@PathVariable Long id) {
        log.debug("REST request to get all WrapperEmployes by society : {}", id);
        List<WrapperEmploye> list = employeService.findAllWrapperEmployeBySociete(id);
        return list;
    }

    /**
     * {@code GET  /wrapperemployes/:id} : get the "id" wrapperEmploye.
     *
     * @param id the id of the wrapperEmploye to retrieve
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wrapperEmploye, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wrapperemployes/{id}")
    public ResponseEntity<WrapperEmploye> getWrapperEmploye(@PathVariable Long id) {
        log.debug("REST request to get WrapperEmploye : {}", id);
        Optional<WrapperEmploye> wrapperEmploye = employeService.findById(id);
        return ResponseUtil.wrapOrNotFound(wrapperEmploye);
    }

    /**
     * {@code POST  /wrapperEmploye} : Create a new wrapperEmploye.
     *
     * @param wrapperEmploye the wrapperEmploye to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wrapperEmploye, or with status {@code 400 (Bad Request)} if the wrapperEmploye has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wrapperemployes")
    public ResponseEntity<WrapperEmploye> createWrapperEmploye(@Valid @RequestBody WrapperEmploye wrapperEmploye) throws URISyntaxException {
        log.debug("REST request to save WrapperEmploye : {}", wrapperEmploye);
        if (wrapperEmploye.getId() != null) {
            throw new BadRequestAlertException("A new employe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (employeService.isEmployeMatriculeExist(wrapperEmploye.getMatricule())) {
            throw new BadRequestAlertException("Le numero de matricule existe déjà !", ENTITY_NAME, " Numero Matricule unique");
        }
        if ((LocalDate.now().getYear() - wrapperEmploye.getDateNaissance().getYear()) < 14) {
            throw new BadRequestAlertException("L'âge minimum pour travailler est de 14 ans !", ENTITY_NAME, " Date de naissance incorrect");
        }
        Optional<WrapperEmploye> result = employeService.createWrapperEmploye(wrapperEmploye);

        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     * {@code PUT  /wrapperEmploye} : Update an existing wrapperEmploye.
     *
     * @param wrapperEmploye the wrapperEmploye to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wrapperEmploye, or with status {@code 400 (Bad Request)} if the wrapperEmploye is not valid, or with
     *         status {@code 500 (Internal Server Error)} if the wrapperEmploye couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wrapperemployes")
    public ResponseEntity<WrapperEmploye> updateWrapperEmploye(@Valid @RequestBody WrapperEmploye wrapperEmploye) throws URISyntaxException {
        log.debug("REST request to update WrapperEmploye : {}", wrapperEmploye);
        if (wrapperEmploye.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WrapperEmploye result = employeService.updateWrapperEmploye(wrapperEmploye);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, wrapperEmploye.getId().toString())).body(result);
    }

    /**
     * {@code DELETE  /wrapperemployes/:id} : delete the "id" wrapperemployes.
     *
     * @param id the id of the wrapperemployes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wrapperemployes/{id}")
    public boolean deleteWrapperEmploye(@PathVariable Long id) {
        log.debug("REST request to delete WrapperEmploye : {}", id);
        boolean result = employeService.deleteWrapperEmploye(id);
        return result;
    }

    /**
     * {@code GET /wrapperemployes/society/:id/typecontrat/:type} : get all the wrapperEmployes by society and by type of contract.
     *
     * @param id of the society
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of society's wrapperEmployes in body.
     */
    @GetMapping("/wrapperemployes/society/{id}/typecontrat/{type}")
    public List<WrapperEmploye> getAllWrapperEmployesBySocietyAndTypeContrat(@PathVariable Long id, @PathVariable String type) {
        log.debug("REST request to get all WrapperEmployes by society and by type of Contract : {}", id, type);
        List<WrapperEmploye> list = employeService.findAllWrapperEmployeBySociete(id);
        List<WrapperEmploye> listeSelect = new ArrayList<WrapperEmploye>();
        for (WrapperEmploye wrapperEmploye : list) {
            if (wrapperEmploye.getCodeTypeContrat().equals(type)) {
                listeSelect.add(wrapperEmploye);
            }
        }

        return listeSelect;
    }

    /**
     * {@code GET /wrapperemployes/society/:id/typecontrat/:type} : get all the wrapperEmployes by society and by type of contract.
     *
     * @param id of the society
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of society's wrapperEmployes in body.
     */
    @GetMapping("/wrapperemployes/society/{id}/statutemploye/{codestatut}")
    public List<WrapperEmploye> getAllWrapperEmployesBySocietyAndSatutEmploye(@PathVariable Long id, @PathVariable String codestatut) {
        log.debug("REST request to get all WrapperEmployes by society and by type of Contract : {}", id, codestatut);
        List<WrapperEmploye> list = employeService.findAllWrapperEmployeBySociete(id);
        List<WrapperEmploye> listeSelect = new ArrayList<WrapperEmploye>();
        for (WrapperEmploye wrapperEmploye : list) {
            if (wrapperEmploye.getCodeRef().equals(codestatut)) {
                listeSelect.add(wrapperEmploye);
            }
        }

        return listeSelect;
    }

    /**
     * {@code PUT  /wrapperEmploye/archive} : Archive an existing wrapperEmploye.
     *
     * @param wrapperEmploye the wrapperEmploye to archive.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wrapperEmploye, or with status {@code 400 (Bad Request)} if the wrapperEmploye is not valid, or with
     *         status {@code 500 (Internal Server Error)} if the wrapperEmploye couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wrapperemploye/archive")
    public ResponseEntity<WrapperEmploye> archiveWrapperEmploye(@Valid @RequestBody WrapperEmploye wrapperEmploye) throws URISyntaxException {
        log.debug("REST request to archive WrapperEmploye : {}", wrapperEmploye);
        if (wrapperEmploye.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WrapperEmploye result = employeService.archiveWrapperEmploye(wrapperEmploye);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, wrapperEmploye.getId().toString())).body(result);
    }

    @GetMapping("/wrappervariablespaie/employe/{idEmploye}/annee/{annee}/mois/{mois}")
    public WrapperVariablesPaie getOneWrapperVariablesPaieByIdEmployeAndAnneeAndMois(@PathVariable Long idEmploye, @PathVariable Integer annee, @PathVariable Integer mois) {
        log.debug("REST request to get one WrapperVariablesPaie by employe, annee, mois : {}", idEmploye, annee, mois);
        return employeService.findOneWrapperVariablesPaieByIdEmployeAndAnneeAndMois(idEmploye, annee, mois);
    }

}
