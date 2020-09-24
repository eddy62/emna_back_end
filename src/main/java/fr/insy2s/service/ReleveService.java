package fr.insy2s.service;

import fr.insy2s.service.dto.ReleveDTO;
import fr.insy2s.utils.wrapper.WrapperArchivedStatement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Releve}.
 */
public interface ReleveService {

    /**
     * Save a releve.
     *
     * @param releveDTO the entity to save.
     * @return the persisted entity.
     */
    ReleveDTO save(ReleveDTO releveDTO);

    /**
     * Get all the releves.
     *
     * @return the list of entities.
     */
    List<ReleveDTO> findAll();


    /**
     * Get the "id" releve.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReleveDTO> findOne(Long id);

    /**
     * Delete the "id" releve.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<ReleveDTO> findAllBySocieteId(Long id);

    List<ReleveDTO> findAllByEtatReleveId(Long id);

    List<ReleveDTO> findAllByEtatReleveIdAndSocieteId(Long idEtat, Long idSociete);

    Optional<BigDecimal> getReleveSoldeById(Long id);

    /**
     * Checked permission for the releve
     *
     * @param idReleve the id of the releve.
     * @param loginCurrentUser the login of the user.
     * @return boolean
     */
    boolean hasPermissionForThisReleve(Long idReleve, String loginCurrentUser);

    /**
     * checked solde Invoices equals solde statements
     *
     * @param idReleve the id of the releve.
     * @return boolean
     */
    boolean balanceOperationsEqualsInvoices(Long idReleve);

    /**
     * Validate the "id" releve.
     *
     * @param idReleve the id of the statement.
     * @param idEtat the id of statut.
     * @return Integer.
     */
    boolean changeStatutStatement(Long idReleve, Long idEtat);

    /**
     * Get WrapperArchivedStatement.
     *
     * @param id the id of the statement.
     * @return WrapperArchivedStatement.
     */
    WrapperArchivedStatement getWrapperArchivedStatement(Long id);
}
