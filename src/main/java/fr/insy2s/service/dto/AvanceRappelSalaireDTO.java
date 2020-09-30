package fr.insy2s.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A DTO for the {@link fr.insy2s.domain.AvanceRappelSalaire} entity.
 */
public class AvanceRappelSalaireDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String type;

    @NotNull
    private LocalDate debutPeriode;

    @NotNull
    private LocalDate finPeriode;

    @NotNull
    private BigDecimal montant;

    @NotNull
    private Integer mois;

    @NotNull
    private Integer annee;


    private Long etatVariablePaieId;

    private Long employeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(o instanceof AvanceRappelSalaireDTO)) {
            return false;
        }

        return id != null && id.equals(((AvanceRappelSalaireDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AvanceRappelSalaireDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", debutPeriode='" + getDebutPeriode() + "'" +
            ", finPeriode='" + getFinPeriode() + "'" +
            ", montant=" + getMontant() +
            ", mois=" + getMois() +
            ", annee=" + getAnnee() +
            ", etatVariablePaieId=" + getEtatVariablePaieId() +
            ", employeId=" + getEmployeId() +
            "}";
    }
}
