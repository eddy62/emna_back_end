package fr.insy2s.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.insy2s.domain.Employe;

/**
 * Spring Data repository for the Employe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

    List<Employe> findBySocieteId(@Param(value = "id") Long id);

    List<Employe> findByTypeContrat(@Param(value = "typeContrat") String typeContrat);
}
