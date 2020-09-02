package fr.insy2s.service.impl;

import fr.insy2s.domain.Document;
import fr.insy2s.repository.DocumentRepository;
import fr.insy2s.repository.SocieteRepository;
import fr.insy2s.service.DocumentService;
import fr.insy2s.service.FactureService;
import fr.insy2s.domain.Facture;
import fr.insy2s.repository.FactureRepository;
import fr.insy2s.service.dto.FactureDTO;
import fr.insy2s.service.dto.FactureTemp;
import fr.insy2s.service.mapper.FactureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Facture}.
 */
@Service
@Transactional
public class FactureServiceImpl implements FactureService {

    private final Logger log = LoggerFactory.getLogger(FactureServiceImpl.class);

    private final FactureRepository factureRepository;

    private final FactureMapper factureMapper;

    private final DocumentService documentService;

    private final DocumentRepository documentRepository;

    private final SocieteRepository societeRepository;

    public FactureServiceImpl(FactureRepository factureRepository, FactureMapper factureMapper, DocumentService documentService, DocumentRepository documentRepository, SocieteRepository societeRepository) {
        this.factureRepository = factureRepository;
        this.factureMapper = factureMapper;
        this.documentService = documentService;
        this.documentRepository = documentRepository;
        this.societeRepository = societeRepository;
    }

    @Override
    public FactureDTO save(FactureDTO factureDTO) {
        log.debug("Request to save Facture : {}", factureDTO);
        Facture facture = factureMapper.toEntity(factureDTO);
        facture = factureRepository.save(facture);
        return factureMapper.toDto(facture);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FactureDTO> findAll() {
        log.debug("Request to get all Factures");
        return factureRepository.findAll().stream()
            .map(factureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FactureDTO> findOne(Long id) {
        log.debug("Request to get Facture : {}", id);
        return factureRepository.findById(id)
            .map(factureMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Facture : {}", id);
        factureRepository.deleteById(id);
    }

    @Override
    public FactureDTO postFactureWithFile(FactureTemp factureTemp) {
        Set<Document> documents = documentService.multiPartFilesToDocuments(Arrays.asList(factureTemp.getListeFiles()));
        Facture facture = factureTemp.toFacture();
        for (Document document: documents
             ) {
            document.setFacture(facture);
            documentRepository.save(document);
        }
        facture.setListeDocuments(documents);
        facture.setSociete(societeRepository.getOne(factureTemp.getSocieteId()));
        //facture.set
        Facture mafacture = factureRepository.save(facture);
        return this.factureMapper.toDto(mafacture);
    }

    @Override
    public List<FactureDTO> findAllBySocieteId(Long id) {
        return factureRepository.
            findAllBySocieteId(id).stream()
            .map(factureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
