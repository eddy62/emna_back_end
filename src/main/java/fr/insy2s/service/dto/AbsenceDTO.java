package fr.insy2s.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.Absence} entity.
 */
public class AbsenceDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate debutAbsence;

    @NotNull
    private LocalDate finAbsence;

    private String justificatif;


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

    public String getJustificatif() {
        return justificatif;
    }

    public void setJustificatif(String justificatif) {
        this.justificatif = justificatif;
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
            ", justificatif='" + getJustificatif() + "'" +
            ", typeAbsenceId=" + getTypeAbsenceId() +
            ", etatVariablePaieId=" + getEtatVariablePaieId() +
            ", employeId=" + getEmployeId() +
            "}";
    }
}
