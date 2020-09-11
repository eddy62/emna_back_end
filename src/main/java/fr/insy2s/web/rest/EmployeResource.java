package fr.insy2s.web.rest;

import fr.insy2s.repository.projection.IEmployeContratProjection;
import fr.insy2s.service.*;
import fr.insy2s.service.dto.*;
import fr.insy2s.service.mapper.WrapperAbsenceMapper;
import fr.insy2s.service.mapper.WrapperPrimeMapper;
import fr.insy2s.utils.wrapper.WrapperAbsence;
import fr.insy2s.utils.wrapper.WrapperEmploye;
import fr.insy2s.utils.wrapper.WrapperPrime;
import fr.insy2s.utils.wrapper.WrapperVariablesPaie;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.web.rest.vm.ArticleVM;
import fr.insy2s.web.rest.vm.ClauseVm;
import fr.insy2s.web.rest.vm.EmployeEtArticleVM;
import fr.insy2s.web.rest.vm.EmployerVM;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Variables de Paie

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

    // Variables de paie
    private final AbsenceService absenceService;
    private final WrapperAbsenceMapper wrapperAbsenceMapper;
    private final AutresVariableService autresVariableService;
    private final AvanceRappelSalaireService avanceRappelSalaireService;
    private final HeuresSupplementairesService heuresSupplementairesService;
    private final NoteDeFraisService noteDeFraisService;
    private final PrimeService primeService;
    private final WrapperPrimeMapper wrapperPrimeMapper;

    public EmployeResource(EmployeService employeService,
                           AvanceRappelSalaireService avanceRappelSalaireService,
                           AbsenceService absenceService,
                           WrapperAbsenceMapper wrapperAbsenceMapper,
                           AutresVariableService autresVariableService,
                           HeuresSupplementairesService heuresSupplementairesService,
                           NoteDeFraisService noteDeFraisService, PrimeService primeService,
                           WrapperPrimeMapper wrapperPrimeMapper) {

        this.employeService = employeService;

        // Variables de paie
        this.absenceService = absenceService;
        this.wrapperAbsenceMapper = wrapperAbsenceMapper;
        this.autresVariableService = autresVariableService;
        this.avanceRappelSalaireService = avanceRappelSalaireService;
        this.heuresSupplementairesService = heuresSupplementairesService;
        this.noteDeFraisService = noteDeFraisService;
        this.primeService = primeService;
        this.wrapperPrimeMapper = wrapperPrimeMapper;
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
            if ((wrapperEmploye.getCodeTypeContrat().equals(type)) && (wrapperEmploye.getStatutEmployeId() == 2)) {
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

    /**
     * {@code GET /wrappervariablespaie/employe/:idEmploye/annee/:annee/mois/:mois} : get one wrapperVariablesPaie by one employe, by one year and by one month.
     *
     * @param idEmploye id of the Employe in all VariablesPaie
     * @param annee     year in all VariablesPaie
     * @param mois      month in all VariablesPaie
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the wrapperVariablesPaie in body
     */
    @GetMapping("/wrappervariablespaie/employe/{idEmploye}/annee/{annee}/mois/{mois}")
    public WrapperVariablesPaie getOneWrapperVariablesPaieByIdEmployeAndAnneeAndMois(@PathVariable Long idEmploye, @PathVariable Integer annee, @PathVariable Integer mois) {
        log.debug("REST request to get one WrapperVariablesPaie by employe:{}, annee:{}, mois:{}", idEmploye, annee, mois);
        return employeService.findOneWrapperVariablesPaieByIdEmployeAndAnneeAndMois(idEmploye, annee, mois);
    }

    /**
     * {@code PUT  /wrappervariablespaie/confirm-variablespaie} : Confirm all Variables de Paie in a WrapperVariablesPaie
     *              etatVariablePaie 1 -> 2
     *
     * @param wrapperVariablesPaieToUpdate the WrapperVariablesPaie containing all the Variables de Paie to update
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} if all the Variables de Paie were updated,
     *      or with status {@code 206 (Partial Content)} if partial Variables de Paie were updated,
     *      or with status {@code 400 (Bad Request)} if none Variables de Paie were updated,
     *      and with body the String "Variables de paie confirmées : x / y " And eventual Variables de Paie not updated
     * @throws URISyntaxException if one of the Variables de Paie objects id is null or does not exist
     */
    @PutMapping("/wrappervariablespaie/confirm-variablespaie")
    public ResponseEntity<String> updateConfirmWrapperVariablesPaie(@Valid @RequestBody WrapperVariablesPaie wrapperVariablesPaieToUpdate) throws URISyntaxException {
        log.debug("REST request to update one WrapperVariablesPaie, state: Confirm {}", wrapperVariablesPaieToUpdate);
        // contient wrapperAbsenceList, autresVariableDTOList, avanceRappelSalaireDTOList, heuresSupplementairesDTOList, noteDeFraisDTOList, wrapperPrimeList

        int nbVariablesToUpdate = 0;
        int nbVariablesUpdated = 0;
        String bilanConfirmation = "Variables de paie confirmées : ";
        StringBuilder variablesNonConfirmees = new StringBuilder();

        // Absence
        List<WrapperAbsence> wrapperAbsenceList = wrapperVariablesPaieToUpdate.getWrapperAbsenceList();
        if (!wrapperAbsenceList.isEmpty()) {
            for (WrapperAbsence wrapperAbsence : wrapperAbsenceList) {
                nbVariablesToUpdate++;
                if (wrapperAbsence.getId() == null) {
                    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "id null");
                } else if (!absenceService.findOne(wrapperAbsence.getId()).isPresent()) {
                    variablesNonConfirmees.append("\n").append(wrapperAbsence.toString()).append("\nid ").append(wrapperAbsence.getId()).append(" does not exist");
                } else if (wrapperAbsence.getEtatVariablePaieId() == 1
                        && absenceService.findOne(wrapperAbsence.getId()).get().getEtatVariablePaieId() == 1) {
                    wrapperAbsence.setEtatVariablePaieId((long) 2);
                    AbsenceDTO absenceDTO = wrapperAbsenceMapper.toAbsenceDTO(wrapperAbsence);
                    AbsenceDTO result = absenceService.save(absenceDTO);
                    if (result != null) {nbVariablesUpdated++;}
                } else {
                    variablesNonConfirmees.append("\n").append(wrapperAbsence.toString());
                }
            }
        }

        // AutresVariable
        List<AutresVariableDTO> autresVariableDTOList = wrapperVariablesPaieToUpdate.getAutresVariableDTOList();
        if (!autresVariableDTOList.isEmpty()) {
            for (AutresVariableDTO autresVariableDTO : autresVariableDTOList) {
                nbVariablesToUpdate++;
                if (autresVariableDTO.getId() == null) {
                    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
                } else if (!autresVariableService.findOne(autresVariableDTO.getId()).isPresent()) {
                    variablesNonConfirmees.append("\n").append(autresVariableDTO.toString()).append("\nid ").append(autresVariableDTO.getId()).append(" does not exist");
                } else if (autresVariableDTO.getEtatVariablePaieId() == 1
                        && autresVariableService.findOne(autresVariableDTO.getId()).get().getEtatVariablePaieId() == 1) {
                    autresVariableDTO.setEtatVariablePaieId((long) 2);
                    AutresVariableDTO result = autresVariableService.save(autresVariableDTO);
                    if (result != null) {nbVariablesUpdated++;}
                } else {
                    variablesNonConfirmees.append("\n").append(autresVariableDTO.toString());
                }
            }
        }

        // AvanceRappelSalaire
        List<AvanceRappelSalaireDTO> avanceRappelSalaireDTOList = wrapperVariablesPaieToUpdate.getAvanceRappelSalaireDTOList();
        if (!avanceRappelSalaireDTOList.isEmpty()) {
            for (AvanceRappelSalaireDTO avanceRappelSalaireDTO : avanceRappelSalaireDTOList) {
                nbVariablesToUpdate++;
                if (avanceRappelSalaireDTO.getId() == null) {
                    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
                } else if (!avanceRappelSalaireService.findOne(avanceRappelSalaireDTO.getId()).isPresent()) {
                    variablesNonConfirmees.append("\n").append(avanceRappelSalaireDTO.toString()).append("\nid ").append(avanceRappelSalaireDTO.getId()).append(" does not exist");
                } else if (avanceRappelSalaireDTO.getEtatVariablePaieId() == 1
                        && avanceRappelSalaireService.findOne(avanceRappelSalaireDTO.getId()).get().getEtatVariablePaieId() == 1) {
                    avanceRappelSalaireDTO.setEtatVariablePaieId((long) 2);
                    AvanceRappelSalaireDTO result = avanceRappelSalaireService.save(avanceRappelSalaireDTO);
                    if (result != null) {nbVariablesUpdated++;}
                } else {
                    variablesNonConfirmees.append("\n").append(avanceRappelSalaireDTO.toString());
                }
            }
        }

        // HeuresSupplementaires
        List<HeuresSupplementairesDTO> heuresSupplementairesDTOList = wrapperVariablesPaieToUpdate.getHeuresSupplementairesDTOList();
        if (!heuresSupplementairesDTOList.isEmpty()) {
            for (HeuresSupplementairesDTO heuresSupplementairesDTO : heuresSupplementairesDTOList) {
                nbVariablesToUpdate++;
                if (heuresSupplementairesDTO.getId() == null) {
                    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
                } else if (!heuresSupplementairesService.findOne(heuresSupplementairesDTO.getId()).isPresent()) {
                    variablesNonConfirmees.append("\n").append(heuresSupplementairesDTO.toString()).append("\nid ").append(heuresSupplementairesDTO.getId()).append(" does not exist");
                } else if (heuresSupplementairesDTO.getEtatVariablePaieId() == 1
                        && heuresSupplementairesService.findOne(heuresSupplementairesDTO.getId()).get().getEtatVariablePaieId() == 1) {
                    heuresSupplementairesDTO.setEtatVariablePaieId((long) 2);
                    HeuresSupplementairesDTO result = heuresSupplementairesService.save(heuresSupplementairesDTO);
                    if (result != null) {nbVariablesUpdated++;}
                } else {
                    variablesNonConfirmees.append("\n").append(heuresSupplementairesDTO.toString());
                }
            }
        }

        // NoteDeFrais
        List<NoteDeFraisDTO> noteDeFraisDTOList = wrapperVariablesPaieToUpdate.getNoteDeFraisDTOList();
        if (!noteDeFraisDTOList.isEmpty()) {
            for (NoteDeFraisDTO noteDeFraisDTO : noteDeFraisDTOList) {
                nbVariablesToUpdate++;
                if (noteDeFraisDTO.getId() == null) {
                    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
                } else if (!noteDeFraisService.findOne(noteDeFraisDTO.getId()).isPresent()) {
                    variablesNonConfirmees.append("\n").append(noteDeFraisDTO.toString()).append("\nid ").append(noteDeFraisDTO.getId()).append(" does not exist");
                } else if (noteDeFraisDTO.getEtatVariablePaieId() == 1
                        && noteDeFraisService.findOne(noteDeFraisDTO.getId()).get().getEtatVariablePaieId() == 1) {
                    noteDeFraisDTO.setEtatVariablePaieId((long) 2);
                    NoteDeFraisDTO result = noteDeFraisService.save(noteDeFraisDTO);
                    if (result != null) {nbVariablesUpdated++;}
                } else {
                    variablesNonConfirmees.append("\n").append(noteDeFraisDTO.toString());
                }
            }
        }

        // Prime
        List<WrapperPrime> wrapperPrimeList = wrapperVariablesPaieToUpdate.getWrapperPrimeList();
        if (!wrapperPrimeList.isEmpty()) {
            nbVariablesToUpdate++;
            for (WrapperPrime wrapperPrime : wrapperPrimeList) {
                if (wrapperPrime.getId() == null) {
                    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
                } else if (!primeService.findOne(wrapperPrime.getId()).isPresent()) {
                    variablesNonConfirmees.append("\n").append(wrapperPrime.toString()).append("\nid ").append(wrapperPrime.getId()).append(" does not exist");
                } else if (wrapperPrime.getEtatVariablePaieId() == 1
                        && primeService.findOne(wrapperPrime.getId()).get().getEtatVariablePaieId() == 1) {
                    wrapperPrime.setEtatVariablePaieId((long) 2);
                    PrimeDTO primeDTO = wrapperPrimeMapper.toPrimeDTO(wrapperPrime);
                    PrimeDTO result = primeService.save(primeDTO);
                    if (result != null) {nbVariablesUpdated++;}
                } else {
                    variablesNonConfirmees.append("\n").append(wrapperPrime.toString());
                }
            }
        }

        bilanConfirmation = "Variable(s) de paie confirmée(s) : " + nbVariablesUpdated + " / " + nbVariablesToUpdate
                + (nbVariablesUpdated < nbVariablesToUpdate ? "\nVariable(s) de paie non-confirmée(s) : " + variablesNonConfirmees : "");

        if (nbVariablesUpdated == nbVariablesToUpdate) {
            return ResponseEntity.status(201).headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, "Confirmation de Variable(s) de Paie")).body(bilanConfirmation);
        } else if (nbVariablesUpdated == 0) {
            return ResponseEntity.status(400).headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, "Confirmation de Variable(s) de Paie")).body(bilanConfirmation);
        } else { // certaines variables mais pas toutes ont pu être confirmées
            return ResponseEntity.status(206).headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, "Confirmation de Variable(s) de Paie")).body(bilanConfirmation);
        }

    }
}
