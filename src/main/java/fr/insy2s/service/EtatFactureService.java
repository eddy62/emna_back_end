package fr.insy2s.service;

import fr.insy2s.service.dto.EtatFactureDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.EtatFacture}.
 */
public interface EtatFactureService {

    /**
     * Save a etatFacture.
     *
     * @param etatFactureDTO the entity to save.
     * @return the persisted entity.
     */
    EtatFactureDTO save(EtatFactureDTO etatFactureDTO);

    /**
     * Get all the etatFactures.
     *
     * @return the list of entities.
     */
    List<EtatFactureDTO> findAll();


    /**
     * Get the "id" etatFacture.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtatFactureDTO> findOne(Long id);

    /**
     * Delete the "id" etatFacture.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
