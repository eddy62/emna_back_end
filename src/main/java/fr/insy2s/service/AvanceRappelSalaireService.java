package fr.insy2s.service;

import fr.insy2s.service.dto.AvanceRappelSalaireDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.AvanceRappelSalaire}.
 */
public interface AvanceRappelSalaireService {

    /**
     * Save a avanceRappelSalaire.
     *
     * @param avanceRappelSalaireDTO the entity to save.
     * @return the persisted entity.
     */
    AvanceRappelSalaireDTO save(AvanceRappelSalaireDTO avanceRappelSalaireDTO);

    /**
     * Get all the avanceRappelSalaires.
     *
     * @return the list of entities.
     */
    List<AvanceRappelSalaireDTO> findAll();


    /**
     * Get the "id" avanceRappelSalaire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AvanceRappelSalaireDTO> findOne(Long id);

    /**
     * Delete the "id" avanceRappelSalaire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
