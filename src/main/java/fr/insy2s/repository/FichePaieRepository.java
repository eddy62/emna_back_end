package fr.insy2s.repository;

import fr.insy2s.domain.FichePaie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FichePaie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FichePaieRepository extends JpaRepository<FichePaie, Long> {
}
