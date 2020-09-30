package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Avenant.
 */
@Entity
@Table(name = "avenant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Avenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "reference", nullable = false)
    private String reference;

    @NotNull
    @Column(name = "signe", nullable = false)
    private Boolean signe;

    @OneToMany(mappedBy = "avenant")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> listeDocuments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "listeAvenants", allowSetters = true)
    private Contrat contrat;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public Avenant reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Boolean isSigne() {
        return signe;
    }

    public Avenant signe(Boolean signe) {
        this.signe = signe;
        return this;
    }

    public void setSigne(Boolean signe) {
        this.signe = signe;
    }

    public Set<Document> getListeDocuments() {
        return listeDocuments;
    }

    public Avenant listeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
        return this;
    }

    public Avenant addListeDocuments(Document document) {
        this.listeDocuments.add(document);
        document.setAvenant(this);
        return this;
    }

    public Avenant removeListeDocuments(Document document) {
        this.listeDocuments.remove(document);
        document.setAvenant(null);
        return this;
    }

    public void setListeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public Avenant contrat(Contrat contrat) {
        this.contrat = contrat;
        return this;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Avenant)) {
            return false;
        }
        return id != null && id.equals(((Avenant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Avenant{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", signe='" + isSigne() + "'" +
            "}";
    }
}
