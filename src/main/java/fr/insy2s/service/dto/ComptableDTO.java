package fr.insy2s.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.Comptable} entity.
 */
public class ComptableDTO implements Serializable {
    
    private Long id;

    private String civilite;


    private Long infoEntrepriseId;

    private Long userId;

    private Long adresseId;
    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComptableDTO)) {
            return false;
        }

        return id != null && id.equals(((ComptableDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ComptableDTO{" +
            "id=" + getId() +
            ", civilite='" + getCivilite() + "'" +
            ", infoEntrepriseId=" + getInfoEntrepriseId() +
            ", userId=" + getUserId() +
            ", adresseId=" + getAdresseId() +
            "}";
    }
}
