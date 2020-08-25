package fr.insy2s.repository;

import fr.insy2s.domain.LigneProduit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LigneProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LigneProduitRepository extends JpaRepository<LigneProduit, Long> {
}
