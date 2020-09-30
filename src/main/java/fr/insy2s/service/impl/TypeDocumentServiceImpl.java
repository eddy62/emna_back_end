package fr.insy2s.service.impl;

import fr.insy2s.domain.TypeDocument;
import fr.insy2s.repository.TypeDocumentRepository;
import fr.insy2s.service.TypeDocumentService;
import fr.insy2s.service.dto.TypeDocumentDTO;
import fr.insy2s.service.mapper.TypeDocumentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TypeDocument}.
 */
@Service
@Transactional
public class TypeDocumentServiceImpl implements TypeDocumentService {

    private final Logger log = LoggerFactory.getLogger(TypeDocumentServiceImpl.class);

    private final TypeDocumentRepository typeDocumentRepository;

    private final TypeDocumentMapper typeDocumentMapper;

    public TypeDocumentServiceImpl(TypeDocumentRepository typeDocumentRepository, TypeDocumentMapper typeDocumentMapper) {
        this.typeDocumentRepository = typeDocumentRepository;
        this.typeDocumentMapper = typeDocumentMapper;
    }

    @Override
    public TypeDocumentDTO save(TypeDocumentDTO typeDocumentDTO) {
        log.debug("Request to save TypeDocument : {}", typeDocumentDTO);
        TypeDocument typeDocument = typeDocumentMapper.toEntity(typeDocumentDTO);
        typeDocument = typeDocumentRepository.save(typeDocument);
        return typeDocumentMapper.toDto(typeDocument);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeDocumentDTO> findAll() {
        log.debug("Request to get all TypeDocuments");
        return typeDocumentRepository.findAll().stream()
            .map(typeDocumentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TypeDocumentDTO> findOne(Long id) {
        log.debug("Request to get TypeDocument : {}", id);
        return typeDocumentRepository.findById(id)
            .map(typeDocumentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeDocument : {}", id);
        typeDocumentRepository.deleteById(id);
    }
}
