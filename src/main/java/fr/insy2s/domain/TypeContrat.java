package fr.insy2s.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A TypeContrat.
 */
@Entity
@Table(name = "type_contrat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeContrat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code_ref", nullable = false, unique = true)
    private String codeRef;

    @NotNull
    @Column(name = "intitule", nullable = false, unique = true)
    private String intitule;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeRef() {
        return codeRef;
    }

    public TypeContrat codeRef(String codeRef) {
        this.codeRef = codeRef;
        return this;
    }

    public void setCodeRef(String codeRef) {
        this.codeRef = codeRef;
    }

    public String getIntitule() {
        return intitule;
    }

    public TypeContrat intitule(String intitule) {
        this.intitule = intitule;
        return this;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeContrat)) {
            return false;
        }
        return id != null && id.equals(((TypeContrat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeContrat{" +
            "id=" + getId() +
            ", codeRef='" + getCodeRef() + "'" +
            ", intitule='" + getIntitule() + "'" +
            "}";
    }
}
