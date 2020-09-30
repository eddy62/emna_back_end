package fr.insy2s.service.mapper;


import fr.insy2s.domain.SaisieArticle;
import fr.insy2s.service.dto.SaisieArticleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link SaisieArticle} and its DTO {@link SaisieArticleDTO}.
 */
@Mapper(componentModel = "spring", uses = {ArticleMapper.class, ContratMapper.class})
public interface SaisieArticleMapper extends EntityMapper<SaisieArticleDTO, SaisieArticle> {

    @Mapping(source = "article.id", target = "articleId")
    @Mapping(source = "contrat.id", target = "contratId")
    SaisieArticleDTO toDto(SaisieArticle saisieArticle);

    @Mapping(source = "articleId", target = "article")
    @Mapping(source = "contratId", target = "contrat")
    SaisieArticle toEntity(SaisieArticleDTO saisieArticleDTO);

    default SaisieArticle fromId(Long id) {
        if (id == null) {
            return null;
        }
        SaisieArticle saisieArticle = new SaisieArticle();
        saisieArticle.setId(id);
        return saisieArticle;
    }
}
