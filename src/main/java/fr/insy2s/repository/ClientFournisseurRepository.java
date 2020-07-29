package fr.insy2s.repository;

import fr.insy2s.domain.ClientFournisseur;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ClientFournisseur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientFournisseurRepository extends JpaRepository<ClientFournisseur, Long> {
    List<ClientFournisseur> findBySocieteId(@Param(value = "id") Long id);
}
