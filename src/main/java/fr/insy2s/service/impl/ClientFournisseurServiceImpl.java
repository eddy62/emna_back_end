package fr.insy2s.service.impl;

import fr.insy2s.service.ClientFournisseurService;
import fr.insy2s.domain.ClientFournisseur;
import fr.insy2s.repository.ClientFournisseurRepository;
import fr.insy2s.service.dto.ClientFournisseurDTO;
import fr.insy2s.service.mapper.ClientFournisseurMapper;
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

    public ClientFournisseurServiceImpl(ClientFournisseurRepository clientFournisseurRepository, ClientFournisseurMapper clientFournisseurMapper) {
        this.clientFournisseurRepository = clientFournisseurRepository;
        this.clientFournisseurMapper = clientFournisseurMapper;
    }

    @Override
    public ClientFournisseurDTO save(ClientFournisseurDTO clientFournisseurDTO) {
        log.debug("Request to save ClientFournisseur : {}", clientFournisseurDTO);
        ClientFournisseur clientFournisseur = clientFournisseurMapper.toEntity(clientFournisseurDTO);
        clientFournisseur = clientFournisseurRepository.save(clientFournisseur);
        return clientFournisseurMapper.toDto(clientFournisseur);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientFournisseurDTO> findAll() {
        log.debug("Request to get all ClientFournisseurs");
        return clientFournisseurRepository.findAll().stream()
            .map(clientFournisseurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ClientFournisseurDTO> findOne(Long id) {
        log.debug("Request to get ClientFournisseur : {}", id);
        return clientFournisseurRepository.findById(id)
            .map(clientFournisseurMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClientFournisseur : {}", id);
        clientFournisseurRepository.deleteById(id);
    }
}
