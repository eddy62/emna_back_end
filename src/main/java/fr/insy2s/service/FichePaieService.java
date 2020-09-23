package fr.insy2s.service;

import fr.insy2s.domain.FichePaie;
import fr.insy2s.service.dto.FichePaieDTO;
import fr.insy2s.utils.wrapper.WrapperAbsence;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.FichePaie}.
 */
public interface FichePaieService {

    /**
     * Save a fichePaie.
     *
     * @param fichePaieDTO the entity to save.
     * @return the persisted entity.
     */
    FichePaieDTO save(FichePaieDTO fichePaieDTO);

    /**
     * Get all the fichePaies.
     *
     * @return the list of entities.
     */
    List<FichePaieDTO> findAll();


    /**
     * Get the "id" fichePaie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FichePaieDTO> findOne(Long id);

    /**
     * Delete the "id" fichePaie.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all the fichePaies by idEmploye, year, monthStart, monthEnd
     *
     * @param idEmploye the idEmploye of all the fichePaies to return
     * @param year the year of all the fichePaies to return
     * @param monthStart the monthStart of all the fichePaies to return
     * @param monthEnd the monthEnd of all the fichePaies to return
     * @return the list of wrapperAbsence
     */
    List<FichePaieDTO> findAllByEmployeYearMonthStartMonthEnd(Long idEmploye, Integer year, Integer monthStart, Integer monthEnd);

}
