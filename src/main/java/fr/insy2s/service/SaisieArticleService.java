package fr.insy2s.service;

import fr.insy2s.domain.SaisieArticle;
import fr.insy2s.service.dto.SaisieArticleDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.SaisieArticle}.
 */
public interface SaisieArticleService {

    /**
     * Save a saisieArticle.
     *
     * @param saisieArticleDTO the entity to save.
     * @return the persisted entity.
     */
    SaisieArticleDTO save(SaisieArticleDTO saisieArticleDTO);

    /**
     * Get all the saisieArticles.
     *
     * @return the list of entities.
     */
    List<SaisieArticleDTO> findAll();

    /**
     * Get the "id" saisieArticle.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SaisieArticleDTO> findOne(Long id);

    /**
     * Delete the "id" saisieArticle.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get the "contratId" date de début.
     *
     * @param id the contratId of the entity.
     * @return the saisieArticle for the Article "date de début".
     */
    SaisieArticleDTO findDateDebutbyContratId(Long id);

    /**
     * Get the "employeeId" date de début active.
     *
     * @param id the employeeId of the entity.
     * @return  the  entity.
     */
    SaisieArticle findActiveStartDateByEmployee(Long id);
}
