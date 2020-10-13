package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

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

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "saisieArticles", allowSetters = true)
    private Article article;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "saisieArticles", allowSetters = true)
    private Contrat contrat;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeSaisieArticles", allowSetters = true)
    private Avenant avenant;

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

    public Avenant getAvenant() {
        return avenant;
    }

    public SaisieArticle avenant(Avenant avenant) {
        this.avenant = avenant;
        return this;
    }

    public void setAvenant(Avenant avenant) {
        this.avenant = avenant;
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
