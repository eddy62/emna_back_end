package fr.insy2s.service.impl;

import fr.insy2s.service.AvanceRappelSalaireService;
import fr.insy2s.domain.AvanceRappelSalaire;
import fr.insy2s.repository.AvanceRappelSalaireRepository;
import fr.insy2s.service.dto.AvanceRappelSalaireDTO;
import fr.insy2s.service.mapper.AvanceRappelSalaireMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AvanceRappelSalaire}.
 */
@Service
@Transactional
public class AvanceRappelSalaireServiceImpl implements AvanceRappelSalaireService {

    private final Logger log = LoggerFactory.getLogger(AvanceRappelSalaireServiceImpl.class);

    private final AvanceRappelSalaireRepository avanceRappelSalaireRepository;

    private final AvanceRappelSalaireMapper avanceRappelSalaireMapper;

    public AvanceRappelSalaireServiceImpl(AvanceRappelSalaireRepository avanceRappelSalaireRepository, AvanceRappelSalaireMapper avanceRappelSalaireMapper) {
        this.avanceRappelSalaireRepository = avanceRappelSalaireRepository;
        this.avanceRappelSalaireMapper = avanceRappelSalaireMapper;
    }

    @Override
    public AvanceRappelSalaireDTO save(AvanceRappelSalaireDTO avanceRappelSalaireDTO) {
        log.debug("Request to save AvanceRappelSalaire : {}", avanceRappelSalaireDTO);
        AvanceRappelSalaire avanceRappelSalaire = avanceRappelSalaireMapper.toEntity(avanceRappelSalaireDTO);
        avanceRappelSalaire = avanceRappelSalaireRepository.save(avanceRappelSalaire);
        return avanceRappelSalaireMapper.toDto(avanceRappelSalaire);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AvanceRappelSalaireDTO> findAll() {
        log.debug("Request to get all AvanceRappelSalaires");
        return avanceRappelSalaireRepository.findAll().stream()
            .map(avanceRappelSalaireMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AvanceRappelSalaireDTO> findOne(Long id) {
        log.debug("Request to get AvanceRappelSalaire : {}", id);
        return avanceRappelSalaireRepository.findById(id)
            .map(avanceRappelSalaireMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AvanceRappelSalaire : {}", id);
        avanceRappelSalaireRepository.deleteById(id);
    }
}
