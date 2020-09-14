package fr.insy2s.repository;

import fr.insy2s.domain.Clause;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Clause entity.
 */
@Repository
public interface ClauseRepository extends JpaRepository<Clause, Long> {

    @Query(value = "select distinct clause from Clause clause left join fetch clause.listeContrats left join fetch clause.listeAvenants",
        countQuery = "select count(distinct clause) from Clause clause")
    Page<Clause> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct clause from Clause clause left join fetch clause.listeContrats left join fetch clause.listeAvenants")
    List<Clause> findAllWithEagerRelationships();

    @Query("select clause from Clause clause left join fetch clause.listeContrats left join fetch clause.listeAvenants where clause.id =:id")
    Optional<Clause> findOneWithEagerRelationships(@Param("id") Long id);

    @Query("FROM Clause c " +
           "WHERE c.societe.id=:idSociete")
    List<Clause> findAllClausesBySocietyId(@Param("idSociete") Long idSociete);

}
