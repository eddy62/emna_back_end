package fr.insy2s.service.dto;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link fr.insy2s.domain.Produit} entity.
 */

public class ProduitDTO implements Serializable {
    @NotNull()
    @Min(1L)
    private Long id;
    @NotNull()
    private String nom;
    @NotNull()
    private String reference;
    private BigDecimal tva;
    @NotNull()
    @Min(0)
    private BigDecimal prix;
    private String unite;
    private String description;
    @NotNull()
    @Min(1L)
    private Long societeId;

    public ProduitDTO() {
    }

    public ProduitDTO(Long id, String nom, String reference, BigDecimal tva, @Min(0) BigDecimal prix, String unite,
                      String description, Long societeId) {
        this.id = id;
        this.nom = nom;
        this.reference = reference;
        this.tva = tva;
        this.prix = prix;
        this.unite = unite;
        this.description = description;
        this.societeId = societeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getTva() {
        return tva;
    }

    public void setTva(BigDecimal tva) {
        this.tva = tva;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(o instanceof ProduitDTO)) {
            return false;
        }

        return id != null && id.equals(((ProduitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProduitDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", reference='" + getReference() + "'" +
            ", tva=" + getTva() +
            ", prix=" + getPrix() +
            ", unite='" + getUnite() + "'" +
            ", description='" + getDescription() + "'" +
            ", societeId=" + getSocieteId() +
            "}";
    }
}
