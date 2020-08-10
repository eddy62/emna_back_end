package fr.insy2s.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link fr.insy2s.domain.Societe} entity.
 */
public class SocieteDTO implements Serializable {
    
    private Long id;

    private String civilite;

    private Long adresseId;

    private Long infoEntrepriseId;

    private Long userId;

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

    public Long getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Long adresseId) {
        this.adresseId = adresseId;
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SocieteDTO societeDTO = (SocieteDTO) o;
        if (societeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), societeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SocieteDTO{" +
            "id=" + getId() +
            ", civilite='" + getCivilite() + "'" +
            ", adresseId=" + getAdresseId() +
            ", infoEntrepriseId=" + getInfoEntrepriseId() +
            ", userId=" + getUserId() +
            ", comptableId=" + getComptableId() +
            "}";
    }
}
