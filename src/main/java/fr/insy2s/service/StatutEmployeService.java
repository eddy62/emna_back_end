package fr.insy2s.service;

import fr.insy2s.service.dto.StatutEmployeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.StatutEmploye}.
 */
public interface StatutEmployeService {

    /**
     * Save a statutEmploye.
     *
     * @param statutEmployeDTO the entity to save.
     * @return the persisted entity.
     */
    StatutEmployeDTO save(StatutEmployeDTO statutEmployeDTO);

    /**
     * Get all the statutEmployes.
     *
     * @return the list of entities.
     */
    List<StatutEmployeDTO> findAll();

    /**
     * Get the "id" statutEmploye.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StatutEmployeDTO> findOne(Long id);

    /**
     * Delete the "id" statutEmploye.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
