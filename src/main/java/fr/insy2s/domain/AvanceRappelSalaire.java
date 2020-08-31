package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A AvanceRappelSalaire.
 */
@Entity
@Table(name = "avance_rappel_salaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AvanceRappelSalaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull
    @Column(name = "debut_periode", nullable = false)
    private LocalDate debutPeriode;

    @NotNull
    @Column(name = "fin_periode", nullable = false)
    private LocalDate finPeriode;

    @NotNull
    @Column(name = "montant", nullable = false)
    private Double montant;

    @ManyToOne
    @JsonIgnoreProperties(value = "avanceRappelSalaires", allowSetters = true)
    private EtatVariablePaie etatVariablePaie;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeAvanceRappelSalaires", allowSetters = true)
    private Employe employe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public AvanceRappelSalaire type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDebutPeriode() {
        return debutPeriode;
    }

    public AvanceRappelSalaire debutPeriode(LocalDate debutPeriode) {
        this.debutPeriode = debutPeriode;
        return this;
    }

    public void setDebutPeriode(LocalDate debutPeriode) {
        this.debutPeriode = debutPeriode;
    }

    public LocalDate getFinPeriode() {
        return finPeriode;
    }

    public AvanceRappelSalaire finPeriode(LocalDate finPeriode) {
        this.finPeriode = finPeriode;
        return this;
    }

    public void setFinPeriode(LocalDate finPeriode) {
        this.finPeriode = finPeriode;
    }

    public Double getMontant() {
        return montant;
    }

    public AvanceRappelSalaire montant(Double montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public EtatVariablePaie getEtatVariablePaie() {
        return etatVariablePaie;
    }

    public AvanceRappelSalaire etatVariablePaie(EtatVariablePaie etatVariablePaie) {
        this.etatVariablePaie = etatVariablePaie;
        return this;
    }

    public void setEtatVariablePaie(EtatVariablePaie etatVariablePaie) {
        this.etatVariablePaie = etatVariablePaie;
    }

    public Employe getEmploye() {
        return employe;
    }

    public AvanceRappelSalaire employe(Employe employe) {
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
        if (!(o instanceof AvanceRappelSalaire)) {
            return false;
        }
        return id != null && id.equals(((AvanceRappelSalaire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AvanceRappelSalaire{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", debutPeriode='" + getDebutPeriode() + "'" +
            ", finPeriode='" + getFinPeriode() + "'" +
            ", montant=" + getMontant() +
            "}";
    }
}
