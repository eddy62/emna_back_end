package fr.insy2s.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

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

    private Double prixHT;

    private Double prixTTC;

    private Float tva;

    private String cheminFichier;


    private Long etatDevisId;

    private Long societeId;

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

    public Double getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(Double prixHT) {
        this.prixHT = prixHT;
    }

    public Double getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(Double prixTTC) {
        this.prixTTC = prixTTC;
    }

    public Float getTva() {
        return tva;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public Long getEtatDevisId() {
        return etatDevisId;
    }

    public void setEtatDevisId(Long etatDevisId) {
        this.etatDevisId = etatDevisId;
    }

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DevisDTO devisDTO = (DevisDTO) o;
        if (devisDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), devisDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DevisDTO{" +
            "id=" + getId() +
            ", numDevis=" + getNumDevis() +
            ", nom='" + getNom() + "'" +
            ", message='" + getMessage() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateLimite='" + getDateLimite() + "'" +
            ", prixHT=" + getPrixHT() +
            ", prixTTC=" + getPrixTTC() +
            ", tva=" + getTva() +
            ", cheminFichier='" + getCheminFichier() + "'" +
            ", etatDevisId=" + getEtatDevisId() +
            ", societeId=" + getSocieteId() +
            ", clientFournisseurId=" + getClientFournisseurId() +
            "}";
    }
}
