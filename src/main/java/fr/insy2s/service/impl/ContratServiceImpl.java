package fr.insy2s.service.impl;

import fr.insy2s.domain.Contrat;
import fr.insy2s.repository.ContratRepository;
import fr.insy2s.repository.projection.IContratAllInfoProjection;
import fr.insy2s.repository.projection.IContratEmployerProjection;
import fr.insy2s.service.ContratService;
import fr.insy2s.service.SaisieArticleService;
import fr.insy2s.service.TypeContratService;
import fr.insy2s.service.dto.ContratDTO;
import fr.insy2s.service.dto.SaisieArticleDTO;
import fr.insy2s.service.dto.TypeContratDTO;
import fr.insy2s.service.mapper.ContratMapper;
import fr.insy2s.service.mapper.WrapperSaisieArticleMapper;
import fr.insy2s.service.mapper.WrapperContratMapper;
import fr.insy2s.utils.wrapper.WrapperSaisieArticle;
import fr.insy2s.utils.wrapper.WrapperContrat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Contrat}.
 */
@Service
@Transactional
public class ContratServiceImpl implements ContratService {

    private final Logger log = LoggerFactory.getLogger(ContratServiceImpl.class);

    private final ContratRepository contratRepository;

    private final ContratMapper contratMapper;

    private final TypeContratService typeContratService;
    private final SaisieArticleService saisieArticleService;
    private final WrapperContratMapper wrapperContratMapper;
    private final WrapperSaisieArticleMapper wrapperSaisieArticleMapper;

    public ContratServiceImpl(ContratRepository contratRepository,
                              ContratMapper contratMapper,
                              TypeContratService typeContratService,
                              WrapperContratMapper wrapperContratMapper,
                              SaisieArticleService saisieArticleService,
                              WrapperSaisieArticleMapper wrapperSaisieArticleMapper) {
        this.contratRepository = contratRepository;
        this.contratMapper = contratMapper;
        this.typeContratService = typeContratService;
        this.wrapperContratMapper = wrapperContratMapper;
        this.saisieArticleService = saisieArticleService;
        this.wrapperSaisieArticleMapper = wrapperSaisieArticleMapper;
    }

    @Override
    public ContratDTO save(ContratDTO contratDTO) {
        log.debug("Request to save Contrat : {}", contratDTO);
        Contrat contrat = contratMapper.toEntity(contratDTO);
        contrat = contratRepository.save(contrat);
        return contratMapper.toDto(contrat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratDTO> findAll() {
        log.debug("Request to get all Contrats");
        return contratRepository.findAll().stream()
            .map(contratMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ContratDTO> findOne(Long id) {
        log.debug("Request to get Contrat : {}", id);
        return contratRepository.findById(id)
            .map(contratMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Contrat : {}", id);
        contratRepository.deleteById(id);
    }

    @Override
    public List<IContratAllInfoProjection> getContratAllInfos(Long id) {
        return this.contratRepository.getContratAllInfo(id);
    }


    @Override
    public List<IContratEmployerProjection> getAllContratEmployerById(Long id) {
        return this.contratRepository.getAllContratEmployerByEmployeId(id);
    }

    @Override
    public Contrat getActiveContratEmployee(Long id) {
        return contratRepository.getActiveContratEmployee(id);
    }

    @Override
    public Optional<WrapperContrat> createWrapperContrat(@Valid WrapperContrat wrapperContrat) {
        final TypeContratDTO typeContratDTO = typeContratService.findOne(wrapperContrat.getIdTypeContrat()).get();
        final ContratDTO contratDTO = wrapperContratMapper.toContratDto(wrapperContrat);
        final ContratDTO newContratDTO = save(contratDTO);
        List<WrapperSaisieArticle> newWrapperSaisieArticle = new ArrayList<WrapperSaisieArticle>();
        for (int i = 0; i < wrapperContrat.getWrapperSaisieArticles().size(); i++) {
            final SaisieArticleDTO saisieArticleDTO = wrapperSaisieArticleMapper.toSaisieArticleDTO(wrapperContrat.getWrapperSaisieArticles().get(i));
            saisieArticleDTO.setContratId(newContratDTO.getId());
            final SaisieArticleDTO newSaisieArticleDTO = saisieArticleService.save(saisieArticleDTO);
            newWrapperSaisieArticle.add(wrapperSaisieArticleMapper.toWrapperArticle(newSaisieArticleDTO));
        }
        WrapperContrat newWrapperContrat = new WrapperContrat(newContratDTO, typeContratDTO, newWrapperSaisieArticle);
        return Optional.of(newWrapperContrat);
    }

    @Override
    public Boolean archiveContrat(Long idContrat, Boolean isArchive) {
        return contratRepository.archiveContrat(idContrat, isArchive) > 0;
    }
}
