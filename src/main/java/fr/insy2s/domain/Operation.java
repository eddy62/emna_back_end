package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Operation.
 */
@Entity
@Table(name = "operation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "rapproche")
    private Boolean rapproche;

    @Column(name = "solde", precision = 21, scale = 2)
    private BigDecimal solde;

    @OneToMany(mappedBy = "operation")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Facture> listeFactures = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "listeOperations", allowSetters = true)
    private Releve releve;

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

    public Operation date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public Operation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public Operation type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isRapproche() {
        return rapproche;
    }

    public Operation rapproche(Boolean rapproche) {
        this.rapproche = rapproche;
        return this;
    }

    public void setRapproche(Boolean rapproche) {
        this.rapproche = rapproche;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public Operation solde(BigDecimal solde) {
        this.solde = solde;
        return this;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public Set<Facture> getListeFactures() {
        return listeFactures;
    }

    public Operation listeFactures(Set<Facture> factures) {
        this.listeFactures = factures;
        return this;
    }

    public Operation addListeFactures(Facture facture) {
        this.listeFactures.add(facture);
        facture.setOperation(this);
        return this;
    }

    public Operation removeListeFactures(Facture facture) {
        this.listeFactures.remove(facture);
        facture.setOperation(null);
        return this;
    }

    public void setListeFactures(Set<Facture> factures) {
        this.listeFactures = factures;
    }

    public Releve getReleve() {
        return releve;
    }

    public Operation releve(Releve releve) {
        this.releve = releve;
        return this;
    }

    public void setReleve(Releve releve) {
        this.releve = releve;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Operation)) {
            return false;
        }
        return id != null && id.equals(((Operation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Operation{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", rapproche='" + isRapproche() + "'" +
            ", solde=" + getSolde() +
            "}";
    }
}
