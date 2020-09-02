package fr.insy2s.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.FichePaie} entity.
 */
public class FichePaieDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate debutPeriode;

    @NotNull
    private LocalDate finPeriode;

    @NotNull
    private String lienDocument;

    @NotNull
    private Integer mois;

    @NotNull
    private Integer annee;


    private Long employeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDebutPeriode() {
        return debutPeriode;
    }

    public void setDebutPeriode(LocalDate debutPeriode) {
        this.debutPeriode = debutPeriode;
    }

    public LocalDate getFinPeriode() {
        return finPeriode;
    }

    public void setFinPeriode(LocalDate finPeriode) {
        this.finPeriode = finPeriode;
    }

    public String getLienDocument() {
        return lienDocument;
    }

    public void setLienDocument(String lienDocument) {
        this.lienDocument = lienDocument;
    }

    public Integer getMois() {
        return mois;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
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
        if (!(o instanceof FichePaieDTO)) {
            return false;
        }

        return id != null && id.equals(((FichePaieDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FichePaieDTO{" +
            "id=" + getId() +
            ", debutPeriode='" + getDebutPeriode() + "'" +
            ", finPeriode='" + getFinPeriode() + "'" +
            ", lienDocument='" + getLienDocument() + "'" +
            ", mois=" + getMois() +
            ", annee=" + getAnnee() +
            ", employeId=" + getEmployeId() +
            "}";
    }
}
