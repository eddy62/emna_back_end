package fr.insy2s.web.rest;

import fr.insy2s.service.AbsenceService;
import fr.insy2s.service.AutresVariableService;
import fr.insy2s.service.HeuresSupplementairesService;
import fr.insy2s.service.NoteDeFraisService;
import fr.insy2s.service.dto.AbsenceDTO;
import fr.insy2s.service.dto.AutresVariableDTO;
import fr.insy2s.service.dto.HeuresSupplementairesDTO;
import fr.insy2s.service.dto.NoteDeFraisDTO;
import fr.insy2s.utils.wrapper.WrapperAbsence;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.Absence}.
 */
@RestController
@RequestMapping("/api")
public class AbsenceResource {

    private final Logger log = LoggerFactory.getLogger(AbsenceResource.class);

    private static final String ENTITY_NAME = "absence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AbsenceService absenceService;

    private final AutresVariableService autresVariableService;

    private final NoteDeFraisService noteDeFraisService;

    private final HeuresSupplementairesService heuresSupplementairesService;

    public AbsenceResource(AbsenceService absenceService, AutresVariableService autresVariableService, NoteDeFraisService noteDeFraisService, HeuresSupplementairesService heuresSupplementairesService) {
        this.absenceService = absenceService;
        this.autresVariableService = autresVariableService;
        this.noteDeFraisService = noteDeFraisService;
        this.heuresSupplementairesService = heuresSupplementairesService;
    }

    /**
     * {@code POST  /absences} : Create a new absence.
     *
     * @param absenceDTO the absenceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new absenceDTO, or with status {@code 400 (Bad Request)} if the absence has already an ID.
     * or with status {@code 208 (Already Reported)} if an absenceDTO already has an equivalent date, and with body the first existing absenceDTO.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/absences")
    public ResponseEntity<AbsenceDTO> createAbsence(@Valid @RequestBody AbsenceDTO absenceDTO) throws URISyntaxException {
        log.debug("REST request to save Absence : {}", absenceDTO);
        if (absenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new absence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Long idEmploye = absenceDTO.getEmployeId();
        LocalDate debutAbsence = absenceDTO.getDebutAbsence();
        LocalDate finAbsence = absenceDTO.getFinAbsence();
        String debutAbsenceSendFront = debutAbsence.toString();
        String finAbsenceSendFront = finAbsence.toString();
        String message = debutAbsenceSendFront + "/" + finAbsenceSendFront;
        Optional<AbsenceDTO> absenceOverlapping = absenceService.findAllOverlappingAbsencesByIdEmploye(idEmploye, debutAbsence, finAbsence);
        Optional<NoteDeFraisDTO> noteDeFraisOverlapping = noteDeFraisService.findNoteDeFraisExistByDate(idEmploye, debutAbsence, finAbsence);
        Optional<HeuresSupplementairesDTO> heuresSupplementairesOverlapping = heuresSupplementairesService.findHeuresSupplementairesExistByDate(idEmploye, debutAbsence, finAbsence);
        Optional<AutresVariableDTO> autresVariableOverlapping = autresVariableService.findAutresVaribaleExistByDate(idEmploye, debutAbsence, finAbsence);
        ArrayList<Optional<?>> overlapArray = new ArrayList<>();
        overlapArray.add(noteDeFraisOverlapping);
        overlapArray.add(heuresSupplementairesOverlapping);
        overlapArray.add(autresVariableOverlapping);
        Integer sizeArrayDtoOverlap = 0;
        for(int i = 0; i<overlapArray.size(); i++)
        {
            if(overlapArray.get(i).isPresent()) {
                sizeArrayDtoOverlap ++;
            }
        }
        if(!absenceOverlapping.isPresent() && !autresVariableOverlapping.isPresent() && !noteDeFraisOverlapping.isPresent() && !heuresSupplementairesOverlapping.isPresent()) {
            AbsenceDTO result = absenceService.save(absenceDTO);
            return ResponseEntity.status(201)
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
        }
        else if(sizeArrayDtoOverlap >= 2) {
            throw new BadRequestAlertException("Several payroll variable exists for the desired absence dates", message, "Plusieurs");
        }
        else if(noteDeFraisOverlapping.isPresent()) {
            throw new BadRequestAlertException("An expense report exists for the desired absence dates", message, "Note de Frais");
        }
        else if(heuresSupplementairesOverlapping.isPresent()) {
            throw new BadRequestAlertException("An overtime exists for the desired absence dates", message, "Heure Supplementaire");
        }
        else if(autresVariableOverlapping.isPresent()) {
            throw new BadRequestAlertException("An other payroll variable exists for the desired absence dates", message, "Autre Variable");
        }
        else{
            AbsenceDTO absenceDoublon = absenceOverlapping.get();
            return ResponseEntity.status(208)
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, absenceDoublon.getId().toString()))
                .body(absenceDoublon);
        }
    }

    /**
     * {@code PUT  /absences} : Updates an existing absence.
     *
     * @param absenceDTO the absenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated absenceDTO,
     * or with status {@code 400 (Bad Request)} if the absenceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the absenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/absences")
    public ResponseEntity<AbsenceDTO> updateAbsence(@Valid @RequestBody AbsenceDTO absenceDTO) throws URISyntaxException {
        log.debug("REST request to update Absence : {}", absenceDTO);
        if (absenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Long idAbsence = absenceDTO.getId();
        Long idEmploye = absenceDTO.getEmployeId();
        LocalDate debutAbsence = absenceDTO.getDebutAbsence();
        LocalDate finAbsence = absenceDTO.getFinAbsence();
        String debutAbsenceSendFront = debutAbsence.toString();
        String finAbsenceSendFront = finAbsence.toString();
        String message = debutAbsenceSendFront + "/" + finAbsenceSendFront;
        Optional<AbsenceDTO> absenceOverlapping = absenceService.findAllOverlappingAbsenceByIdEmployeForUpdate(idAbsence, idEmploye, debutAbsence, finAbsence);
        Optional<NoteDeFraisDTO> noteDeFraisOverlapping = noteDeFraisService.findNoteDeFraisExistByDate(idEmploye, debutAbsence, finAbsence);
        Optional<HeuresSupplementairesDTO> heuresSupplementairesOverlapping = heuresSupplementairesService.findHeuresSupplementairesExistByDate(idEmploye, debutAbsence, finAbsence);
        Optional<AutresVariableDTO> autresVariableOverlapping = autresVariableService.findAutresVaribaleExistByDate(idEmploye, debutAbsence, finAbsence);
        ArrayList<Optional<?>> overlapArray = new ArrayList<>();
        overlapArray.add(noteDeFraisOverlapping);
        overlapArray.add(heuresSupplementairesOverlapping);
        overlapArray.add(autresVariableOverlapping);
        Integer sizeArrayDtoOverlap = 0;
        for(int i = 0; i<overlapArray.size(); i++)
        {
            if(overlapArray.get(i).isPresent()) {
                sizeArrayDtoOverlap ++;
            }
        }
        if(!absenceOverlapping.isPresent() && !autresVariableOverlapping.isPresent() && !noteDeFraisOverlapping.isPresent() && !heuresSupplementairesOverlapping.isPresent()) {
            AbsenceDTO result = absenceService.save(absenceDTO);
            return ResponseEntity.status(201)
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
        }
        else if(sizeArrayDtoOverlap >= 2) {
            throw new BadRequestAlertException("Several payroll variable exists for the desired absence dates", message, "Plusieurs");
        }
        else if(noteDeFraisOverlapping.isPresent()) {
            throw new BadRequestAlertException("An expense report exists for the desired absence dates", message, "Note de Frais");
        }
        else if(heuresSupplementairesOverlapping.isPresent()) {
            throw new BadRequestAlertException("An overtime exists for the desired absence dates", message, "Heure Supplementaire");
        }
        else if(autresVariableOverlapping.isPresent()) {
            throw new BadRequestAlertException("An other payroll variable exists for the desired absence dates", message, "Autre Variable");
        }
        else{
            AbsenceDTO absenceDoublon = absenceOverlapping.get();
            return ResponseEntity.status(208)
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, absenceDoublon.getId().toString()))
                .body(absenceDoublon);
        }
    }

    /**
     * {@code GET  /absences} : get all the absences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of absences in body.
     */
    @GetMapping("/absences")
    public List<AbsenceDTO> getAllAbsences() {
        log.debug("REST request to get all Absences");
        return absenceService.findAll();
    }

    /**
     * {@code GET  /absences/:id} : get the "id" absence.
     *
     * @param id the id of the absenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the absenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/absences/{id}")
    public ResponseEntity<AbsenceDTO> getAbsence(@PathVariable Long id) {
        log.debug("REST request to get Absence : {}", id);
        Optional<AbsenceDTO> absenceDTO = absenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(absenceDTO);
    }

    /**
     * {@code DELETE  /absences/:id} : delete the "id" absence.
     *
     * @param id the id of the absenceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/absences/{id}")
    public ResponseEntity<Void> deleteAbsence(@PathVariable Long id) {
        log.debug("REST request to delete Absence : {}", id);
        absenceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET /wrapperabsences/employe/:idEmploye/annee/:annee/mois/:mois} : get all wrapperAbsences by one employe, by one year and by one month.
     *
     * @param idEmploye id of the Employe in all Absences
     * @param annee     year in all Absences
     * @param mois      month in all Absences
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the wrapperAbsences in body
     */
    @GetMapping("/wrapperabsences/employe/{idEmploye}/annee/{annee}/mois/{mois}")
    public List<WrapperAbsence> getAllWrapperAbsenceByIdEmployeAndAnneeAndMois(@PathVariable Long idEmploye, @PathVariable Integer annee, @PathVariable Integer mois) {
        log.debug("REST request to get all WrapperAbsence by employe:{}, annee:{}, mois:{}", idEmploye, annee, mois);
        return absenceService.findAllWrapperAbsenceByIdEmployeAndAnneeAndMois(idEmploye, annee, mois);
    }
}
