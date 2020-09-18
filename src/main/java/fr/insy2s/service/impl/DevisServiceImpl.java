package fr.insy2s.service.impl;

import fr.insy2s.service.DevisService;
import fr.insy2s.domain.Devis;
import fr.insy2s.repository.DevisRepository;
import fr.insy2s.service.dto.DevisDTO;
import fr.insy2s.service.mapper.DevisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Devis}.
 */
@Service
@Transactional
public class DevisServiceImpl implements DevisService {

    private final Logger log = LoggerFactory.getLogger(DevisServiceImpl.class);

    private final DevisRepository devisRepository;

    private final DevisMapper devisMapper;

    public DevisServiceImpl(DevisRepository devisRepository, DevisMapper devisMapper) {
        this.devisRepository = devisRepository;
        this.devisMapper = devisMapper;
    }

    @Override
    public DevisDTO save(DevisDTO devisDTO) {
        log.debug("Request to save Devis : {}", devisDTO);
        Devis devis = devisMapper.toEntity(devisDTO);
        devis = devisRepository.save(devis);
        return devisMapper.toDto(devis);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DevisDTO> findAll() {
        log.debug("Request to get all Devis");
        return devisRepository.findAll().stream()
            .map(devisMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DevisDTO> findOne(Long id) {
        log.debug("Request to get Devis : {}", id);
        return devisRepository.findById(id)
            .map(devisMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Devis : {}", id);
        devisRepository.deleteById(id);
    }

    @Override
    public List<DevisDTO> findAllQuotesBySocietyId(Long idSociete) {
        log.debug("Request to get all the quotes by society id: {}", idSociete);
        return this.devisRepository.findAllQuotesBySocietyId(idSociete)
            .stream()
            .map(devisMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
