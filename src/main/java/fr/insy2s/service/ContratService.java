package fr.insy2s.service;

import fr.insy2s.domain.Contrat;
import fr.insy2s.repository.projection.IContratAllInfoProjection;
import fr.insy2s.repository.projection.IContratEmployerProjection;
import fr.insy2s.service.dto.ContratDTO;
import fr.insy2s.utils.wrapper.WrapperContrat;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Contrat}.
 */
public interface ContratService {

    /**
     * Save a contrat.
     *
     * @param contratDTO the entity to save.
     * @return the persisted entity.
     */
    ContratDTO save(ContratDTO contratDTO);

    /**
     * Get all the contrats.
     *
     * @return the list of entities.
     */
    List<ContratDTO> findAll();


    /**
     * Get the "id" contrat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContratDTO> findOne(Long id);

    /**
     * Delete the "id" contrat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all info with "id" contrat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    List<IContratAllInfoProjection> getContratAllInfos(Long id);


    List<IContratEmployerProjection> getAllContratEmployerById(Long id);

    Contrat getActiveContratEmployee(Long id);

    /**
     * Create a WrapperContrat
     *
     * @param wrapperContrat wrapperContrat to create
     * @return the created WrapperContrat
     */
    Optional<WrapperContrat> createWrapperContrat(@Valid WrapperContrat wrapperContrat);

    boolean signeContract(Long id);
}
