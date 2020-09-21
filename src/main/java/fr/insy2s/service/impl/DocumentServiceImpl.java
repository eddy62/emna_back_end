package fr.insy2s.service.impl;

import fr.insy2s.service.DocumentService;
import fr.insy2s.domain.Document;
import fr.insy2s.repository.DocumentRepository;
import fr.insy2s.service.dto.DocumentDTO;
import fr.insy2s.service.mapper.DocumentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Document}.
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private final Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);

    private final DocumentRepository documentRepository;

    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    /**
     * Save a document.
     *
     * @param documentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DocumentDTO save(DocumentDTO documentDTO) {
        log.debug("Request to save Document : {}", documentDTO);
        Document document = documentMapper.toEntity(documentDTO);
        document = documentRepository.save(document);
        return documentMapper.toDto(document);
    }

    /**
     * Get all the documents.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocumentDTO> findAll() {
        log.debug("Request to get all Documents");
        return documentRepository.findAll().stream()
            .map(documentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one document by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentDTO> findOne(Long id) {
        log.debug("Request to get Document : {}", id);
        return documentRepository.findById(id)
            .map(documentMapper::toDto);
    }

    /**
     * Delete the document by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Document : {}", id);
        documentRepository.deleteById(id);
    }

    public Set<Document> multiPartFilesToDocuments(List<MultipartFile> files){
        return files.stream()
            .map((this::multiPartFileToDocument))
            .collect(Collectors.toSet());
    }

    public Document multiPartFileToDocument(MultipartFile file) {
        Path path1 = Paths.get("./fichiers/").normalize().toAbsolutePath();
        Document document = new Document();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String nom = "file"+ timestamp.getTime();
        String extension[] = file.getContentType().split("/");
        String cheminComplet = path1+"/"+nom +"."+ extension[1];
        document.setCheminFichier(cheminComplet);
        try {
            Path path = Paths.get(cheminComplet);
            File f = new File(path.toUri());
            f.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }

    /**
     * Get all the documents by "id" Absence.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocumentDTO> findAllByAbsenceId(Long id) {
        log.debug("Request to get all Documents by Absence id");
        return documentRepository.findAllByAbsenceId(id).stream()
            .map(documentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the documents by "id" Note de Frais.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocumentDTO> findAllByNoteDeFraisId(Long id) {
        log.debug("Request to get all Documents by Note de Frais id");
        return documentRepository.findAllByNoteDeFraisId(id).stream()
            .map(documentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the documents by "id" Autre.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocumentDTO> findAllByAutresVariablesId(Long id) {
        log.debug("Request to get all Documents by Autre id");
        return documentRepository.findAllByAutresVariablesId(id).stream()
            .map(documentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


}
