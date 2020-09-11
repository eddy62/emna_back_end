package fr.insy2s.repository;

import fr.insy2s.domain.Depense;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Depense entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepenseRepository extends JpaRepository<Depense, Long> {

    List<Depense> findAllBySocieteIdOrderByNumeroDesc(Long id);
}
