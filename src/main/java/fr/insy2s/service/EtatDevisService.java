package fr.insy2s.service;

import fr.insy2s.service.dto.EtatDevisDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.EtatDevis}.
 */
public interface EtatDevisService {

    /**
     * Save a etatDevis.
     *
     * @param etatDevisDTO the entity to save.
     * @return the persisted entity.
     */
    EtatDevisDTO save(EtatDevisDTO etatDevisDTO);

    /**
     * Get all the etatDevis.
     *
     * @return the list of entities.
     */
    List<EtatDevisDTO> findAll();


    /**
     * Get the "id" etatDevis.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtatDevisDTO> findOne(Long id);

    /**
     * Delete the "id" etatDevis.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
