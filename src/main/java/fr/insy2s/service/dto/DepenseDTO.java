package fr.insy2s.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A DTO for the {@link fr.insy2s.domain.Depense} entity.
 */
public class DepenseDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long numero;

    private LocalDate date;

    private BigDecimal prix;

    private String moyenDePaiement;

    private String raison;


    private Long societeId;

    private Long operationId;

    private Long clientFournisseurId;

    private Long etatDepenseId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public String getMoyenDePaiement() {
        return moyenDePaiement;
    }

    public void setMoyenDePaiement(String moyenDePaiement) {
        this.moyenDePaiement = moyenDePaiement;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
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

    public Long getEtatDepenseId() {
        return etatDepenseId;
    }

    public void setEtatDepenseId(Long etatDepenseId) {
        this.etatDepenseId = etatDepenseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepenseDTO)) {
            return false;
        }

        return id != null && id.equals(((DepenseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepenseDTO{" +
            "id=" + getId() +
            ", numero=" + getNumero() +
            ", date='" + getDate() + "'" +
            ", prix=" + getPrix() +
            ", moyenDePaiement='" + getMoyenDePaiement() + "'" +
            ", raison='" + getRaison() + "'" +
            ", societeId=" + getSocieteId() +
            ", operationId=" + getOperationId() +
            ", clientFournisseurId=" + getClientFournisseurId() +
            ", etatDepenseId=" + getEtatDepenseId() +
            "}";
    }
}
