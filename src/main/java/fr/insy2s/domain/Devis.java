package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Devis.
 */
@Entity
@Table(name = "devis")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Devis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "num_devis")
    private Long numDevis;

    @Column(name = "nom")
    private String nom;

    @Column(name = "message")
    private String message;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "date_limite")
    private LocalDate dateLimite;

    @Column(name = "prix_ht")
    private Double prixHT;

    @Column(name = "prix_ttc")
    private Double prixTTC;

    @Column(name = "tva")
    private Float tva;

    @Column(name = "chemin_fichier")
    private String cheminFichier;

    @OneToOne
    @JoinColumn(unique = true)
    private EtatDevis etatDevis;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDevis", allowSetters = true)
    private Societe societe;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDevis", allowSetters = true)
    private ClientFournisseur clientFournisseur;

    @ManyToMany(mappedBy = "listeDevis")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Produit> listeProduits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumDevis() {
        return numDevis;
    }

    public Devis numDevis(Long numDevis) {
        this.numDevis = numDevis;
        return this;
    }

    public void setNumDevis(Long numDevis) {
        this.numDevis = numDevis;
    }

    public String getNom() {
        return nom;
    }

    public Devis nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMessage() {
        return message;
    }

    public Devis message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Devis dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateLimite() {
        return dateLimite;
    }

    public Devis dateLimite(LocalDate dateLimite) {
        this.dateLimite = dateLimite;
        return this;
    }

    public void setDateLimite(LocalDate dateLimite) {
        this.dateLimite = dateLimite;
    }

    public Double getPrixHT() {
        return prixHT;
    }

    public Devis prixHT(Double prixHT) {
        this.prixHT = prixHT;
        return this;
    }

    public void setPrixHT(Double prixHT) {
        this.prixHT = prixHT;
    }

    public Double getPrixTTC() {
        return prixTTC;
    }

    public Devis prixTTC(Double prixTTC) {
        this.prixTTC = prixTTC;
        return this;
    }

    public void setPrixTTC(Double prixTTC) {
        this.prixTTC = prixTTC;
    }

    public Float getTva() {
        return tva;
    }

    public Devis tva(Float tva) {
        this.tva = tva;
        return this;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public Devis cheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
        return this;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public EtatDevis getEtatDevis() {
        return etatDevis;
    }

    public Devis etatDevis(EtatDevis etatDevis) {
        this.etatDevis = etatDevis;
        return this;
    }

    public void setEtatDevis(EtatDevis etatDevis) {
        this.etatDevis = etatDevis;
    }

    public Societe getSociete() {
        return societe;
    }

    public Devis societe(Societe societe) {
        this.societe = societe;
        return this;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public ClientFournisseur getClientFournisseur() {
        return clientFournisseur;
    }

    public Devis clientFournisseur(ClientFournisseur clientFournisseur) {
        this.clientFournisseur = clientFournisseur;
        return this;
    }

    public void setClientFournisseur(ClientFournisseur clientFournisseur) {
        this.clientFournisseur = clientFournisseur;
    }

    public Set<Produit> getListeProduits() {
        return listeProduits;
    }

    public Devis listeProduits(Set<Produit> produits) {
        this.listeProduits = produits;
        return this;
    }

    public Devis addListeProduits(Produit produit) {
        this.listeProduits.add(produit);
        produit.getListeDevis().add(this);
        return this;
    }

    public Devis removeListeProduits(Produit produit) {
        this.listeProduits.remove(produit);
        produit.getListeDevis().remove(this);
        return this;
    }

    public void setListeProduits(Set<Produit> produits) {
        this.listeProduits = produits;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Devis)) {
            return false;
        }
        return id != null && id.equals(((Devis) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Devis{" +
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
            "}";
    }
}
