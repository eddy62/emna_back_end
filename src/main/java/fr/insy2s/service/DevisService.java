package fr.insy2s.service;

import fr.insy2s.service.dto.DevisDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Devis}.
 */
public interface DevisService {

    /**
     * Save a devis.
     *
     * @param devisDTO the entity to save.
     * @return the persisted entity.
     */
    DevisDTO save(DevisDTO devisDTO);

    /**
     * Get all the devis.
     *
     * @return the list of entities.
     */
    List<DevisDTO> findAll();


    /**
     * Get the "id" devis.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DevisDTO> findOne(Long id);

    /**
     * Delete the "id" devis.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all quotes by society id.
     *
     * @param idSociete the id of the society.
     * @return the list of entities
     */
    List<DevisDTO> findAllQuotesBySocietyId(Long idSociete);
}
