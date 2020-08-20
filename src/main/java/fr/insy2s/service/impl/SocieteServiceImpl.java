package fr.insy2s.service.impl;

import fr.insy2s.domain.User;
import fr.insy2s.service.AdresseService;
import fr.insy2s.service.InfoEntrepriseService;
import fr.insy2s.service.SocieteService;
import fr.insy2s.service.UserService;
import fr.insy2s.domain.Societe;
import fr.insy2s.repository.SocieteRepository;
import fr.insy2s.service.dto.*;
import fr.insy2s.service.mapper.*;
import fr.insy2s.utils.wrapper.WrapperComptable;
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
    public WrapperSociete creerOuModifierSociete(SocieteInfoEntrepriseAdresseUserDTO societeInfoEntrepriseAdresseUserDTO, String callingMethode) {

        SocieteInfoEntrepriseAdresseUserMapper so = new SocieteInfoEntrepriseAdresseUserMapper();
        UserDTO userDTO = so.SocieteInfoEntrepriseAdresseUserDtoToUserDto(societeInfoEntrepriseAdresseUserDTO);
        SocieteDTO societeDTO = so.SocieteInfoEntrepriseAdresseUserDtoToSocieteDto(societeInfoEntrepriseAdresseUserDTO);
        InfoEntrepriseDTO infoEntrepriseDTO = so.SocieteInfoEntrepriseAdresseUserDtoToInfoEntrepriseDto(societeInfoEntrepriseAdresseUserDTO);
        AdresseDTO adresseDTO = so.SocieteInfoEntrepriseAdresseUserDtoToAdresseDto(societeInfoEntrepriseAdresseUserDTO);

        try{

            InfoEntrepriseDTO iES = infoEntrepriseService.save(infoEntrepriseDTO);
            AdresseDTO a = adresseService.save(adresseDTO);
            User u = null;

            //Before saving comptable, we should link the IDs of the created object.
            if(callingMethode.equals("create")){
                u = userService.createUser(userDTO);
                societeDTO.setInfoEntrepriseId(iES.getId());
                societeDTO.setUserId(u.getId());
                societeDTO.setAdresseId(a.getId());
            }else {
                Optional<UserDTO> ud = userService.updateUser(userDTO);

                //For now is only to avoid null
                SocieteDTO s = save(societeDTO);
                return new WrapperSociete(s,a,iES,userDTO);
            }

            SocieteDTO s = save(societeDTO);
            //it should return ud in the else statement when the calling Methode is update.
            return new WrapperSociete(s,a,iES,userMapper.userToUserDTO(u));

        }catch (Exception e){
            System.out.println("Error in creeComptable : "+e);
        }
        return null;
    }


    @Override
    public WrapperSociete getSociete(Long id) {
        SocieteDTO sd = findOne(id).get();
        AdresseDTO ad = new AdresseDTO();
        InfoEntrepriseDTO ied = new InfoEntrepriseDTO();
        User ud = new User();

        if(sd.getAdresseId() != null){
            ad = adresseService.findOne(sd.getAdresseId()).get();
        }

        if(sd.getInfoEntrepriseId() != null){
            ied = infoEntrepriseService.findOne(sd.getInfoEntrepriseId()).get();
        }

        if(sd.getInfoEntrepriseId() != null){
            ud = userService.getUserWithAuthorities (sd.getUserId()).get();
        }
        return new WrapperSociete(sd,ad,ied, userMapper.userToUserDTO(ud));
    }

    @Override
    public Optional<SocieteDTO> findByUser(Long id) {
        log.debug("Request to get Society from a specific User id : {}", id);
        return societeRepository.findByUserId(id).map(societeMapper::toDto);
    }

}
