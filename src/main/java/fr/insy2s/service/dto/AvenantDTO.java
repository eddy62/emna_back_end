package fr.insy2s.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.Avenant} entity.
 */
public class AvenantDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String reference;

    @NotNull
    private Boolean signe;


    private Long saisieArticleId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Boolean isSigne() {
        return signe;
    }

    public void setSigne(Boolean signe) {
        this.signe = signe;
    }

    public Long getSaisieArticleId() {
        return saisieArticleId;
    }

    public void setSaisieArticleId(Long saisieArticleId) {
        this.saisieArticleId = saisieArticleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AvenantDTO)) {
            return false;
        }

        return id != null && id.equals(((AvenantDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AvenantDTO{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", signe='" + isSigne() + "'" +
            ", saisieArticleId=" + getSaisieArticleId() +
            "}";
    }
}
