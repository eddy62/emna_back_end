package fr.insy2s.service;

import fr.insy2s.service.dto.ContratDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Contrat}.
 */
public interface ContratService {

    /**
     * Save a contrat.
     *
     * @param contratDTO the entity to save.
     * @return the persisted entity.
     */
    ContratDTO save(ContratDTO contratDTO);

    /**
     * Get all the contrats.
     *
     * @return the list of entities.
     */
    List<ContratDTO> findAll();


    /**
     * Get the "id" contrat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContratDTO> findOne(Long id);

    /**
     * Delete the "id" contrat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
