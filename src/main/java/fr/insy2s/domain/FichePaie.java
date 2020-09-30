package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A FichePaie.
 */
@Entity
@Table(name = "fiche_paie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FichePaie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "debut_periode", nullable = false)
    private LocalDate debutPeriode;

    @NotNull
    @Column(name = "fin_periode", nullable = false)
    private LocalDate finPeriode;

    @NotNull
    @Column(name = "mois", nullable = false)
    private Integer mois;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @OneToMany(mappedBy = "fichePaie")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> listeDocuments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "listeFichePaies", allowSetters = true)
    private Employe employe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDebutPeriode() {
        return debutPeriode;
    }

    public FichePaie debutPeriode(LocalDate debutPeriode) {
        this.debutPeriode = debutPeriode;
        return this;
    }

    public void setDebutPeriode(LocalDate debutPeriode) {
        this.debutPeriode = debutPeriode;
    }

    public LocalDate getFinPeriode() {
        return finPeriode;
    }

    public FichePaie finPeriode(LocalDate finPeriode) {
        this.finPeriode = finPeriode;
        return this;
    }

    public void setFinPeriode(LocalDate finPeriode) {
        this.finPeriode = finPeriode;
    }

    public Integer getMois() {
        return mois;
    }

    public FichePaie mois(Integer mois) {
        this.mois = mois;
        return this;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public Integer getAnnee() {
        return annee;
    }

    public FichePaie annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Set<Document> getListeDocuments() {
        return listeDocuments;
    }

    public FichePaie listeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
        return this;
    }

    public FichePaie addListeDocuments(Document document) {
        this.listeDocuments.add(document);
        document.setFichePaie(this);
        return this;
    }

    public FichePaie removeListeDocuments(Document document) {
        this.listeDocuments.remove(document);
        document.setFichePaie(null);
        return this;
    }

    public void setListeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
    }

    public Employe getEmploye() {
        return employe;
    }

    public FichePaie employe(Employe employe) {
        this.employe = employe;
        return this;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FichePaie)) {
            return false;
        }
        return id != null && id.equals(((FichePaie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FichePaie{" +
            "id=" + getId() +
            ", debutPeriode='" + getDebutPeriode() + "'" +
            ", finPeriode='" + getFinPeriode() + "'" +
            ", mois=" + getMois() +
            ", annee=" + getAnnee() +
            "}";
    }
}
