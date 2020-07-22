package fr.insy2s.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.NoteDeFrais} entity.
 */
public class NoteDeFraisDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String designation;

    @NotNull
    private LocalDate date;

    @NotNull
    private Double montant;

    private String justificatif;


    private Long employeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getJustificatif() {
        return justificatif;
    }

    public void setJustificatif(String justificatif) {
        this.justificatif = justificatif;
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
        if (!(o instanceof NoteDeFraisDTO)) {
            return false;
        }

        return id != null && id.equals(((NoteDeFraisDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoteDeFraisDTO{" +
            "id=" + getId() +
            ", designation='" + getDesignation() + "'" +
            ", date='" + getDate() + "'" +
            ", montant=" + getMontant() +
            ", justificatif='" + getJustificatif() + "'" +
            ", employeId=" + getEmployeId() +
            "}";
    }
}
