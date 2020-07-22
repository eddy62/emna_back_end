package fr.insy2s.repository;

import fr.insy2s.domain.AutresVariable;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AutresVariable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutresVariableRepository extends JpaRepository<AutresVariable, Long> {
}
