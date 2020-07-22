package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Produit.
 */
@Entity
@Table(name = "produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "reference")
    private Integer reference;

    @Column(name = "tva")
    private Float tva;

    @Column(name = "prix")
    private Double prix;

    @Column(name = "unite")
    private String unite;

    @Column(name = "quantite")
    private String quantite;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "produit_liste_factures",
               joinColumns = @JoinColumn(name = "produit_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "liste_factures_id", referencedColumnName = "id"))
    private Set<Facture> listeFactures = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "produit_liste_devis",
               joinColumns = @JoinColumn(name = "produit_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "liste_devis_id", referencedColumnName = "id"))
    private Set<Devis> listeDevis = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "listeProduits", allowSetters = true)
    private Societe societe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Produit nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getReference() {
        return reference;
    }

    public Produit reference(Integer reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public Float getTva() {
        return tva;
    }

    public Produit tva(Float tva) {
        this.tva = tva;
        return this;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }

    public Double getPrix() {
        return prix;
    }

    public Produit prix(Double prix) {
        this.prix = prix;
        return this;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getUnite() {
        return unite;
    }

    public Produit unite(String unite) {
        this.unite = unite;
        return this;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getQuantite() {
        return quantite;
    }

    public Produit quantite(String quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public Produit description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Facture> getListeFactures() {
        return listeFactures;
    }

    public Produit listeFactures(Set<Facture> factures) {
        this.listeFactures = factures;
        return this;
    }

    public Produit addListeFactures(Facture facture) {
        this.listeFactures.add(facture);
        facture.getListeProduits().add(this);
        return this;
    }

    public Produit removeListeFactures(Facture facture) {
        this.listeFactures.remove(facture);
        facture.getListeProduits().remove(this);
        return this;
    }

    public void setListeFactures(Set<Facture> factures) {
        this.listeFactures = factures;
    }

    public Set<Devis> getListeDevis() {
        return listeDevis;
    }

    public Produit listeDevis(Set<Devis> devis) {
        this.listeDevis = devis;
        return this;
    }

    public Produit addListeDevis(Devis devis) {
        this.listeDevis.add(devis);
        devis.getListeProduits().add(this);
        return this;
    }

    public Produit removeListeDevis(Devis devis) {
        this.listeDevis.remove(devis);
        devis.getListeProduits().remove(this);
        return this;
    }

    public void setListeDevis(Set<Devis> devis) {
        this.listeDevis = devis;
    }

    public Societe getSociete() {
        return societe;
    }

    public Produit societe(Societe societe) {
        this.societe = societe;
        return this;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return id != null && id.equals(((Produit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", reference=" + getReference() +
            ", tva=" + getTva() +
            ", prix=" + getPrix() +
            ", unite='" + getUnite() + "'" +
            ", quantite='" + getQuantite() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
