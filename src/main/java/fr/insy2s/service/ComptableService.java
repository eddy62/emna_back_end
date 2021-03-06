package fr.insy2s.service;

import fr.insy2s.service.dto.ComptableDTO;
import fr.insy2s.service.dto.ComptableInfoEntrepriseAdresseUserDTO;
import fr.insy2s.utils.wrapper.WrapperComptable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Comptable}.
 */
public interface ComptableService {

    /**
     * Save a comptable.
     *
     * @param comptableDTO the entity to save.
     * @return the persisted entity.
     */
    ComptableDTO save(ComptableDTO comptableDTO);

    /**
     * Get all the comptables.
     *
     * @return the list of entities.
     */
    List<ComptableDTO> findAll();


    /**
     * Get the "id" comptable.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ComptableDTO> findOne(Long id);

    /**
     * Delete the "id" comptable.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Wrappe a  comptable.
     *
     * @param comptableInfoEntrepriseAdresseUserDTO the compable with the objects needed to wrrape.
     */
    WrapperComptable creerOuModifierComptable(ComptableInfoEntrepriseAdresseUserDTO comptableInfoEntrepriseAdresseUserDTO, String callingMethode);
    WrapperComptable getComptable(Long id);

    Optional<ComptableDTO> findByUser(Long id);
}
