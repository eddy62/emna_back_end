package fr.insy2s.service;

import fr.insy2s.service.dto.ReleveDTO;
import org.springframework.http.ResponseEntity;

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
    List <ReleveDTO> findAllBySocieteId(Long id);
    List <ReleveDTO> findAllByEtatReleveId(Long id);
    List <ReleveDTO> findAllByEtatReleveIdAndSocieteId(Long idEtat,Long idSociete);

    /**
     * Validate the "id" releve.
     *
     * @param id the id of the entity.
     */
    boolean validateReleve(Long id);
}
