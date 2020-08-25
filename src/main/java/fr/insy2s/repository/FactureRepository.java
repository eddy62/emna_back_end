package fr.insy2s.repository;

import fr.insy2s.domain.Facture;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Facture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

    List<Facture> findAllBySocieteId(Long id);
}
