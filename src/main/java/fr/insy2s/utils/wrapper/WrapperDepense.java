package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Detail depense
 */
public class WrapperDepense {

    //Depense
    private Long id;
    private Long numero;
    private LocalDate date;
    private BigDecimal prix;
    private String moyenDePaiement;
    private String raison;
    private Long societeId;
    private Long operationId;

    //Client_fournisseur
    private String fournisseurNom;
    private Long clientFournisseurId;

    //Etat_depense
    private String etatDepenseLibelle;
    private Long etatDepenseId;

    public WrapperDepense() {}

    public WrapperDepense(DepenseDTO depenseDTO, EtatDepenseDTO etatDepenseDTO, ClientFournisseurDTO clientFournisseurDTO) {
        this.id = depenseDTO.getId();
        this.numero = depenseDTO.getNumero();
        this.date = depenseDTO.getDate();
        this.prix = depenseDTO.getPrix();
        this.moyenDePaiement = depenseDTO.getMoyenDePaiement();
        this.raison = depenseDTO.getRaison();
        this.societeId = depenseDTO.getSocieteId();
        this.operationId = depenseDTO.getOperationId();
        this.fournisseurNom = clientFournisseurDTO.getNom();
        this.clientFournisseurId = clientFournisseurDTO.getId();
        this.etatDepenseLibelle = etatDepenseDTO.getLibelle();
        this.etatDepenseId = etatDepenseDTO.getId();
    }

    public WrapperDepense(Long id, Long numero, LocalDate date, BigDecimal prix, String moyenDePaiement, String raison, Long societeId, Long operationId, String fournisseurNom, Long clientFournisseurId, String etatDepenseLibelle, Long etatDepenseId) {
        this.id = id;
        this.numero = numero;
        this.date = date;
        this.prix = prix;
        this.moyenDePaiement = moyenDePaiement;
        this.raison = raison;
        this.societeId = societeId;
        this.operationId = operationId;
        this.fournisseurNom = fournisseurNom;
        this.clientFournisseurId = clientFournisseurId;
        this.etatDepenseLibelle = etatDepenseLibelle;
        this.etatDepenseId = etatDepenseId;
    }

    public static DepenseDTO toDTO(WrapperDepense wrapperDepense) {
        DepenseDTO depenseDTO = new DepenseDTO();
        depenseDTO.setId(wrapperDepense.getId());
        depenseDTO.setNumero(wrapperDepense.getNumero());
        depenseDTO.setDate(wrapperDepense.getDate());
        depenseDTO.setPrix(wrapperDepense.getPrix());
        depenseDTO.setMoyenDePaiement(wrapperDepense.getMoyenDePaiement());
        depenseDTO.setRaison(wrapperDepense.getRaison());
        depenseDTO.setSocieteId(wrapperDepense.getSocieteId());
        depenseDTO.setOperationId(wrapperDepense.getOperationId());
        depenseDTO.setClientFournisseurId(wrapperDepense.getClientFournisseurId());
        depenseDTO.setEtatDepenseId(wrapperDepense.getEtatDepenseId());
        return depenseDTO;
    }

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public Long getEtatDepenseId() {
        return etatDepenseId;
    }

    public void setEtatDepenseId(Long etatDepenseId) {
        this.etatDepenseId = etatDepenseId;
    }

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

    public Long getClientFournisseurId() {
        return clientFournisseurId;
    }

    public void setClientFournisseurId(Long clientFournisseurId) {
        this.clientFournisseurId = clientFournisseurId;
    }

    @Override
    public String toString() {
        return "WrapperDepense{" +
            "id=" + id +
            ", numero=" + numero +
            ", date=" + date +
            ", prix=" + prix +
            ", moyenDePaiement='" + moyenDePaiement + '\'' +
            ", raison='" + raison + '\'' +
            ", societeId=" + societeId +
            ", operationId=" + operationId +
            ", fournisseurNom='" + fournisseurNom + '\'' +
            ", clientFournisseurId=" + clientFournisseurId +
            ", etatDepenseLibelle='" + etatDepenseLibelle + '\'' +
            ", etatDepenseId=" + etatDepenseId +
            '}';
    }
}
