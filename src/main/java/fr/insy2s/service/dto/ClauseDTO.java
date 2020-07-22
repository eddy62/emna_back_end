package fr.insy2s.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link fr.insy2s.domain.Clause} entity.
 */
public class ClauseDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String reference;

    @NotNull
    private String description;

    private Set<ContratDTO> listeContrats = new HashSet<>();
    private Set<AvenantDTO> listeAvenants = new HashSet<>();

    private Long societeId;

    private Long articleId;
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ContratDTO> getListeContrats() {
        return listeContrats;
    }

    public void setListeContrats(Set<ContratDTO> contrats) {
        this.listeContrats = contrats;
    }

    public Set<AvenantDTO> getListeAvenants() {
        return listeAvenants;
    }

    public void setListeAvenants(Set<AvenantDTO> avenants) {
        this.listeAvenants = avenants;
    }

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClauseDTO)) {
            return false;
        }

        return id != null && id.equals(((ClauseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClauseDTO{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", description='" + getDescription() + "'" +
            ", listeContrats='" + getListeContrats() + "'" +
            ", listeAvenants='" + getListeAvenants() + "'" +
            ", societeId=" + getSocieteId() +
            ", articleId=" + getArticleId() +
            "}";
    }
}
