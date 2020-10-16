package fr.insy2s.service;

import fr.insy2s.service.dto.HeuresSupplementairesDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.HeuresSupplementaires}.
 */
public interface HeuresSupplementairesService {

    /**
     * Save a heuresSupplementaires.
     *
     * @param heuresSupplementairesDTO the entity to save.
     * @return the persisted entity.
     */
    HeuresSupplementairesDTO save(HeuresSupplementairesDTO heuresSupplementairesDTO);

    /**
     * Get all the heuresSupplementaires.
     *
     * @return the list of entities.
     */
    List<HeuresSupplementairesDTO> findAll();


    /**
     * Get the "id" heuresSupplementaires.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HeuresSupplementairesDTO> findOne(Long id);

    /**
     * Delete the "id" heuresSupplementaires.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Optional<HeuresSupplementairesDTO> findHeuresSupplementairesExistByDate(Long idEmploye, LocalDate debutAbsence, LocalDate finAbsence);
}
