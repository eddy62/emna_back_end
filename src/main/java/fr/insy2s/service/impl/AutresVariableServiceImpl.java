package fr.insy2s.service.impl;

import fr.insy2s.service.AutresVariableService;
import fr.insy2s.domain.AutresVariable;
import fr.insy2s.repository.AutresVariableRepository;
import fr.insy2s.service.dto.AutresVariableDTO;
import fr.insy2s.service.mapper.AutresVariableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AutresVariable}.
 */
@Service
@Transactional
public class AutresVariableServiceImpl implements AutresVariableService {

    private final Logger log = LoggerFactory.getLogger(AutresVariableServiceImpl.class);

    private final AutresVariableRepository autresVariableRepository;

    private final AutresVariableMapper autresVariableMapper;

    public AutresVariableServiceImpl(AutresVariableRepository autresVariableRepository, AutresVariableMapper autresVariableMapper) {
        this.autresVariableRepository = autresVariableRepository;
        this.autresVariableMapper = autresVariableMapper;
    }

    @Override
    public AutresVariableDTO save(AutresVariableDTO autresVariableDTO) {
        log.debug("Request to save AutresVariable : {}", autresVariableDTO);
        AutresVariable autresVariable = autresVariableMapper.toEntity(autresVariableDTO);
        autresVariable = autresVariableRepository.save(autresVariable);
        return autresVariableMapper.toDto(autresVariable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutresVariableDTO> findAll() {
        log.debug("Request to get all AutresVariables");
        return autresVariableRepository.findAll().stream()
            .map(autresVariableMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AutresVariableDTO> findOne(Long id) {
        log.debug("Request to get AutresVariable : {}", id);
        return autresVariableRepository.findById(id)
            .map(autresVariableMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AutresVariable : {}", id);
        autresVariableRepository.deleteById(id);
    }
}
