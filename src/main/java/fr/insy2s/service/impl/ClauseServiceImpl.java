package fr.insy2s.service.impl;

import fr.insy2s.service.ClauseService;
import fr.insy2s.domain.Clause;
import fr.insy2s.repository.ClauseRepository;
import fr.insy2s.service.dto.ClauseDTO;
import fr.insy2s.service.mapper.ClauseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Clause}.
 */
@Service
@Transactional
public class ClauseServiceImpl implements ClauseService {

    private final Logger log = LoggerFactory.getLogger(ClauseServiceImpl.class);

    private final ClauseRepository clauseRepository;

    private final ClauseMapper clauseMapper;

    public ClauseServiceImpl(ClauseRepository clauseRepository, ClauseMapper clauseMapper) {
        this.clauseRepository = clauseRepository;
        this.clauseMapper = clauseMapper;
    }

    @Override
    public ClauseDTO save(ClauseDTO clauseDTO) {
        log.debug("Request to save Clause : {}", clauseDTO);
        Clause clause = clauseMapper.toEntity(clauseDTO);
        clause = clauseRepository.save(clause);
        return clauseMapper.toDto(clause);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClauseDTO> findAll() {
        log.debug("Request to get all Clauses");
        return clauseRepository.findAllWithEagerRelationships().stream()
            .map(clauseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    public Page<ClauseDTO> findAllWithEagerRelationships(Pageable pageable) {
        return clauseRepository.findAllWithEagerRelationships(pageable).map(clauseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClauseDTO> findOne(Long id) {
        log.debug("Request to get Clause : {}", id);
        return clauseRepository.findOneWithEagerRelationships(id)
            .map(clauseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Clause : {}", id);
        clauseRepository.deleteById(id);
    }
}
