package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Depense.
 */
@Entity
@Table(name = "depense")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Depense implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "numero", nullable = false)
    private Long numero;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "prix", precision = 21, scale = 2)
    private BigDecimal prix;

    @Column(name = "moyen_de_paiement")
    private String moyenDePaiement;

    @Column(name = "raison")
    private String raison;

    @OneToMany(mappedBy = "depense")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> listeDocuments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "depenses", allowSetters = true)
    private Societe societe;

    @ManyToOne
    @JsonIgnoreProperties(value = "depenses", allowSetters = true)
    private Operation operation;

    @ManyToOne
    @JsonIgnoreProperties(value = "depenses", allowSetters = true)
    private ClientFournisseur clientFournisseur;

    @ManyToOne
    @JsonIgnoreProperties(value = "depenses", allowSetters = true)
    private EtatDepense etatDepense;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public Depense numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getDate() {
        return date;
    }

    public Depense date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public Depense prix(BigDecimal prix) {
        this.prix = prix;
        return this;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public String getMoyenDePaiement() {
        return moyenDePaiement;
    }

    public Depense moyenDePaiement(String moyenDePaiement) {
        this.moyenDePaiement = moyenDePaiement;
        return this;
    }

    public void setMoyenDePaiement(String moyenDePaiement) {
        this.moyenDePaiement = moyenDePaiement;
    }

    public String getRaison() {
        return raison;
    }

    public Depense raison(String raison) {
        this.raison = raison;
        return this;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public Set<Document> getListeDocuments() {
        return listeDocuments;
    }

    public Depense listeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
        return this;
    }

    public Depense addListeDocuments(Document document) {
        this.listeDocuments.add(document);
        document.setDepense(this);
        return this;
    }

    public Depense removeListeDocuments(Document document) {
        this.listeDocuments.remove(document);
        document.setDepense(null);
        return this;
    }

    public void setListeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
    }

    public Societe getSociete() {
        return societe;
    }

    public Depense societe(Societe societe) {
        this.societe = societe;
        return this;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Operation getOperation() {
        return operation;
    }

    public Depense operation(Operation operation) {
        this.operation = operation;
        return this;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public ClientFournisseur getClientFournisseur() {
        return clientFournisseur;
    }

    public Depense clientFournisseur(ClientFournisseur clientFournisseur) {
        this.clientFournisseur = clientFournisseur;
        return this;
    }

    public void setClientFournisseur(ClientFournisseur clientFournisseur) {
        this.clientFournisseur = clientFournisseur;
    }

    public EtatDepense getEtatDepense() {
        return etatDepense;
    }

    public Depense etatDepense(EtatDepense etatDepense) {
        this.etatDepense = etatDepense;
        return this;
    }

    public void setEtatDepense(EtatDepense etatDepense) {
        this.etatDepense = etatDepense;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Depense)) {
            return false;
        }
        return id != null && id.equals(((Depense) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Depense{" +
            "id=" + getId() +
            ", numero=" + getNumero() +
            ", date='" + getDate() + "'" +
            ", prix=" + getPrix() +
            ", moyenDePaiement='" + getMoyenDePaiement() + "'" +
            ", raison='" + getRaison() + "'" +
            "}";
    }
}
