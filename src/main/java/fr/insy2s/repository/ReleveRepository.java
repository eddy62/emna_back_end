package fr.insy2s.repository;

import fr.insy2s.domain.Releve;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Releve entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReleveRepository extends JpaRepository<Releve, Long> {
}
