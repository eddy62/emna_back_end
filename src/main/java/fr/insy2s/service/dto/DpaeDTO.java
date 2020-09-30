package fr.insy2s.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link fr.insy2s.domain.Dpae} entity.
 */
public class DpaeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String lieu;

    @NotNull
    private LocalDate date;


    private Long employeId;
    
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
            ", employeId=" + getEmployeId() +
            "}";
    }
}
