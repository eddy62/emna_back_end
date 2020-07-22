package fr.insy2s.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link fr.insy2s.domain.Article} entity.
 */
public class ArticleDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String reference;

    @NotNull
    private String titre;

    @NotNull
    private String description;

    private Set<AvenantDTO> listeAvenants = new HashSet<>();
    private Set<ContratDTO> listeContrats = new HashSet<>();

    private Long societeId;
    
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AvenantDTO> getListeAvenants() {
        return listeAvenants;
    }

    public void setListeAvenants(Set<AvenantDTO> avenants) {
        this.listeAvenants = avenants;
    }

    public Set<ContratDTO> getListeContrats() {
        return listeContrats;
    }

    public void setListeContrats(Set<ContratDTO> contrats) {
        this.listeContrats = contrats;
    }

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArticleDTO)) {
            return false;
        }

        return id != null && id.equals(((ArticleDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArticleDTO{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", titre='" + getTitre() + "'" +
            ", description='" + getDescription() + "'" +
            ", listeAvenants='" + getListeAvenants() + "'" +
            ", listeContrats='" + getListeContrats() + "'" +
            ", societeId=" + getSocieteId() +
            "}";
    }
}
