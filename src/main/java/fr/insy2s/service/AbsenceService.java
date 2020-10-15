package fr.insy2s.service;

import fr.insy2s.service.dto.AbsenceDTO;
import fr.insy2s.utils.wrapper.WrapperAbsence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Absence}.
 */
public interface AbsenceService {

    /**
     * Save a absence.
     *
     * @param absenceDTO the entity to save.
     * @return the persisted entity.
     */
    AbsenceDTO save(AbsenceDTO absenceDTO);

    /**
     * Get all the absences.
     *
     * @return the list of entities.
     */
    List<AbsenceDTO> findAll();


    /**
     * Get the "id" absence.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AbsenceDTO> findOne(Long id);

    /**
     * Delete the "id" absence.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all the WrapperAbsence by idEmploye, annee, mois
     *
     * @param idEmploye the idEmploye of all the wrapperAbsence to return
     * @param annee the annee of all the wrapperAbsence to return
     * @param mois the mois of all the wrapperAbsence to return
     * @return the list of wrapperAbsence
     */
    List<WrapperAbsence> findAllWrapperAbsenceByIdEmployeAndAnneeAndMois(Long idEmploye, Integer annee, Integer mois);

/*
    */
/**
     * Update the WrapperAbsence
     *
     * @param wrapperAbsence WrapperAbsence to update
     * @return the updated WrapperAbsence
     *//*

    WrapperAbsence updateWrapperAbsence(WrapperAbsence wrapperAbsence);
*/

    Optional<AbsenceDTO> findAllOverlappingAbsencesByIdEmploye(Long idEmploye, LocalDate debutAbsence, LocalDate finAbsence);

    Optional<AbsenceDTO> findAllOverlappingAbsenceByIdEmployeForUpdate(Long idAbsence, Long idEmploye, LocalDate debutAbsence, LocalDate finAbsence);

    Optional<AbsenceDTO> findAbsenceExistByDate(Long idEmploye, LocalDate dateToCheck);
}
