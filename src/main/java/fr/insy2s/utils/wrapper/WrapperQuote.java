package fr.insy2s.utils.wrapper;

import fr.insy2s.domain.ClientFournisseur;
import fr.insy2s.domain.Devis;
import fr.insy2s.service.dto.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * WrapperQuote comportant les informations du Devis :
 * - informations devis
 * - informations client
 * - adresse client
 * - lignes produits
 * - documents relatifs
 * - prix TTC
 */
public class WrapperQuote {

    // informations devis
    private Long id;
    private Long numDevis;
    private String nom;
    private String message;
    private LocalDate dateCreation;
    private LocalDate dateLimite;
    private String etat;

    // informations client
    private Long clientFournisseurId;
    private String clientFournisseurNom;
    private String clientFournisseurSiret;
    private String clientFournisseurTelephone;
    private String clientFournisseurEmail;

    // adresse client
    private Long adresseId;
    private String numeroRue;
    private String boitePostale;
    private String nomRue;
    private String codePostal;
    private String ville;
    private String pays;

    // lignes produits
    private List<WrapperLigneProduit> ligneProduits;

    // documents relatifs
    private List<DocumentDTO> documentDTOList;

    // prix TTC
    private BigDecimal prixTTC;

    public WrapperQuote() {}

    public WrapperQuote(Devis devis, AdresseDTO adresseDTO, List<WrapperLigneProduit> ligneProduits,List<DocumentDTO> documentDTOList,  BigDecimal prixTTC) {


        // informations devis

        this.etat=devis.getEtatDevis().getLibelle();
        this.id = devis.getId();
        this.numDevis = devis.getNumDevis();
        this.nom = devis.getNom();
        this.message = devis.getMessage();
        this.dateCreation = devis.getDateCreation();
        this.dateLimite = devis.getDateLimite();

        // informations client
        ClientFournisseur clientFournisseur = devis.getClientFournisseur();
        this.clientFournisseurId = clientFournisseur.getId();
        this.clientFournisseurNom = clientFournisseur.getNom();
        this.clientFournisseurSiret = clientFournisseur.getSiret();
        this.clientFournisseurTelephone = clientFournisseur.getTelephone();
        this.clientFournisseurEmail = clientFournisseur.getEmail();

        // adresse client
        this.adresseId = adresseDTO.getId();
        this.numeroRue = adresseDTO.getNumeroRue();
        this.boitePostale = adresseDTO.getBoitePostale();
        this.nomRue = adresseDTO.getNomRue();
        this.codePostal = adresseDTO.getBoitePostale();
        this.ville = adresseDTO.getVille();
        this.pays = adresseDTO.getPays();

        // lignes produits
        this.ligneProduits = ligneProduits;

        // documents relatifs
        this.documentDTOList = documentDTOList;

        // prix TTC
        this.prixTTC = prixTTC;
    }

    // getter & setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumDevis() {
        return numDevis;
    }

    public void setNumDevis(Long numDevis) {
        this.numDevis = numDevis;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(LocalDate dateLimite) {
        this.dateLimite = dateLimite;
    }

    public Long getClientFournisseurId() {
        return clientFournisseurId;
    }

    public void setClientFournisseurId(Long clientFournisseurId) {
        this.clientFournisseurId = clientFournisseurId;
    }

    public String getClientFournisseurNom() {
        return clientFournisseurNom;
    }

    public void setClientFournisseurNom(String clientFournisseurNom) {
        this.clientFournisseurNom = clientFournisseurNom;
    }

    public String getClientFournisseurSiret() {
        return clientFournisseurSiret;
    }

    public void setClientFournisseurSiret(String clientFournisseurSiret) {
        this.clientFournisseurSiret = clientFournisseurSiret;
    }

    public String getClientFournisseurTelephone() {
        return clientFournisseurTelephone;
    }

    public void setClientFournisseurTelephone(String clientFournisseurTelephone) {
        this.clientFournisseurTelephone = clientFournisseurTelephone;
    }

    public String getClientFournisseurEmail() {
        return clientFournisseurEmail;
    }

    public void setClientFournisseurEmail(String clientFournisseurEmail) {
        this.clientFournisseurEmail = clientFournisseurEmail;
    }

    public Long getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Long adresseId) {
        this.adresseId = adresseId;
    }

    public String getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(String numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getBoitePostale() {
        return boitePostale;
    }

    public void setBoitePostale(String boitePostale) {
        this.boitePostale = boitePostale;
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

    public List<WrapperLigneProduit> getLigneProduits() {
        return ligneProduits;
    }

    public void setLigneProduits(List<WrapperLigneProduit> ligneProduits) {
        this.ligneProduits = ligneProduits;
    }

    public List<DocumentDTO> getDocumentDTOList() {
        return documentDTOList;
    }

    public void setDocumentDTOList(List<DocumentDTO> documentDTOList) {
        this.documentDTOList = documentDTOList;
    }

    public BigDecimal getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(BigDecimal prixTTC) {
        this.prixTTC = prixTTC;
    }
    
    public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}


}