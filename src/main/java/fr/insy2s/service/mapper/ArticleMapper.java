package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.ArticleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Article} and its DTO {@link ArticleDTO}.
 */
@Mapper(componentModel = "spring", uses = {AvenantMapper.class, ContratMapper.class, SocieteMapper.class})
public interface ArticleMapper extends EntityMapper<ArticleDTO, Article> {

    ArticleDTO toDto(Article article);

    Article toEntity(ArticleDTO articleDTO);

    default Article fromId(Long id) {
        if (id == null) {
            return null;
        }
        Article article = new Article();
        article.setId(id);
        return article;
    }
}
