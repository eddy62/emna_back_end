package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Contrat.
 */
@Entity
@Table(name = "contrat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contrat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "titre", nullable = false)
    private String titre;

    @NotNull
    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    @NotNull
    @Column(name = "signe", nullable = false)
    private Boolean signe;

    @Column(name = "archive")
    private Boolean archive;

    @OneToMany(mappedBy = "contrat")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> listeDocuments = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "contrats", allowSetters = true)
    private TypeContrat typeContrat;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeContrats", allowSetters = true)
    private Employe employe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public Contrat titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Contrat dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean isSigne() {
        return signe;
    }

    public Contrat signe(Boolean signe) {
        this.signe = signe;
        return this;
    }

    public void setSigne(Boolean signe) {
        this.signe = signe;
    }

    public Boolean isArchive() {
        return archive;
    }

    public Contrat archive(Boolean archive) {
        this.archive = archive;
        return this;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public Set<Document> getListeDocuments() {
        return listeDocuments;
    }

    public Contrat listeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
        return this;
    }

    public Contrat addListeDocuments(Document document) {
        this.listeDocuments.add(document);
        document.setContrat(this);
        return this;
    }

    public Contrat removeListeDocuments(Document document) {
        this.listeDocuments.remove(document);
        document.setContrat(null);
        return this;
    }

    public void setListeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public Contrat typeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
        return this;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Contrat employe(Employe employe) {
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
        if (!(o instanceof Contrat)) {
            return false;
        }
        return id != null && id.equals(((Contrat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contrat{" +
            "id=" + getId() +
            ", titre='" + getTitre() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", signe='" + isSigne() + "'" +
            ", archive='" + isArchive() + "'" +
            "}";
    }
}
