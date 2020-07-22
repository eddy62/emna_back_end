package fr.insy2s.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.ClientFournisseur} entity.
 */
public class ClientFournisseurDTO implements Serializable {
    
    private Long id;


    private Long infoEntrepriseId;

    private Long societeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInfoEntrepriseId() {
        return infoEntrepriseId;
    }

    public void setInfoEntrepriseId(Long infoEntrepriseId) {
        this.infoEntrepriseId = infoEntrepriseId;
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
        if (!(o instanceof ClientFournisseurDTO)) {
            return false;
        }

        return id != null && id.equals(((ClientFournisseurDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientFournisseurDTO{" +
            "id=" + getId() +
            ", infoEntrepriseId=" + getInfoEntrepriseId() +
            ", societeId=" + getSocieteId() +
            "}";
    }
}
