package fr.insy2s.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link fr.insy2s.domain.Prime} entity.
 */
public class PrimeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private BigDecimal montant;

    @NotNull
    private Integer mois;

    @NotNull
    private Integer annee;


    private Long typePrimeId;

    private Long etatVariablePaieId;

    private Long employeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
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

    public Long getTypePrimeId() {
        return typePrimeId;
    }

    public void setTypePrimeId(Long typePrimeId) {
        this.typePrimeId = typePrimeId;
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
        if (!(o instanceof PrimeDTO)) {
            return false;
        }

        return id != null && id.equals(((PrimeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrimeDTO{" +
            "id=" + getId() +
            ", montant=" + getMontant() +
            ", mois=" + getMois() +
            ", annee=" + getAnnee() +
            ", typePrimeId=" + getTypePrimeId() +
            ", etatVariablePaieId=" + getEtatVariablePaieId() +
            ", employeId=" + getEmployeId() +
            "}";
    }
}
