package fr.insy2s.repository;

import fr.insy2s.domain.InfoEntreprise;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InfoEntreprise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InfoEntrepriseRepository extends JpaRepository<InfoEntreprise, Long> {
}
