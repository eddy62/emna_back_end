package fr.insy2s.repository;

import fr.insy2s.domain.EtatDepense;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EtatDepense entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatDepenseRepository extends JpaRepository<EtatDepense, Long> {
}
