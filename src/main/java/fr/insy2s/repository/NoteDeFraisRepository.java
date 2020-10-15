package fr.insy2s.repository;

import fr.insy2s.domain.NoteDeFrais;
import fr.insy2s.utils.wrapper.WrapperNoteDeFrais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data  repository for the NoteDeFrais entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoteDeFraisRepository extends JpaRepository<NoteDeFrais, Long> {
    @Query("SELECT n FROM NoteDeFrais n " +
            "WHERE n.employe.id=:idEmploye AND n.annee=:annee AND n.mois=:mois")
    List<NoteDeFrais> findAllNoteDeFraisByIdEmployeAndAnneeAndMois(@Param("idEmploye") Long idEmploye, @Param("annee") Integer annee, @Param("mois") Integer mois);

    @Query("SELECT n FROM NoteDeFrais n " +
            "WHERE n.employe.id=:idEmploye AND n.annee=:annee AND n.mois=:mois")
    List<WrapperNoteDeFrais> findAllWrapperNoteDeFraisByIdEmployeAndAnneeAndMois(@Param("idEmploye") Long idEmploye, @Param("annee") Integer annee, @Param("mois") Integer mois);

    @Query("SELECT n FROM NoteDeFrais n WHERE n.employe.id=:idEmploye AND n.date BETWEEN  :debutAbsence AND :finAbsence")
    List<NoteDeFrais> findNoteDeFraisExistByDate(@Param("idEmploye") Long idEmploye, @Param("debutAbsence") LocalDate debutAbsence, @Param("finAbsence") LocalDate finAbsence);
}
