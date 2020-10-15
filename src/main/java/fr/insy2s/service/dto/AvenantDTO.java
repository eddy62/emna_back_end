package fr.insy2s.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.Avenant} entity.
 */
public class AvenantDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String reference;

    @NotNull
    private Boolean signe;

    @NotNull
    private LocalDate dateDeCreation;

    private LocalDate dateDeSignature;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Boolean isSigne() {
        return signe;
    }

    public void setSigne(Boolean signe) {
        this.signe = signe;
    }

    public LocalDate getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(LocalDate dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public LocalDate getDateDeSignature() {
        return dateDeSignature;
    }

    public void setDateDeSignature(LocalDate dateDeSignature) {
        this.dateDeSignature = dateDeSignature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AvenantDTO)) {
            return false;
        }

        return id != null && id.equals(((AvenantDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AvenantDTO{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", signe='" + isSigne() + "'" +
            ", dateDeCreation='" + getDateDeCreation() + "'" +
            ", dateDeSignature='" + getDateDeSignature() + "'" +
            "}";
    }
}
