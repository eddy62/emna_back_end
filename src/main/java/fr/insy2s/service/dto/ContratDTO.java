package fr.insy2s.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.Contrat} entity.
 */
public class ContratDTO implements Serializable {

    private Long id;

    @NotNull
    private String titre;

    @NotNull
    private LocalDate dateCreation;

    @NotNull
    private Boolean signe;

    private Boolean archive;

    private Long employeId;

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

    public Boolean isSigne() {
        return signe;
    }

    public void setSigne(Boolean signe) {
        this.signe = signe;
    }

    public Boolean isArchive() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContratDTO)) {
            return false;
        }

        return id != null && id.equals(((ContratDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContratDTO{" +
            "id=" + getId() +
            ", titre='" + getTitre() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", signe='" + isSigne() + "'" +
            ", archive='" + isArchive() + "'" +
            ", employeId=" + getEmployeId() +
            "}";
    }
}
