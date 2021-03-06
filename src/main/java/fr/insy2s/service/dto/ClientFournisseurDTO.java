package fr.insy2s.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.ClientFournisseur} entity.
 */
public class ClientFournisseurDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String nom;

    private String siret;

    private String telephone;

    private String email;


    private Long adresseId;

    private Long societeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Long adresseId) {
        this.adresseId = adresseId;
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
            ", nom='" + getNom() + "'" +
            ", siret='" + getSiret() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", adresseId=" + getAdresseId() +
            ", societeId=" + getSocieteId() +
            "}";
    }
}
