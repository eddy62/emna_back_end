package fr.insy2s.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.Prime} entity.
 */
public class PrimeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String type;

    @NotNull
    private Double montant;


    private Long typePrimeId;

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

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Long getTypePrimeId() {
        return typePrimeId;
    }

    public void setTypePrimeId(Long typePrimeId) {
        this.typePrimeId = typePrimeId;
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
            ", type='" + getType() + "'" +
            ", montant=" + getMontant() +
            ", typePrimeId=" + getTypePrimeId() +
            ", employeId=" + getEmployeId() +
            "}";
    }
}
