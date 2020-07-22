package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Article.
 */
@Entity
@Table(name = "article")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "reference", nullable = false)
    private String reference;

    @NotNull
    @Column(name = "titre", nullable = false)
    private String titre;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "article")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Clause> listeClauses = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "article_liste_avenants",
               joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "liste_avenants_id", referencedColumnName = "id"))
    private Set<Avenant> listeAvenants = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "article_liste_contrats",
               joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "liste_contrats_id", referencedColumnName = "id"))
    private Set<Contrat> listeContrats = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "listeArticles", allowSetters = true)
    private Societe societe;

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

    public Article reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTitre() {
        return titre;
    }

    public Article titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public Article description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Clause> getListeClauses() {
        return listeClauses;
    }

    public Article listeClauses(Set<Clause> clauses) {
        this.listeClauses = clauses;
        return this;
    }

    public Article addListeClauses(Clause clause) {
        this.listeClauses.add(clause);
        clause.setArticle(this);
        return this;
    }

    public Article removeListeClauses(Clause clause) {
        this.listeClauses.remove(clause);
        clause.setArticle(null);
        return this;
    }

    public void setListeClauses(Set<Clause> clauses) {
        this.listeClauses = clauses;
    }

    public Set<Avenant> getListeAvenants() {
        return listeAvenants;
    }

    public Article listeAvenants(Set<Avenant> avenants) {
        this.listeAvenants = avenants;
        return this;
    }

    public Article addListeAvenants(Avenant avenant) {
        this.listeAvenants.add(avenant);
        avenant.getListeArticles().add(this);
        return this;
    }

    public Article removeListeAvenants(Avenant avenant) {
        this.listeAvenants.remove(avenant);
        avenant.getListeArticles().remove(this);
        return this;
    }

    public void setListeAvenants(Set<Avenant> avenants) {
        this.listeAvenants = avenants;
    }

    public Set<Contrat> getListeContrats() {
        return listeContrats;
    }

    public Article listeContrats(Set<Contrat> contrats) {
        this.listeContrats = contrats;
        return this;
    }

    public Article addListeContrats(Contrat contrat) {
        this.listeContrats.add(contrat);
        contrat.getListeArticles().add(this);
        return this;
    }

    public Article removeListeContrats(Contrat contrat) {
        this.listeContrats.remove(contrat);
        contrat.getListeArticles().remove(this);
        return this;
    }

    public void setListeContrats(Set<Contrat> contrats) {
        this.listeContrats = contrats;
    }

    public Societe getSociete() {
        return societe;
    }

    public Article societe(Societe societe) {
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
        if (!(o instanceof Article)) {
            return false;
        }
        return id != null && id.equals(((Article) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Article{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", titre='" + getTitre() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
