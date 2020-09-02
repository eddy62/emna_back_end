package fr.insy2s.repository;

import fr.insy2s.domain.AutresVariable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the AutresVariable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutresVariableRepository extends JpaRepository<AutresVariable, Long> {
    @Query("SELECT av FROM AutresVariable av " +
            "WHERE av.employe.id=:idEmploye AND av.annee=:annee AND av.mois=:mois")
    List<AutresVariable> findAllAutresVariableByIdEmployeAndAnneeAndMois(@Param("idEmploye") Long idEmploye, @Param("annee") Integer annee, @Param("mois") Integer mois);
}
