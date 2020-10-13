package fr.insy2s.repository;

import fr.insy2s.domain.Devis;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Devis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {

    @Query("select d FROM Devis d, ClientFournisseur c WHERE d.clientFournisseur.id=c.id and c.societe.id=:id")
    List<Devis> findQuotesBySocietyId(@Param("id") Long id);

    List<Devis> findQuoteById(Long id);
}
