package fr.insy2s.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Avenant.
 */
@Entity
@Table(name = "avenant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Avenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "reference", nullable = false)
    private String reference;

    @NotNull
    @Column(name = "signe", nullable = false)
    private Boolean signe;

    @NotNull
    @Column(name = "date_de_creation", nullable = false)
    private LocalDate dateDeCreation;

    @Column(name = "date_de_signature")
    private LocalDate dateDeSignature;

    @OneToMany(mappedBy = "avenant")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<SaisieArticle> listeSaisieArticles = new HashSet<>();

    @OneToMany(mappedBy = "avenant")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> listeDocuments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public Avenant reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Boolean isSigne() {
        return signe;
    }

    public Avenant signe(Boolean signe) {
        this.signe = signe;
        return this;
    }

    public void setSigne(Boolean signe) {
        this.signe = signe;
    }

    public LocalDate getDateDeCreation() {
        return dateDeCreation;
    }

    public Avenant dateDeCreation(LocalDate dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
        return this;
    }

    public void setDateDeCreation(LocalDate dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public LocalDate getDateDeSignature() {
        return dateDeSignature;
    }

    public Avenant dateDeSignature(LocalDate dateDeSignature) {
        this.dateDeSignature = dateDeSignature;
        return this;
    }

    public void setDateDeSignature(LocalDate dateDeSignature) {
        this.dateDeSignature = dateDeSignature;
    }

    public Set<SaisieArticle> getListeSaisieArticles() {
        return listeSaisieArticles;
    }

    public Avenant listeSaisieArticles(Set<SaisieArticle> saisieArticles) {
        this.listeSaisieArticles = saisieArticles;
        return this;
    }

    public Avenant addListeSaisieArticle(SaisieArticle saisieArticle) {
        this.listeSaisieArticles.add(saisieArticle);
        saisieArticle.setAvenant(this);
        return this;
    }

    public Avenant removeListeSaisieArticle(SaisieArticle saisieArticle) {
        this.listeSaisieArticles.remove(saisieArticle);
        saisieArticle.setAvenant(null);
        return this;
    }

    public void setListeSaisieArticles(Set<SaisieArticle> saisieArticles) {
        this.listeSaisieArticles = saisieArticles;
    }

    public Set<Document> getListeDocuments() {
        return listeDocuments;
    }

    public Avenant listeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
        return this;
    }

    public Avenant addListeDocuments(Document document) {
        this.listeDocuments.add(document);
        document.setAvenant(this);
        return this;
    }

    public Avenant removeListeDocuments(Document document) {
        this.listeDocuments.remove(document);
        document.setAvenant(null);
        return this;
    }

    public void setListeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Avenant)) {
            return false;
        }
        return id != null && id.equals(((Avenant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Avenant{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", signe='" + isSigne() + "'" +
            ", dateDeCreation='" + getDateDeCreation() + "'" +
            ", dateDeSignature='" + getDateDeSignature() + "'" +
            "}";
    }
}
