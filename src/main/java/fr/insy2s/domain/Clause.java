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
 * A Clause.
 */
@Entity
@Table(name = "clause")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Clause implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "reference", nullable = false)
    private String reference;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(cascade=CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "clause_liste_contrats",
               joinColumns = @JoinColumn(name = "clause_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "liste_contrats_id", referencedColumnName = "id"))
    private Set<Contrat> listeContrats = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "clause_liste_avenants",
               joinColumns = @JoinColumn(name = "clause_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "liste_avenants_id", referencedColumnName = "id"))
    private Set<Avenant> listeAvenants = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "listeClauses", allowSetters = true)
    private Societe societe;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeClauses", allowSetters = true)
    private Article article;

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

    public Clause reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public Clause description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Contrat> getListeContrats() {
        return listeContrats;
    }

    public Clause listeContrats(Set<Contrat> contrats) {
        this.listeContrats = contrats;
        return this;
    }

    public Clause addListeContrats(Contrat contrat) {
        this.listeContrats.add(contrat);
        contrat.getListeClauses().add(this);
        return this;
    }

    public Clause removeListeContrats(Contrat contrat) {
        this.listeContrats.remove(contrat);
        contrat.getListeClauses().remove(this);
        return this;
    }

    public void setListeContrats(Set<Contrat> contrats) {
        this.listeContrats = contrats;
    }

    public Set<Avenant> getListeAvenants() {
        return listeAvenants;
    }

    public Clause listeAvenants(Set<Avenant> avenants) {
        this.listeAvenants = avenants;
        return this;
    }

    public Clause addListeAvenants(Avenant avenant) {
        this.listeAvenants.add(avenant);
        avenant.getListeClauses().add(this);
        return this;
    }

    public Clause removeListeAvenants(Avenant avenant) {
        this.listeAvenants.remove(avenant);
        avenant.getListeClauses().remove(this);
        return this;
    }

    public void setListeAvenants(Set<Avenant> avenants) {
        this.listeAvenants = avenants;
    }

    public Societe getSociete() {
        return societe;
    }

    public Clause societe(Societe societe) {
        this.societe = societe;
        return this;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Article getArticle() {
        return article;
    }

    public Clause article(Article article) {
        this.article = article;
        return this;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Clause)) {
            return false;
        }
        return id != null && id.equals(((Clause) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Clause{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
