package fr.insy2s.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A StatutEmploye.
 */
@Entity
@Table(name = "statut_employe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StatutEmploye implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code_ref", nullable = false, unique = true)
    private String codeRef;

    @NotNull
    @Column(name = "libelle", nullable = false, unique = true)
    private String libelle;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeRef() {
        return codeRef;
    }

    public StatutEmploye codeRef(String codeRef) {
        this.codeRef = codeRef;
        return this;
    }

    public void setCodeRef(String codeRef) {
        this.codeRef = codeRef;
    }

    public String getLibelle() {
        return libelle;
    }

    public StatutEmploye libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StatutEmploye)) {
            return false;
        }
        return id != null && id.equals(((StatutEmploye) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "StatutEmploye{" +
            "id=" + getId() +
            ", codeRef='" + getCodeRef() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
