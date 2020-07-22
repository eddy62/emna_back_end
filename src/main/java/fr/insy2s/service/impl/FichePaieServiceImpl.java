package fr.insy2s.service.impl;

import fr.insy2s.service.FichePaieService;
import fr.insy2s.domain.FichePaie;
import fr.insy2s.repository.FichePaieRepository;
import fr.insy2s.service.dto.FichePaieDTO;
import fr.insy2s.service.mapper.FichePaieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FichePaie}.
 */
@Service
@Transactional
public class FichePaieServiceImpl implements FichePaieService {

    private final Logger log = LoggerFactory.getLogger(FichePaieServiceImpl.class);

    private final FichePaieRepository fichePaieRepository;

    private final FichePaieMapper fichePaieMapper;

    public FichePaieServiceImpl(FichePaieRepository fichePaieRepository, FichePaieMapper fichePaieMapper) {
        this.fichePaieRepository = fichePaieRepository;
        this.fichePaieMapper = fichePaieMapper;
    }

    @Override
    public FichePaieDTO save(FichePaieDTO fichePaieDTO) {
        log.debug("Request to save FichePaie : {}", fichePaieDTO);
        FichePaie fichePaie = fichePaieMapper.toEntity(fichePaieDTO);
        fichePaie = fichePaieRepository.save(fichePaie);
        return fichePaieMapper.toDto(fichePaie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FichePaieDTO> findAll() {
        log.debug("Request to get all FichePaies");
        return fichePaieRepository.findAll().stream()
            .map(fichePaieMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FichePaieDTO> findOne(Long id) {
        log.debug("Request to get FichePaie : {}", id);
        return fichePaieRepository.findById(id)
            .map(fichePaieMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FichePaie : {}", id);
        fichePaieRepository.deleteById(id);
    }
}
