package fr.insy2s.repository;

import fr.insy2s.domain.Releve;

import java.util.List;
import java.util.Optional;

import fr.insy2s.utils.EtatReleveConstants;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sun.jvm.hotspot.types.JLongField;

/**
 * Spring Data  repository for the Releve entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReleveRepository extends JpaRepository<Releve, Long> {
	List <Releve> findAllBySocieteId(Long id);
	List <Releve> findAllByEtatReleveId(Long id);
	List <Releve> findAllByEtatReleveIdAndSocieteId(Long idEtat,Long idSociete);

	@Modifying
	@Query("update  Releve r set r.etatReleve.id =:idEtat where r.id=:id")
    Integer validateRelever(@Param("id") Long id,@Param("idEtat") Long idEtat);
}
