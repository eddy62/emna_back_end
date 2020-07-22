package fr.insy2s.repository;

import fr.insy2s.domain.Prime;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Prime entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrimeRepository extends JpaRepository<Prime, Long> {
}
