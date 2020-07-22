package fr.insy2s.repository;

import fr.insy2s.domain.Comptable;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Comptable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComptableRepository extends JpaRepository<Comptable, Long> {
}
