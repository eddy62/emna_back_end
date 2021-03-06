package fr.insy2s.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.Releve} entity.
 */
public class ReleveDTO implements Serializable {
    
    private Long id;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private String banque;


    private Long etatReleveId;

    private Long societeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getBanque() {
        return banque;
    }

    public void setBanque(String banque) {
        this.banque = banque;
    }

    public Long getEtatReleveId() {
        return etatReleveId;
    }

    public void setEtatReleveId(Long etatReleveId) {
        this.etatReleveId = etatReleveId;
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
        if (!(o instanceof ReleveDTO)) {
            return false;
        }

        return id != null && id.equals(((ReleveDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReleveDTO{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", banque='" + getBanque() + "'" +
            ", etatReleveId=" + getEtatReleveId() +
            ", societeId=" + getSocieteId() +
            "}";
    }
}
