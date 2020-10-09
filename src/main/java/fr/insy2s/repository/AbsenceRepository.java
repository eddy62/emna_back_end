package fr.insy2s.repository;

import fr.insy2s.domain.Absence;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data  repository for the Absence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    @Query("SELECT a FROM Absence a " +
            "WHERE a.employe.id=:idEmploye AND a.annee=:annee AND a.mois=:mois")
    List<Absence> findAllAbsenceByIdEmployeAndAnneeAndMois(@Param("idEmploye") Long idEmploye, @Param("annee") Integer annee, @Param("mois") Integer mois);

    @Query("SELECT a FROM Absence a WHERE a.employe.id=:idEmploye AND " +
            "(a.debutAbsence >= :debutAbsence AND a.finAbsence <= :finAbsence) OR " +
            "(a.finAbsence >= :debutAbsence AND a.finAbsence <= : finAbsence) OR " +
            "(a.debutAbsence >= :debutAbsence AND a.debutAbsence <= :finAbsence) OR " +
            "(a.debutAbsence <= :debutAbsence AND a.finAbsence >= :finAbsence)")
    List<Absence> findAllOverlappingAbsencesByIdEmploye(@Param("idEmploye") Long idEmploye, @Param("debutAbsence") LocalDate debutAbsence, @Param("finAbsence") LocalDate finAbsence);
}
