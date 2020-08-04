package fr.insy2s.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link fr.insy2s.domain.StatutEmploye} entity.
 */
public class StatutEmployeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String codeRef;

    @NotNull
    private String libelle;

    
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StatutEmployeDTO statutEmployeDTO = (StatutEmployeDTO) o;
        if (statutEmployeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statutEmployeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StatutEmployeDTO{" +
            "id=" + getId() +
            ", codeRef='" + getCodeRef() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
