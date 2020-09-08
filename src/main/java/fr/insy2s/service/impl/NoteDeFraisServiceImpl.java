package fr.insy2s.service.impl;

import fr.insy2s.domain.NoteDeFrais;
import fr.insy2s.repository.NoteDeFraisRepository;
import fr.insy2s.service.NoteDeFraisService;
import fr.insy2s.service.dto.NoteDeFraisDTO;
import fr.insy2s.service.mapper.NoteDeFraisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link NoteDeFrais}.
 */
@Service
@Transactional
public class NoteDeFraisServiceImpl implements NoteDeFraisService {

    private final Logger log = LoggerFactory.getLogger(NoteDeFraisServiceImpl.class);

    private final NoteDeFraisRepository noteDeFraisRepository;

    private final NoteDeFraisMapper noteDeFraisMapper;

    public NoteDeFraisServiceImpl(NoteDeFraisRepository noteDeFraisRepository, NoteDeFraisMapper noteDeFraisMapper) {
        this.noteDeFraisRepository = noteDeFraisRepository;
        this.noteDeFraisMapper = noteDeFraisMapper;
    }

    @Override
    public NoteDeFraisDTO save(NoteDeFraisDTO noteDeFraisDTO) {
        log.debug("Request to save NoteDeFrais : {}", noteDeFraisDTO);
        NoteDeFrais noteDeFrais = noteDeFraisMapper.toEntity(noteDeFraisDTO);
        noteDeFrais = noteDeFraisRepository.save(noteDeFrais);
        return noteDeFraisMapper.toDto(noteDeFrais);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteDeFraisDTO> findAll() {
        log.debug("Request to get all NoteDeFrais");
        return noteDeFraisRepository.findAll().stream()
            .map(noteDeFraisMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<NoteDeFraisDTO> findOne(Long id) {
        log.debug("Request to get NoteDeFrais : {}", id);
        return noteDeFraisRepository.findById(id)
            .map(noteDeFraisMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NoteDeFrais : {}", id);
        noteDeFraisRepository.deleteById(id);
    }

    @Override
    public List<NoteDeFraisDTO> findAllNoteDeFraisByIdEmployeAndAnneeAndMois(Long idEmploye, Integer annee, Integer mois) {
        log.debug("Request to get all NoteDeFrais with IdEmploye {} Annee {} Mois {}",idEmploye,annee,mois);
        return noteDeFraisRepository.findAllNoteDeFraisByIdEmployeAndAnneeAndMois(idEmploye, annee, mois).stream()
                .map(noteDeFraisMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
