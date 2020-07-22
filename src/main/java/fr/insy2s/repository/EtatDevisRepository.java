package fr.insy2s.repository;

import fr.insy2s.domain.EtatDevis;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EtatDevis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatDevisRepository extends JpaRepository<EtatDevis, Long> {
}
