package fr.insy2s.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link fr.insy2s.domain.Absence} entity.
 */
public class AbsenceDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate debutAbsence;

    @NotNull
    private LocalDate finAbsence;

    @NotNull
    private Integer mois;

    @NotNull
    private Integer annee;


    private Long typeAbsenceId;

    private Long etatVariablePaieId;

    private Long employeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDebutAbsence() {
        return debutAbsence;
    }

    public void setDebutAbsence(LocalDate debutAbsence) {
        this.debutAbsence = debutAbsence;
    }

    public LocalDate getFinAbsence() {
        return finAbsence;
    }

    public void setFinAbsence(LocalDate finAbsence) {
        this.finAbsence = finAbsence;
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

    public Long getTypeAbsenceId() {
        return typeAbsenceId;
    }

    public void setTypeAbsenceId(Long typeAbsenceId) {
        this.typeAbsenceId = typeAbsenceId;
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
        if (!(o instanceof AbsenceDTO)) {
            return false;
        }

        return id != null && id.equals(((AbsenceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AbsenceDTO{" +
            "id=" + getId() +
            ", debutAbsence='" + getDebutAbsence() + "'" +
            ", finAbsence='" + getFinAbsence() + "'" +
            ", mois=" + getMois() +
            ", annee=" + getAnnee() +
            ", typeAbsenceId=" + getTypeAbsenceId() +
            ", etatVariablePaieId=" + getEtatVariablePaieId() +
            ", employeId=" + getEmployeId() +
            "}";
    }
}
