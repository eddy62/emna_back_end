package fr.insy2s.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link fr.insy2s.domain.TypePrime} entity.
 */
public class TypePrimeDTO implements Serializable {
    
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypePrimeDTO typePrimeDTO = (TypePrimeDTO) o;
        if (typePrimeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typePrimeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypePrimeDTO{" +
            "id=" + getId() +
            ", codeRef='" + getCodeRef() + "'" +
            ", intitule='" + getIntitule() + "'" +
            "}";
    }
}
