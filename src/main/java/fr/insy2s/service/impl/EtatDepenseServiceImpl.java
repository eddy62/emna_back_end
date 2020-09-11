package fr.insy2s.service.impl;

import fr.insy2s.service.EtatDepenseService;
import fr.insy2s.domain.EtatDepense;
import fr.insy2s.repository.EtatDepenseRepository;
import fr.insy2s.service.dto.EtatDepenseDTO;
import fr.insy2s.service.mapper.EtatDepenseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EtatDepense}.
 */
@Service
@Transactional
public class EtatDepenseServiceImpl implements EtatDepenseService {

    private final Logger log = LoggerFactory.getLogger(EtatDepenseServiceImpl.class);

    private final EtatDepenseRepository etatDepenseRepository;

    private final EtatDepenseMapper etatDepenseMapper;

    public EtatDepenseServiceImpl(EtatDepenseRepository etatDepenseRepository, EtatDepenseMapper etatDepenseMapper) {
        this.etatDepenseRepository = etatDepenseRepository;
        this.etatDepenseMapper = etatDepenseMapper;
    }

    @Override
    public EtatDepenseDTO save(EtatDepenseDTO etatDepenseDTO) {
        log.debug("Request to save EtatDepense : {}", etatDepenseDTO);
        EtatDepense etatDepense = etatDepenseMapper.toEntity(etatDepenseDTO);
        etatDepense = etatDepenseRepository.save(etatDepense);
        return etatDepenseMapper.toDto(etatDepense);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EtatDepenseDTO> findAll() {
        log.debug("Request to get all EtatDepenses");
        return etatDepenseRepository.findAll().stream()
            .map(etatDepenseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EtatDepenseDTO> findOne(Long id) {
        log.debug("Request to get EtatDepense : {}", id);
        return etatDepenseRepository.findById(id)
            .map(etatDepenseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtatDepense : {}", id);
        etatDepenseRepository.deleteById(id);
    }
}
