package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Dpae.
 */
@Entity
@Table(name = "dpae")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dpae implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "lieu", nullable = false)
    private String lieu;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "lien_document", nullable = false)
    private String lienDocument;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDpaes", allowSetters = true)
    private Employe employe;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDpaes", allowSetters = true)
    private Societe societe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public Dpae lieu(String lieu) {
        this.lieu = lieu;
        return this;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDate() {
        return date;
    }

    public Dpae date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLienDocument() {
        return lienDocument;
    }

    public Dpae lienDocument(String lienDocument) {
        this.lienDocument = lienDocument;
        return this;
    }

    public void setLienDocument(String lienDocument) {
        this.lienDocument = lienDocument;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Dpae employe(Employe employe) {
        this.employe = employe;
        return this;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Societe getSociete() {
        return societe;
    }

    public Dpae societe(Societe societe) {
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
        if (!(o instanceof Dpae)) {
            return false;
        }
        return id != null && id.equals(((Dpae) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Dpae{" +
            "id=" + getId() +
            ", lieu='" + getLieu() + "'" +
            ", date='" + getDate() + "'" +
            ", lienDocument='" + getLienDocument() + "'" +
            "}";
    }
}
