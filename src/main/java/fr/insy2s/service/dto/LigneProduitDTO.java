package fr.insy2s.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.LigneProduit} entity.
 */
public class LigneProduitDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer quantite;

    private String nom;

    private String description;

    private Float tva;

    @NotNull
    private Float prix;


    private Long factureId;

    private Long devisId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getTva() {
        return tva;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Long getFactureId() {
        return factureId;
    }

    public void setFactureId(Long factureId) {
        this.factureId = factureId;
    }

    public Long getDevisId() {
        return devisId;
    }

    public void setDevisId(Long devisId) {
        this.devisId = devisId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LigneProduitDTO)) {
            return false;
        }

        return id != null && id.equals(((LigneProduitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LigneProduitDTO{" +
            "id=" + getId() +
            ", quantite=" + getQuantite() +
            ", nom='" + getNom() + "'" +
            ", description='" + getDescription() + "'" +
            ", tva=" + getTva() +
            ", prix=" + getPrix() +
            ", factureId=" + getFactureId() +
            ", devisId=" + getDevisId() +
            "}";
    }
}
