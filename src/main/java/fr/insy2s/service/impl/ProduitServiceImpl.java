package fr.insy2s.service.impl;

import fr.insy2s.repository.UserRepository;
import fr.insy2s.security.AuthoritiesConstants;
import fr.insy2s.security.SecurityUtils;
import fr.insy2s.service.ProduitService;
import fr.insy2s.domain.Produit;
import fr.insy2s.repository.ProduitRepository;
import fr.insy2s.service.dto.ProduitDTO;
import fr.insy2s.service.dto.UserDTO;
import fr.insy2s.service.mapper.ProduitMapper;
import fr.insy2s.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Produit}.
 */
@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private final Logger log = LoggerFactory.getLogger(ProduitServiceImpl.class);

    private final ProduitRepository produitRepository;

    private final ProduitMapper produitMapper;

    private final UserMapper userMapper ;

    private final UserRepository userRepository ;

    public ProduitServiceImpl(ProduitRepository produitRepository, ProduitMapper produitMapper,  UserMapper userMapper , UserRepository userRepository ) {
        this.produitRepository = produitRepository;
        this.produitMapper = produitMapper;
        this.userMapper = userMapper;
        this.userRepository =userRepository;
    }

    @Override
    public ProduitDTO save(ProduitDTO produitDTO) {
        log.debug("Request to save Produit : {}", produitDTO);
        Produit produit = produitMapper.toEntity(produitDTO);
        produit = produitRepository.save(produit);
        return produitMapper.toDto(produit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduitDTO> findAll() {
        log.debug("Request to get all Produits");
        return produitRepository.findAll().stream()
            .map(produitMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }




    @Override
    @Transactional(readOnly = true)
    public Optional<ProduitDTO> findOne(Long id) {
        log.debug("Request to get Produit : {}", id);
        return produitRepository.findById(id)
            .map(produitMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Produit : {}", id);
        produitRepository.deleteById(id);
    }

    @Override
    public List<ProduitDTO>findAllBySocieteId(Long id){
        log.debug("Request to get all produit");
        List<Produit> listeProduit = produitRepository.findBySocieteId(id);
        return listeProduit.stream()
            .map(produitMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Récupère un user à partir de son login
     *
     * @param loginUser
     * @return
     */
    public UserDTO findOneByLogin(final String loginUser) {
        return userMapper.userToUserDTO(userRepository.findOneByLogin(loginUser).get());
    }

    @Override
    public Boolean connectedUserIsSociete(){
        UserDTO currentUser =findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        for(String auth:currentUser.getAuthorities()){
            if(AuthoritiesConstants.SOCIETY.equals(auth))return true;
        }
        return false;
    }


    @Override
    public Boolean verfyIdOfUserConnected(Long id){
        UserDTO currentUser =findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        if(currentUser.getId()==id)return true;
        else return false;
    }
}
