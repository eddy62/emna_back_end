package fr.insy2s.service;

import fr.insy2s.domain.Employe;
import fr.insy2s.service.dto.ComptableInfoEntrepriseAdresseUserDTO;
import fr.insy2s.service.dto.EmployeDTO;
import fr.insy2s.service.dto.SocieteDTO;
import fr.insy2s.service.dto.SocieteInfoEntrepriseAdresseUserDTO;
import fr.insy2s.utils.wrapper.WrapperComptable;
import fr.insy2s.utils.wrapper.WrapperEmploye;
import fr.insy2s.utils.wrapper.WrapperSociete;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Societe}.
 */
public interface SocieteService {

    /**
     * Save a societe.
     *
     * @param societeDTO the entity to save.
     * @return the persisted entity.
     */
    SocieteDTO save(SocieteDTO societeDTO);

    /**
     * Get all the societes.
     *
     * @return the list of entities.
     */
    List<SocieteDTO> findAll();


    /**
     * Get the "id" societe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SocieteDTO> findOne(Long id);

    /**
     * Delete the "id" societe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get the "id" WrapperSociete
     * @param id
     * @return the wrapperSociete
     */
    Optional<WrapperSociete> findById(Long id);

    List<SocieteDTO> findAllByComptableId(Long id);

    Optional<SocieteDTO> findByUser(Long id);

    WrapperSociete creerOuModifierSociete(SocieteInfoEntrepriseAdresseUserDTO societeInfoEntrepriseAdresseUserDTO, String callingMethode);
    WrapperSociete getSociete(Long id);

    /**
     * Get all the WrapperEmploye by society
     *
     * @return the list of Employe
     */
    List<EmployeDTO> findAllEmployeBySociete(Long societeId);
}
