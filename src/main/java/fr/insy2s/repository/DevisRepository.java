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

    @Query("FROM Devis d " +
           "WHERE d.societe.id=:idSociete")
    List<Devis> findAllQuotesBySocietyId(@Param("idSociete") Long idSociete);
}
