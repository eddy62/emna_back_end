package fr.insy2s.service.dto;

import fr.insy2s.domain.Depense;
import fr.insy2s.domain.Facture;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class DepenseTemp {
    private Long numfact;

    private String message;

    private LocalDate date;

    private Double prixTTC;

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

    public Double getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(Double prixTTC) {
        this.prixTTC = prixTTC;
    }

    public DepenseTemp(Long numfact, String message, LocalDate date, Double prixTTC, String client, Long societeId, String moyenDePaiement, MultipartFile[] listeFiles) {
        this.numfact = numfact;
        this.message = message;
        this.date = date;
        this.prixTTC = prixTTC;
        this.client = client;
        this.societeId = societeId;
        this.moyenDePaiement = moyenDePaiement;
        this.listeFiles = listeFiles;
    }

    public Depense toDepense(){
        Depense depense = new Depense();
        depense.setDate(this.getDate());
        depense.setRaison(this.getMessage());
        depense.setMoyenDePaiement(this.getMoyenDePaiement());
        depense.setNumero(this.getNumfact());
        depense.setPrix(this.getPrixTTC());
        return depense;
    }
}
