package fr.insy2s.service.impl;

import fr.insy2s.domain.Contrat;
import fr.insy2s.repository.ContratRepository;
import fr.insy2s.repository.projection.IContratAllInfoProjection;
import fr.insy2s.repository.projection.IContratEmployerProjection;
import fr.insy2s.service.ContratService;
import fr.insy2s.service.dto.ContratDTO;
import fr.insy2s.service.mapper.ContratMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Contrat}.
 */
@Service
@Transactional
public class ContratServiceImpl implements ContratService {

    private final Logger log = LoggerFactory.getLogger(ContratServiceImpl.class);

    private final ContratRepository contratRepository;

    private final ContratMapper contratMapper;

    public ContratServiceImpl(ContratRepository contratRepository, ContratMapper contratMapper) {
        this.contratRepository = contratRepository;
        this.contratMapper = contratMapper;
    }

    @Override
    public ContratDTO save(ContratDTO contratDTO) {
        log.debug("Request to save Contrat : {}", contratDTO);
        Contrat contrat = contratMapper.toEntity(contratDTO);
        contrat = contratRepository.save(contrat);
        return contratMapper.toDto(contrat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratDTO> findAll() {
        log.debug("Request to get all Contrats");
        return contratRepository.findAll().stream()
            .map(contratMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ContratDTO> findOne(Long id) {
        log.debug("Request to get Contrat : {}", id);
        return contratRepository.findById(id)
            .map(contratMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Contrat : {}", id);
        contratRepository.deleteById(id);
    }

    @Override
    public List<IContratAllInfoProjection> getContratAllInfos(Long id) {
        return this.contratRepository.getContratAllInfo(id);
    }

    @Override
    public List<IContratEmployerProjection> getAllContratEmployerById(Long id) {
        return this.contratRepository.getAllContratEmployerByEmployeId(id);
    }
}
