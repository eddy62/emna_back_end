package fr.insy2s.repository;

import fr.insy2s.domain.Dpae;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Dpae entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DpaeRepository extends JpaRepository<Dpae, Long> {
}
