package fr.insy2s.service.impl;

import fr.insy2s.service.AdresseService;
import fr.insy2s.service.InfoEntrepriseService;
import fr.insy2s.service.SocieteService;
import fr.insy2s.service.UserService;
import fr.insy2s.domain.Societe;
import fr.insy2s.repository.SocieteRepository;
import fr.insy2s.service.dto.AdresseDTO;
import fr.insy2s.service.dto.InfoEntrepriseDTO;
import fr.insy2s.service.dto.SocieteDTO;
import fr.insy2s.service.dto.UserDTO;
import fr.insy2s.service.mapper.AdresseMapper;
import fr.insy2s.service.mapper.SocieteMapper;
import fr.insy2s.service.mapper.UserMapper;
import fr.insy2s.utils.wrapper.WrapperSociete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Societe}.
 */
@Service
@Transactional
public class SocieteServiceImpl implements SocieteService {

    private final Logger log = LoggerFactory.getLogger(SocieteServiceImpl.class);

    private final SocieteRepository societeRepository;
    private final AdresseService adresseService;
    private final InfoEntrepriseService infoEntrepriseService;
    private final UserService userService;

    private final SocieteMapper societeMapper;
    private final UserMapper userMapper;


    public SocieteServiceImpl(SocieteRepository societeRepository, SocieteMapper societeMapper, AdresseService adresseService, InfoEntrepriseService infoEntrepriseService, UserService userService,UserMapper userMapper) {
        this.societeRepository = societeRepository;
        this.societeMapper = societeMapper;
        this.adresseService= adresseService;
        this.infoEntrepriseService=infoEntrepriseService;
        this.userService=userService;
        this.userMapper=userMapper;
    }

    @Override
    public SocieteDTO save(SocieteDTO societeDTO) {
        log.debug("Request to save Societe : {}", societeDTO);
        Societe societe = societeMapper.toEntity(societeDTO);
        societe = societeRepository.save(societe);
        return societeMapper.toDto(societe);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SocieteDTO> findAll() {
        log.debug("Request to get all Societes");
        return societeRepository.findAll().stream()
            .map(societeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SocieteDTO> findOne(Long id) {
        log.debug("Request to get Societe : {}", id);
        return societeRepository.findById(id)
            .map(societeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Societe : {}", id);
        societeRepository.deleteById(id);
    }

    @Override
    public Optional<WrapperSociete> findById(Long id) {
        log.debug("Request to get WrapperSociete : {}", id);
        final SocieteDTO societeDTO = societeMapper.toDto(societeRepository.findById(id).get());
        final AdresseDTO adresseDTO = adresseService.findOne(societeDTO.getAdresseId()).get();
        final InfoEntrepriseDTO infoEntrepriseDTO = infoEntrepriseService.findOne(societeDTO.getInfoEntrepriseId()).get();
        final UserDTO userDTO = userMapper.userToUserDTO(userService.getUserWithAuthorities(societeDTO.getUserId()).get());
        final Optional<WrapperSociete> wrapperSociete = Optional.of(new WrapperSociete(societeDTO, adresseDTO, infoEntrepriseDTO, userDTO));
        return wrapperSociete.isPresent() ? Optional.of(wrapperSociete.get()) : Optional.empty();
    }

    @Override
    public List<SocieteDTO> findAllByComptableId(Long id) {
        log.debug("Request to get all Societes from a specific Comptable id : {}", id);
        return societeRepository.findByComptableId(id).stream()
            .map(societeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Optional<SocieteDTO> findByUser(Long id) {
        log.debug("Request to get Society from a specific User id : {}", id);
        return societeRepository.findByUserId(id).map(societeMapper::toDto);
    }
}
