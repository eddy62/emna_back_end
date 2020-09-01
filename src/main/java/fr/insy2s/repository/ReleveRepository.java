package fr.insy2s.repository;

import fr.insy2s.domain.Releve;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Releve entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReleveRepository extends JpaRepository<Releve, Long> {
	List <Releve> findAllBySocieteId(Long id);
	List <Releve> findAllByEtatReleveId(Long id);
	List <Releve> findAllByEtatReleveIdAndSocieteId(Long idEtat,Long idSociete);

	@Query("SELECT SUM(o.solde) FROM Operation o WHERE o.releve.id = :id")
    Optional<BigDecimal> getReleveSoldeById(@Param(value = "id") Long id);
}
