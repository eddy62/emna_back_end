package fr.insy2s.repository;

import fr.insy2s.domain.Facture;
import fr.insy2s.domain.LigneProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Facture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

    List<Facture> findAllBySocieteIdOrderByNumfactDesc(Long id);

    @Query("select f From Facture f " +
            "where f.date >= (select r.dateDebut from Releve r where r.id=:idReleve) " +
            "and f.date <= (select r.dateFin from Releve r where r.id=:idReleve)" +
            "and f.operation is null")
    List<Facture> findAllInvoicesByStatement(@Param("idReleve") Long idReleve);

    @Query("select f from Facture f " +
        "where f.operation.id =:idOperation")
    List<Facture> balanceOfInvoicesByTransaction(@Param("idOperation") Long idOperation);

    @Query("select l from LigneProduit l " +
    "where l.facture.id =: idFacture")
    List<LigneProduit> getLigneProduitByIdFacture(@Param("idFacture") Long idFacture);

    @Modifying
    @Query("update Facture f "+
            "set f.operation.id =:idOperation " +
            "where f.id=:idFacture")
    Integer mergeOperationByIdFacture(@Param("idFacture") Long idFacture,@Param("idOperation") Long idOperation);

    @Query("select f from Facture f " +
    "where f.operation.id=:idOperation")
    List<Facture> findAllInvoicesByOperationId(@Param("idOperation") Long idOperation);
}
