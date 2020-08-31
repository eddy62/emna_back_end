package fr.insy2s.service;

import fr.insy2s.service.dto.DpaeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Dpae}.
 */
public interface DpaeService {

    /**
     * Save a dpae.
     *
     * @param dpaeDTO the entity to save.
     * @return the persisted entity.
     */
    DpaeDTO save(DpaeDTO dpaeDTO);

    /**
     * Get all the dpaes.
     *
     * @return the list of entities.
     */
    List<DpaeDTO> findAll();


    /**
     * Get the "id" dpae.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DpaeDTO> findOne(Long id);

    /**
     * Delete the "id" dpae.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
