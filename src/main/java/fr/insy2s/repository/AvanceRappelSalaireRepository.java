package fr.insy2s.repository;

import fr.insy2s.domain.AvanceRappelSalaire;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the AvanceRappelSalaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvanceRappelSalaireRepository extends JpaRepository<AvanceRappelSalaire, Long> {
    @Query("SELECT ars FROM AvanceRappelSalaire ars " +
            "WHERE ars.employe.id=:idEmploye AND ars.annee=:annee AND ars.mois=:mois")
    List<AvanceRappelSalaire> findAllAvanceRappelSalaireByIdEmployeAndAnneeAndMois(@Param("idEmploye") Long idEmploye, @Param("annee") Integer annee, @Param("mois") Integer mois);
}
