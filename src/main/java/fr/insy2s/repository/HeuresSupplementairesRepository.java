package fr.insy2s.repository;

import fr.insy2s.domain.HeuresSupplementaires;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data  repository for the HeuresSupplementaires entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HeuresSupplementairesRepository extends JpaRepository<HeuresSupplementaires, Long> {
    @Query("SELECT hs FROM HeuresSupplementaires hs " +
            "WHERE hs.employe.id=:idEmploye AND hs.annee=:annee AND hs.mois=:mois")
    List<HeuresSupplementaires> findAllHeuresSupplementairesByIdEmployeAndAnneeAndMois(@Param("idEmploye") Long idEmploye, @Param("annee") Integer annee, @Param("mois") Integer mois);

    @Query("SELECT hs FROM HeuresSupplementaires hs WHERE hs.employe.id=:idEmploye AND hs.date BETWEEN  :debutAbsence AND :finAbsence")
    List<HeuresSupplementaires> findHeuresSupplementairesExistByDate(@Param("idEmploye") Long idEmploye, @Param("debutAbsence") LocalDate debutAbsence, @Param("finAbsence") LocalDate finAbsence);
}
