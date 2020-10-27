package fr.insy2s.repository;

import fr.insy2s.domain.SaisieArticle;

import org.h2.command.dml.Update;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SaisieArticle entity.
 */
//    @Modifying
//    @Query("insert into TaskDocumentEntity c (c.idTask, c.description, c.filepath) values (:id,:description,:filepath)")
//    public void insertDocumentByTaskId(@Param("id") Long id,@Param("description") String description,@Param("filepath") String filepath);
@SuppressWarnings("unused")
@Repository
public interface SaisieArticleRepository extends JpaRepository<SaisieArticle, Long> {

//    @Modifying
//    @Query("insert into SaisieArticle s (s.libelle, s.avenant, s.article, s.contrat) values(:libelle, :idAvenant, :idArticle, :idContrat)")
//    void saveSaisieArticle(@Param("idAvenant") Long idAvenant,@Param("idArticle") Long idArticle,
//                           @Param("idContrat") Long idContrat,@Param("libelle") String libelle);
}
