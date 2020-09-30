package fr.insy2s.service;

import fr.insy2s.service.dto.TypeDocumentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.TypeDocument}.
 */
public interface TypeDocumentService {

    /**
     * Save a typeDocument.
     *
     * @param typeDocumentDTO the entity to save.
     * @return the persisted entity.
     */
    TypeDocumentDTO save(TypeDocumentDTO typeDocumentDTO);

    /**
     * Get all the typeDocuments.
     *
     * @return the list of entities.
     */
    List<TypeDocumentDTO> findAll();


    /**
     * Get the "id" typeDocument.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeDocumentDTO> findOne(Long id);

    /**
     * Delete the "id" typeDocument.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
