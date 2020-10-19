package fr.insy2s.service;

import fr.insy2s.service.dto.AvenantDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Avenant}.
 */
public interface AvenantService {

    /**
     * Save a avenant.
     *
     * @param avenantDTO the entity to save.
     * @return the persisted entity.
     */
    AvenantDTO save(AvenantDTO avenantDTO);

    /**
     * Get all the avenants.
     *
     * @return the list of entities.
     */
    List<AvenantDTO> findAll();


    /**
     * Get the "id" avenant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AvenantDTO> findOne(Long id);

    /**
     * Delete the "id" avenant.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Rècupère la liste des avenants avec l'id contrat
     * @param idContract id du contrat
     * @return une liste d'avenant
     */
    List<AvenantDTO> getAllAmendmentByContractId(long idContract);

    /**
     * get pdf for WrapperAmendment
     * @param idAmendment id of Amendment
     * @return
     */
    byte[] getPDFAmendement(Long idAmendment);

    /**
     *
     * @return String for the name pdf
     */
    String getNamePdf(Long idAmendment);
}
