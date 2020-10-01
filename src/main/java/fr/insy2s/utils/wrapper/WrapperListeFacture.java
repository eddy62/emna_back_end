package fr.insy2s.utils.wrapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WrapperListeFacture {

    private Long id;

    private Long numfact;

    private String type;

    private LocalDate date;

    private BigDecimal prixTTC;


    private String nomClient;

    private String etatFacture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumfact() {
        return numfact;
    }

    public void setNumfact(Long numfact) {
        this.numfact = numfact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public BigDecimal getPrixTTC() {

        return prixTTC;
    }

    public void setPrixTTC(BigDecimal prixTTC) {
        this.prixTTC = prixTTC;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getEtatFacture() {
        return etatFacture;
    }

    public void setEtatFacture(String etatFacture) {
        this.etatFacture = etatFacture;
    }


    public WrapperListeFacture(Long id, Long numfact, String type, LocalDate date, BigDecimal prixTTC, String nomClient, String etatFacture) {
        this.id = id;
        this.numfact = numfact;
        this.type = type;
        this.date = date;
        this.prixTTC = prixTTC;
        this.nomClient = nomClient;
        this.etatFacture = etatFacture;
    }
}
