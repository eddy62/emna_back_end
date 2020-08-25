package fr.insy2s.repository;

import fr.insy2s.domain.ClientFournisseur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ClientFournisseur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientFournisseurRepository extends JpaRepository<ClientFournisseur, Long> {
}
