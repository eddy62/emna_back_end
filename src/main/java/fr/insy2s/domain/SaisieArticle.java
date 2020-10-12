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
 * A SaisieArticle.
 */
@Entity
@Table(name = "saisie_article")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SaisieArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "saisieArticle")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Avenant> listeAvenants = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "saisieArticles", allowSetters = true)
    private Article article;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "saisieArticles", allowSetters = true)
    private Contrat contrat;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public SaisieArticle libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Avenant> getListeAvenants() {
        return listeAvenants;
    }

    public SaisieArticle listeAvenants(Set<Avenant> avenants) {
        this.listeAvenants = avenants;
        return this;
    }

    public SaisieArticle addListeAvenant(Avenant avenant) {
        this.listeAvenants.add(avenant);
        avenant.setSaisieArticle(this);
        return this;
    }

    public SaisieArticle removeListeAvenant(Avenant avenant) {
        this.listeAvenants.remove(avenant);
        avenant.setSaisieArticle(null);
        return this;
    }

    public void setListeAvenants(Set<Avenant> avenants) {
        this.listeAvenants = avenants;
    }

    public Article getArticle() {
        return article;
    }

    public SaisieArticle article(Article article) {
        this.article = article;
        return this;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public SaisieArticle contrat(Contrat contrat) {
        this.contrat = contrat;
        return this;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SaisieArticle)) {
            return false;
        }
        return id != null && id.equals(((SaisieArticle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SaisieArticle{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
