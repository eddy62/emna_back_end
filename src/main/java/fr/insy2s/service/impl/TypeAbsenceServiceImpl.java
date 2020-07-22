package fr.insy2s.service.impl;

import fr.insy2s.service.TypeAbsenceService;
import fr.insy2s.domain.TypeAbsence;
import fr.insy2s.repository.TypeAbsenceRepository;
import fr.insy2s.service.dto.TypeAbsenceDTO;
import fr.insy2s.service.mapper.TypeAbsenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TypeAbsence}.
 */
@Service
@Transactional
public class TypeAbsenceServiceImpl implements TypeAbsenceService {

    private final Logger log = LoggerFactory.getLogger(TypeAbsenceServiceImpl.class);

    private final TypeAbsenceRepository typeAbsenceRepository;

    private final TypeAbsenceMapper typeAbsenceMapper;

    public TypeAbsenceServiceImpl(TypeAbsenceRepository typeAbsenceRepository, TypeAbsenceMapper typeAbsenceMapper) {
        this.typeAbsenceRepository = typeAbsenceRepository;
        this.typeAbsenceMapper = typeAbsenceMapper;
    }

    @Override
    public TypeAbsenceDTO save(TypeAbsenceDTO typeAbsenceDTO) {
        log.debug("Request to save TypeAbsence : {}", typeAbsenceDTO);
        TypeAbsence typeAbsence = typeAbsenceMapper.toEntity(typeAbsenceDTO);
        typeAbsence = typeAbsenceRepository.save(typeAbsence);
        return typeAbsenceMapper.toDto(typeAbsence);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeAbsenceDTO> findAll() {
        log.debug("Request to get all TypeAbsences");
        return typeAbsenceRepository.findAll().stream()
            .map(typeAbsenceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TypeAbsenceDTO> findOne(Long id) {
        log.debug("Request to get TypeAbsence : {}", id);
        return typeAbsenceRepository.findById(id)
            .map(typeAbsenceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeAbsence : {}", id);
        typeAbsenceRepository.deleteById(id);
    }
}
