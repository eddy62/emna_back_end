package fr.insy2s.service.impl;

import fr.insy2s.service.EtatVariablePaieService;
import fr.insy2s.domain.EtatVariablePaie;
import fr.insy2s.repository.EtatVariablePaieRepository;
import fr.insy2s.service.dto.EtatVariablePaieDTO;
import fr.insy2s.service.mapper.EtatVariablePaieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EtatVariablePaie}.
 */
@Service
@Transactional
public class EtatVariablePaieServiceImpl implements EtatVariablePaieService {

    private final Logger log = LoggerFactory.getLogger(EtatVariablePaieServiceImpl.class);

    private final EtatVariablePaieRepository etatVariablePaieRepository;

    private final EtatVariablePaieMapper etatVariablePaieMapper;

    public EtatVariablePaieServiceImpl(EtatVariablePaieRepository etatVariablePaieRepository, EtatVariablePaieMapper etatVariablePaieMapper) {
        this.etatVariablePaieRepository = etatVariablePaieRepository;
        this.etatVariablePaieMapper = etatVariablePaieMapper;
    }

    @Override
    public EtatVariablePaieDTO save(EtatVariablePaieDTO etatVariablePaieDTO) {
        log.debug("Request to save EtatVariablePaie : {}", etatVariablePaieDTO);
        EtatVariablePaie etatVariablePaie = etatVariablePaieMapper.toEntity(etatVariablePaieDTO);
        etatVariablePaie = etatVariablePaieRepository.save(etatVariablePaie);
        return etatVariablePaieMapper.toDto(etatVariablePaie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EtatVariablePaieDTO> findAll() {
        log.debug("Request to get all EtatVariablePaies");
        return etatVariablePaieRepository.findAll().stream()
            .map(etatVariablePaieMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EtatVariablePaieDTO> findOne(Long id) {
        log.debug("Request to get EtatVariablePaie : {}", id);
        return etatVariablePaieRepository.findById(id)
            .map(etatVariablePaieMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtatVariablePaie : {}", id);
        etatVariablePaieRepository.deleteById(id);
    }
}
