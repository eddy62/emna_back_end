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


//    @Query(nativeQuery = true, value = "SELECT e.ID AS employerId, e.NOM_USAGE AS employerNom,e.PRENOM AS employerPrenom, e.SOCIETE_ID AS societeId , a.TITRE AS articleTitre ,a.DESCRIPTION AS articleDescription,a.ID AS articleID, a.REFERENCE AS articleReference, c1.ID AS clauseId  , c1.DESCRIPTION AS clauseDescription FROM EMPLOYE e INNER JOIN SOCIETE s ON e.SOCIETE_ID = s.ID INNER JOIN ARTICLE a ON a.SOCIETE_ID = s.ID INNER JOIN CLAUSE c1 ON c1.SOCIETE_ID = s.ID AND c1.ARTICLE_ID = a.ID WHERE e.SOCIETE_ID=1 ;")
//    List<IEmployeContratProjection> getAllEmployeArticleClauseBySocieteId(@Param("id") Long id);

    List<Employe> findBySocieteId(@Param(value = "id") Long id);

    List<Employe> findByTypeContrat(@Param(value = "typeContrat") String typeContrat);

    Employe findByMatricule(@Param(value = "matricule")String matricule);


}
