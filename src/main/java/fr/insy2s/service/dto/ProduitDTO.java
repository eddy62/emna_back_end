package fr.insy2s.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link fr.insy2s.domain.Produit} entity.
 */
public class ProduitDTO implements Serializable {
    
    private Long id;

    private String nom;

    private Integer reference;

    private Float tva;

    private Double prix;

    private String unite;

    private String quantite;

    private String description;

    private Set<FactureDTO> listeFactures = new HashSet<>();
    private Set<DevisDTO> listeDevis = new HashSet<>();

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

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public Float getTva() {
        return tva;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<FactureDTO> getListeFactures() {
        return listeFactures;
    }

    public void setListeFactures(Set<FactureDTO> factures) {
        this.listeFactures = factures;
    }

    public Set<DevisDTO> getListeDevis() {
        return listeDevis;
    }

    public void setListeDevis(Set<DevisDTO> devis) {
        this.listeDevis = devis;
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
        if (!(o instanceof ProduitDTO)) {
            return false;
        }

        return id != null && id.equals(((ProduitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProduitDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", reference=" + getReference() +
            ", tva=" + getTva() +
            ", prix=" + getPrix() +
            ", unite='" + getUnite() + "'" +
            ", quantite='" + getQuantite() + "'" +
            ", description='" + getDescription() + "'" +
            ", listeFactures='" + getListeFactures() + "'" +
            ", listeDevis='" + getListeDevis() + "'" +
            ", societeId=" + getSocieteId() +
            "}";
    }
}
