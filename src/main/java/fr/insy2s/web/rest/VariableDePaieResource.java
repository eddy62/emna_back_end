package fr.insy2s.web.rest;

import fr.insy2s.service.*;
import fr.insy2s.service.dto.*;
import fr.insy2s.service.mapper.WrapperAbsenceMapper;
import fr.insy2s.service.mapper.WrapperPrimeMapper;
import fr.insy2s.utils.wrapper.WrapperAbsence;
import fr.insy2s.utils.wrapper.WrapperPrime;
import fr.insy2s.utils.wrapper.WrapperVariablesPaie;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * VariableDePaieResource controller
 */
@RestController
@RequestMapping("/api")
public class VariableDePaieResource {

    private final Logger log = LoggerFactory.getLogger(VariableDePaieResource.class);

    private static final String  ENTITY_NAME = "wrapperVariablesDePaie";

    @Value("${jhipster.clientApp.name}")
    private String               applicationName;

    private final VariableDePaieService variableDePaieService;

    // Variables de paie
    private final AbsenceService absenceService;
    private final WrapperAbsenceMapper wrapperAbsenceMapper;
    private final AutresVariableService autresVariableService;
    private final AvanceRappelSalaireService avanceRappelSalaireService;
    private final HeuresSupplementairesService heuresSupplementairesService;
    private final NoteDeFraisService noteDeFraisService;
    private final PrimeService primeService;
    private final WrapperPrimeMapper wrapperPrimeMapper;

    public VariableDePaieResource(VariableDePaieService variableDePaieService,
                                  AbsenceService absenceService,
                                  WrapperAbsenceMapper wrapperAbsenceMapper,
                                  AutresVariableService autresVariableService,
                                  AvanceRappelSalaireService avanceRappelSalaireService,
                                  HeuresSupplementairesService heuresSupplementairesService,
                                  NoteDeFraisService noteDeFraisService,
                                  PrimeService primeService,
                                  WrapperPrimeMapper wrapperPrimeMapper){

        this.variableDePaieService = variableDePaieService;

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
        return variableDePaieService.findOneWrapperVariablesPaieByIdEmployeAndAnneeAndMois(idEmploye, annee, mois);
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
     */
    @PutMapping("/wrappervariablespaie/confirm-variablespaie")
    public ResponseEntity<String> updateConfirmWrapperVariablesPaie(@Valid @RequestBody WrapperVariablesPaie wrapperVariablesPaieToUpdate) {
        log.debug("REST request to update one WrapperVariablesPaie, state: Confirm {}", wrapperVariablesPaieToUpdate);
        // contient wrapperAbsenceList, autresVariableDTOList, avanceRappelSalaireDTOList, heuresSupplementairesDTOList, noteDeFraisDTOList, wrapperPrimeList

        int nbVariablesToUpdate = 0;
        int nbVariablesUpdated = 0;
        String bilanConfirmation;
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
