package fr.insy2s.service.impl;

import fr.insy2s.service.TypePrimeService;
import fr.insy2s.domain.TypePrime;
import fr.insy2s.repository.TypePrimeRepository;
import fr.insy2s.service.dto.TypePrimeDTO;
import fr.insy2s.service.mapper.TypePrimeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TypePrime}.
 */
@Service
@Transactional
public class TypePrimeServiceImpl implements TypePrimeService {

    private final Logger log = LoggerFactory.getLogger(TypePrimeServiceImpl.class);

    private final TypePrimeRepository typePrimeRepository;

    private final TypePrimeMapper typePrimeMapper;

    public TypePrimeServiceImpl(TypePrimeRepository typePrimeRepository, TypePrimeMapper typePrimeMapper) {
        this.typePrimeRepository = typePrimeRepository;
        this.typePrimeMapper = typePrimeMapper;
    }

    @Override
    public TypePrimeDTO save(TypePrimeDTO typePrimeDTO) {
        log.debug("Request to save TypePrime : {}", typePrimeDTO);
        TypePrime typePrime = typePrimeMapper.toEntity(typePrimeDTO);
        typePrime = typePrimeRepository.save(typePrime);
        return typePrimeMapper.toDto(typePrime);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypePrimeDTO> findAll() {
        log.debug("Request to get all TypePrimes");
        return typePrimeRepository.findAll().stream()
            .map(typePrimeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TypePrimeDTO> findOne(Long id) {
        log.debug("Request to get TypePrime : {}", id);
        return typePrimeRepository.findById(id)
            .map(typePrimeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypePrime : {}", id);
        typePrimeRepository.deleteById(id);
    }
}
