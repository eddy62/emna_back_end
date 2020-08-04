package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Absence.
 */
@Entity
@Table(name = "absence")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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

    @Column(name = "justificatif")
    private String justificatif;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("absences")
    private TypeAbsence typeAbsence;

    @ManyToOne
    @JsonIgnoreProperties("listeAbsences")
    private Employe employe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
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

    public String getJustificatif() {
        return justificatif;
    }

    public Absence justificatif(String justificatif) {
        this.justificatif = justificatif;
        return this;
    }

    public void setJustificatif(String justificatif) {
        this.justificatif = justificatif;
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
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

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

    @Override
    public String toString() {
        return "Absence{" +
            "id=" + getId() +
            ", debutAbsence='" + getDebutAbsence() + "'" +
            ", finAbsence='" + getFinAbsence() + "'" +
            ", justificatif='" + getJustificatif() + "'" +
            "}";
    }
}
