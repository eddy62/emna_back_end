package fr.insy2s.service.impl;

import fr.insy2s.domain.*;
import fr.insy2s.repository.*;
import fr.insy2s.service.ClientFournisseurService;
import fr.insy2s.service.DocumentService;
import fr.insy2s.service.FactureService;
import fr.insy2s.service.dto.ClientFournisseurDTO;
import fr.insy2s.service.dto.DepenseTemp;
import fr.insy2s.service.dto.FactureDTO;
import fr.insy2s.service.dto.FactureTemp;
import fr.insy2s.service.mapper.ClientFournisseurMapper;
import fr.insy2s.service.mapper.FactureMapper;
import fr.insy2s.utils.wrapper.WrapperListeFacture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Facture}.
 */
@Service
@Transactional
public class FactureServiceImpl implements FactureService {

    private final Logger log = LoggerFactory.getLogger(FactureServiceImpl.class);

    private final FactureRepository factureRepository;

    private final FactureMapper factureMapper;

    private final SocieteRepository societeRepository;

    private final ClientFournisseurService clientFournisseurService;

    private final ClientFournisseurMapper clientFournisseurMapper;

    private final ClientFournisseurRepository clientFournisseurRepository;

    private final AdresseRepository adresseRepository;

    private final EtatFactureRepository etatFactureRepository;


    public FactureServiceImpl(FactureRepository factureRepository, FactureMapper factureMapper, DocumentService documentService, DocumentRepository documentRepository, SocieteRepository societeRepository, ClientFournisseurService clientFournisseurService, ClientFournisseurMapper clientFournisseurMapper, ClientFournisseurRepository clientFournisseurRepository, AdresseRepository adresseRepository, EtatFactureRepository etatFactureRepository) {

        this.factureRepository = factureRepository;
        this.factureMapper = factureMapper;
        this.societeRepository = societeRepository;
        this.clientFournisseurService = clientFournisseurService;
        this.clientFournisseurMapper = clientFournisseurMapper;
        this.clientFournisseurRepository = clientFournisseurRepository;
        this.adresseRepository = adresseRepository;
        this.etatFactureRepository = etatFactureRepository;
    }

    @Override
    public FactureDTO save(FactureDTO factureDTO) {
        log.debug("Request to save Facture : {}", factureDTO);
        Facture facture = factureMapper.toEntity(factureDTO);
        facture = factureRepository.save(facture);
        return factureMapper.toDto(facture);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FactureDTO> findAll() {
        log.debug("Request to get all Factures");
        return factureRepository.findAll().stream()
            .map(factureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FactureDTO> findOne(Long id) {
        log.debug("Request to get Facture : {}", id);
        return factureRepository.findById(id)
            .map(factureMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Facture : {}", id);
        factureRepository.deleteById(id);
    }

    @Override
    public FactureDTO postFacture(FactureTemp factureTemp) {
        Facture facture = factureTemp.toFacture();

        Adresse adresse = new Adresse();
        adresse.setCodePostal(factureTemp.getCodePostal());
        adresse.setNomRue(factureTemp.getNomRue());
        adresse.setNumeroRue(factureTemp.getNumRue());
        adresse.setPays(factureTemp.getPays());
        adresse.setVille(factureTemp.getVille());
        facture.setAdresse(adresseRepository.save(adresse));

        facture.setType("Vente");

        facture.setSociete(societeRepository.getOne(factureTemp.getSocieteId()));

        facture.setEtatFacture(etatFactureRepository.getOne(1L));

        Optional<ClientFournisseurDTO> clientFournisseurDTO = clientFournisseurService.findByNomAndSocieteId(factureTemp.getClient(), factureTemp.getSocieteId());
        if (clientFournisseurDTO.isPresent()) {
            facture.setClientFournisseur(clientFournisseurMapper.toEntity(clientFournisseurDTO.get()));
        } else {
            ClientFournisseur clientFournisseur = new ClientFournisseur();
            clientFournisseur.setNom(factureTemp.getClient());
            clientFournisseur.setSociete(societeRepository.getOne(factureTemp.getSocieteId()));
            facture.setClientFournisseur(clientFournisseurRepository.save(clientFournisseur));
        }

        Facture mafacture = factureRepository.save(facture);

        return this.factureMapper.toDto(mafacture);
    }

    @Override
    public List<FactureDTO> findAllBySocieteId(Long id) {
        return factureRepository.
            findAllBySocieteIdOrderByNumfactDesc(id).stream()
            .map(factureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override

    public List<WrapperListeFacture> findAllWrapperVenteBySocieteId(Long id) {
        List<Facture> listeFacture = factureRepository.findAllBySocieteIdOrderByNumfactDesc(id);
        List<WrapperListeFacture> wrapperListeFactures = new ArrayList<WrapperListeFacture>();
        for (Facture facture: listeFacture) {
            if (facture.getType().equals("Vente")) {
                WrapperListeFacture wrapperListeFacture = new WrapperListeFacture(facture.getId(), facture.getNumfact(), facture.getType(), facture.getDate(), facture.getPrixTTC(), facture.getClientFournisseur().getNom(), facture.getEtatFacture().getLibelle());
                wrapperListeFactures.add(wrapperListeFacture);
            }
        }
        return wrapperListeFactures;
    }

    public List<FactureDTO> findAllInvoicesByStatement(Long idReleve) {
        log.debug("Request to get all Factures for the statement concerned: {}", idReleve);
        return this.factureRepository.findAllInvoicesByStatement(idReleve)
                                     .stream()
                                     .map(factureMapper::toDto)
                                     .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Long getLastNumFact(Long id) {
         List<Facture> factureList  = factureRepository.findAllBySocieteIdOrderByNumfactDesc(id);
         Long max = 0L;

        for (Facture facture: factureList
             ) {
            if (facture.getNumfact()!=null && facture.getNumfact()>max){
                max = facture.getNumfact();
            }
        }
         return max;
    }

    @Override
    public List<FactureDTO> findAllInvoicesByOperationId(Long idOperation) {
        log.debug("Request to get all factures from id operation");
        return this.factureRepository.findAllInvoicesByOperationId(idOperation)
                                     .stream()
                                     .map(factureMapper::toDto)
                                     .collect(Collectors.toList());
    }

    @Override
    public Integer mergeOperationByIdFacture(Long idFacture, Long idOperation) {
        return factureRepository.mergeOperationByIdFacture(idFacture,idOperation);

    }

}
