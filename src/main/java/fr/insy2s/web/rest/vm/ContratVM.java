package fr.insy2s.web.rest.vm;

import java.time.LocalDate;
import java.util.List;

public class ContratVM {
    Long id;

    String titre;

    LocalDate dateCreation;

    Boolean signe;

    Boolean archive;

    Long employeId;

    Long societeId;

    List<ClauseVm> clauses;

    public ContratVM() {
    }

    public ContratVM(Long id, String titre, LocalDate dateCreation, Boolean signe, Boolean archive, Long employeId, Long societeId, List<ClauseVm> clauses) {
        this.id = id;
        this.titre = titre;
        this.dateCreation = dateCreation;
        this.signe = signe;
        this.archive = archive;
        this.employeId = employeId;
        this.societeId = societeId;
        this.clauses = clauses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getSigne() {
        return signe;
    }

    public void setSigne(Boolean signe) {
        this.signe = signe;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
    }

    public List<ClauseVm> getClauses() {
        return clauses;
    }

    public void setClauses(List<ClauseVm> clauses) {
        this.clauses = clauses;
    }
}
