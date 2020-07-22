package fr.insy2s.service.impl;

import fr.insy2s.service.SocieteService;
import fr.insy2s.domain.Societe;
import fr.insy2s.repository.SocieteRepository;
import fr.insy2s.service.dto.SocieteDTO;
import fr.insy2s.service.mapper.SocieteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Societe}.
 */
@Service
@Transactional
public class SocieteServiceImpl implements SocieteService {

    private final Logger log = LoggerFactory.getLogger(SocieteServiceImpl.class);

    private final SocieteRepository societeRepository;

    private final SocieteMapper societeMapper;

    public SocieteServiceImpl(SocieteRepository societeRepository, SocieteMapper societeMapper) {
        this.societeRepository = societeRepository;
        this.societeMapper = societeMapper;
    }

    @Override
    public SocieteDTO save(SocieteDTO societeDTO) {
        log.debug("Request to save Societe : {}", societeDTO);
        Societe societe = societeMapper.toEntity(societeDTO);
        societe = societeRepository.save(societe);
        return societeMapper.toDto(societe);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SocieteDTO> findAll() {
        log.debug("Request to get all Societes");
        return societeRepository.findAll().stream()
            .map(societeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SocieteDTO> findOne(Long id) {
        log.debug("Request to get Societe : {}", id);
        return societeRepository.findById(id)
            .map(societeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Societe : {}", id);
        societeRepository.deleteById(id);
    }
}
