package fr.insy2s.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A DTO for the {@link fr.insy2s.domain.Operation} entity.
 */
public class OperationDTO implements Serializable {
    
    private Long id;

    private LocalDate date;

    private String description;

    private String type;

    private Boolean rapproche;

    private BigDecimal solde;


    private Long releveId;
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isRapproche() {
        return rapproche;
    }

    public void setRapproche(Boolean rapproche) {
        this.rapproche = rapproche;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public Long getReleveId() {
        return releveId;
    }

    public void setReleveId(Long releveId) {
        this.releveId = releveId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperationDTO)) {
            return false;
        }

        return id != null && id.equals(((OperationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperationDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", rapproche='" + isRapproche() + "'" +
            ", solde=" + getSolde() +
            ", releveId=" + getReleveId() +
            "}";
    }
}
