package fr.insy2s.service.impl;

import fr.insy2s.service.EtatDevisService;
import fr.insy2s.domain.EtatDevis;
import fr.insy2s.repository.EtatDevisRepository;
import fr.insy2s.service.dto.EtatDevisDTO;
import fr.insy2s.service.mapper.EtatDevisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EtatDevis}.
 */
@Service
@Transactional
public class EtatDevisServiceImpl implements EtatDevisService {

    private final Logger log = LoggerFactory.getLogger(EtatDevisServiceImpl.class);

    private final EtatDevisRepository etatDevisRepository;

    private final EtatDevisMapper etatDevisMapper;

    public EtatDevisServiceImpl(EtatDevisRepository etatDevisRepository, EtatDevisMapper etatDevisMapper) {
        this.etatDevisRepository = etatDevisRepository;
        this.etatDevisMapper = etatDevisMapper;
    }

    @Override
    public EtatDevisDTO save(EtatDevisDTO etatDevisDTO) {
        log.debug("Request to save EtatDevis : {}", etatDevisDTO);
        EtatDevis etatDevis = etatDevisMapper.toEntity(etatDevisDTO);
        etatDevis = etatDevisRepository.save(etatDevis);
        return etatDevisMapper.toDto(etatDevis);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EtatDevisDTO> findAll() {
        log.debug("Request to get all EtatDevis");
        return etatDevisRepository.findAll().stream()
            .map(etatDevisMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EtatDevisDTO> findOne(Long id) {
        log.debug("Request to get EtatDevis : {}", id);
        return etatDevisRepository.findById(id)
            .map(etatDevisMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtatDevis : {}", id);
        etatDevisRepository.deleteById(id);
    }
}
