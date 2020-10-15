package fr.insy2s.service.mapper;

import fr.insy2s.service.dto.SaisieArticleDTO;
import fr.insy2s.utils.wrapper.WrapperSaisieArticle;
import org.springframework.stereotype.Service;

@Service
public class WrapperSaisieArticleMapper {

    public SaisieArticleDTO toSaisieArticleDTO(final WrapperSaisieArticle wrapperSaisieArticle) {
        SaisieArticleDTO saisieArticleDTO = new SaisieArticleDTO();
        saisieArticleDTO.setId(wrapperSaisieArticle.getId());
        saisieArticleDTO.setLibelle(wrapperSaisieArticle.getLibelle());
        saisieArticleDTO.setArticleId(wrapperSaisieArticle.getIdArticle());
        saisieArticleDTO.setContratId(wrapperSaisieArticle.getIdContrat());

        return saisieArticleDTO;
    }

    public WrapperSaisieArticle toWrapperArticle(final SaisieArticleDTO saisieArticleDTO) {
        WrapperSaisieArticle wrapperSaisieArticle = new WrapperSaisieArticle();
        wrapperSaisieArticle.setId(saisieArticleDTO.getId());
        wrapperSaisieArticle.setLibelle(saisieArticleDTO.getLibelle());
        wrapperSaisieArticle.setIdArticle(saisieArticleDTO.getArticleId());
        wrapperSaisieArticle.setIdContrat(saisieArticleDTO.getContratId());

        return wrapperSaisieArticle;
    }
}
