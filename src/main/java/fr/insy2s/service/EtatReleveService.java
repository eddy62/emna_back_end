package fr.insy2s.service;

import fr.insy2s.service.dto.EtatReleveDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.EtatReleve}.
 */
public interface EtatReleveService {

    /**
     * Save a etatReleve.
     *
     * @param etatReleveDTO the entity to save.
     * @return the persisted entity.
     */
    EtatReleveDTO save(EtatReleveDTO etatReleveDTO);

    /**
     * Get all the etatReleves.
     *
     * @return the list of entities.
     */
    List<EtatReleveDTO> findAll();


    /**
     * Get the "id" etatReleve.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtatReleveDTO> findOne(Long id);

    /**
     * Delete the "id" etatReleve.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
