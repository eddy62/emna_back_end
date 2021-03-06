package fr.insy2s.service.impl;

import fr.insy2s.domain.Adresse;
import fr.insy2s.domain.ClientFournisseur;
import fr.insy2s.domain.Societe;
import fr.insy2s.repository.AdresseRepository;
import fr.insy2s.repository.ClientFournisseurRepository;
import fr.insy2s.repository.SocieteRepository;
import fr.insy2s.repository.UserRepository;
import fr.insy2s.security.AuthoritiesConstants;
import fr.insy2s.security.SecurityUtils;
import fr.insy2s.service.ClientFournisseurService;
import fr.insy2s.service.dto.AdresseDTO;
import fr.insy2s.service.dto.ClientFournisseurDTO;
import fr.insy2s.service.dto.UserDTO;
import fr.insy2s.service.mapper.AdresseMapper;
import fr.insy2s.service.mapper.ClientFournisseurMapper;
import fr.insy2s.service.mapper.UserMapper;
import fr.insy2s.utils.wrapper.WrapperClientFournisseur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ClientFournisseur}.
 */
@Service
@Transactional
public class ClientFournisseurServiceImpl implements ClientFournisseurService {

    private final Logger log = LoggerFactory.getLogger(ClientFournisseurServiceImpl.class);

    private final ClientFournisseurRepository clientFournisseurRepository;

    private final ClientFournisseurMapper clientFournisseurMapper;

    private final SocieteRepository societeRepository;

    private final AdresseRepository adresseRepository;

    private final AdresseMapper adresseMapper ;

    private final UserMapper userMapper ;

    private final UserRepository userRepository ;

    public ClientFournisseurServiceImpl(ClientFournisseurRepository clientFournisseurRepository, ClientFournisseurMapper clientFournisseurMapper , SocieteRepository societeRepository , AdresseRepository adresseRepository , AdresseMapper adresseMapper , UserMapper userMapper , UserRepository userRepository) {
        this.clientFournisseurRepository = clientFournisseurRepository;
        this.clientFournisseurMapper = clientFournisseurMapper;
        this.societeRepository = societeRepository;
        this.adresseRepository = adresseRepository;
        this.adresseMapper = adresseMapper;
        this.userMapper = userMapper;
        this.userRepository =userRepository;
    }

    /**
     * Save a clientFournisseur.
     *
     * @param clientFournisseurDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClientFournisseurDTO save(ClientFournisseurDTO clientFournisseurDTO) {
        log.debug("Request to save ClientFournisseur : {}", clientFournisseurDTO);
        ClientFournisseur clientFournisseur = clientFournisseurMapper.toEntity(clientFournisseurDTO);
        clientFournisseur = clientFournisseurRepository.save(clientFournisseur);
        return clientFournisseurMapper.toDto(clientFournisseur);
    }

    /**
     * Get all the clientFournisseurs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClientFournisseurDTO> findAll() {
        log.debug("Request to get all ClientFournisseurs");
        return clientFournisseurRepository.findAll().stream()
            .map(clientFournisseurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one clientFournisseur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClientFournisseurDTO> findOne(Long id) {
        log.debug("Request to get ClientFournisseur : {}", id);
        return clientFournisseurRepository.findById(id)
            .map(clientFournisseurMapper::toDto);
    }

    /**
     * Delete the clientFournisseur by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClientFournisseur : {}", id);
        clientFournisseurRepository.deleteById(id);
    }

    @Override
    public ClientFournisseurDTO saveWrapperClientFournisseur(WrapperClientFournisseur clientFournisseur) {
        ClientFournisseur client = new ClientFournisseur();
        try {
            client.setEmail(clientFournisseur.getEmail());
            client.setNom(clientFournisseur.getNom().toUpperCase());
            client.setSiret(clientFournisseur.getSiret());
            client.setTelephone(clientFournisseur.getTelephone());
            if (clientFournisseur.getIdSociete() != null) {
                Optional<Societe> societe = societeRepository.findById(clientFournisseur.getIdSociete());
                client.setSociete(societe.get());
            }

            if(clientFournisseur.getIdAdresse() == null) {
                Adresse adresse = wrapperClientFournisseurToAdresse(clientFournisseur , true);
                if (adresse.getId()!= null) {
                    client.setAdresse(adresse);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        ClientFournisseur newClient = clientFournisseurRepository.save(client);
        return clientFournisseurMapper.toDto(newClient);
    }

    /**
     * Permet de créer un adresse à partir de l'objet wrapperClientFournisseur
     * @param client wrapperClientFournisseur
     * @return adresse
     */
    private Adresse wrapperClientFournisseurToAdresse(WrapperClientFournisseur client  , Boolean creatOrUpdate){
        try {
        if(creatOrUpdate) {
            Adresse adresse = new Adresse();
            adresse.setNumeroRue(client.getNumeroRue());
            adresse.setNomRue(client.getNomRue());
            adresse.setCodePostal(client.getCodePostal());
            adresse.setVille(client.getVille());
            adresse.setPays(client.getPays());
            return adresseRepository.save(adresse);

        }
        if(!creatOrUpdate) {
            Optional<Adresse> adresseUpdated =adresseRepository.findById(client.getIdAdresse());
            adresseUpdated.get().setNumeroRue(client.getNumeroRue());
            adresseUpdated.get().setNomRue(client.getNomRue());
            adresseUpdated.get().setCodePostal(client.getCodePostal());
            adresseUpdated.get().setVille(client.getVille());
            adresseUpdated.get().setPays(client.getPays());
            return adresseRepository.save(adresseUpdated.get());
        }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Recupere clientFournisseur par son id
     *
     * @param id l'id de client fournisseur
     * @return le client founisseur si present en BDD
     */
    @Override
    public Optional<WrapperClientFournisseur> getClientById(Long id) {
        Optional<ClientFournisseur> client = clientFournisseurRepository.findById(id);
         return client.isPresent() ? Optional.of(toWrapperCLientFournisseur(client.get())) : Optional.empty();
    }

    /**
     * Transforme le ClientFournisseur en WrapperClientFournisseur
     *
     * @param client Le clientFournisseur
     * @return Un client sous l'objet WrapperClientFournisseur
     */
    public WrapperClientFournisseur toWrapperCLientFournisseur(ClientFournisseur client) {
        WrapperClientFournisseur wrapperCLient = new WrapperClientFournisseur();

        try {
            ClientFournisseurDTO clientSaved = clientFournisseurMapper.toDto(client);
            wrapperCLient.setId(clientSaved.getId());
            wrapperCLient.setNom(clientSaved.getNom());
            wrapperCLient.setEmail(clientSaved.getEmail());
            wrapperCLient.setTelephone(clientSaved.getTelephone());
            wrapperCLient.setSiret(clientSaved.getSiret());
            wrapperCLient.setIdSociete(clientSaved.getSocieteId());

            if (client.getAdresse()!=null) {
                Optional<Adresse> adresse = adresseRepository.findById(client.getAdresse().getId());
                if (adresse != null) {
                    AdresseDTO adresseDTO = adresseMapper.toDto(adresse.get());
                    wrapperCLient.setIdAdresse(adresseDTO.getId());
                    wrapperCLient.setNumeroRue(adresseDTO.getNumeroRue());
                    wrapperCLient.setNomRue(adresseDTO.getNomRue());
                    wrapperCLient.setBoitePostale(adresseDTO.getBoitePostale());
                    wrapperCLient.setCodePostal(adresseDTO.getCodePostal());
                    wrapperCLient.setVille(adresseDTO.getVille());
                    wrapperCLient.setPays(adresseDTO.getPays());
                }
            }

            return wrapperCLient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Get all the clientFournisseurs.
     *
     * @return the list of entities.
     */
    @Override
    public List<WrapperClientFournisseur> findAllBySocieteId(Long id ) {
        log.debug("Request to get all ClientFournisseurs");
        List<ClientFournisseur> listeclient = clientFournisseurRepository.findBySocieteId(id);
        return listeclient.stream().map(clientFournisseur -> toWrapperCLientFournisseur(clientFournisseur)).collect(Collectors.toList());
    }

    @Override
    public WrapperClientFournisseur updateWrapperClientFournisseur(WrapperClientFournisseur wrapperClientFournisseur) {
        Optional<ClientFournisseur> result = clientFournisseurRepository.findById(wrapperClientFournisseur.getId());
        ClientFournisseur client = result.get();
        try {
            client.setEmail(wrapperClientFournisseur.getEmail());
            client.setNom(wrapperClientFournisseur.getNom());
            client.setSiret(wrapperClientFournisseur.getSiret());
            client.setTelephone(wrapperClientFournisseur.getTelephone());
            if (wrapperClientFournisseur.getIdSociete() != null) {
                Optional<Societe> societe = societeRepository.findById(wrapperClientFournisseur.getIdSociete());
                client.setSociete(societe.get());
            }
            if(wrapperClientFournisseur.getIdAdresse() !=null) {
                Adresse adresse = wrapperClientFournisseurToAdresse(wrapperClientFournisseur , false);
                if (adresse.getId()!= null) {
                    client.setAdresse(adresse);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ClientFournisseur clientUpdated = clientFournisseurRepository.save(client);
        return toWrapperCLientFournisseur(clientUpdated);
      }


    /**
     * Get one the clientFounisseur by name.
     * @param nom the name of the entity
     * @return the  entity.
     */
    @Override
    public Optional<ClientFournisseurDTO> findByNom(String nom ) {
        log.debug("Request to get ClientFournisseur : {}", nom);
        return clientFournisseurRepository.findByNom(nom.toUpperCase())
                .map(clientFournisseurMapper::toDto);
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

    /**
     * Get one the clientFounisseur by name and societe id.
     * @param nom the name of the entity
     * @return the  entity.
     */
    @Override
    public Optional<ClientFournisseurDTO> findByNomAndSocieteId(String nom, Long id) {
        log.debug("Request to get ClientFournisseur : {}",nom);
        return clientFournisseurRepository.findByNomAndSocieteId(nom, id)
            .map(clientFournisseurMapper::toDto);
    }

    @Override
    public List<ClientFournisseurDTO> findBySiretAndSocietyId(String siret, Long id) {
        log.debug("Request to get all ClientFournisseurs By Siret Society");
        return clientFournisseurRepository.findBySiretAndSocieteId(siret, id).stream()
            .map(clientFournisseurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    public Boolean connectedUserIsSociete(){
        UserDTO currentUser =findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        for(String auth:currentUser.getAuthorities()){
            if(AuthoritiesConstants.SOCIETY.equals(auth) || AuthoritiesConstants.ADMIN.equals(auth))return true;
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

