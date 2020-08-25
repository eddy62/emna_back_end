package fr.insy2s.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.HeuresSupplementaires} entity.
 */
public class HeuresSupplementairesDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private Integer nombreHeure;

    private String justificatif;


    private Long etatVariablePaieId;

    private Long employeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getNombreHeure() {
        return nombreHeure;
    }

    public void setNombreHeure(Integer nombreHeure) {
        this.nombreHeure = nombreHeure;
    }

    public String getJustificatif() {
        return justificatif;
    }

    public void setJustificatif(String justificatif) {
        this.justificatif = justificatif;
    }

    public Long getEtatVariablePaieId() {
        return etatVariablePaieId;
    }

    public void setEtatVariablePaieId(Long etatVariablePaieId) {
        this.etatVariablePaieId = etatVariablePaieId;
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
        if (!(o instanceof HeuresSupplementairesDTO)) {
            return false;
        }

        return id != null && id.equals(((HeuresSupplementairesDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HeuresSupplementairesDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", nombreHeure=" + getNombreHeure() +
            ", justificatif='" + getJustificatif() + "'" +
            ", etatVariablePaieId=" + getEtatVariablePaieId() +
            ", employeId=" + getEmployeId() +
            "}";
    }
}
