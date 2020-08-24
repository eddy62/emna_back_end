package fr.insy2s.web.rest.vm;

import java.util.List;

public class ArticleVM {

    Long articleId;

    String articleTitre;
    String articleDescription;
    List<ClauseVm> listClauses;

    public ArticleVM(Long articleId, String articleTitre, String articleDescription, List<ClauseVm> listClauses) {
        this.articleId = articleId;
        this.articleTitre = articleTitre;
        this.articleDescription = articleDescription;
        this.listClauses = listClauses;
    }

    public ArticleVM() {
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitre() {
        return articleTitre;
    }

    public void setArticleTitre(String articleTitre) {
        this.articleTitre = articleTitre;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public List<ClauseVm> getListClauses() {
        return listClauses;
    }

    public void setListClauses(List<ClauseVm> listClauses) {
        this.listClauses = listClauses;
    }
}
