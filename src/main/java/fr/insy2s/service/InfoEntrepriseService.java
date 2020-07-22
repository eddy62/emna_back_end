package fr.insy2s.service;

import fr.insy2s.service.dto.InfoEntrepriseDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.InfoEntreprise}.
 */
public interface InfoEntrepriseService {

    /**
     * Save a infoEntreprise.
     *
     * @param infoEntrepriseDTO the entity to save.
     * @return the persisted entity.
     */
    InfoEntrepriseDTO save(InfoEntrepriseDTO infoEntrepriseDTO);

    /**
     * Get all the infoEntreprises.
     *
     * @return the list of entities.
     */
    List<InfoEntrepriseDTO> findAll();


    /**
     * Get the "id" infoEntreprise.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InfoEntrepriseDTO> findOne(Long id);

    /**
     * Delete the "id" infoEntreprise.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
