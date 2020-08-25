package fr.insy2s.repository;

import fr.insy2s.domain.AvanceRappelSalaire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AvanceRappelSalaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvanceRappelSalaireRepository extends JpaRepository<AvanceRappelSalaire, Long> {
}
