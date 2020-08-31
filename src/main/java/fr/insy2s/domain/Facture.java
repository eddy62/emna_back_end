package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Facture.
 */
@Entity
@Table(name = "facture")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

    @Column(name = "type")
    private String type;

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

    @Column(name = "moyen_de_paiement")
    private String moyenDePaiement;

    @OneToMany(mappedBy = "facture")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> listeDocuments = new HashSet<>();

    @OneToMany(mappedBy = "facture")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<LigneProduit> listeLigneProduits = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "factures", allowSetters = true)
    private EtatFacture etatFacture;

    @ManyToOne
    @JsonIgnoreProperties(value = "factures", allowSetters = true)
    private Adresse adresse;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeFactures", allowSetters = true)
    private Societe societe;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeFactures", allowSetters = true)
    private Operation operation;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeFactures", allowSetters = true)
    private ClientFournisseur clientFournisseur;

    // jhipster-needle-entity-add-field - JHipster will add fields here
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

    public Set<Document> getListeDocuments() {
        return listeDocuments;
    }

    public Facture listeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
        return this;
    }

    public Facture addListeDocuments(Document document) {
        this.listeDocuments.add(document);
        document.setFacture(this);
        return this;
    }

    public Facture removeListeDocuments(Document document) {
        this.listeDocuments.remove(document);
        document.setFacture(null);
        return this;
    }

    public void setListeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
    }

    public Set<LigneProduit> getListeLigneProduits() {
        return listeLigneProduits;
    }

    public Facture listeLigneProduits(Set<LigneProduit> ligneProduits) {
        this.listeLigneProduits = ligneProduits;
        return this;
    }

    public Facture addListeLigneProduit(LigneProduit ligneProduit) {
        this.listeLigneProduits.add(ligneProduit);
        ligneProduit.setFacture(this);
        return this;
    }

    public Facture removeListeLigneProduit(LigneProduit ligneProduit) {
        this.listeLigneProduits.remove(ligneProduit);
        ligneProduit.setFacture(null);
        return this;
    }

    public void setListeLigneProduits(Set<LigneProduit> ligneProduits) {
        this.listeLigneProduits = ligneProduits;
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
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

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

    // prettier-ignore
    @Override
    public String toString() {
        return "Facture{" +
            "id=" + getId() +
            ", numfact=" + getNumfact() +
            ", nom='" + getNom() + "'" +
            ", type='" + getType() + "'" +
            ", message='" + getMessage() + "'" +
            ", date='" + getDate() + "'" +
            ", dateEcheance='" + getDateEcheance() + "'" +
            ", prixHT=" + getPrixHT() +
            ", prixTTC=" + getPrixTTC() +
            ", tva=" + getTva() +
            ", moyenDePaiement='" + getMoyenDePaiement() + "'" +
            "}";
    }
}
