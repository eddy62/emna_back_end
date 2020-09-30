package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A NoteDeFrais.
 */
@Entity
@Table(name = "note_de_frais")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NoteDeFrais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "designation", nullable = false)
    private String designation;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "montant", precision = 21, scale = 2, nullable = false)
    private BigDecimal montant;

    @NotNull
    @Column(name = "mois", nullable = false)
    private Integer mois;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @OneToMany(mappedBy = "noteDeFrais")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> listeDocuments = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "noteDeFrais", allowSetters = true)
    private EtatVariablePaie etatVariablePaie;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeNoteDeFrais", allowSetters = true)
    private Employe employe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public NoteDeFrais designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getDate() {
        return date;
    }

    public NoteDeFrais date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public NoteDeFrais montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Integer getMois() {
        return mois;
    }

    public NoteDeFrais mois(Integer mois) {
        this.mois = mois;
        return this;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public Integer getAnnee() {
        return annee;
    }

    public NoteDeFrais annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Set<Document> getListeDocuments() {
        return listeDocuments;
    }

    public NoteDeFrais listeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
        return this;
    }

    public NoteDeFrais addListeDocuments(Document document) {
        this.listeDocuments.add(document);
        document.setNoteDeFrais(this);
        return this;
    }

    public NoteDeFrais removeListeDocuments(Document document) {
        this.listeDocuments.remove(document);
        document.setNoteDeFrais(null);
        return this;
    }

    public void setListeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
    }

    public EtatVariablePaie getEtatVariablePaie() {
        return etatVariablePaie;
    }

    public NoteDeFrais etatVariablePaie(EtatVariablePaie etatVariablePaie) {
        this.etatVariablePaie = etatVariablePaie;
        return this;
    }

    public void setEtatVariablePaie(EtatVariablePaie etatVariablePaie) {
        this.etatVariablePaie = etatVariablePaie;
    }

    public Employe getEmploye() {
        return employe;
    }

    public NoteDeFrais employe(Employe employe) {
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
        if (!(o instanceof NoteDeFrais)) {
            return false;
        }
        return id != null && id.equals(((NoteDeFrais) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoteDeFrais{" +
            "id=" + getId() +
            ", designation='" + getDesignation() + "'" +
            ", date='" + getDate() + "'" +
            ", montant=" + getMontant() +
            ", mois=" + getMois() +
            ", annee=" + getAnnee() +
            "}";
    }
}
