package fr.insy2s.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.Societe} entity.
 */
public class SocieteDTO implements Serializable {
    
    private Long id;

    private String civilite;


    private Long infoEntrepriseId;

    private Long userId;

    private Long adresseId;

    private Long comptableId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public Long getInfoEntrepriseId() {
        return infoEntrepriseId;
    }

    public void setInfoEntrepriseId(Long infoEntrepriseId) {
        this.infoEntrepriseId = infoEntrepriseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Long adresseId) {
        this.adresseId = adresseId;
    }

    public Long getComptableId() {
        return comptableId;
    }

    public void setComptableId(Long comptableId) {
        this.comptableId = comptableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SocieteDTO)) {
            return false;
        }

        return id != null && id.equals(((SocieteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SocieteDTO{" +
            "id=" + getId() +
            ", civilite='" + getCivilite() + "'" +
            ", infoEntrepriseId=" + getInfoEntrepriseId() +
            ", userId=" + getUserId() +
            ", adresseId=" + getAdresseId() +
            ", comptableId=" + getComptableId() +
            "}";
    }
}
