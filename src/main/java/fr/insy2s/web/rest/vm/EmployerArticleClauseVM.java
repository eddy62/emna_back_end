package fr.insy2s.web.rest.vm;

public class EmployerArticleClauseVM {
    Long employerId;

    String employerNom;

    String employerPrenom;

    Long societeId;

    String articleTitre;

    Long clauseId;

    String clauseReference;

    String clauseDescription;

    public EmployerArticleClauseVM(Long employerId, String employerNom, String employerPrenom, Long societeId, String articleTitre, Long clauseId, String clauseReference, String clauseDescription) {
        this.employerId = employerId;
        this.employerNom = employerNom;
        this.employerPrenom = employerPrenom;
        this.societeId = societeId;
        this.articleTitre = articleTitre;
        this.clauseId = clauseId;
        this.clauseReference = clauseReference;
        this.clauseDescription = clauseDescription;
    }
    public EmployerArticleClauseVM(){}

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public String getEmployerNom() {
        return employerNom;
    }

    public void setEmployerNom(String employerNom) {
        this.employerNom = employerNom;
    }

    public String getEmployerPrenom() {
        return employerPrenom;
    }

    public void setEmployerPrenom(String employerPrenom) {
        this.employerPrenom = employerPrenom;
    }

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
    }

    public String getArticleTitre() {
        return articleTitre;
    }

    public void setArticleTitre(String articleTitre) {
        this.articleTitre = articleTitre;
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

    public String getClauseDescription() {
        return clauseDescription;
    }

    public void setClauseDescription(String clauseDescription) {
        this.clauseDescription = clauseDescription;
    }
}
