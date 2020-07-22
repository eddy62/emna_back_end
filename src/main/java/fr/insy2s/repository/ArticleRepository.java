package fr.insy2s.repository;

import fr.insy2s.domain.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Article entity.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "select distinct article from Article article left join fetch article.listeAvenants left join fetch article.listeContrats",
        countQuery = "select count(distinct article) from Article article")
    Page<Article> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct article from Article article left join fetch article.listeAvenants left join fetch article.listeContrats")
    List<Article> findAllWithEagerRelationships();

    @Query("select article from Article article left join fetch article.listeAvenants left join fetch article.listeContrats where article.id =:id")
    Optional<Article> findOneWithEagerRelationships(@Param("id") Long id);
}
