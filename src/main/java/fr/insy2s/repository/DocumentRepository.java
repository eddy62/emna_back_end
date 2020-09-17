package fr.insy2s.repository;

import fr.insy2s.domain.Document;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data  repository for the Document entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findAllByAbsenceId(@Param(value = "id") Long id);

    List<Document> findAllByNoteDeFraisId(@Param(value = "id") Long id);

    //List<Document> findAllByAutresVariableId(@Param(value = "id") Long id);
}
