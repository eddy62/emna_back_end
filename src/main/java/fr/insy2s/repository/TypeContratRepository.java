package fr.insy2s.repository;

import fr.insy2s.domain.TypeContrat;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeContrat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeContratRepository extends JpaRepository<TypeContrat, Long> {
}
