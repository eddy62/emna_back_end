package fr.insy2s.service.impl;

import fr.insy2s.service.InfoEntrepriseService;
import fr.insy2s.domain.InfoEntreprise;
import fr.insy2s.repository.InfoEntrepriseRepository;
import fr.insy2s.service.dto.InfoEntrepriseDTO;
import fr.insy2s.service.mapper.InfoEntrepriseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link InfoEntreprise}.
 */
@Service
@Transactional
public class InfoEntrepriseServiceImpl implements InfoEntrepriseService {

    private final Logger log = LoggerFactory.getLogger(InfoEntrepriseServiceImpl.class);

    private final InfoEntrepriseRepository infoEntrepriseRepository;

    private final InfoEntrepriseMapper infoEntrepriseMapper;

    public InfoEntrepriseServiceImpl(InfoEntrepriseRepository infoEntrepriseRepository, InfoEntrepriseMapper infoEntrepriseMapper) {
        this.infoEntrepriseRepository = infoEntrepriseRepository;
        this.infoEntrepriseMapper = infoEntrepriseMapper;
    }

    @Override
    public InfoEntrepriseDTO save(InfoEntrepriseDTO infoEntrepriseDTO) {
        log.debug("Request to save InfoEntreprise : {}", infoEntrepriseDTO);
        InfoEntreprise infoEntreprise = infoEntrepriseMapper.toEntity(infoEntrepriseDTO);
        infoEntreprise = infoEntrepriseRepository.save(infoEntreprise);
        return infoEntrepriseMapper.toDto(infoEntreprise);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InfoEntrepriseDTO> findAll() {
        log.debug("Request to get all InfoEntreprises");
        return infoEntrepriseRepository.findAll().stream()
            .map(infoEntrepriseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<InfoEntrepriseDTO> findOne(Long id) {
        log.debug("Request to get InfoEntreprise : {}", id);
        return infoEntrepriseRepository.findById(id)
            .map(infoEntrepriseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InfoEntreprise : {}", id);
        infoEntrepriseRepository.deleteById(id);
    }
}
