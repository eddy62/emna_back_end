package fr.insy2s.service;

import fr.insy2s.service.dto.TypeContratDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.TypeContrat}.
 */
public interface TypeContratService {

    /**
     * Save a typeContrat.
     *
     * @param typeContratDTO the entity to save.
     * @return the persisted entity.
     */
    TypeContratDTO save(TypeContratDTO typeContratDTO);

    /**
     * Get all the typeContrats.
     *
     * @return the list of entities.
     */
    List<TypeContratDTO> findAll();


    /**
     * Get the "id" typeContrat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeContratDTO> findOne(Long id);

    /**
     * Delete the "id" typeContrat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
