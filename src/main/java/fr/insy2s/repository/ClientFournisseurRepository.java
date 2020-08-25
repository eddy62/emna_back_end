package fr.insy2s.repository;

import fr.insy2s.domain.ClientFournisseur;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ClientFournisseur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientFournisseurRepository extends JpaRepository<ClientFournisseur, Long> {
    /**
     * get liste th clientFounisseur by id societe
     * @param id the societe
     * @return liste the clientFournisseur
     */
    List<ClientFournisseur> findBySocieteId(@Param(value = "id") Long id);

    /**
     * get by "nom" the entity
     * @param nom the clientFounisseur
     * @return the entity
     */
    Optional<ClientFournisseur> findByNom(String nom);
}
