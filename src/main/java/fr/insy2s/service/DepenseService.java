package fr.insy2s.service;

import fr.insy2s.service.dto.DepenseDTO;
import fr.insy2s.service.dto.DepenseTemp;
import fr.insy2s.utils.wrapper.WrapperDepense;
import fr.insy2s.utils.wrapper.WrapperListeDepense;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Depense}.
 */
public interface DepenseService {

    /**
     * Save a depense.
     *
     * @param depenseDTO the entity to save.
     * @return the persisted entity.
     */
    DepenseDTO save(DepenseDTO depenseDTO);

    /**
     * Get all the depenses.
     *
     * @return the list of entities.
     */
    List<DepenseDTO> findAll();


    /**
     * Get the "id" depense.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WrapperDepense> findOne(Long id);

    /**
     * Delete the "id" depense.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<WrapperListeDepense> findAllDepenseBySocieteId(Long id);

    DepenseDTO postDepenseWithFile(DepenseTemp depenseTemp);
    DepenseDTO createFromWrapperDepense(WrapperDepense wrapperDepense);
}
