package fr.insy2s.service.dto;

import fr.insy2s.domain.Facture;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class DepenseTemp {
    private Long numfact;

    private String message;

    private LocalDate date;

    private Integer prixTTC;

    private String client;

    private Long societeId;

    private String moyenDePaiement;

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

    public Integer getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(Integer prixTTC) {
        this.prixTTC = prixTTC;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
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

    public String getMoyenDePaiement() {
        return moyenDePaiement;
    }

    public void setMoyenDePaiement(String moyenDePaiement) {
        this.moyenDePaiement = moyenDePaiement;
    }

    public DepenseTemp(Long numfact, String message, LocalDate date, Integer prixTTC, String client, Long societeId, String moyenDePaiement, MultipartFile[] listeFiles) {
        this.numfact = numfact;
        this.message = message;
        this.date = date;
        this.prixTTC = prixTTC;
        this.client = client;
        this.societeId = societeId;
        this.moyenDePaiement = moyenDePaiement;
        this.listeFiles = listeFiles;
    }

    public Facture toFacture(){
        Facture facture = new Facture();
        facture.setDate(this.getDate());
        facture.setMessage(this.getMessage());
        facture.setMoyenDePaiement(this.getMoyenDePaiement());
        facture.setNumfact(this.getNumfact());
        facture.setPrixTTC(this.getPrixTTC());
        return facture;
    }
}
