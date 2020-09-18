package fr.insy2s.service.impl;

import fr.insy2s.domain.Avenant;
import fr.insy2s.domain.Contrat;
import fr.insy2s.service.ClauseService;
import fr.insy2s.domain.Clause;
import fr.insy2s.repository.ClauseRepository;
import fr.insy2s.service.dto.AvenantDTO;
import fr.insy2s.service.dto.ClauseDTO;
import fr.insy2s.service.dto.ContratDTO;
import fr.insy2s.service.mapper.AvenantMapper;
import fr.insy2s.service.mapper.ClauseMapper;
import fr.insy2s.service.mapper.ContratMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Clause}.
 */
@Service
@Transactional
public class ClauseServiceImpl implements ClauseService {

    private final Logger log = LoggerFactory.getLogger(ClauseServiceImpl.class);

    private final ClauseRepository clauseRepository;

    private final ClauseMapper clauseMapper;
    private final ContratMapper contratMapper;
    private final AvenantMapper avenantMapper;

    public ClauseServiceImpl(ClauseRepository clauseRepository, ClauseMapper clauseMapper, ContratMapper contratMapper, AvenantMapper avenantMapper) {
        this.clauseRepository = clauseRepository;
        this.clauseMapper = clauseMapper;
        this.contratMapper = contratMapper;
        this.avenantMapper = avenantMapper;
    }

    @Override
    public ClauseDTO save(ClauseDTO clauseDTO) {
        log.debug("Request to save Clause : {}", clauseDTO);
        Clause clause = clauseMapper.toEntity(clauseDTO);
        clause = clauseRepository.save(clause);
        return clauseMapper.toDto(clause);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClauseDTO> findAll() {
        log.debug("Request to get all Clauses");
        return clauseRepository.findAllWithEagerRelationships().stream()
            .map(clauseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    public Page<ClauseDTO> findAllWithEagerRelationships(Pageable pageable) {
        return clauseRepository.findAllWithEagerRelationships(pageable).map(clauseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClauseDTO> findOne(Long id) {
        log.debug("Request to get Clause : {}", id);
        return clauseRepository.findOneWithEagerRelationships(id)
            .map(clauseMapper::toDto);
    }

    @Override
    public Optional<ClauseDTO> findById(Long id) {
        log.debug("Request to get Clause : {}", id);
        Clause clause = clauseRepository.findById(id).get();
        ClauseDTO clauseDto = new ClauseDTO();
        if(clause.getArticle() != null) {
            clauseDto.setArticleId(clause.getArticle().getId());
        }
        clauseDto.setDescription(clause.getDescription());
        clauseDto.setId(clause.getId());
        Set<AvenantDTO> listAvenantDto = new HashSet<>();
        for (Avenant avenant : clause.getListeAvenants()) {
            Optional<Avenant> optionalAvenant = Optional.of(avenant);
            Optional<AvenantDTO> optionalAvenantDTO = optionalAvenant.map(avenantMapper::toDto);
            listAvenantDto.add(optionalAvenantDTO.get());
        }

        Set<ContratDTO> listContratDto = new HashSet<>();
        for (Contrat contrat : clause.getListeContrats()) {
            Optional<Contrat> optionalContrat = Optional.of(contrat);
            Optional<ContratDTO> optionalContratDTO = optionalContrat.map(contratMapper::toDto);
            listContratDto.add(optionalContratDTO.get());
        }
        clauseDto.setListeAvenants(listAvenantDto);
        clauseDto.setListeContrats(listContratDto);
        clauseDto.setReference(clause.getReference());
        clauseDto.setSocieteId(clause.getSociete().getId());
        return Optional.of(clauseDto);
    }

    @Override
    public List<ClauseDTO> findAllClausesBySocietyId(Long idSociete) {
        log.debug("Request to get all the clauses by society id: {}", idSociete);
        return this.clauseRepository.findAllClausesBySocietyId(idSociete)
            .stream()
            .map(clauseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Clause : {}", id);
        clauseRepository.deleteById(id);
    }
}
