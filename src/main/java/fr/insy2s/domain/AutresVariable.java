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
 * A AutresVariable.
 */
@Entity
@Table(name = "autres_variable")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AutresVariable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "montant", precision = 21, scale = 2)
    private BigDecimal montant;

    @NotNull
    @Column(name = "mois", nullable = false)
    private Integer mois;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @OneToMany(mappedBy = "autresVariable")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> listeDocuments = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "autresVariables", allowSetters = true)
    private EtatVariablePaie etatVariablePaie;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeAutresVariables", allowSetters = true)
    private Employe employe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public AutresVariable description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public AutresVariable date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public AutresVariable montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Integer getMois() {
        return mois;
    }

    public AutresVariable mois(Integer mois) {
        this.mois = mois;
        return this;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public Integer getAnnee() {
        return annee;
    }

    public AutresVariable annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Set<Document> getListeDocuments() {
        return listeDocuments;
    }

    public AutresVariable listeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
        return this;
    }

    public AutresVariable addListeDocuments(Document document) {
        this.listeDocuments.add(document);
        document.setAutresVariable(this);
        return this;
    }

    public AutresVariable removeListeDocuments(Document document) {
        this.listeDocuments.remove(document);
        document.setAutresVariable(null);
        return this;
    }

    public void setListeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
    }

    public EtatVariablePaie getEtatVariablePaie() {
        return etatVariablePaie;
    }

    public AutresVariable etatVariablePaie(EtatVariablePaie etatVariablePaie) {
        this.etatVariablePaie = etatVariablePaie;
        return this;
    }

    public void setEtatVariablePaie(EtatVariablePaie etatVariablePaie) {
        this.etatVariablePaie = etatVariablePaie;
    }

    public Employe getEmploye() {
        return employe;
    }

    public AutresVariable employe(Employe employe) {
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
        if (!(o instanceof AutresVariable)) {
            return false;
        }
        return id != null && id.equals(((AutresVariable) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AutresVariable{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", date='" + getDate() + "'" +
            ", montant=" + getMontant() +
            ", mois=" + getMois() +
            ", annee=" + getAnnee() +
            "}";
    }
}
