package fr.insy2s.service;

import fr.insy2s.service.dto.ArticleDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Article}.
 */
public interface ArticleService {

    /**
     * Save a article.
     *
     * @param articleDTO the entity to save.
     * @return the persisted entity.
     */
    ArticleDTO save(ArticleDTO articleDTO);

    /**
     * Get all the articles.
     *
     * @return the list of entities.
     */
    List<ArticleDTO> findAll();

    /**
     * Get all the articles with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ArticleDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" article.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ArticleDTO> findOne(Long id);

    /**
     * Delete the "id" article.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Check if an article already exists.
     *
     * @param articleDTO the article to compare.
     */
    boolean isArticleAlreadyExists(ArticleDTO articleDTO);
}
