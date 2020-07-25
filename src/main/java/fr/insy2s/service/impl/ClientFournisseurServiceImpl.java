package fr.insy2s.service.impl;

import fr.insy2s.domain.Adresse;
import fr.insy2s.domain.Societe;
import fr.insy2s.repository.AdresseRepository;
import fr.insy2s.repository.SocieteRepository;
import fr.insy2s.service.ClientFournisseurService;
import fr.insy2s.domain.ClientFournisseur;
import fr.insy2s.repository.ClientFournisseurRepository;
import fr.insy2s.service.dto.ClientFournisseurDTO;
import fr.insy2s.service.mapper.ClientFournisseurMapper;
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

    public ClientFournisseurServiceImpl(ClientFournisseurRepository clientFournisseurRepository, ClientFournisseurMapper clientFournisseurMapper , SocieteRepository societeRepository , AdresseRepository adresseRepository) {
        this.clientFournisseurRepository = clientFournisseurRepository;
        this.clientFournisseurMapper = clientFournisseurMapper;
        this.societeRepository = societeRepository;
        this.adresseRepository = adresseRepository;
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
            client.setNom(clientFournisseur.getNom());
            client.setSiren(clientFournisseur.getSiren());
            client.setTelephone(clientFournisseur.getTelephone());
            if (clientFournisseur.getIdSociete() != null) {
                Optional<Societe> societe = societeRepository.findById(clientFournisseur.getIdSociete());
                client.setSociete(societe.get());
            }

            Adresse adresse = wrapperClientFournisseurToAdresse(clientFournisseur);
            if (adresse.getId()!= null) {
                client.setAdresse(adresse);
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
    private Adresse wrapperClientFournisseurToAdresse(WrapperClientFournisseur client){
        Adresse adresse = new Adresse();
        try {
            if (client.getIdAdresse() == null) {
                adresse.setNumeroRue(client.getNumeroRue());
                if (client.getNomRue() != null) {
                    adresse.setNomRue(client.getNomRue());
                }
                if (client.getNomRue() != null) {
                    adresse.setCodePostal(client.getCodePostal());
                }
                if (client.getNomRue() != null) {
                    adresse.setVille(client.getVille());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return adresseRepository.save(adresse);
    }
}
