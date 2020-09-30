package fr.insy2s.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.TypeDocument} entity.
 */
public class TypeDocumentDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String codeRef;

    @NotNull
    private String intitule;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeRef() {
        return codeRef;
    }

    public void setCodeRef(String codeRef) {
        this.codeRef = codeRef;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeDocumentDTO)) {
            return false;
        }

        return id != null && id.equals(((TypeDocumentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeDocumentDTO{" +
            "id=" + getId() +
            ", codeRef='" + getCodeRef() + "'" +
            ", intitule='" + getIntitule() + "'" +
            "}";
    }
}
