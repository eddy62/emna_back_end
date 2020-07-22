package fr.insy2s.service.impl;

import fr.insy2s.service.EtatReleveService;
import fr.insy2s.domain.EtatReleve;
import fr.insy2s.repository.EtatReleveRepository;
import fr.insy2s.service.dto.EtatReleveDTO;
import fr.insy2s.service.mapper.EtatReleveMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EtatReleve}.
 */
@Service
@Transactional
public class EtatReleveServiceImpl implements EtatReleveService {

    private final Logger log = LoggerFactory.getLogger(EtatReleveServiceImpl.class);

    private final EtatReleveRepository etatReleveRepository;

    private final EtatReleveMapper etatReleveMapper;

    public EtatReleveServiceImpl(EtatReleveRepository etatReleveRepository, EtatReleveMapper etatReleveMapper) {
        this.etatReleveRepository = etatReleveRepository;
        this.etatReleveMapper = etatReleveMapper;
    }

    @Override
    public EtatReleveDTO save(EtatReleveDTO etatReleveDTO) {
        log.debug("Request to save EtatReleve : {}", etatReleveDTO);
        EtatReleve etatReleve = etatReleveMapper.toEntity(etatReleveDTO);
        etatReleve = etatReleveRepository.save(etatReleve);
        return etatReleveMapper.toDto(etatReleve);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EtatReleveDTO> findAll() {
        log.debug("Request to get all EtatReleves");
        return etatReleveRepository.findAll().stream()
            .map(etatReleveMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EtatReleveDTO> findOne(Long id) {
        log.debug("Request to get EtatReleve : {}", id);
        return etatReleveRepository.findById(id)
            .map(etatReleveMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtatReleve : {}", id);
        etatReleveRepository.deleteById(id);
    }
}
