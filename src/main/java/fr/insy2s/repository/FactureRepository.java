package fr.insy2s.repository;

import fr.insy2s.domain.Facture;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Facture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

    List<Facture> findAllBySocieteIdOrderByNumfactDesc(Long id);

    @Query("select f From Facture f " +
           "where f.date between (select r.dateDebut from Releve r where r.id=:idReleve) " +
           "and (select r.dateFin from Releve r where r.id=:idReleve)")
    List<Facture> findAllInvoicesByStatement(@Param("idReleve") Long idReleve);

    @Query("select sum(f.prixTTC) from Facture f " +
        "where f.operation.id =:idOperation")
    Optional<BigDecimal> balanceOfInvoicesByTransaction(@Param("idOperation") Long idOperation);

}
