package fr.insy2s.service.impl;

import fr.insy2s.service.HeuresSupplementairesService;
import fr.insy2s.domain.HeuresSupplementaires;
import fr.insy2s.repository.HeuresSupplementairesRepository;
import fr.insy2s.service.dto.HeuresSupplementairesDTO;
import fr.insy2s.service.mapper.HeuresSupplementairesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link HeuresSupplementaires}.
 */
@Service
@Transactional
public class HeuresSupplementairesServiceImpl implements HeuresSupplementairesService {

    private final Logger log = LoggerFactory.getLogger(HeuresSupplementairesServiceImpl.class);

    private final HeuresSupplementairesRepository heuresSupplementairesRepository;

    private final HeuresSupplementairesMapper heuresSupplementairesMapper;

    public HeuresSupplementairesServiceImpl(HeuresSupplementairesRepository heuresSupplementairesRepository, HeuresSupplementairesMapper heuresSupplementairesMapper) {
        this.heuresSupplementairesRepository = heuresSupplementairesRepository;
        this.heuresSupplementairesMapper = heuresSupplementairesMapper;
    }

    @Override
    public HeuresSupplementairesDTO save(HeuresSupplementairesDTO heuresSupplementairesDTO) {
        log.debug("Request to save HeuresSupplementaires : {}", heuresSupplementairesDTO);
        HeuresSupplementaires heuresSupplementaires = heuresSupplementairesMapper.toEntity(heuresSupplementairesDTO);
        heuresSupplementaires = heuresSupplementairesRepository.save(heuresSupplementaires);
        return heuresSupplementairesMapper.toDto(heuresSupplementaires);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HeuresSupplementairesDTO> findAll() {
        log.debug("Request to get all HeuresSupplementaires");
        return heuresSupplementairesRepository.findAll().stream()
            .map(heuresSupplementairesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<HeuresSupplementairesDTO> findOne(Long id) {
        log.debug("Request to get HeuresSupplementaires : {}", id);
        return heuresSupplementairesRepository.findById(id)
            .map(heuresSupplementairesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete HeuresSupplementaires : {}", id);
        heuresSupplementairesRepository.deleteById(id);
    }
}
