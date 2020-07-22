package fr.insy2s.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EtatFacture.
 */
@Entity
@Table(name = "etat_facture")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtatFacture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false, unique = true)
    private String libelle;

    @NotNull
    @Column(name = "code_ref", nullable = false, unique = true)
    private String codeRef;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public EtatFacture libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCodeRef() {
        return codeRef;
    }

    public EtatFacture codeRef(String codeRef) {
        this.codeRef = codeRef;
        return this;
    }

    public void setCodeRef(String codeRef) {
        this.codeRef = codeRef;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtatFacture)) {
            return false;
        }
        return id != null && id.equals(((EtatFacture) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EtatFacture{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", codeRef='" + getCodeRef() + "'" +
            "}";
    }
}
