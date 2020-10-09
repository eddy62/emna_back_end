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

    @OneToMany(mappedBy = "devis")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<LigneProduit> listeLigneProduits = new HashSet<>();

    @OneToMany(mappedBy = "devis")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> listeDocuments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "devis", allowSetters = true)
    private EtatDevis etatDevis;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDevis", allowSetters = true)
    private ClientFournisseur clientFournisseur;

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

    public Set<LigneProduit> getListeLigneProduits() {
        return listeLigneProduits;
    }

    public Devis listeLigneProduits(Set<LigneProduit> ligneProduits) {
        this.listeLigneProduits = ligneProduits;
        return this;
    }

    public Devis addListeLigneProduit(LigneProduit ligneProduit) {
        this.listeLigneProduits.add(ligneProduit);
        ligneProduit.setDevis(this);
        return this;
    }

    public Devis removeListeLigneProduit(LigneProduit ligneProduit) {
        this.listeLigneProduits.remove(ligneProduit);
        ligneProduit.setDevis(null);
        return this;
    }

    public void setListeLigneProduits(Set<LigneProduit> ligneProduits) {
        this.listeLigneProduits = ligneProduits;
    }

    public Set<Document> getListeDocuments() {
        return listeDocuments;
    }

    public Devis listeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
        return this;
    }

    public Devis addListeDocuments(Document document) {
        this.listeDocuments.add(document);
        document.setDevis(this);
        return this;
    }

    public Devis removeListeDocuments(Document document) {
        this.listeDocuments.remove(document);
        document.setDevis(null);
        return this;
    }

    public void setListeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
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

    @Override
    public String toString() {
        return "Devis{" +
            "id=" + id +
            ", numDevis=" + numDevis +
            ", nom='" + nom + '\'' +
            ", message='" + message + '\'' +
            ", dateCreation=" + dateCreation +
            ", dateLimite=" + dateLimite +
            ", listeLigneProduits=" + listeLigneProduits +
            ", listeDocuments=" + listeDocuments +
            ", etatDevis=" + etatDevis +
            ", clientFournisseur=" + clientFournisseur +
            '}';
    }
}
