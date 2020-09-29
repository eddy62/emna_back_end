package fr.insy2s.service;

import fr.insy2s.domain.Document;
import fr.insy2s.service.dto.DocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Document}.
 */
public interface DocumentService {

    /**
     * Save a document.
     *
     * @param documentDTO the entity to save.
     * @return the persisted entity.
     */
    DocumentDTO save(DocumentDTO documentDTO);

    /**
     * Get all the documents.
     *
     * @return the list of entities.
     */
    List<DocumentDTO> findAll();

    /**
     * Get the "id" document.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DocumentDTO> findOne(Long id);

    /**
     * Delete the "id" document.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get a set of Document from a list of MultipartFile files
     *
     * @param files the files to get a Documents from.
     * @return the set of Entity Document
     */
    Set<Document> multiPartFilesToDocuments(List<MultipartFile> files);

    /**
     * Get one Document from a MultipartFile file
     *
     * @param file the file to get a Document from.
     * @return the Entity Document
     */
    Document multiPartFileToDocument(MultipartFile file);

    /**
     * Get all the documents by "id" Absence.
     *
     * @param id the id of Absence to get all documents from.
     * @return the list of DocumentDTO.
     */
    List<DocumentDTO> findAllByAbsenceId(Long id);

    /**
     * Get all the documents by "id" Note de Frais
     *
     * @return the list of entities.
     */
    List<DocumentDTO> findAllByNoteDeFraisId(Long id);

    /**
     * Get all the documents by "id" AutresVariable
     *
     * @param id the id of AutresVariable to get all documents from.
     * @return the list of DocumentDTO.
     */
    List<DocumentDTO> findAllByAutresVariablesId(Long id);
}
