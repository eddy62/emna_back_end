package fr.insy2s.web.rest.vm;

public class ClauseVm {
    Long articleId;
    Long clauseId;
    String clauseDesciption;

    public ClauseVm(Long articleId, Long clauseId, String clauseDesciption) {
        this.articleId = articleId;
        this.clauseId = clauseId;
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

    public String getClauseDesciption() {
        return clauseDesciption;
    }

    public void setClauseDesciption(String clauseDesciption) {
        this.clauseDesciption = clauseDesciption;
    }
}
