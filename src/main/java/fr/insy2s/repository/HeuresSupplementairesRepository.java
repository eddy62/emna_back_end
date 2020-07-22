package fr.insy2s.repository;

import fr.insy2s.domain.HeuresSupplementaires;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the HeuresSupplementaires entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HeuresSupplementairesRepository extends JpaRepository<HeuresSupplementaires, Long> {
}
