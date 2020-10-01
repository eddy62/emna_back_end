package fr.insy2s.service;

import fr.insy2s.service.dto.ClauseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Clause}.
 */
public interface ClauseService {

    /**
     * Save a clause.
     *
     * @param clauseDTO the entity to save.
     * @return the persisted entity.
     */
    ClauseDTO save(ClauseDTO clauseDTO);

    /**
     * Get all the clauses.
     *
     * @return the list of entities.
     */
    List<ClauseDTO> findAll();

    /**
     * Get all the clauses with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ClauseDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" clause.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClauseDTO> findOne(Long id);

    /**
     * Delete the "id" clause.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get the "id" clause.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClauseDTO> findById(Long id);

    /**
     * Get all the clauses by society id.
     *
     * @param idSociety the id of the society
     * @return the list of entities.
     */
    List<ClauseDTO> findAllClausesBySocietyId(Long idSociety);
}
