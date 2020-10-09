package fr.insy2s.web.rest;

import fr.insy2s.service.*;
import fr.insy2s.service.dto.*;
import fr.insy2s.service.mapper.WrapperAbsenceMapper;
import fr.insy2s.service.mapper.WrapperAutresVariableMapper;
import fr.insy2s.service.mapper.WrapperNoteDeFraisMapper;
import fr.insy2s.service.mapper.WrapperPrimeMapper;
import fr.insy2s.utils.wrapper.*;
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

    private static final String ENTITY_NAME = "wrapperVariablesDePaie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VariableDePaieService variableDePaieService;

    // Variables de paie
    private final AbsenceService absenceService;
    private final WrapperAbsenceMapper wrapperAbsenceMapper;
    private final AutresVariableService autresVariableService;
    private final WrapperAutresVariableMapper wrapperAutresVariableMapper;
    private final AvanceRappelSalaireService avanceRappelSalaireService;
    private final HeuresSupplementairesService heuresSupplementairesService;
    private final NoteDeFraisService noteDeFraisService;
    private final WrapperNoteDeFraisMapper wrapperNoteDeFraisMapper;
    private final PrimeService primeService;
    private final WrapperPrimeMapper wrapperPrimeMapper;

    public VariableDePaieResource(VariableDePaieService variableDePaieService,
                                  AbsenceService absenceService,
                                  WrapperAbsenceMapper wrapperAbsenceMapper,
                                  AutresVariableService autresVariableService,
                                  WrapperAutresVariableMapper wrapperAutresVariableMapper,
                                  AvanceRappelSalaireService avanceRappelSalaireService,
                                  HeuresSupplementairesService heuresSupplementairesService,
                                  NoteDeFraisService noteDeFraisService,
                                  WrapperNoteDeFraisMapper wrapperNoteDeFraisMapper,
                                  PrimeService primeService,
                                  WrapperPrimeMapper wrapperPrimeMapper) {

        this.variableDePaieService = variableDePaieService;

        // Variables de paie
        this.absenceService = absenceService;
        this.wrapperAbsenceMapper = wrapperAbsenceMapper;
        this.autresVariableService = autresVariableService;
        this.wrapperAutresVariableMapper = wrapperAutresVariableMapper;
        this.avanceRappelSalaireService = avanceRappelSalaireService;
        this.heuresSupplementairesService = heuresSupplementairesService;
        this.noteDeFraisService = noteDeFraisService;
        this.wrapperNoteDeFraisMapper = wrapperNoteDeFraisMapper;
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
     * @author Erik DUNAIS
     */
    @GetMapping("/wrappervariablespaie/employe/{idEmploye}/annee/{annee}/mois/{mois}")
    public WrapperVariablesPaie getOneWrapperVariablesPaieByIdEmployeAndAnneeAndMois(@PathVariable Long idEmploye, @PathVariable Integer annee, @PathVariable Integer mois) {
        log.debug("REST request to get one WrapperVariablesPaie by employe:{}, annee:{}, mois:{}", idEmploye, annee, mois);
        return variableDePaieService.findOneWrapperVariablesPaieByIdEmployeAndAnneeAndMois(idEmploye, annee, mois);
    }

    /**
     * {@code PUT  /wrappervariablespaie/process-variablespaie} : Confirm all Variables de Paie in a WrapperVariablesPaie
     * etatVariablePaie 1 -> 2 if enterprise
     * etatVariablePaie 2 -> 3 if accountant
     *
     * @param wrapperVariablesPaieToUpdate the WrapperVariablesPaie containing all the Variables de Paie to update
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} if all the Variables de Paie were updated,
     * or with status {@code 206 (Partial Content)} if partial Variables de Paie were updated,
     * or with status {@code 400 (Bad Request)} if none Variables de Paie were updated,
     * and with body the String "Variables de paie traitées : x / y " And eventual Variables de Paie not updated
     * @author Erik DUNAIS
     */
    @PutMapping("/wrappervariablespaie/process-variablespaie/{idOperation}")
    public ResponseEntity<String> updateConfirmWrapperVariablesPaie(@Valid @RequestBody WrapperVariablesPaie wrapperVariablesPaieToUpdate, @PathVariable Integer idOperation) {
        // contient wrapperAbsenceList, wrapperAutresVariableList, avanceRappelSalaireDTOList, heuresSupplementairesDTOList, noteDeFraisDTOList, wrapperPrimeList
        String typeOperation;

        switch (idOperation) {
            case 1:
                typeOperation = "confirmée";
                log.debug("REST request to update one WrapperVariablesPaie, state: Confirm {}", wrapperVariablesPaieToUpdate);
                break;

            case 2:
                typeOperation = "validée";
                log.debug("REST request to update one WrapperVariablesPaie, state: Validate {}", wrapperVariablesPaieToUpdate);
                break;
            default:
                log.debug("REST request to update one WrapperVariablesPaie, state: ? {}", wrapperVariablesPaieToUpdate);
                typeOperation = "?";
        }


        int nbVariablesToUpdate = 0;
        int nbVariablesUpdated = 0;
        String bilanTraitement;
        StringBuilder variablesNonTraitees = new StringBuilder();

        // Absence
        List<WrapperAbsence> wrapperAbsenceList = wrapperVariablesPaieToUpdate.getWrapperAbsenceList();
        if (!wrapperAbsenceList.isEmpty()) {
            for (WrapperAbsence wrapperAbsence : wrapperAbsenceList) {
                nbVariablesToUpdate++;
                if (wrapperAbsence.getId() == null) {
                    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "id null");
                } else if (!absenceService.findOne(wrapperAbsence.getId()).isPresent()) {
                    variablesNonTraitees.append("\n").append(wrapperAbsence.toString()).append("\nid ").append(wrapperAbsence.getId()).append(" does not exist");
                } else if (idOperation == 1) {
                    if (wrapperAbsence.getEtatVariablePaieId() == 1
                            && absenceService.findOne(wrapperAbsence.getId()).get().getEtatVariablePaieId() == 1) {
                        wrapperAbsence.setEtatVariablePaieId((long) 2);
                        AbsenceDTO absenceDTO = wrapperAbsenceMapper.toAbsenceDTO(wrapperAbsence);
                        AbsenceDTO result = absenceService.save(absenceDTO);
                        if (result != null) {
                            nbVariablesUpdated++;
                        }
                    } else {
                    variablesNonTraitees.append("\n").append(wrapperAbsence.toString());
                    }
                } else if (idOperation == 2) {
                    if (wrapperAbsence.getEtatVariablePaieId() == 2
                            && absenceService.findOne(wrapperAbsence.getId()).get().getEtatVariablePaieId() == 2) {
                        wrapperAbsence.setEtatVariablePaieId((long) 3);
                        AbsenceDTO absenceDTO = wrapperAbsenceMapper.toAbsenceDTO(wrapperAbsence);
                        AbsenceDTO result = absenceService.save(absenceDTO);
                        if (result != null) {
                            nbVariablesUpdated++;
                        }
                    } else {
                        variablesNonTraitees.append("\n").append(wrapperAbsence.toString());
                    }
                } else {
                    variablesNonTraitees.append("\n").append(wrapperAbsence.toString());
                }
            }
        }

        // AutresVariable
        List<WrapperAutresVariable> wrapperAutresVariableList = wrapperVariablesPaieToUpdate.getWrapperAutresVariableList();
        if (!wrapperAutresVariableList.isEmpty()) {
            for (WrapperAutresVariable wrapperAutresVariable : wrapperAutresVariableList) {
                nbVariablesToUpdate++;
                if (wrapperAutresVariable.getId() == null) {
                    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
                } else if (!autresVariableService.findOne(wrapperAutresVariable.getId()).isPresent()) {
                    variablesNonTraitees.append("\n").append(wrapperAutresVariable.toString()).append("\nid ").append(wrapperAutresVariable.getId()).append(" does not exist");
                } else if (idOperation == 1) {
                    if (wrapperAutresVariable.getEtatVariablePaieId() == 1
                        && autresVariableService.findOne(wrapperAutresVariable.getId()).get().getEtatVariablePaieId() == 1) {
                        wrapperAutresVariable.setEtatVariablePaieId((long) 2);
                        AutresVariableDTO autresVariableDTO = wrapperAutresVariableMapper.toAutresVariableDTO(wrapperAutresVariable);
                        AutresVariableDTO result = autresVariableService.save(autresVariableDTO);
                        if (result != null) {
                            nbVariablesUpdated++;
                        }
                    } else {
                        variablesNonTraitees.append("\n").append(wrapperAutresVariable.toString());
                    }
                } else if (idOperation == 2) {
                    if (wrapperAutresVariable.getEtatVariablePaieId() == 2
                            && autresVariableService.findOne(wrapperAutresVariable.getId()).get().getEtatVariablePaieId() == 2) {
                        wrapperAutresVariable.setEtatVariablePaieId((long) 3);
                        AutresVariableDTO autresVariableDTO = wrapperAutresVariableMapper.toAutresVariableDTO(wrapperAutresVariable);
                        AutresVariableDTO result = autresVariableService.save(autresVariableDTO);
                        if (result != null) {
                            nbVariablesUpdated++;
                        }
                    } else {
                        variablesNonTraitees.append("\n").append(wrapperAutresVariable.toString());
                    }
                } else {
                    variablesNonTraitees.append("\n").append(wrapperAutresVariable.toString());
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
                    variablesNonTraitees.append("\n").append(avanceRappelSalaireDTO.toString()).append("\nid ").append(avanceRappelSalaireDTO.getId()).append(" does not exist");
                } else if (idOperation == 1) {
                    if (avanceRappelSalaireDTO.getEtatVariablePaieId() == 1
                            && avanceRappelSalaireService.findOne(avanceRappelSalaireDTO.getId()).get().getEtatVariablePaieId() == 1) {
                        avanceRappelSalaireDTO.setEtatVariablePaieId((long) 2);
                        AvanceRappelSalaireDTO result = avanceRappelSalaireService.save(avanceRappelSalaireDTO);
                        if (result != null) {
                            nbVariablesUpdated++;
                        }
                    } else {
                        variablesNonTraitees.append("\n").append(avanceRappelSalaireDTO.toString());
                    }
                } else if (idOperation == 2) {
                    if (avanceRappelSalaireDTO.getEtatVariablePaieId() == 2
                            && avanceRappelSalaireService.findOne(avanceRappelSalaireDTO.getId()).get().getEtatVariablePaieId() == 2) {
                            avanceRappelSalaireDTO.setEtatVariablePaieId((long) 3);
                            AvanceRappelSalaireDTO result = avanceRappelSalaireService.save(avanceRappelSalaireDTO);
                            if (result != null) {
                                nbVariablesUpdated++;
                            }
                    } else {
                        variablesNonTraitees.append("\n").append(avanceRappelSalaireDTO.toString());
                    }
                } else {
                    variablesNonTraitees.append("\n").append(avanceRappelSalaireDTO.toString());
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
                    variablesNonTraitees.append("\n").append(heuresSupplementairesDTO.toString()).append("\nid ").append(heuresSupplementairesDTO.getId()).append(" does not exist");
                } else if  (idOperation == 1) {
                    if (heuresSupplementairesDTO.getEtatVariablePaieId() == 1
                            && heuresSupplementairesService.findOne(heuresSupplementairesDTO.getId()).get().getEtatVariablePaieId() == 1) {
                        heuresSupplementairesDTO.setEtatVariablePaieId((long) 2);
                        HeuresSupplementairesDTO result = heuresSupplementairesService.save(heuresSupplementairesDTO);
                        if (result != null) {
                            nbVariablesUpdated++;
                        }
                    } else {
                        variablesNonTraitees.append("\n").append(heuresSupplementairesDTO.toString());
                    }
                } else if  (idOperation == 2) {
                    if (heuresSupplementairesDTO.getEtatVariablePaieId() == 2
                            && heuresSupplementairesService.findOne(heuresSupplementairesDTO.getId()).get().getEtatVariablePaieId() == 2) {
                        heuresSupplementairesDTO.setEtatVariablePaieId((long) 3);
                        HeuresSupplementairesDTO result = heuresSupplementairesService.save(heuresSupplementairesDTO);
                        if (result != null) {
                            nbVariablesUpdated++;
                        }
                    } else {
                    variablesNonTraitees.append("\n").append(heuresSupplementairesDTO.toString());
                }
                } else {
                    variablesNonTraitees.append("\n").append(heuresSupplementairesDTO.toString());
                }
            }
        }

        // NoteDeFrais
        List<WrapperNoteDeFrais> wrapperNoteDeFraisList = wrapperVariablesPaieToUpdate.getWrapperNoteDeFraisList();
        if (!wrapperNoteDeFraisList.isEmpty()) {
            for (WrapperNoteDeFrais wrapperNoteDeFrais : wrapperNoteDeFraisList) {
                nbVariablesToUpdate++;
                if (wrapperNoteDeFrais.getId() == null) {
                    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
                } else if (!noteDeFraisService.findOne(wrapperNoteDeFrais.getId()).isPresent()) {
                    variablesNonTraitees.append("\n").append(wrapperNoteDeFrais.toString()).append("\nid ").append(wrapperNoteDeFrais.getId()).append(" does not exist");
                } else if  (idOperation == 1) {
                    if (wrapperNoteDeFrais.getEtatVariablePaieId() == 1
                        && noteDeFraisService.findOne(wrapperNoteDeFrais.getId()).get().getEtatVariablePaieId() == 1) {
                        wrapperNoteDeFrais.setEtatVariablePaieId((long) 2);
                        NoteDeFraisDTO noteDeFraisDTO = wrapperNoteDeFraisMapper.toNoteDeFraisDTO(wrapperNoteDeFrais);
                        NoteDeFraisDTO result = noteDeFraisService.save(noteDeFraisDTO);
                        if (result != null) {
                            nbVariablesUpdated++;
                        }
                    } else {
                        variablesNonTraitees.append("\n").append(wrapperNoteDeFrais.toString());
                    }
                } else if  (idOperation == 2) {
                    if (wrapperNoteDeFrais.getEtatVariablePaieId() == 2
                            && noteDeFraisService.findOne(wrapperNoteDeFrais.getId()).get().getEtatVariablePaieId() == 2) {
                        wrapperNoteDeFrais.setEtatVariablePaieId((long) 3);
                        NoteDeFraisDTO noteDeFraisDTO = wrapperNoteDeFraisMapper.toNoteDeFraisDTO(wrapperNoteDeFrais);
                        NoteDeFraisDTO result = noteDeFraisService.save(noteDeFraisDTO);
                        if (result != null) {
                            nbVariablesUpdated++;
                        }
                    } else {
                        variablesNonTraitees.append("\n").append(wrapperNoteDeFrais.toString());
                    }
                } else {
                    variablesNonTraitees.append("\n").append(wrapperNoteDeFrais.toString());
                }
            }
        }

        // Prime
        List<WrapperPrime> wrapperPrimeList = wrapperVariablesPaieToUpdate.getWrapperPrimeList();
        if (!wrapperPrimeList.isEmpty()) {
            for (WrapperPrime wrapperPrime : wrapperPrimeList) {
                nbVariablesToUpdate++;
                if (wrapperPrime.getId() == null) {
                    throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
                } else if (!primeService.findOne(wrapperPrime.getId()).isPresent()) {
                    variablesNonTraitees.append("\n").append(wrapperPrime.toString()).append("\nid ").append(wrapperPrime.getId()).append(" does not exist");
                } else if  (idOperation == 1) {
                    if (wrapperPrime.getEtatVariablePaieId() == 1
                        && primeService.findOne(wrapperPrime.getId()).get().getEtatVariablePaieId() == 1) {
                        wrapperPrime.setEtatVariablePaieId((long) 2);
                        PrimeDTO primeDTO = wrapperPrimeMapper.toPrimeDTO(wrapperPrime);
                        PrimeDTO result = primeService.save(primeDTO);
                        if (result != null) {
                            nbVariablesUpdated++;
                        }
                    } else {
                        variablesNonTraitees.append("\n").append(wrapperPrime.toString());
                    }
                } else if  (idOperation == 2) {
                    if (wrapperPrime.getEtatVariablePaieId() == 2
                            && primeService.findOne(wrapperPrime.getId()).get().getEtatVariablePaieId() == 2) {
                        wrapperPrime.setEtatVariablePaieId((long) 3);
                        PrimeDTO primeDTO = wrapperPrimeMapper.toPrimeDTO(wrapperPrime);
                        PrimeDTO result = primeService.save(primeDTO);
                        if (result != null) {
                            nbVariablesUpdated++;
                        }
                    } else {
                        variablesNonTraitees.append("\n").append(wrapperPrime.toString());
                    }
                } else {
                    variablesNonTraitees.append("\n").append(wrapperPrime.toString());
                }
            }
        }

        bilanTraitement = "Variable(s) de paie " + typeOperation + "(s) : " + nbVariablesUpdated + " / " + nbVariablesToUpdate
                + (nbVariablesUpdated < nbVariablesToUpdate ? "\nVariable(s) de paie non-" + typeOperation + "(s) : " + variablesNonTraitees : "");

        if (nbVariablesUpdated == nbVariablesToUpdate) {
            return ResponseEntity.status(201).headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, "Traitement de Variable(s) de Paie")).body(bilanTraitement);
        } else if (nbVariablesUpdated == 0) {
            return ResponseEntity.status(400).headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, "Traitement de Variable(s) de Paie")).body(bilanTraitement);
        } else { // certaines variables mais pas toutes ont pu être traitées
            return ResponseEntity.status(206).headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, "Traitement de Variable(s) de Paie")).body(bilanTraitement);
        }
    }
}
