package fr.insy2s.repository;

import fr.insy2s.domain.StatutEmploye;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StatutEmploye entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatutEmployeRepository extends JpaRepository<StatutEmploye, Long> {
}
