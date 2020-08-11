package fr.insy2s.service.impl;

import fr.insy2s.domain.User;
import fr.insy2s.repository.AdresseRepository;
import fr.insy2s.repository.InfoEntrepriseRepository;
import fr.insy2s.repository.UserRepository;
import fr.insy2s.service.AdresseService;
import fr.insy2s.service.ComptableService;
import fr.insy2s.domain.Comptable;
import fr.insy2s.repository.ComptableRepository;
import fr.insy2s.service.InfoEntrepriseService;
import fr.insy2s.service.UserService;
import fr.insy2s.service.dto.*;
import fr.insy2s.service.mapper.*;
import fr.insy2s.utils.wrapper.WrapperComptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Comptable}.
 */
@Service
@Transactional
public class ComptableServiceImpl implements ComptableService {

    private final Logger log = LoggerFactory.getLogger(ComptableServiceImpl.class);

    private final ComptableRepository comptableRepository;


    private final  UserMapper userMapper;
    private final ComptableMapper comptableMapper;
    private final InfoEntrepriseService infoEntrepriseService;
    private final  UserService userService;
    private final AdresseService adresseService;



    public ComptableServiceImpl(ComptableRepository comptableRepository, ComptableMapper comptableMapper,   UserMapper userMapper,   InfoEntrepriseService infoEntrepriseService, UserService userService, AdresseService adresseService) {
        this.comptableRepository = comptableRepository;
        this.comptableMapper = comptableMapper;
        this.userMapper = userMapper;
        this.infoEntrepriseService = infoEntrepriseService;
        this.userService = userService;
        this.adresseService = adresseService;
    }

    @Override
    public ComptableDTO save(ComptableDTO comptableDTO) {
        log.debug("Request to save Comptable : {}", comptableDTO);
        Comptable comptable = comptableMapper.toEntity(comptableDTO);
        comptable = comptableRepository.save(comptable);
        return comptableMapper.toDto(comptable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComptableDTO> findAll() {
        log.debug("Request to get all Comptables");
        return comptableRepository.findAll().stream()
            .map(comptableMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ComptableDTO> findOne(Long id) {
        log.debug("Request to get Comptable : {}", id);
        return comptableRepository.findById(id)
            .map(comptableMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comptable : {}", id);
        comptableRepository.deleteById(id);
    }

    @Override
    public WrapperComptable creerOuModifierComptable(ComptableInfoEntrepriseAdresseUserDTO comptableInfoEntrepriseAdresseUserDTO, String callingMethode) {

        ComptableInfoEntrepriseAdresseUserMapper cm = new ComptableInfoEntrepriseAdresseUserMapper();
        UserDTO userDTO = cm.comptableInfoEntrepriseAdresseUserDtoToUserDto(comptableInfoEntrepriseAdresseUserDTO);
        ComptableDTO comptableDTO = cm.comptableInfoEntrepriseAdresseUserDtoToComptableDto(comptableInfoEntrepriseAdresseUserDTO);
        InfoEntrepriseDTO infoEntrepriseDTO = cm.comptableInfoEntrepriseAdresseUserDtoToInfoEntrepriseDto(comptableInfoEntrepriseAdresseUserDTO);
        AdresseDTO adresseDTO = cm.comptableInfoEntrepriseAdresseUserDtoToAdresseDto(comptableInfoEntrepriseAdresseUserDTO);

        try{

            InfoEntrepriseDTO iES = infoEntrepriseService.save(infoEntrepriseDTO);
            AdresseDTO a = adresseService.save(adresseDTO);
            User u = null;


            //Before saving comptable, we should link the IDs of the created object.
            if(callingMethode.equals("create")){
                u = userService.createUser(userDTO);
                comptableDTO.setInfoEntrepriseId(iES.getId());
                comptableDTO.setUserId(u.getId());
                comptableDTO.setAdresseId(a.getId());
            }else {
                Optional<UserDTO> ud = userService.updateUser(userDTO);

                //For now is only to avoid null
                ComptableDTO c = save(comptableDTO);
                return new WrapperComptable(c,a,iES,userDTO);

            }
            ComptableDTO c = save(comptableDTO);
            //it should return ud in the else statement when the callingMethode is update.
            return new WrapperComptable(c,a,iES,userMapper.userToUserDTO(u));

        }catch (Exception e){
            System.out.println("Error in creeComptable : "+e);
        }

        return null;
    }

    @Override
    public WrapperComptable getComptable(Long id) {

        ComptableDTO cd = findOne(id).get();
        AdresseDTO ad = new AdresseDTO();
        InfoEntrepriseDTO ied = new InfoEntrepriseDTO();
        User ud = new User();

        if(cd.getAdresseId() != null){
            ad = adresseService.findOne(cd.getAdresseId()).get();
        }

        if(cd.getInfoEntrepriseId() != null){
            ied = infoEntrepriseService.findOne(cd.getInfoEntrepriseId()).get();
        }

        if(cd.getInfoEntrepriseId() != null){
            ud = userService.getUserWithAuthorities (cd.getUserId()).get();
        }
        return new WrapperComptable(cd,ad,ied, userMapper.userToUserDTO(ud));

    }


}
