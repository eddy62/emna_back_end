package fr.insy2s.repository;

import fr.insy2s.domain.Avenant;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Avenant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvenantRepository extends JpaRepository<Avenant, Long> {
}
