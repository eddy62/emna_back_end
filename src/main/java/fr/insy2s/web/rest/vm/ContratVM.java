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

    String clauses;

    public ContratVM(Long id, String titre, LocalDate dateCreation, Boolean signe, Boolean archive, Long employeId, Long societeId, String clauses) {
        this.id = id;
        this.titre = titre;
        this.dateCreation = dateCreation;
        this.signe = signe;
        this.archive = archive;
        this.employeId = employeId;
        this.societeId = societeId;
        this.clauses = clauses;
    }

    public ContratVM() {
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

    public String getClauses() {
        return clauses;
    }

    public void setClauses(String clauses) {
        this.clauses = clauses;
    }

    @Override
    public String toString() {
        return "ContratVM{" +
            "id=" + id +
            ", titre='" + titre + '\'' +
            ", dateCreation=" + dateCreation +
            ", signe=" + signe +
            ", archive=" + archive +
            ", employeId=" + employeId +
            ", societeId=" + societeId +
            ", clauses=" + clauses +
            '}';
    }
}
