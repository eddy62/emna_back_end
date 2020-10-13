package fr.insy2s.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.SaisieArticle} entity.
 */
public class SaisieArticleDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;


    private Long articleId;

    private Long contratId;

    private Long avenantId;
    
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

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getContratId() {
        return contratId;
    }

    public void setContratId(Long contratId) {
        this.contratId = contratId;
    }

    public Long getAvenantId() {
        return avenantId;
    }

    public void setAvenantId(Long avenantId) {
        this.avenantId = avenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SaisieArticleDTO)) {
            return false;
        }

        return id != null && id.equals(((SaisieArticleDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SaisieArticleDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", articleId=" + getArticleId() +
            ", contratId=" + getContratId() +
            ", avenantId=" + getAvenantId() +
            "}";
    }
}
