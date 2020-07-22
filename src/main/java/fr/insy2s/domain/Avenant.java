package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
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

    @ManyToOne
    @JsonIgnoreProperties(value = "listeAvenants", allowSetters = true)
    private Contrat contrat;

    @ManyToMany(mappedBy = "listeAvenants")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Article> listeArticles = new HashSet<>();

    @ManyToMany(mappedBy = "listeAvenants")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Clause> listeClauses = new HashSet<>();

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

    public Contrat getContrat() {
        return contrat;
    }

    public Avenant contrat(Contrat contrat) {
        this.contrat = contrat;
        return this;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Set<Article> getListeArticles() {
        return listeArticles;
    }

    public Avenant listeArticles(Set<Article> articles) {
        this.listeArticles = articles;
        return this;
    }

    public Avenant addListeArticles(Article article) {
        this.listeArticles.add(article);
        article.getListeAvenants().add(this);
        return this;
    }

    public Avenant removeListeArticles(Article article) {
        this.listeArticles.remove(article);
        article.getListeAvenants().remove(this);
        return this;
    }

    public void setListeArticles(Set<Article> articles) {
        this.listeArticles = articles;
    }

    public Set<Clause> getListeClauses() {
        return listeClauses;
    }

    public Avenant listeClauses(Set<Clause> clauses) {
        this.listeClauses = clauses;
        return this;
    }

    public Avenant addListeClauses(Clause clause) {
        this.listeClauses.add(clause);
        clause.getListeAvenants().add(this);
        return this;
    }

    public Avenant removeListeClauses(Clause clause) {
        this.listeClauses.remove(clause);
        clause.getListeAvenants().remove(this);
        return this;
    }

    public void setListeClauses(Set<Clause> clauses) {
        this.listeClauses = clauses;
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
            "}";
    }
}
