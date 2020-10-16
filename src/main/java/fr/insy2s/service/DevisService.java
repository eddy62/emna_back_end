package fr.insy2s.service;

import fr.insy2s.service.dto.DevisDTO;
import fr.insy2s.utils.wrapper.WrapperQuote;

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
     * Get all the WrapperQuotes by society id.
     *
     * @param id the id of the society.
     * @return the list of entities Wrapper
     */
    List<WrapperQuote> findAllWrapperQuotes(Long id);

    /**
     * Get the "id" quote.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    WrapperQuote findQuote(Long id);

    /**
     * Save a quote.
     *
     * @param wrapperQuote the entity to save.
     * @return the persisted entity.
     */
    DevisDTO saveQuote(WrapperQuote wrapperQuote);

    /**
     * Get the "id" devis for get the number of the new quote
     *
     * @param id the id of the entity.
     * @return the number of the new quote.
     */
    Long findQuoteNumber(Long id);
}
