package fr.insy2s.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link fr.insy2s.domain.Facture} entity.
 */
public class FactureDTO implements Serializable {
    
    private Long id;

    private Long numfact;

    private String nom;

    private String type;

    private String message;

    private LocalDate date;

    private LocalDate dateEcheance;

    private String moyenDePaiement;


    private Long devisId;

    private Long etatFactureId;

    private Long adresseId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getMoyenDePaiement() {
        return moyenDePaiement;
    }

    public void setMoyenDePaiement(String moyenDePaiement) {
        this.moyenDePaiement = moyenDePaiement;
    }

    public Long getDevisId() {
        return devisId;
    }

    public void setDevisId(Long devisId) {
        this.devisId = devisId;
    }

    public Long getEtatFactureId() {
        return etatFactureId;
    }

    public void setEtatFactureId(Long etatFactureId) {
        this.etatFactureId = etatFactureId;
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
            ", type='" + getType() + "'" +
            ", message='" + getMessage() + "'" +
            ", date='" + getDate() + "'" +
            ", dateEcheance='" + getDateEcheance() + "'" +
            ", moyenDePaiement='" + getMoyenDePaiement() + "'" +
            ", devisId=" + getDevisId() +
            ", etatFactureId=" + getEtatFactureId() +
            ", adresseId=" + getAdresseId() +
            ", societeId=" + getSocieteId() +
            ", operationId=" + getOperationId() +
            ", clientFournisseurId=" + getClientFournisseurId() +
            "}";
    }
}
