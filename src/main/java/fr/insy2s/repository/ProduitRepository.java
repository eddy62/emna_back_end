package fr.insy2s.repository;

import fr.insy2s.domain.Produit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Produit entity.
 */
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

     /**
     * get liste th clientFounisseur by id societe
     * @param id the societe
     * @return liste the clientFournisseur
     */
    List<Produit> findBySocieteId(@Param(value = "id") Long id);


}
