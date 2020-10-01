package fr.insy2s.utils.wrapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WrapperListeDepense {

    private Long id;

    private Long numero;

    private LocalDate date;

    private BigDecimal prix;

    private String nomFournisseur;

    private String etatDepense;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public String getEtatDepense() {
        return etatDepense;
    }

    public void setEtatDepense(String etatDepense) {
        this.etatDepense = etatDepense;
    }

    public WrapperListeDepense(Long id, Long numero, LocalDate date, BigDecimal prix, String nomFournisseur, String etatDepense) {
        this.id = id;
        this.numero = numero;
        this.date = date;
        this.prix = prix;
        this.nomFournisseur = nomFournisseur;
        this.etatDepense = etatDepense;
    }
}
