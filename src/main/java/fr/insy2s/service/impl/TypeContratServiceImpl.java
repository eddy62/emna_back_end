package fr.insy2s.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.insy2s.domain.TypeContrat;
import fr.insy2s.repository.TypeContratRepository;
import fr.insy2s.service.TypeContratService;
import fr.insy2s.service.dto.TypeContratDTO;
import fr.insy2s.service.mapper.TypeContratMapper;

/**
 * Service Implementation for managing {@link TypeContrat}.
 */
@Service
@Transactional
public class TypeContratServiceImpl implements TypeContratService {

    private final Logger                log = LoggerFactory.getLogger(TypeContratServiceImpl.class);

    private final TypeContratRepository typeContratRepository;

    private final TypeContratMapper     typeContratMapper;

    public TypeContratServiceImpl(TypeContratRepository typeContratRepository, TypeContratMapper typeContratMapper) {
        this.typeContratRepository = typeContratRepository;
        this.typeContratMapper = typeContratMapper;
    }

    @Override
    public TypeContratDTO save(TypeContratDTO typeContratDTO) {
        log.debug("Request to save TypeContrat : {}", typeContratDTO);
        TypeContrat typeContrat = typeContratMapper.toEntity(typeContratDTO);
        typeContrat = typeContratRepository.save(typeContrat);
        return typeContratMapper.toDto(typeContrat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeContratDTO> findAll() {
        log.debug("Request to get all TypeContrats");
        return typeContratRepository.findAll().stream().map(typeContratMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeContratDTO> findOne(Long id) {
        log.debug("Request to get TypeContrat : {}", id);
        return typeContratRepository.findById(id).map(typeContratMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeContrat : {}", id);
        typeContratRepository.deleteById(id);
    }

    @Override
    public TypeContratDTO findByCodeRef(String codeRef) {
        log.debug("Request to get TypeContrat : {}", codeRef);
        List<TypeContratDTO> liste = findAll();

        for (TypeContratDTO typeContratDTO : liste) {
            if (typeContratDTO.getCodeRef().equals(codeRef)) {
                return typeContratDTO;
            }
        }

        return null;
    }
}
