package fr.insy2s.repository;

import fr.insy2s.domain.SaisieArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SaisieArticle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SaisieArticleRepository extends JpaRepository<SaisieArticle, Long> {
    @Query("SELECT sa from SaisieArticle sa WHERE sa.contrat.id = :idContrat AND sa.article.id = 2")
    SaisieArticle findDateDebutbyContratId(@Param("idContrat") Long id);

    @Query("SELECT sa, c, tc FROM SaisieArticle sa" +
        " JOIN Contrat c on (c.id = sa.contrat.id)" +
        " JOIN TypeContrat tc on (tc.id = c.typeContrat.id)" +
        " JOIN Employe e on (e.id = c.employe.id)" +
        " WHERE e.id = :employeeId" +
        " AND c.archive = false" +
        " AND sa.article.id = 2")
    SaisieArticle findActiveStartDateByEmployee(@Param(value = "employeeId") Long employeeId);

//    @Modifying
//    @Query("insert into TaskDocumentEntity c (c.idTask, c.description, c.filepath) values (:id,:description,:filepath)")
//    public void insertDocumentByTaskId(@Param("id") Long id,@Param("description") String description,@Param("filepath") String filepath);

//    @Modifying
//    @Query("insert into SaisieArticle s (s.libelle, s.avenant, s.article, s.contrat) values(:libelle, :idAvenant, :idArticle, :idContrat)")
//    void saveSaisieArticle(@Param("idAvenant") Long idAvenant,@Param("idArticle") Long idArticle,
//                           @Param("idContrat") Long idContrat,@Param("libelle") String libelle);
}
