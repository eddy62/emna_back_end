package fr.insy2s.web.rest.vm;

import java.util.List;

public class EmployerVM {
    Long employerId;

    String employerNom;

    String employerPrenom;

    Long societeId;

    List<ArticleVM> listArticles;

    public EmployerVM(Long employerId, String employerNom, String employerPrenom, Long societeId, List<ArticleVM> listArticles) {
        this.employerId = employerId;
        this.employerNom = employerNom;
        this.employerPrenom = employerPrenom;
        this.societeId = societeId;
        this.listArticles = listArticles;
    }

    public EmployerVM() {
    }

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

    public List<ArticleVM> getListArticles() {
        return listArticles;
    }

    public void setListArticles(List<ArticleVM> listArticles) {
        this.listArticles = listArticles;
    }
}
