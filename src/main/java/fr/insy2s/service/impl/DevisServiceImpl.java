package fr.insy2s.service.impl;

import fr.insy2s.domain.Devis;
import fr.insy2s.domain.Document;
import fr.insy2s.domain.LigneProduit;
import fr.insy2s.service.ClientFournisseurService;
import fr.insy2s.service.DevisService;
import fr.insy2s.repository.DevisRepository;
import fr.insy2s.service.dto.ClientFournisseurDTO;
import fr.insy2s.service.dto.DevisDTO;
import fr.insy2s.service.dto.DocumentDTO;
import fr.insy2s.service.mapper.*;
import fr.insy2s.utils.QuoteStateConstants;
import fr.insy2s.utils.TotalUtil;
import fr.insy2s.utils.wrapper.WrapperLigneProduit;
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
    private final DocumentMapper documentMapper;
    private final ClientFournisseurService clientFournisseurService;

    public DevisServiceImpl(DevisRepository devisRepository,
                            DevisMapper devisMapper,
                            ClientFournisseurMapper clientFournisseurMapper,
                            AdresseMapper adresseMapper,
                            DocumentMapper documentMapper,
                            ClientFournisseurService clientFournisseurService
                            ,EtatDevisMapper etatDevisMapper) {
        this.devisRepository = devisRepository;
        this.devisMapper = devisMapper;
        this.clientFournisseurMapper = clientFournisseurMapper;
        this.adresseMapper = adresseMapper;
        this.documentMapper = documentMapper;
        this.clientFournisseurService = clientFournisseurService;
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

            List<WrapperLigneProduit> ligneProduits = new ArrayList<>();
            for (LigneProduit ligneProduit : devis.getListeLigneProduits()){
                ligneProduits.add(new WrapperLigneProduit(ligneProduit));
            }

            WrapperQuote wrapperQuote = new WrapperQuote(
         
                devis,
                adresseMapper.toDto(devis.getClientFournisseur().getAdresse()),
                ligneProduits,
                documentDTOList,
                TotalUtil.getTTCDevis(devis)
            );

            wrapperQuoteList.add(wrapperQuote);
        }
        return wrapperQuoteList;
    }

    /**
     * Get the WrapperQuote by id.
     *
     * @param id the id of the entity.
     * @return the Quote Wrapper
     */
    @Override
    public WrapperQuote findQuote(Long id) {
        log.debug("Request to get Quote : {}", id);

        Optional<Devis> optionalDevis = devisRepository.findDevisById(id);
        Devis devis = optionalDevis.get();

        List<WrapperLigneProduit> ligneProduits = new ArrayList<>();
        devis.getListeLigneProduits().forEach(ligneProduit -> ligneProduits.add(new WrapperLigneProduit(ligneProduit)));

        List<DocumentDTO> documentDTOList = new ArrayList<>();
        for (Document document : devis.getListeDocuments()){
            documentDTOList.add(documentMapper.toDto(document));
        }

        return new WrapperQuote(
            devis,
            adresseMapper.toDto(devis.getClientFournisseur().getAdresse()),
            ligneProduits,
            documentDTOList,
            TotalUtil.getTTCDevis(devis)
        );
    }

    /**
     * Save a quote.
     *
     * @param wrapperQuote the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DevisDTO saveQuote(WrapperQuote wrapperQuote) {
        Devis quote = new Devis();
        try {
            // informations devis
            quote.setNumDevis(wrapperQuote.getNumDevis());
            quote.setNom(wrapperQuote.getNom());
            quote.setMessage(wrapperQuote.getMessage());
            quote.setDateCreation(wrapperQuote.getDateCreation());
            quote.setDateLimite(wrapperQuote.getDateLimite());

            // informations client
            Optional<ClientFournisseurDTO> clientFournisseurDTO = clientFournisseurService.findOne(wrapperQuote.getClientFournisseurId());
            quote.setClientFournisseur(clientFournisseurMapper.toEntity(clientFournisseurDTO.get()));

            // lignes de produits

        } catch (Exception e) {
            e.printStackTrace();
        }

        Devis newQuote = devisRepository.save(quote);
        return devisMapper.toDto(newQuote);
    }

    /**
     * Get the "id" devis for get the number of the new quote.
     *
     * @param id the id of the entity.
     * @return the number of the new quote.
     */
    @Override
    public Long findQuoteNumber(Long id) {
        List<Devis> quoteList = devisRepository.findQuotesBySocietyId(id);
        Long quoteNumber = 0L;

        for (Devis quote : quoteList) {
            if (quote.getNumDevis() != null && quote.getNumDevis() > quoteNumber) {
                quoteNumber = quote.getNumDevis();
            }
        }
        return quoteNumber;
    }

    @Override
	public Optional<DevisDTO> changeState(Long id) {
		Optional <DevisDTO> opRes = devisRepository.findById(id).map(devisMapper::toDto);
		DevisDTO quote = null;
		if(opRes.isPresent()) {
			quote = opRes.get();
			long idStateQuote = quote.getEtatDevisId();
			if(idStateQuote==QuoteStateConstants.DEVIS_ARCHIVE) {
				quote.setEtatDevisId(QuoteStateConstants.DEVIS_SIGNE);
			}else if(idStateQuote==QuoteStateConstants.DEVIS_SIGNE) {
				quote.setEtatDevisId(QuoteStateConstants.DEVIS_ARCHIVE);
			}
			devisRepository.save(devisMapper.toEntity(quote));
			
			
		}
		Optional<DevisDTO>optional = Optional.of(quote);
		return optional;
	}
}
