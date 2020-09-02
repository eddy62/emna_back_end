package fr.insy2s.repository;

import fr.insy2s.domain.Prime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Prime entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrimeRepository extends JpaRepository<Prime, Long> {
    @Query("SELECT p FROM Prime p " +
            "WHERE p.employe.id=:idEmploye AND p.annee=:annee AND p.mois=:mois")
    List<Prime> findAllPrimeByIdEmployeAndAnneeAndMois(@Param("idEmploye") Long idEmploye, @Param("annee") Integer annee, @Param("mois") Integer mois);
}
