package fr.insy2s.service;

import fr.insy2s.service.dto.EtatDepenseDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.EtatDepense}.
 */
public interface EtatDepenseService {

    /**
     * Save a etatDepense.
     *
     * @param etatDepenseDTO the entity to save.
     * @return the persisted entity.
     */
    EtatDepenseDTO save(EtatDepenseDTO etatDepenseDTO);

    /**
     * Get all the etatDepenses.
     *
     * @return the list of entities.
     */
    List<EtatDepenseDTO> findAll();


    /**
     * Get the "id" etatDepense.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtatDepenseDTO> findOne(Long id);

    /**
     * Delete the "id" etatDepense.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
