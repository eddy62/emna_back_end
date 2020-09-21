package fr.insy2s.service.impl;

import fr.insy2s.service.ArticleService;
import fr.insy2s.domain.Article;
import fr.insy2s.repository.ArticleRepository;
import fr.insy2s.service.dto.ArticleDTO;
import fr.insy2s.service.mapper.ArticleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Article}.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private final ArticleRepository articleRepository;

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public ArticleDTO save(ArticleDTO articleDTO) {
        log.debug("Request to save Article : {}", articleDTO);
        Article article = articleMapper.toEntity(articleDTO);
        article = articleRepository.save(article);
        return articleMapper.toDto(article);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleDTO> findAll() {
        log.debug("Request to get all Articles");
        return articleRepository.findAllWithEagerRelationships().stream()
            .map(articleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public Page<ArticleDTO> findAllWithEagerRelationships(Pageable pageable) {
        return articleRepository.findAllWithEagerRelationships(pageable).map(articleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleDTO> findOne(Long id) {
        log.debug("Request to get Article : {}", id);
        return articleRepository.findOneWithEagerRelationships(id)
            .map(articleMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Article : {}", id);
        articleRepository.deleteById(id);
    }

    @Override
    public boolean isArticleAlreadyExists(ArticleDTO articleToCompare) {
        String articleToCompareNormalizedTitle      = removeDiacriticalMarks(articleToCompare.getTitre());
        String articleToCompareNormalizedIntitule   = removeDiacriticalMarks(articleToCompare.getIntitule());

        return this.findAll().stream().anyMatch(article -> {
            boolean isSameTitle = articleToCompareNormalizedTitle.equals(
                removeDiacriticalMarks(article.getTitre())
            );
            boolean isSameIntitule  = articleToCompareNormalizedIntitule.equals(
                removeDiacriticalMarks(article.getIntitule())
            );
            return isSameTitle || isSameIntitule;
        });
    }

    /**
     * Remove all sort of accent and spaces into a string.
     */
    private String removeDiacriticalMarks(String string) {
        return Normalizer.normalize(removeSpaces(string), Normalizer.Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    private String removeSpaces(String string) {
        return string.trim().toLowerCase()
            .replaceAll("\\s+", "")
            .replaceAll("\\p{Punct}", "");
    }
}
