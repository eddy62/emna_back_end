package fr.insy2s.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link fr.insy2s.domain.TypeAbsence} entity.
 */
public class TypeAbsenceDTO implements Serializable {
    
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

        TypeAbsenceDTO typeAbsenceDTO = (TypeAbsenceDTO) o;
        if (typeAbsenceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeAbsenceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeAbsenceDTO{" +
            "id=" + getId() +
            ", codeRef='" + getCodeRef() + "'" +
            ", intitule='" + getIntitule() + "'" +
            "}";
    }
}
