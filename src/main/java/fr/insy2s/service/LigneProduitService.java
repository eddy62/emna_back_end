package fr.insy2s.service;

import fr.insy2s.service.dto.LigneProduitDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.LigneProduit}.
 */
public interface LigneProduitService {

    /**
     * Save a ligneProduit.
     *
     * @param ligneProduitDTO the entity to save.
     * @return the persisted entity.
     */
    LigneProduitDTO save(LigneProduitDTO ligneProduitDTO);

    /**
     * Get all the ligneProduits.
     *
     * @return the list of entities.
     */
    List<LigneProduitDTO> findAll();


    /**
     * Get the "id" ligneProduit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LigneProduitDTO> findOne(Long id);

    /**
     * Delete the "id" ligneProduit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
