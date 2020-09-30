package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A HeuresSupplementaires.
 */
@Entity
@Table(name = "heures_supplementaires")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HeuresSupplementaires implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "nombre_heure", nullable = false)
    private Integer nombreHeure;

    @NotNull
    @Column(name = "mois", nullable = false)
    private Integer mois;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "heuresSupplementaires", allowSetters = true)
    private EtatVariablePaie etatVariablePaie;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeHeureSupplementaires", allowSetters = true)
    private Employe employe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public HeuresSupplementaires date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getNombreHeure() {
        return nombreHeure;
    }

    public HeuresSupplementaires nombreHeure(Integer nombreHeure) {
        this.nombreHeure = nombreHeure;
        return this;
    }

    public void setNombreHeure(Integer nombreHeure) {
        this.nombreHeure = nombreHeure;
    }

    public Integer getMois() {
        return mois;
    }

    public HeuresSupplementaires mois(Integer mois) {
        this.mois = mois;
        return this;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public Integer getAnnee() {
        return annee;
    }

    public HeuresSupplementaires annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public EtatVariablePaie getEtatVariablePaie() {
        return etatVariablePaie;
    }

    public HeuresSupplementaires etatVariablePaie(EtatVariablePaie etatVariablePaie) {
        this.etatVariablePaie = etatVariablePaie;
        return this;
    }

    public void setEtatVariablePaie(EtatVariablePaie etatVariablePaie) {
        this.etatVariablePaie = etatVariablePaie;
    }

    public Employe getEmploye() {
        return employe;
    }

    public HeuresSupplementaires employe(Employe employe) {
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
        if (!(o instanceof HeuresSupplementaires)) {
            return false;
        }
        return id != null && id.equals(((HeuresSupplementaires) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HeuresSupplementaires{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", nombreHeure=" + getNombreHeure() +
            ", mois=" + getMois() +
            ", annee=" + getAnnee() +
            "}";
    }
}
