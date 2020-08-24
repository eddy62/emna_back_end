package fr.insy2s.web.rest.vm;

public class ClauseVm {
    Long articleId;
    Long clauseId;
    String clauseReference;
    String clauseDesciption;

    public ClauseVm(Long articleId, Long clauseId, String clauseReference, String clauseDesciption) {
        this.articleId = articleId;
        this.clauseId = clauseId;
        this.clauseReference = clauseReference;
        this.clauseDesciption = clauseDesciption;
    }

    public ClauseVm() {
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getClauseId() {
        return clauseId;
    }

    public void setClauseId(Long clauseId) {
        this.clauseId = clauseId;
    }

    public String getClauseReference() {
        return clauseReference;
    }

    public void setClauseReference(String clauseReference) {
        this.clauseReference = clauseReference;
    }

    public String getClauseDesciption() {
        return clauseDesciption;
    }

    public void setClauseDesciption(String clauseDesciption) {
        this.clauseDesciption = clauseDesciption;
    }
}
