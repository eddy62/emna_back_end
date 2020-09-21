package fr.insy2s.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.Article} entity.
 */
public class ArticleDTO implements Serializable {

    private Long id;

    @NotNull
    private String titre;

    @NotNull
    private String intitule;

    @NotNull
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String asString() {
        String title    = this.titre.trim().toLowerCase().replaceAll("\\s+", "");
        String intitule = this.intitule.trim().toLowerCase().replaceAll("\\s+", "");
        return title + intitule;
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
            ", titre='" + getTitre() + "'" +
            ", intitule='" + getIntitule() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
