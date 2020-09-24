package fr.insy2s.service;

import fr.insy2s.service.dto.NoteDeFraisDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.NoteDeFrais}.
 */
public interface NoteDeFraisService {

    /**
     * Save a noteDeFrais.
     *
     * @param noteDeFraisDTO the entity to save.
     * @return the persisted entity.
     */
    NoteDeFraisDTO save(NoteDeFraisDTO noteDeFraisDTO);

    /**
     * Get all the noteDeFrais.
     *
     * @return the list of entities.
     */
    List<NoteDeFraisDTO> findAll();


    /**
     * Get the "id" noteDeFrais.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NoteDeFraisDTO> findOne(Long id);

    /**
     * Delete the "id" noteDeFrais.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all noteDeFrais by one employe, by one year and by one month.
     *
     * @param idEmploye id of the Employe in Entity
     * @param annee     year of the Employe in Entity
     * @param mois      month of the Employe in Entity
     * @return a list of NoteDeFraisDTO
     */
    List<NoteDeFraisDTO> findAllNoteDeFraisByIdEmployeAndAnneeAndMois(Long idEmploye, Integer annee, Integer mois);
}
