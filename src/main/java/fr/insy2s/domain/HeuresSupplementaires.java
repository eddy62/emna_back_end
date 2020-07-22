package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

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

    @Column(name = "justificatif")
    private String justificatif;

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

    public String getJustificatif() {
        return justificatif;
    }

    public HeuresSupplementaires justificatif(String justificatif) {
        this.justificatif = justificatif;
        return this;
    }

    public void setJustificatif(String justificatif) {
        this.justificatif = justificatif;
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
            ", justificatif='" + getJustificatif() + "'" +
            "}";
    }
}
