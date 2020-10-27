package fr.insy2s.repository;

import fr.insy2s.domain.LigneProduit;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the LigneProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LigneProduitRepository extends JpaRepository<LigneProduit, Long> {

    @Query("select l from LigneProduit l " +
        "where l.facture.id =: idFacture")
    List<LigneProduit> getLigneProduitByIdFacture(@Param("idFacture") Long idFacture);

    boolean existsByProduit_Id(Long id);
    void deleteByDevis_Id(Long id);
}
