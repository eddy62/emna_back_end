package fr.insy2s.service;

import fr.insy2s.service.dto.FactureDTO;
import fr.insy2s.service.dto.FactureTemp;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Facture}.
 */
public interface FactureService {

    /**
     * Save a facture.
     *
     * @param factureDTO the entity to save.
     * @return the persisted entity.
     */
    FactureDTO save(FactureDTO factureDTO);

    /**
     * Get all the factures.
     *
     * @return the list of entities.
     */
    List<FactureDTO> findAll();


    /**
     * Get the "id" facture.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FactureDTO> findOne(Long id);

    /**
     * Delete the "id" facture.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    FactureDTO postFactureWithFile(FactureTemp factureTemp);

    List<FactureDTO> findAllBySocieteId(Long id);

    /**
     * Get all the factures for the statement concerned.
     *
     * @param id the id of the statement
     * @return the list of entities.
     */
    List<FactureDTO> findAllInvoicesByStatement(Long idReleve);

}
