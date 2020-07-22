package fr.insy2s.repository;

import fr.insy2s.domain.EtatFacture;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EtatFacture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatFactureRepository extends JpaRepository<EtatFacture, Long> {
}
