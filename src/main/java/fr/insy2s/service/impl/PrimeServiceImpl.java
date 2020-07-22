package fr.insy2s.service.impl;

import fr.insy2s.service.PrimeService;
import fr.insy2s.domain.Prime;
import fr.insy2s.repository.PrimeRepository;
import fr.insy2s.service.dto.PrimeDTO;
import fr.insy2s.service.mapper.PrimeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Prime}.
 */
@Service
@Transactional
public class PrimeServiceImpl implements PrimeService {

    private final Logger log = LoggerFactory.getLogger(PrimeServiceImpl.class);

    private final PrimeRepository primeRepository;

    private final PrimeMapper primeMapper;

    public PrimeServiceImpl(PrimeRepository primeRepository, PrimeMapper primeMapper) {
        this.primeRepository = primeRepository;
        this.primeMapper = primeMapper;
    }

    @Override
    public PrimeDTO save(PrimeDTO primeDTO) {
        log.debug("Request to save Prime : {}", primeDTO);
        Prime prime = primeMapper.toEntity(primeDTO);
        prime = primeRepository.save(prime);
        return primeMapper.toDto(prime);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrimeDTO> findAll() {
        log.debug("Request to get all Primes");
        return primeRepository.findAll().stream()
            .map(primeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PrimeDTO> findOne(Long id) {
        log.debug("Request to get Prime : {}", id);
        return primeRepository.findById(id)
            .map(primeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Prime : {}", id);
        primeRepository.deleteById(id);
    }
}
