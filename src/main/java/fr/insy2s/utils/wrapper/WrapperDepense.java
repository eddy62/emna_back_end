package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Detail depense
 */
public class WrapperDepense {

    //Depense
    private Long Id;
    private Long numero;
    private LocalDate date;
    private BigDecimal prix;
    private String moyenDePaiement;
    private String raison;

    //Client_fournisseur
    private String fournisseurNom;

    //Etat_depense
    private String etatDepenseLibelle;


    public WrapperDepense(DepenseDTO depenseDTO, EtatDepenseDTO etatDepenseDTO, ClientFournisseurDTO clientFournisseurDTO) {
        super();
        //Depense
        this.Id = depenseDTO.getId();
        this.numero = depenseDTO.getNumero();
        this.date = depenseDTO.getDate();
        this.prix = depenseDTO.getPrix();
        this.moyenDePaiement = depenseDTO.getMoyenDePaiement();
        this.raison = depenseDTO.getRaison();

        //Etat depense
        this.etatDepenseLibelle = etatDepenseDTO.getLibelle();

        //client fournisseur (Wrapper)
        this.fournisseurNom = clientFournisseurDTO.getNom();
    }

    public WrapperDepense(Long id, Long numero, LocalDate date, BigDecimal prix, String moyenDePaiement, String raison, Long fournisseurNom, Long etatDepenseLibelle) {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getMoyenDePaiement() {
        return moyenDePaiement;
    }

    public void setMoyenDePaiement(String moyenDePaiement) {
        this.moyenDePaiement = moyenDePaiement;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getFournisseurNom() {
        return fournisseurNom;
    }

    public void setFournisseurNom(String fournisseurNom) {
        this.fournisseurNom = fournisseurNom;
    }

    public String getEtatDepenseLibelle() {
        return etatDepenseLibelle;
    }

    public void setEtatDepenseLibelle(String etatDepenseLibelle) {
        this.etatDepenseLibelle = etatDepenseLibelle;
    }
}
