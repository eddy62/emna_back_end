package fr.insy2s.repository;

import fr.insy2s.domain.Absence;
import fr.insy2s.domain.FichePaie;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the FichePaie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FichePaieRepository extends JpaRepository<FichePaie, Long> {
    @Query("SELECT fp FROM FichePaie fp " +
        "WHERE fp.employe.id=:idEmploye AND fp.annee=:year AND fp.mois>=:monthStart AND fp.mois<=:monthEnd")
    List<FichePaie> findAllByEmployeYearMonthStartMonthEnd(@Param("idEmploye") Long idEmploye, @Param("year") Integer year, @Param("monthStart") Integer monthStart, @Param("monthEnd") Integer monthEnd);
}
