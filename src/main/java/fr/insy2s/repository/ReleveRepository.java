package fr.insy2s.repository;

import fr.insy2s.domain.Releve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Releve entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReleveRepository extends JpaRepository<Releve, Long> {
    List<Releve> findAllBySocieteId(Long id);

    List<Releve> findAllByEtatReleveId(Long id);

    List<Releve> findAllByEtatReleveIdAndSocieteId(Long idEtat, Long idSociete);

    @Modifying
    @Query("update  Releve r set r.etatReleve.id =:idEtat where r.id=:id")
    Integer validateRelever(@Param("id") Long id, @Param("idEtat") Long idEtat);

    @Query("SELECT SUM(o.solde) FROM Operation o WHERE o.releve.id = :id")
    Optional<BigDecimal> getReleveSoldeById(@Param(value = "id") Long id);

    @Query("from Releve r " +
            "join Societe s on r.societe.id = s.id " +
            "join Comptable c on s.comptable.id = c.id " +
            "join User u on c.user.id = u.id " +
            "where r.id =:idReleve and u.login like :loginCurrentUser")
    Releve checkPermissionForThisReleve(@Param(value = "idReleve") Long idReleve,
                                        @Param(value = "loginCurrentUser") String loginCurrentUser);
}
