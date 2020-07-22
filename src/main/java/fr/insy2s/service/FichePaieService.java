package fr.insy2s.service;

import fr.insy2s.service.dto.FichePaieDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.FichePaie}.
 */
public interface FichePaieService {

    /**
     * Save a fichePaie.
     *
     * @param fichePaieDTO the entity to save.
     * @return the persisted entity.
     */
    FichePaieDTO save(FichePaieDTO fichePaieDTO);

    /**
     * Get all the fichePaies.
     *
     * @return the list of entities.
     */
    List<FichePaieDTO> findAll();


    /**
     * Get the "id" fichePaie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FichePaieDTO> findOne(Long id);

    /**
     * Delete the "id" fichePaie.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
