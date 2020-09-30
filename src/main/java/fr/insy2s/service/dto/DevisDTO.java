package fr.insy2s.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link fr.insy2s.domain.Devis} entity.
 */
public class DevisDTO implements Serializable {
    
    private Long id;

    private Long numDevis;

    private String nom;

    private String message;

    private LocalDate dateCreation;

    private LocalDate dateLimite;


    private Long etatDevisId;

    private Long clientFournisseurId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumDevis() {
        return numDevis;
    }

    public void setNumDevis(Long numDevis) {
        this.numDevis = numDevis;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(LocalDate dateLimite) {
        this.dateLimite = dateLimite;
    }

    public Long getEtatDevisId() {
        return etatDevisId;
    }

    public void setEtatDevisId(Long etatDevisId) {
        this.etatDevisId = etatDevisId;
    }

    public Long getClientFournisseurId() {
        return clientFournisseurId;
    }

    public void setClientFournisseurId(Long clientFournisseurId) {
        this.clientFournisseurId = clientFournisseurId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DevisDTO)) {
            return false;
        }

        return id != null && id.equals(((DevisDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DevisDTO{" +
            "id=" + getId() +
            ", numDevis=" + getNumDevis() +
            ", nom='" + getNom() + "'" +
            ", message='" + getMessage() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateLimite='" + getDateLimite() + "'" +
            ", etatDevisId=" + getEtatDevisId() +
            ", clientFournisseurId=" + getClientFournisseurId() +
            "}";
    }
}
