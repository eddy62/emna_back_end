package fr.insy2s.repository;

import fr.insy2s.domain.Dpae;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data  repository for the Dpae entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DpaeRepository extends JpaRepository<Dpae, Long> {
    @Query ("SELECT dp FROM Dpae dp " +
    "WHERE dp.contrat.employe.id=:idEmploye AND dp.date>=:startDate AND dp.date<=:endDate")
    List<Dpae> findAllDpaeByEmployeIdMonthStartMonthEnd(@Param("idEmploye") Long idEmploye, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}

