package fr.insy2s.service;

import fr.insy2s.repository.projection.IEmployeContratProjection;
import fr.insy2s.service.dto.EmployeDTO;
import fr.insy2s.utils.wrapper.WrapperEmploye;
import fr.insy2s.utils.wrapper.WrapperVariablesPaie;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Employe}.
 */
public interface EmployeService {

    /**
     * Save a employe.
     *
     * @param employeDTO the entity to save.
     * @return the persisted entity.
     */
    EmployeDTO save(EmployeDTO employeDTO);

    /**
     * Get all the employes.
     *
     * @return the list of entities.
     */
    List<EmployeDTO> findAll();

    /**
     * Get the "id" employe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmployeDTO> findOne(Long id);

    /**
     * Delete the "id" employe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);


    List<IEmployeContratProjection> getAllEmployeArticleClauseBySocieteId(Long id);

    /**
     * Get all the WrapperEmploye
     *
     * @return the list of wrapperEmploye
     */
    List<WrapperEmploye> findAllWrapperEmploye();

    /**
     * Get all the WrapperEmploye by society
     *
     * @return the list of wrapperEmploye
     */
    List<WrapperEmploye> findAllWrapperEmployeBySociete(final Long societeId);

    /**
     * Get the "id" wrapperEmploye
     *
     * @param id id of the wrapperEmploye to return
     * @return the wrapperEmploye
     */
    Optional<WrapperEmploye> findById(final Long id);

    /**
     * Create a WrapperEmploye
     *
     * @param wrapperEmploye WrapperEmploye to create
     * @return the created WrapperEmploye
     */
    Optional<WrapperEmploye> createWrapperEmploye(@Valid WrapperEmploye wrapperEmploye);

    /**
     * Update the WrapperEmploye
     *
     * @param wrapperEmploye WrapperEmploye to update
     * @return the updated WrapperEmploye
     */
    WrapperEmploye updateWrapperEmploye(@Valid WrapperEmploye wrapperEmploye);

    /**
     * Delete the "id" WrapperEmploye
     *
     * @param id id of the WrapperEmploye to delete
     * @return a boolean of success of deletion
     */
    boolean deleteWrapperEmploye(Long id);

    /**
     * Archive the "id" WrapperEmploye
     *
     * @param wrapperEmploye
     * @return the archived WrapperEmploye
     */
    WrapperEmploye archiveWrapperEmploye(@Valid WrapperEmploye wrapperEmploye);

    /**
     * Check si un numero de matricule existe deja
     *
     * @param matricule
     * @return
     */
    boolean isEmployeMatriculeExist(final String matricule);

    /**
     * Get one wrapperVariablesPaie by one employe, by one year and by one month.
     *
     * @param idEmploye id of the Employe in all VariablesPaie
     * @param annee     year in all VariablesPaie
     * @param mois      month in all VariablesPaie
     * @return the archived WrapperEmploye
     */
    WrapperVariablesPaie findOneWrapperVariablesPaieByIdEmployeAndAnneeAndMois(Long idEmploye, Integer annee, Integer mois);

}
