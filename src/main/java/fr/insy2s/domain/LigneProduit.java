package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A LigneProduit.
 */
@Entity
@Table(name = "ligne_produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LigneProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "quantite", nullable = false)
    private Integer quantite;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "tva")
    private Float tva;

    @NotNull
    @Column(name = "prix", nullable = false)
    private Float prix;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeLigneProduits", allowSetters = true)
    private Facture facture;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeLigneProduits", allowSetters = true)
    private Devis devis;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public LigneProduit quantite(Integer quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public LigneProduit nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public LigneProduit description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getTva() {
        return tva;
    }

    public LigneProduit tva(Float tva) {
        this.tva = tva;
        return this;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }

    public Float getPrix() {
        return prix;
    }

    public LigneProduit prix(Float prix) {
        this.prix = prix;
        return this;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Facture getFacture() {
        return facture;
    }

    public LigneProduit facture(Facture facture) {
        this.facture = facture;
        return this;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Devis getDevis() {
        return devis;
    }

    public LigneProduit devis(Devis devis) {
        this.devis = devis;
        return this;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LigneProduit)) {
            return false;
        }
        return id != null && id.equals(((LigneProduit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LigneProduit{" +
            "id=" + getId() +
            ", quantite=" + getQuantite() +
            ", nom='" + getNom() + "'" +
            ", description='" + getDescription() + "'" +
            ", tva=" + getTva() +
            ", prix=" + getPrix() +
            "}";
    }
}
