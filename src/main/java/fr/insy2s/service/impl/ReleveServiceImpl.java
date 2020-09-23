package fr.insy2s.service.impl;

import fr.insy2s.domain.Facture;
import fr.insy2s.domain.Operation;
import fr.insy2s.domain.Societe;
import fr.insy2s.repository.*;
import fr.insy2s.service.ReleveService;
import fr.insy2s.domain.Releve;
import fr.insy2s.service.dto.ReleveDTO;
import fr.insy2s.service.mapper.ReleveMapper;
import fr.insy2s.utils.DateUtil;
import fr.insy2s.utils.wrapper.WrapperArchivedStatement;
import fr.insy2s.utils.wrapper.WrapperPDFSingleOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Releve}.
 */
@Service
@Transactional
public class ReleveServiceImpl implements ReleveService {

    private final Logger log = LoggerFactory.getLogger(ReleveServiceImpl.class);

    private final ReleveRepository releveRepository;

    private final ReleveMapper releveMapper;

    private final EtatReleveRepository etatReleveRepository;

    private final OperationRepository operationRepository;

    private final FactureRepository factureRepository;

    private final SocieteRepository societeRepository;

    public ReleveServiceImpl(ReleveRepository releveRepository, ReleveMapper releveMapper, EtatReleveRepository etatReleveRepository, OperationRepository operationRepository, FactureRepository factureRepository, SocieteRepository societeRepository) {
        this.releveRepository = releveRepository;
        this.releveMapper = releveMapper;
        this.etatReleveRepository = etatReleveRepository;
        this.operationRepository = operationRepository;
        this.factureRepository = factureRepository;
        this.societeRepository = societeRepository;
    }

    @Override
    public ReleveDTO save(ReleveDTO releveDTO) {
        log.debug("Request to save Releve : {}", releveDTO);
        Releve releve = releveMapper.toEntity(releveDTO);
        releve.setEtatReleve(etatReleveRepository.getOne(1L));
        releve = releveRepository.save(releve);
        return releveMapper.toDto(releve);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleveDTO> findAll() {
        log.debug("Request to get all Releves");
        return releveRepository.findAll().stream()
            .map(releveMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReleveDTO> findOne(Long id) {
        log.debug("Request to get Releve : {}", id);
        return releveRepository.findById(id)
            .map(releveMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Releve : {}", id);
        releveRepository.deleteById(id);
    }

    public List<ReleveDTO> findAllBySocieteId(Long id) {
        log.debug("Request to get all Releves by Societe Id");
        return releveRepository.findAllBySocieteId(id).stream().map(releveMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public List<ReleveDTO> findAllByEtatReleveId(Long id) {
        log.debug("Request to get all Releves by Societe Id");
        return releveRepository.findAllByEtatReleveId(id).stream().map(releveMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public List<ReleveDTO> findAllByEtatReleveIdAndSocieteId(Long idEtat, Long idSociete) {
        log.debug("Request to get all Releves by Societe Id");
        List<ReleveDTO> releves = releveRepository.findAllByEtatReleveIdAndSocieteId(idEtat, idSociete)
            .stream().map(releveMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
        Optional<BigDecimal> solde;
        for (ReleveDTO releve : releves) {
            solde = getReleveSoldeById(releve.getId());
            releve.setSolde(
                solde.map(BigDecimal::doubleValue).orElse(0.0)
             );
        }
        return releves;
    }

    @Override
    public Optional<BigDecimal> getReleveSoldeById(Long id)
    {
        log.debug("Request to get solde by Releve Id");
        return releveRepository.getReleveSoldeById(id);
    }

    @Override
    public boolean hasPermissionForThisReleve(Long idReleve, String loginCurrentUser) {
        log.debug("Request to get relever by idReleve and loginCurrentUser");
        return releveRepository.checkPermissionForThisReleve(idReleve, loginCurrentUser) != null;
    }

    @Override
    public boolean balanceOperationsEqualsInvoices(Long idReleve) {
        List<Operation> operations = operationRepository.findAllByReleveId(idReleve);
        boolean isSoldeEquals = false;

        if (operations != null) {
            int i = 0;
            do {
                Long idOperation = operations.get(i).getId();
                BigDecimal sommeFacture = factureRepository.balanceOfInvoicesByTransaction(idOperation).orElse(new BigDecimal(0));
                double sommeF = sommeFacture.doubleValue();
                isSoldeEquals = Double.compare(operations.get(i).getSolde(), sommeF) == 0;
                i++;
            }
            while (isSoldeEquals && i < operations.size());
        }
        return isSoldeEquals;
    }

    @Override
    public boolean changeStatutStatement(Long idReleve, Long idEtat) {
        log.debug("REST request to validate Releve");
        Integer result = releveRepository.validateRelever(idReleve, idEtat);
        return result != 0;
    }

    // C'est mieux de faire appel à un autre service our faire une nouvelle requête ?

    @Override
    public WrapperArchivedStatement getWrapperArchivedStatement(Long idStatement)
    {
        ReleveDTO releveDTO                 = this.findOne(idStatement).orElse(null);
        assert releveDTO                    != null;
        Optional<Societe> societeDTO        = societeRepository.findById(releveDTO.getSocieteId());
        List<Operation> operationDTOList    = operationRepository.findAllByReleveId(idStatement);
        List<Facture> factureDTOList        = factureRepository.findAllInvoicesByStatement(idStatement);
        return new WrapperArchivedStatement(
            releveDTO.getId(),
            DateUtil.convertToFrenchDate(releveDTO.getDateDebut()),
            DateUtil.convertToFrenchDate(releveDTO.getDateFin()),
            releveDTO.getSolde().toString(),
            releveDTO.getBanque(),
            societeDTO.get().getCivilite(),
            getListOfWrapperPDFSingleOperation(operationDTOList)
        );
    }

    private List<WrapperPDFSingleOperation> getListOfWrapperPDFSingleOperation(List<Operation> operations)
    {
        List<WrapperPDFSingleOperation> wrapperPDFSingleOperations = new ArrayList<>();

        operations.forEach(operation -> {
            wrapperPDFSingleOperations.add( new WrapperPDFSingleOperation(
                operation.getId(),
                DateUtil.convertToFrenchDate(operation.getDate()),
                operation.getDescription(),
                operation.getType(),
                operation.getSolde() + "€"
            ));
        });

        return wrapperPDFSingleOperations;
    }
}
