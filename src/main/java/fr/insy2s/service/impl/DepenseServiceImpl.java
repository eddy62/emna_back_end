package fr.insy2s.service.impl;

import fr.insy2s.domain.ClientFournisseur;
import fr.insy2s.domain.Document;
import fr.insy2s.domain.EtatDepense;
import fr.insy2s.repository.*;
import fr.insy2s.service.ClientFournisseurService;
import fr.insy2s.service.DepenseService;
import fr.insy2s.domain.Depense;
import fr.insy2s.service.DocumentService;
import fr.insy2s.service.dto.ClientFournisseurDTO;
import fr.insy2s.service.dto.DepenseDTO;
import fr.insy2s.service.dto.DepenseTemp;
import fr.insy2s.service.mapper.ClientFournisseurMapper;
import fr.insy2s.service.mapper.DepenseMapper;
import fr.insy2s.service.mapper.EtatDepenseMapper;
import fr.insy2s.utils.wrapper.WrapperDepense;
import fr.insy2s.utils.wrapper.WrapperListeDepense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Depense}.
 */
@Service
@Transactional
public class DepenseServiceImpl implements DepenseService {

    private final Logger log = LoggerFactory.getLogger(DepenseServiceImpl.class);

    private final DepenseRepository depenseRepository;

    private final DepenseMapper depenseMapper;

    private final EtatDepenseMapper etatDepenseMapper;

    private final DocumentService documentService;

    private final DocumentRepository documentRepository;

    private final SocieteRepository societeRepository;

    private final ClientFournisseurService clientFournisseurService;

    private final ClientFournisseurMapper clientFournisseurMapper;

    private final ClientFournisseurRepository clientFournisseurRepository;

    private final EtatDepenseRepository etatDepenseRepository;

    public DepenseServiceImpl(DepenseRepository depenseRepository, DepenseMapper depenseMapper, EtatDepenseMapper etatDepenseMapper, DocumentService documentService, DocumentRepository documentRepository, SocieteRepository societeRepository, ClientFournisseurService clientFournisseurService, ClientFournisseurMapper clientFournisseurMapper, ClientFournisseurRepository clientFournisseurRepository, EtatDepenseRepository etatDepenseRepository) {
        this.depenseRepository = depenseRepository;
        this.depenseMapper = depenseMapper;
        this.etatDepenseMapper = etatDepenseMapper;
        this.documentService = documentService;
        this.documentRepository = documentRepository;
        this.societeRepository = societeRepository;
        this.clientFournisseurService = clientFournisseurService;
        this.clientFournisseurMapper = clientFournisseurMapper;
        this.clientFournisseurRepository = clientFournisseurRepository;
        this.etatDepenseRepository = etatDepenseRepository;
    }

    @Override
    public DepenseDTO save(DepenseDTO depenseDTO) {
        log.debug("Request to save Depense : {}", depenseDTO);
        Depense depense = depenseMapper.toEntity(depenseDTO);
        depense = depenseRepository.save(depense);
        return depenseMapper.toDto(depense);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepenseDTO> findAll() {
        log.debug("Request to get all Depenses");
        return depenseRepository.findAll().stream()
            .map(depenseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WrapperDepense> findOne(Long id) {
        log.debug("Request to get Depense : {}", id);
        Optional<Depense> depense = depenseRepository.findById(id);
        ClientFournisseur clientFournisseur = depense.get().getClientFournisseur();
        EtatDepense etatDepense = depense.get().getEtatDepense();
        return Optional.of(new WrapperDepense(depenseMapper.toDto(depense.get()), etatDepenseMapper.toDto(etatDepense),
            clientFournisseurMapper.toDto(clientFournisseur)));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Depense : {}", id);
        depenseRepository.deleteById(id);
    }

    @Override
    public DepenseDTO postDepenseWithFile(DepenseTemp depenseTemp) {
        Depense depense = depenseTemp.toDepense();
        if(depenseTemp.getListeFiles()!=null) {
            Set<Document> documents = documentService.multiPartFilesToDocuments(Arrays.asList(depenseTemp.getListeFiles()));
            for (Document document : documents
            ) {
                document.setDepense(depense);
            }
            depense.setListeDocuments(documents);
        }

        List<Depense> depenseList  = depenseRepository.findAllBySocieteIdOrderByNumeroDesc(depenseTemp.getSocieteId());
        Long max = 0L;
        for (Depense depenseOfList: depenseList
        ) {
            if (depenseOfList.getNumero()>max){
                max = depenseOfList.getNumero();
            }
        }
        depense.setNumero(max+1);

        depense.setSociete(societeRepository.getOne(depenseTemp.getSocieteId()));

        depense.setEtatDepense(etatDepenseRepository.getOne(1L));

        Optional<ClientFournisseurDTO> clientFournisseurDTO = clientFournisseurService.findByNomAndSocieteId(depenseTemp.getClient(), depenseTemp.getSocieteId());
        if (clientFournisseurDTO.isPresent()) {
            depense.setClientFournisseur(clientFournisseurMapper.toEntity(clientFournisseurDTO.get()));
        } else {
            ClientFournisseur clientFournisseur = new ClientFournisseur();
            clientFournisseur.setNom(depenseTemp.getClient());
            clientFournisseur.setSociete(societeRepository.getOne(depenseTemp.getSocieteId()));
            depense.setClientFournisseur(clientFournisseurRepository.save(clientFournisseur));
        }

        Depense maDepense = depenseRepository.save(depense);

        if(depense.getListeDocuments()!=null) {
            documentRepository.saveAll(depense.getListeDocuments());
        }

        return this.depenseMapper.toDto(depense);
    }

    @Override
    public List<WrapperListeDepense> findAllDepenseBySocieteId(Long id) {
        List<Depense> depenseList = depenseRepository.findAllBySocieteIdOrderByNumeroDesc(id);
        List<WrapperListeDepense> listeWrapper = new ArrayList<>();
        for (Depense depense: depenseList) {
                WrapperListeDepense wrapperListeDepense = new WrapperListeDepense(depense.getId(), depense.getNumero(), depense.getDate(), depense.getPrix(), depense.getClientFournisseur().getNom(), depense.getEtatDepense().getLibelle());
                listeWrapper.add(wrapperListeDepense);

        }
        return listeWrapper;
    }

}
