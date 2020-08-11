package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Contrat.
 */
@Entity
@Table(name = "contrat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Contrat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "titre", nullable = false)
    private String titre;

    @NotNull
    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    @NotNull
    @Column(name = "signe", nullable = false)
    private Boolean signe;

    @Column(name = "archive")
    private Boolean archive;

    @OneToMany(mappedBy = "contrat")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Avenant> listeAvenants = new HashSet<>();

    @OneToMany(mappedBy = "contrat")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Document> listeDocuments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("listeContrats")
    private Employe employe;

    @ManyToOne
    @JsonIgnoreProperties("listeContrats")
    private Societe societe;

    @ManyToMany(mappedBy = "listeContrats")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Article> listeArticles = new HashSet<>();

    @ManyToMany(mappedBy = "listeContrats")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Clause> listeClauses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public Contrat titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Contrat dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean isSigne() {
        return signe;
    }

    public Contrat signe(Boolean signe) {
        this.signe = signe;
        return this;
    }

    public void setSigne(Boolean signe) {
        this.signe = signe;
    }

    public Boolean isArchive() {
        return archive;
    }

    public Contrat archive(Boolean archive) {
        this.archive = archive;
        return this;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public Set<Avenant> getListeAvenants() {
        return listeAvenants;
    }

    public Contrat listeAvenants(Set<Avenant> avenants) {
        this.listeAvenants = avenants;
        return this;
    }

    public Contrat addListeAvenants(Avenant avenant) {
        this.listeAvenants.add(avenant);
        avenant.setContrat(this);
        return this;
    }

    public Contrat removeListeAvenants(Avenant avenant) {
        this.listeAvenants.remove(avenant);
        avenant.setContrat(null);
        return this;
    }

    public void setListeAvenants(Set<Avenant> avenants) {
        this.listeAvenants = avenants;
    }

    public Set<Document> getListeDocuments() {
        return listeDocuments;
    }

    public Contrat listeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
        return this;
    }

    public Contrat addListeDocuments(Document document) {
        this.listeDocuments.add(document);
        document.setContrat(this);
        return this;
    }

    public Contrat removeListeDocuments(Document document) {
        this.listeDocuments.remove(document);
        document.setContrat(null);
        return this;
    }

    public void setListeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Contrat employe(Employe employe) {
        this.employe = employe;
        return this;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Societe getSociete() {
        return societe;
    }

    public Contrat societe(Societe societe) {
        this.societe = societe;
        return this;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Set<Article> getListeArticles() {
        return listeArticles;
    }

    public Contrat listeArticles(Set<Article> articles) {
        this.listeArticles = articles;
        return this;
    }

    public Contrat addListeArticles(Article article) {
        this.listeArticles.add(article);
        article.getListeContrats().add(this);
        return this;
    }

    public Contrat removeListeArticles(Article article) {
        this.listeArticles.remove(article);
        article.getListeContrats().remove(this);
        return this;
    }

    public void setListeArticles(Set<Article> articles) {
        this.listeArticles = articles;
    }

    public Set<Clause> getListeClauses() {
        return listeClauses;
    }

    public Contrat listeClauses(Set<Clause> clauses) {
        this.listeClauses = clauses;
        return this;
    }

    public Contrat addListeClauses(Clause clause) {
        this.listeClauses.add(clause);
        clause.getListeContrats().add(this);
        return this;
    }

    public Contrat removeListeClauses(Clause clause) {
        this.listeClauses.remove(clause);
        clause.getListeContrats().remove(this);
        return this;
    }

    public void setListeClauses(Set<Clause> clauses) {
        this.listeClauses = clauses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contrat)) {
            return false;
        }
        return id != null && id.equals(((Contrat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Contrat{" +
            "id=" + getId() +
            ", titre='" + getTitre() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", signe='" + isSigne() + "'" +
            ", archive='" + isArchive() + "'" +
            "}";
    }
}
