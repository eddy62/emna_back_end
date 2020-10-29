package fr.insy2s.service.impl;

import fr.insy2s.domain.SaisieArticle;
import fr.insy2s.repository.SaisieArticleRepository;
import fr.insy2s.service.SaisieArticleService;
import fr.insy2s.service.dto.SaisieArticleDTO;
import fr.insy2s.service.mapper.SaisieArticleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SaisieArticle}.
 */
@Service
@Transactional
public class SaisieArticleServiceImpl implements SaisieArticleService {

    private final Logger log = LoggerFactory.getLogger(SaisieArticleServiceImpl.class);

    private final SaisieArticleRepository saisieArticleRepository;

    private final SaisieArticleMapper saisieArticleMapper;

    public SaisieArticleServiceImpl(SaisieArticleRepository saisieArticleRepository, SaisieArticleMapper saisieArticleMapper) {
        this.saisieArticleRepository = saisieArticleRepository;
        this.saisieArticleMapper = saisieArticleMapper;
    }

    @Override
    public SaisieArticleDTO save(SaisieArticleDTO saisieArticleDTO) {
        log.debug("Request to save SaisieArticle : {}", saisieArticleDTO);
        SaisieArticle saisieArticle = saisieArticleMapper.toEntity(saisieArticleDTO);
        saisieArticle = saisieArticleRepository.save(saisieArticle);
        return saisieArticleMapper.toDto(saisieArticle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaisieArticleDTO> findAll() {
        log.debug("Request to get all SaisieArticles");
        return saisieArticleRepository.findAll().stream()
            .map(saisieArticleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SaisieArticleDTO> findOne(Long id) {
        log.debug("Request to get SaisieArticle : {}", id);
        return saisieArticleRepository.findById(id)
            .map(saisieArticleMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SaisieArticle : {}", id);
        saisieArticleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public SaisieArticleDTO findDateDebutbyContratId(Long id) {
        log.debug("Request to get SaisieArticle for the Article date de début : {}", id);
        return saisieArticleMapper.toDto(saisieArticleRepository.findDateDebutbyContratId(id));
    }

    @Override
    @Transactional(readOnly = true)
    public SaisieArticle findActiveStartDateByEmployee(Long employeeId) {
        log.debug("Request to get date de début active by Employe : {}", employeeId);
        return saisieArticleRepository.findActiveStartDateByEmployee(employeeId);
    }

    public void saveSaisieArticle(SaisieArticleDTO saisieArticleSave) {
        Long idArticle = saisieArticleSave.getArticleId();
        Long idContrat = saisieArticleSave.getContratId();
        Long idAvenant = saisieArticleSave.getAvenantId();
        String libelle = saisieArticleSave.getLibelle();
//        saisieArticleRepository.saveSaisieArticle(idAvenant,idArticle,idContrat,libelle);
    }
}
