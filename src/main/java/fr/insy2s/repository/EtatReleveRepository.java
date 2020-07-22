package fr.insy2s.repository;

import fr.insy2s.domain.EtatReleve;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EtatReleve entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatReleveRepository extends JpaRepository<EtatReleve, Long> {
}
