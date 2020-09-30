package fr.insy2s.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link fr.insy2s.domain.LigneProduit} entity.
 */
public class LigneProduitDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer quantite;

    private String commentaire;

    private BigDecimal remise;


    private Long produitId;

    private Long factureId;

    private Long devisId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public BigDecimal getRemise() {
        return remise;
    }

    public void setRemise(BigDecimal remise) {
        this.remise = remise;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getFactureId() {
        return factureId;
    }

    public void setFactureId(Long factureId) {
        this.factureId = factureId;
    }

    public Long getDevisId() {
        return devisId;
    }

    public void setDevisId(Long devisId) {
        this.devisId = devisId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LigneProduitDTO)) {
            return false;
        }

        return id != null && id.equals(((LigneProduitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LigneProduitDTO{" +
            "id=" + getId() +
            ", quantite=" + getQuantite() +
            ", commentaire='" + getCommentaire() + "'" +
            ", remise=" + getRemise() +
            ", produitId=" + getProduitId() +
            ", factureId=" + getFactureId() +
            ", devisId=" + getDevisId() +
            "}";
    }
}
