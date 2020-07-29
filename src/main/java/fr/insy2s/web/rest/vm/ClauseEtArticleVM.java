package fr.insy2s.web.rest.vm;

public class ClauseEtArticleVM {

    String titreArticle;
    String descriptionClause;

    public String getTitreArticle() {
        return titreArticle;
    }

    public void setTitreArticle(String titreArticle) {
        this.titreArticle = titreArticle;
    }

    public String getDescriptionClause() {
        return descriptionClause;
    }

    public void setDescriptionClause(String descriptionClause) {
        this.descriptionClause = descriptionClause;
    }

    public ClauseEtArticleVM(String titreArticle, String descriptionClause) {
        this.titreArticle = titreArticle;
        this.descriptionClause = descriptionClause;
    }

    public ClauseEtArticleVM() {
    }
}
