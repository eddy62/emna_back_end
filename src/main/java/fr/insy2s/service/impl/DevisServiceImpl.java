package fr.insy2s.service.impl;

import fr.insy2s.domain.Document;
import fr.insy2s.domain.LigneProduit;
import fr.insy2s.service.DevisService;
import fr.insy2s.domain.Devis;
import fr.insy2s.repository.DevisRepository;
import fr.insy2s.service.dto.DevisDTO;
import fr.insy2s.service.dto.DocumentDTO;
import fr.insy2s.service.dto.LigneProduitDTO;
import fr.insy2s.service.mapper.*;
import fr.insy2s.utils.wrapper.WrapperQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Devis}.
 */
@Service
@Transactional
public class DevisServiceImpl implements DevisService {

    private final Logger log = LoggerFactory.getLogger(DevisServiceImpl.class);
    private final DevisRepository devisRepository;
    private final DevisMapper devisMapper;
    private final ClientFournisseurMapper clientFournisseurMapper;
    private final AdresseMapper adresseMapper;
    private final LigneProduitMapper ligneProduitMapper;
    private final DocumentMapper documentMapper;


    public DevisServiceImpl(DevisRepository devisRepository,
                            DevisMapper devisMapper,
                            ClientFournisseurMapper clientFournisseurMapper,
                            AdresseMapper adresseMapper,
                            LigneProduitMapper ligneProduitMapper,
                            DocumentMapper documentMapper) {
        this.devisRepository = devisRepository;
        this.devisMapper = devisMapper;
        this.clientFournisseurMapper = clientFournisseurMapper;
        this.adresseMapper = adresseMapper;
        this.ligneProduitMapper = ligneProduitMapper;
        this.documentMapper = documentMapper;
    }

    @Override
    public DevisDTO save(DevisDTO devisDTO) {
        log.debug("Request to save Devis : {}", devisDTO);
        Devis devis = devisMapper.toEntity(devisDTO);
        devis = devisRepository.save(devis);
        return devisMapper.toDto(devis);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DevisDTO> findAll() {
        log.debug("Request to get all Devis");
        return devisRepository.findAll().stream()
            .map(devisMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DevisDTO> findOne(Long id) {
        log.debug("Request to get Devis : {}", id);
        return devisRepository.findById(id)
            .map(devisMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Devis : {}", id);
        devisRepository.deleteById(id);
    }

    @Override
    public List<DevisDTO> findAllQuotesBySocietyId(Long idSociete) {
        log.debug("Request to get all the quotes by society id: {}", idSociete);

        return this.devisRepository.findAllQuotesBySocietyId(idSociete)
            .stream()
            .map(devisMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the WrapperQuotes by society id.
     *
     * @param id the id of the society.
     * @return the list of entities Wrapper
     */
    @Override
    public List<WrapperQuote> findAllWrapperQuotes(Long id) {
        log.debug("Request to get all the WrapperQuotes by society id: {}", id);
        List<Devis> quoteList = devisRepository.findQuotesBySocietyId(id);
        List<WrapperQuote> wrapperQuoteList = new ArrayList<>();

        for (Devis devis : quoteList){

            List<DocumentDTO> documentDTOList = new ArrayList<>();
            for (Document document : devis.getListeDocuments()){
                documentDTOList.add(documentMapper.toDto(document));
            }

            List<LigneProduitDTO> ligneProduitDTOList = new ArrayList<>();
            for (LigneProduit ligneProduit : devis.getListeLigneProduits()){
                ligneProduitDTOList.add(ligneProduitMapper.toDto(ligneProduit));
            }

            WrapperQuote wrapperQuote = new WrapperQuote(devisMapper.toDto(devis), clientFournisseurMapper.toDto(devis.getClientFournisseur()), adresseMapper.toDto(devis.getClientFournisseur().getAdresse()), ligneProduitDTOList, documentDTOList);
            wrapperQuoteList.add(wrapperQuote);
        }
        return wrapperQuoteList;
    }

}
