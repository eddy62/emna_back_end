package fr.insy2s.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.EtatReleve} entity.
 */
public class EtatReleveDTO implements Serializable {
    
    private Long id;

    private String libelle;

    private String codeRef;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCodeRef() {
        return codeRef;
    }

    public void setCodeRef(String codeRef) {
        this.codeRef = codeRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtatReleveDTO)) {
            return false;
        }

        return id != null && id.equals(((EtatReleveDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EtatReleveDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", codeRef='" + getCodeRef() + "'" +
            "}";
    }
}
