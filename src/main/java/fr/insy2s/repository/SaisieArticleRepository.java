package fr.insy2s.repository;

import fr.insy2s.domain.SaisieArticle;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SaisieArticle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SaisieArticleRepository extends JpaRepository<SaisieArticle, Long> {
}
