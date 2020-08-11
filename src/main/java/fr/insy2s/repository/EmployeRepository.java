package fr.insy2s.repository;


import fr.insy2s.domain.Employe;
import fr.insy2s.repository.projection.IEmployeContratProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Spring Data repository for the Employe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {


    @Query(nativeQuery = true, value = "SELECT e.ID AS employerId, e.NOM_USAGE AS employerNom,e.PRENOM AS employerPrenom, e.SOCIETE_ID AS societeId , a.TITRE AS articleTitre  , c1.ID AS clauseId , c1.REFERENCE AS clauseReference , c1.DESCRIPTION AS clauseDescription FROM EMPLOYE e INNER JOIN SOCIETE s ON e.SOCIETE_ID = s.ID INNER JOIN ARTICLE a ON a.SOCIETE_ID = s.ID INNER JOIN CLAUSE c1 ON c1.SOCIETE_ID = s.ID AND c1.ARTICLE_ID = a.ID INNER JOIN CLAUSE_LISTE_CONTRATS clc ON c1.ID = clc.CLAUSE_ID INNER JOIN CONTRAT c2 ON c2.ID = clc.LISTE_CONTRATS_ID WHERE e.SOCIETE_ID=:id AND C2.EMPLOYE_ID = e.ID ;")
    List<IEmployeContratProjection> getAllEmployeArticleClauseBySocieteId(@Param("id") Long id);

    List<Employe> findBySocieteId(@Param(value = "id") Long id);

    List<Employe> findByTypeContrat(@Param(value = "typeContrat") String typeContrat);

}
