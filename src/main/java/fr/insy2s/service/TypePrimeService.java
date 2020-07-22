package fr.insy2s.service;

import fr.insy2s.service.dto.TypePrimeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.TypePrime}.
 */
public interface TypePrimeService {

    /**
     * Save a typePrime.
     *
     * @param typePrimeDTO the entity to save.
     * @return the persisted entity.
     */
    TypePrimeDTO save(TypePrimeDTO typePrimeDTO);

    /**
     * Get all the typePrimes.
     *
     * @return the list of entities.
     */
    List<TypePrimeDTO> findAll();


    /**
     * Get the "id" typePrime.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypePrimeDTO> findOne(Long id);

    /**
     * Delete the "id" typePrime.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
