package fr.insy2s.utils.wrapper;
import fr.insy2s.domain.Devis;
import fr.insy2s.domain.Facture;
import fr.insy2s.domain.LigneProduit;
import fr.insy2s.domain.Produit;
import fr.insy2s.service.dto.LigneProduitDTO;
import fr.insy2s.service.dto.ProduitDTO;

import java.math.BigDecimal;

public class WrapperLigneProduit {
    private Long id;
    private Integer quantite;
    private String commentaire;
    private BigDecimal remise;
    private Long factureId;
    private Long devisId;

    // Produit
    private String nomProduit;
    private String referenceProduit;
    private BigDecimal tvaProduit;
    private BigDecimal prixProduit;
    private String uniteProduit;
    private String descriptionProduit;
    private Long societeId;

    public WrapperLigneProduit () {}

    public WrapperLigneProduit(LigneProduit ligneProduit) {
        Facture facture         = ligneProduit.getFacture();
        Devis devis             = ligneProduit.getDevis();
        Produit produit         = ligneProduit.getProduit();
        this.id                 = ligneProduit.getId();
        this.quantite           = ligneProduit.getQuantite();
        this.commentaire        = ligneProduit.getCommentaire();
        this.remise             = ligneProduit.getRemise();
        this.factureId          = (facture != null ) ? facture.getId() : null;
        this.devisId            = (devis != null ) ? devis.getId() : null;
        this.nomProduit         = produit.getNom();
        this.referenceProduit   = produit.getReference();
        this.tvaProduit         = produit.getTva();
        this.prixProduit        = produit.getPrix();
        this.uniteProduit       = produit.getUnite();
        this.descriptionProduit = produit.getDescription();
        this.societeId          = produit.getSociete().getId();
    }

    public BigDecimal getTvaProduit() {
        return tvaProduit;
    }

    public void setTvaProduit(BigDecimal tvaProduit) {
        this.tvaProduit = tvaProduit;
    }

    public BigDecimal getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(BigDecimal prixProduit) {
        this.prixProduit = prixProduit;
    }

    public String getUniteProduit() {
        return uniteProduit;
    }

    public void setUniteProduit(String uniteProduit) {
        this.uniteProduit = uniteProduit;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

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

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getReferenceProduit() {
        return referenceProduit;
    }

    public void setReferenceProduit(String referenceProduit) {
        this.referenceProduit = referenceProduit;
    }

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
    }
}
