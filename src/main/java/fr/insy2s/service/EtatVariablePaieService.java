package fr.insy2s.service;

import fr.insy2s.service.dto.EtatVariablePaieDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.EtatVariablePaie}.
 */
public interface EtatVariablePaieService {

    /**
     * Save a etatVariablePaie.
     *
     * @param etatVariablePaieDTO the entity to save.
     * @return the persisted entity.
     */
    EtatVariablePaieDTO save(EtatVariablePaieDTO etatVariablePaieDTO);

    /**
     * Get all the etatVariablePaies.
     *
     * @return the list of entities.
     */
    List<EtatVariablePaieDTO> findAll();


    /**
     * Get the "id" etatVariablePaie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtatVariablePaieDTO> findOne(Long id);

    /**
     * Delete the "id" etatVariablePaie.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
