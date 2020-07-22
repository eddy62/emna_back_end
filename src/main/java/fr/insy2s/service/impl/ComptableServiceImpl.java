package fr.insy2s.service.impl;

import fr.insy2s.service.ComptableService;
import fr.insy2s.domain.Comptable;
import fr.insy2s.repository.ComptableRepository;
import fr.insy2s.service.dto.ComptableDTO;
import fr.insy2s.service.mapper.ComptableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Comptable}.
 */
@Service
@Transactional
public class ComptableServiceImpl implements ComptableService {

    private final Logger log = LoggerFactory.getLogger(ComptableServiceImpl.class);

    private final ComptableRepository comptableRepository;

    private final ComptableMapper comptableMapper;

    public ComptableServiceImpl(ComptableRepository comptableRepository, ComptableMapper comptableMapper) {
        this.comptableRepository = comptableRepository;
        this.comptableMapper = comptableMapper;
    }

    @Override
    public ComptableDTO save(ComptableDTO comptableDTO) {
        log.debug("Request to save Comptable : {}", comptableDTO);
        Comptable comptable = comptableMapper.toEntity(comptableDTO);
        comptable = comptableRepository.save(comptable);
        return comptableMapper.toDto(comptable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComptableDTO> findAll() {
        log.debug("Request to get all Comptables");
        return comptableRepository.findAll().stream()
            .map(comptableMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ComptableDTO> findOne(Long id) {
        log.debug("Request to get Comptable : {}", id);
        return comptableRepository.findById(id)
            .map(comptableMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comptable : {}", id);
        comptableRepository.deleteById(id);
    }
}
