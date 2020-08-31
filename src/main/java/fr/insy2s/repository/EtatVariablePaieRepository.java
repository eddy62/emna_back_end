package fr.insy2s.repository;

import fr.insy2s.domain.EtatVariablePaie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EtatVariablePaie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatVariablePaieRepository extends JpaRepository<EtatVariablePaie, Long> {
}
