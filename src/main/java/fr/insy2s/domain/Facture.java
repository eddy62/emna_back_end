package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Facture.
 */
@Entity
@Table(name = "facture")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "numfact")
    private Long numfact;

    @Column(name = "nom")
    private String nom;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "date_echeance")
    private LocalDate dateEcheance;

    @Column(name = "prix_ht")
    private Integer prixHT;

    @Column(name = "prix_ttc")
    private Integer prixTTC;

    @Column(name = "tva")
    private Float tva;

    @Column(name = "fichier")
    private String fichier;

    @Column(name = "chemin_fichier")
    private String cheminFichier;

    @Column(name = "type")
    private String type;

    @Column(name = "moyen_de_paiement")
    private String moyenDePaiement;

    @OneToOne
    @JoinColumn(unique = true)
    private Adresse adresse;

    @ManyToOne
    @JsonIgnoreProperties("factures")
    private EtatFacture etatFacture;

    @ManyToOne
    @JsonIgnoreProperties("listeFactures")
    private Societe societe;

    @ManyToOne
    @JsonIgnoreProperties("listeFactures")
    private Operation operation;

    @ManyToOne
    @JsonIgnoreProperties("listeFactures")
    private ClientFournisseur clientFournisseur;

    @ManyToMany(mappedBy = "listeFactures")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Produit> listeProduits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumfact() {
        return numfact;
    }

    public Facture numfact(Long numfact) {
        this.numfact = numfact;
        return this;
    }

    public void setNumfact(Long numfact) {
        this.numfact = numfact;
    }

    public String getNom() {
        return nom;
    }

    public Facture nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMessage() {
        return message;
    }

    public Facture message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public Facture date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public Facture dateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
        return this;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Integer getPrixHT() {
        return prixHT;
    }

    public Facture prixHT(Integer prixHT) {
        this.prixHT = prixHT;
        return this;
    }

    public void setPrixHT(Integer prixHT) {
        this.prixHT = prixHT;
    }

    public Integer getPrixTTC() {
        return prixTTC;
    }

    public Facture prixTTC(Integer prixTTC) {
        this.prixTTC = prixTTC;
        return this;
    }

    public void setPrixTTC(Integer prixTTC) {
        this.prixTTC = prixTTC;
    }

    public Float getTva() {
        return tva;
    }

    public Facture tva(Float tva) {
        this.tva = tva;
        return this;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }

    public String getFichier() {
        return fichier;
    }

    public Facture fichier(String fichier) {
        this.fichier = fichier;
        return this;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public Facture cheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
        return this;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public String getType() {
        return type;
    }

    public Facture type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoyenDePaiement() {
        return moyenDePaiement;
    }

    public Facture moyenDePaiement(String moyenDePaiement) {
        this.moyenDePaiement = moyenDePaiement;
        return this;
    }

    public void setMoyenDePaiement(String moyenDePaiement) {
        this.moyenDePaiement = moyenDePaiement;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public Facture adresse(Adresse adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public EtatFacture getEtatFacture() {
        return etatFacture;
    }

    public Facture etatFacture(EtatFacture etatFacture) {
        this.etatFacture = etatFacture;
        return this;
    }

    public void setEtatFacture(EtatFacture etatFacture) {
        this.etatFacture = etatFacture;
    }

    public Societe getSociete() {
        return societe;
    }

    public Facture societe(Societe societe) {
        this.societe = societe;
        return this;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Operation getOperation() {
        return operation;
    }

    public Facture operation(Operation operation) {
        this.operation = operation;
        return this;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public ClientFournisseur getClientFournisseur() {
        return clientFournisseur;
    }

    public Facture clientFournisseur(ClientFournisseur clientFournisseur) {
        this.clientFournisseur = clientFournisseur;
        return this;
    }

    public void setClientFournisseur(ClientFournisseur clientFournisseur) {
        this.clientFournisseur = clientFournisseur;
    }

    public Set<Produit> getListeProduits() {
        return listeProduits;
    }

    public Facture listeProduits(Set<Produit> produits) {
        this.listeProduits = produits;
        return this;
    }

    public Facture addListeProduits(Produit produit) {
        this.listeProduits.add(produit);
        produit.getListeFactures().add(this);
        return this;
    }

    public Facture removeListeProduits(Produit produit) {
        this.listeProduits.remove(produit);
        produit.getListeFactures().remove(this);
        return this;
    }

    public void setListeProduits(Set<Produit> produits) {
        this.listeProduits = produits;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Facture)) {
            return false;
        }
        return id != null && id.equals(((Facture) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Facture{" +
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
            "}";
    }
}
