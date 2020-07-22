package fr.insy2s.service;

import fr.insy2s.service.dto.TypeAbsenceDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.TypeAbsence}.
 */
public interface TypeAbsenceService {

    /**
     * Save a typeAbsence.
     *
     * @param typeAbsenceDTO the entity to save.
     * @return the persisted entity.
     */
    TypeAbsenceDTO save(TypeAbsenceDTO typeAbsenceDTO);

    /**
     * Get all the typeAbsences.
     *
     * @return the list of entities.
     */
    List<TypeAbsenceDTO> findAll();


    /**
     * Get the "id" typeAbsence.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeAbsenceDTO> findOne(Long id);

    /**
     * Delete the "id" typeAbsence.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
