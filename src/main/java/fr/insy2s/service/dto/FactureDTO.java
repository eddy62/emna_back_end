package fr.insy2s.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.Facture} entity.
 */
public class FactureDTO implements Serializable {
    
    private Long id;

    private Long numfact;

    private String nom;

    private String message;

    private LocalDate date;

    private LocalDate dateEcheance;

    private Integer prixHT;

    private Integer prixTTC;

    private Float tva;

    private String fichier;

    private String cheminFichier;

    private String type;

    private String moyenDePaiement;


    private Long adresseId;

    private Long etatFactureId;

    private Long societeId;

    private Long operationId;

    private Long clientFournisseurId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumfact() {
        return numfact;
    }

    public void setNumfact(Long numfact) {
        this.numfact = numfact;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Integer getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(Integer prixHT) {
        this.prixHT = prixHT;
    }

    public Integer getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(Integer prixTTC) {
        this.prixTTC = prixTTC;
    }

    public Float getTva() {
        return tva;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoyenDePaiement() {
        return moyenDePaiement;
    }

    public void setMoyenDePaiement(String moyenDePaiement) {
        this.moyenDePaiement = moyenDePaiement;
    }

    public Long getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Long adresseId) {
        this.adresseId = adresseId;
    }

    public Long getEtatFactureId() {
        return etatFactureId;
    }

    public void setEtatFactureId(Long etatFactureId) {
        this.etatFactureId = etatFactureId;
    }

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
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
        if (!(o instanceof FactureDTO)) {
            return false;
        }

        return id != null && id.equals(((FactureDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FactureDTO{" +
            "id=" + getId() +
            ", numfact=" + getNumfact() +
            ", nom='" + getNom() + "'" +
            ", message='" + getMessage() + "'" +
            ", date='" + getDate() + "'" +
            ", dateEcheance='" + getDateEcheance() + "'" +
            ", prixHT=" + getPrixHT() +
            ", prixTTC=" + getPrixTTC() +
            ", tva=" + getTva() +
            ", fichier='" + getFichier() + "'" +
            ", cheminFichier='" + getCheminFichier() + "'" +
            ", type='" + getType() + "'" +
            ", moyenDePaiement='" + getMoyenDePaiement() + "'" +
            ", adresseId=" + getAdresseId() +
            ", etatFactureId=" + getEtatFactureId() +
            ", societeId=" + getSocieteId() +
            ", operationId=" + getOperationId() +
            ", clientFournisseurId=" + getClientFournisseurId() +
            "}";
    }
}
