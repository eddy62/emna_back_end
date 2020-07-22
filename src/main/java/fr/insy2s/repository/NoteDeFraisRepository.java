package fr.insy2s.repository;

import fr.insy2s.domain.NoteDeFrais;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NoteDeFrais entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoteDeFraisRepository extends JpaRepository<NoteDeFrais, Long> {
}
