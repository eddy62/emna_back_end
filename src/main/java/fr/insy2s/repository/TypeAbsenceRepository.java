package fr.insy2s.repository;

import fr.insy2s.domain.TypeAbsence;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeAbsence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeAbsenceRepository extends JpaRepository<TypeAbsence, Long> {
}
