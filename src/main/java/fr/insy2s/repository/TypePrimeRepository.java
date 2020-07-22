package fr.insy2s.repository;

import fr.insy2s.domain.TypePrime;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypePrime entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypePrimeRepository extends JpaRepository<TypePrime, Long> {
}
