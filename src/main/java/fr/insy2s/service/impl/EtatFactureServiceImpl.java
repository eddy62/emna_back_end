package fr.insy2s.service.impl;

import fr.insy2s.service.EtatFactureService;
import fr.insy2s.domain.EtatFacture;
import fr.insy2s.repository.EtatFactureRepository;
import fr.insy2s.service.dto.EtatFactureDTO;
import fr.insy2s.service.mapper.EtatFactureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EtatFacture}.
 */
@Service
@Transactional
public class EtatFactureServiceImpl implements EtatFactureService {

    private final Logger log = LoggerFactory.getLogger(EtatFactureServiceImpl.class);

    private final EtatFactureRepository etatFactureRepository;

    private final EtatFactureMapper etatFactureMapper;

    public EtatFactureServiceImpl(EtatFactureRepository etatFactureRepository, EtatFactureMapper etatFactureMapper) {
        this.etatFactureRepository = etatFactureRepository;
        this.etatFactureMapper = etatFactureMapper;
    }

    @Override
    public EtatFactureDTO save(EtatFactureDTO etatFactureDTO) {
        log.debug("Request to save EtatFacture : {}", etatFactureDTO);
        EtatFacture etatFacture = etatFactureMapper.toEntity(etatFactureDTO);
        etatFacture = etatFactureRepository.save(etatFacture);
        return etatFactureMapper.toDto(etatFacture);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EtatFactureDTO> findAll() {
        log.debug("Request to get all EtatFactures");
        return etatFactureRepository.findAll().stream()
            .map(etatFactureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EtatFactureDTO> findOne(Long id) {
        log.debug("Request to get EtatFacture : {}", id);
        return etatFactureRepository.findById(id)
            .map(etatFactureMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtatFacture : {}", id);
        etatFactureRepository.deleteById(id);
    }
}
