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
 * A Absence.
 */
@Entity
@Table(name = "absence")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Absence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "debut_absence", nullable = false)
    private LocalDate debutAbsence;

    @NotNull
    @Column(name = "fin_absence", nullable = false)
    private LocalDate finAbsence;

    @NotNull
    @Column(name = "mois", nullable = false)
    private Integer mois;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @OneToMany(mappedBy = "absence")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> listeDocuments = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "absences", allowSetters = true)
    private TypeAbsence typeAbsence;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "absences", allowSetters = true)
    private EtatVariablePaie etatVariablePaie;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeAbsences", allowSetters = true)
    private Employe employe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDebutAbsence() {
        return debutAbsence;
    }

    public Absence debutAbsence(LocalDate debutAbsence) {
        this.debutAbsence = debutAbsence;
        return this;
    }

    public void setDebutAbsence(LocalDate debutAbsence) {
        this.debutAbsence = debutAbsence;
    }

    public LocalDate getFinAbsence() {
        return finAbsence;
    }

    public Absence finAbsence(LocalDate finAbsence) {
        this.finAbsence = finAbsence;
        return this;
    }

    public void setFinAbsence(LocalDate finAbsence) {
        this.finAbsence = finAbsence;
    }

    public Integer getMois() {
        return mois;
    }

    public Absence mois(Integer mois) {
        this.mois = mois;
        return this;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public Integer getAnnee() {
        return annee;
    }

    public Absence annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Set<Document> getListeDocuments() {
        return listeDocuments;
    }

    public Absence listeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
        return this;
    }

    public Absence addListeDocuments(Document document) {
        this.listeDocuments.add(document);
        document.setAbsence(this);
        return this;
    }

    public Absence removeListeDocuments(Document document) {
        this.listeDocuments.remove(document);
        document.setAbsence(null);
        return this;
    }

    public void setListeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
    }

    public TypeAbsence getTypeAbsence() {
        return typeAbsence;
    }

    public Absence typeAbsence(TypeAbsence typeAbsence) {
        this.typeAbsence = typeAbsence;
        return this;
    }

    public void setTypeAbsence(TypeAbsence typeAbsence) {
        this.typeAbsence = typeAbsence;
    }

    public EtatVariablePaie getEtatVariablePaie() {
        return etatVariablePaie;
    }

    public Absence etatVariablePaie(EtatVariablePaie etatVariablePaie) {
        this.etatVariablePaie = etatVariablePaie;
        return this;
    }

    public void setEtatVariablePaie(EtatVariablePaie etatVariablePaie) {
        this.etatVariablePaie = etatVariablePaie;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Absence employe(Employe employe) {
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
        if (!(o instanceof Absence)) {
            return false;
        }
        return id != null && id.equals(((Absence) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Absence{" +
            "id=" + getId() +
            ", debutAbsence='" + getDebutAbsence() + "'" +
            ", finAbsence='" + getFinAbsence() + "'" +
            ", mois=" + getMois() +
            ", annee=" + getAnnee() +
            "}";
    }
}
