package fr.insy2s.service.dto;

import fr.insy2s.domain.Document;
import fr.insy2s.domain.Facture;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 * Model de la facture reçu via le formulaire de création de facture
 *
 */

public class FactureTemp {

    private Long numfact;

    private String message;

    private LocalDate date;

    private LocalDate dateEcheance;

    /*private Integer prixHT;

    private Integer prixTTC;

    private Float tva;*/

    private String moyenDePaiement;

    private String client;

    private String numRue;

    private String nomRue;

    private String codePostal;

    private String ville;

    private String pays;

    private Long societeId;

    private MultipartFile[] listeFiles;

    public Long getNumfact() {
        return numfact;
    }

    public void setNumfact(Long numfact) {
        this.numfact = numfact;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    /*public Integer getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(Integer prixHT) {
        this.prixHT = prixHT;
    }

    public Integer getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(Integer prixTTC) {
        this.prixTTC = prixTTC;
    }

    public Float getTva() {
        return tva;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }*/

    public String getMoyenDePaiement() {
        return moyenDePaiement;
    }

    public void setMoyenDePaiement(String moyenDePaiement) {
        this.moyenDePaiement = moyenDePaiement;
    }

    public String getNumRue() {
        return numRue;
    }

    public void setNumRue(String numRue) {
        this.numRue = numRue;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
    }

    public MultipartFile[] getListeFiles() {
        return listeFiles;
    }

    public void setListeFiles(MultipartFile[] listeFiles) {
        this.listeFiles = listeFiles;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public FactureTemp(Long numfact, String message, LocalDate date, LocalDate dateEcheance, String moyenDePaiement, String client, String numRue, String nomRue, String codePostal, String ville, String pays, Long societeId, MultipartFile[] listeFiles) {
        this.numfact = numfact;
        this.message = message;
        this.date = date;
        this.dateEcheance = dateEcheance;
        /*this.prixHT = prixHT;
        this.prixTTC = prixTTC;
        this.tva = tva;*/
        this.moyenDePaiement = moyenDePaiement;
        this.client = client;
        this.numRue = numRue;
        this.nomRue = nomRue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.societeId = societeId;
        this.listeFiles = listeFiles;
    }

    public Facture toFacture(){
        Facture facture = new Facture();
        facture.setDate(this.getDate());
        facture.setMessage(this.getMessage());
        facture.setMoyenDePaiement(this.getMoyenDePaiement());
        facture.setNumfact(this.getNumfact());
        /*facture.setPrixHT(this.getPrixHT());
        facture.setPrixTTC(this.getPrixTTC());
        facture.setTva(this.getTva());*/
        facture.setDateEcheance(this.getDateEcheance());
        return facture;
    }


}
