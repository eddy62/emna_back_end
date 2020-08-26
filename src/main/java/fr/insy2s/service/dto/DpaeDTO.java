package fr.insy2s.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.Dpae} entity.
 */
public class DpaeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String lieu;

    @NotNull
    private LocalDate date;

    @NotNull
    private String lienDocument;


    private Long employeId;

    private Long societeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLienDocument() {
        return lienDocument;
    }

    public void setLienDocument(String lienDocument) {
        this.lienDocument = lienDocument;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DpaeDTO)) {
            return false;
        }

        return id != null && id.equals(((DpaeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DpaeDTO{" +
            "id=" + getId() +
            ", lieu='" + getLieu() + "'" +
            ", date='" + getDate() + "'" +
            ", lienDocument='" + getLienDocument() + "'" +
            ", employeId=" + getEmployeId() +
            ", societeId=" + getSocieteId() +
            "}";
    }
}
