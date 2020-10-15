package fr.insy2s.service;

import fr.insy2s.service.dto.AbsenceDTO;
import fr.insy2s.service.dto.AutresVariableDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.AutresVariable}.
 */
public interface AutresVariableService {

    /**
     * Save a autresVariable.
     *
     * @param autresVariableDTO the entity to save.
     * @return the persisted entity.
     */
    AutresVariableDTO save(AutresVariableDTO autresVariableDTO);

    /**
     * Get all the autresVariables.
     *
     * @return the list of entities.
     */
    List<AutresVariableDTO> findAll();


    /**
     * Get the "id" autresVariable.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AutresVariableDTO> findOne(Long id);

    /**
     * Delete the "id" autresVariable.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Optional<AutresVariableDTO> findAutresVaribaleExistByDate(Long idEmploye, LocalDate debutAbsence, LocalDate finAbsence);
}
