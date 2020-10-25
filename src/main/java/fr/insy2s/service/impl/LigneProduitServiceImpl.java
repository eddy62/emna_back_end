package fr.insy2s.service.impl;

import fr.insy2s.service.LigneProduitService;
import fr.insy2s.domain.LigneProduit;
import fr.insy2s.repository.LigneProduitRepository;
import fr.insy2s.service.dto.LigneProduitDTO;
import fr.insy2s.service.mapper.LigneProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link LigneProduit}.
 */
@Service
@Transactional

public class LigneProduitServiceImpl implements LigneProduitService {

    private final Logger log = LoggerFactory.getLogger(LigneProduitServiceImpl.class);

    private final LigneProduitRepository ligneProduitRepository;

    private final LigneProduitMapper ligneProduitMapper;

    public LigneProduitServiceImpl(LigneProduitRepository ligneProduitRepository, LigneProduitMapper ligneProduitMapper) {
        this.ligneProduitRepository = ligneProduitRepository;
        this.ligneProduitMapper = ligneProduitMapper;
    }

    @Override
    public LigneProduitDTO save(LigneProduitDTO ligneProduitDTO) {
        log.debug("Request to save LigneProduit : {}", ligneProduitDTO);
        LigneProduit ligneProduit = ligneProduitMapper.toEntity(ligneProduitDTO);
        ligneProduit = ligneProduitRepository.save(ligneProduit);
        return ligneProduitMapper.toDto(ligneProduit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LigneProduitDTO> findAll() {
        log.debug("Request to get all LigneProduits");
        return ligneProduitRepository.findAll().stream()
            .map(ligneProduitMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<LigneProduitDTO> findOne(Long id) {
        log.debug("Request to get LigneProduit : {}", id);
        return ligneProduitRepository.findById(id)
            .map(ligneProduitMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LigneProduit : {}", id);
        ligneProduitRepository.deleteById(id);
    }

    @Override
    public void deleteByDevisId(Long id) {
        ligneProduitRepository.deleteByDevis_Id(id);
    }
}
