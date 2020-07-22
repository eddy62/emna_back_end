package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Releve.
 */
@Entity
@Table(name = "releve")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Releve implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "solde")
    private Double solde;

    @Column(name = "banque")
    private String banque;

    @Column(name = "chemin_fichier")
    private String cheminFichier;

    @OneToOne
    @JoinColumn(unique = true)
    private EtatReleve etatReleve;

    @OneToMany(mappedBy = "releve")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Operation> listeOperations = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "listeReleves", allowSetters = true)
    private Societe societe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Releve dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Releve dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Double getSolde() {
        return solde;
    }

    public Releve solde(Double solde) {
        this.solde = solde;
        return this;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public String getBanque() {
        return banque;
    }

    public Releve banque(String banque) {
        this.banque = banque;
        return this;
    }

    public void setBanque(String banque) {
        this.banque = banque;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public Releve cheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
        return this;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public EtatReleve getEtatReleve() {
        return etatReleve;
    }

    public Releve etatReleve(EtatReleve etatReleve) {
        this.etatReleve = etatReleve;
        return this;
    }

    public void setEtatReleve(EtatReleve etatReleve) {
        this.etatReleve = etatReleve;
    }

    public Set<Operation> getListeOperations() {
        return listeOperations;
    }

    public Releve listeOperations(Set<Operation> operations) {
        this.listeOperations = operations;
        return this;
    }

    public Releve addListeOperations(Operation operation) {
        this.listeOperations.add(operation);
        operation.setReleve(this);
        return this;
    }

    public Releve removeListeOperations(Operation operation) {
        this.listeOperations.remove(operation);
        operation.setReleve(null);
        return this;
    }

    public void setListeOperations(Set<Operation> operations) {
        this.listeOperations = operations;
    }

    public Societe getSociete() {
        return societe;
    }

    public Releve societe(Societe societe) {
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
        if (!(o instanceof Releve)) {
            return false;
        }
        return id != null && id.equals(((Releve) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Releve{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", solde=" + getSolde() +
            ", banque='" + getBanque() + "'" +
            ", cheminFichier='" + getCheminFichier() + "'" +
            "}";
    }
}
