package fr.insy2s.service;

import fr.insy2s.service.dto.AbsenceDTO;
import fr.insy2s.utils.wrapper.WrapperAbsence;

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

    List<WrapperAbsence> findAllWrapperAbsenceByIdEmployeAndAnneeAndMois(Long idEmploye, Integer annee, Integer mois);
}
