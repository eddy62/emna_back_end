package fr.insy2s.utils.wrapper;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class WrapperClientFournisseur {



    private  Long idSociete ;
    private Long id ;
    private String nom;
    private Integer siren;
    private String telephone;
    private String email;

    //info adresse
    private Long idAdresse;
    private String numeroRue;
    private String boitePostale;
    private String nomRue;
    private String codePostal;
    private String ville;

    public WrapperClientFournisseur() {
    }

    public WrapperClientFournisseur(Long idSociete, Long id, String nom, Integer siren, String telephone, String email, Long idAdresse, String numeroRue,
                                    String boitePostale, String nomRue, String codePostal, String ville) {
        this.idSociete = idSociete;
        this.id = id;
        this.nom = nom;
        this.siren = siren;
        this.telephone = telephone;
        this.email = email;
        this.idAdresse = idAdresse;
        this.numeroRue = numeroRue;
        this.boitePostale = boitePostale;
        this.nomRue = nomRue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Long getIdSociete() {
        return idSociete;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Integer getSiren() {
        return siren;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public Long getIdAdresse() {
        return idAdresse;
    }

    public String getNumeroRue() {
        return numeroRue;
    }

    public String getBoitePostale() {
        return boitePostale;
    }

    public String getNomRue() {
        return nomRue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setIdSociete(Long idSociete) {
        this.idSociete = idSociete;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSiren(Integer siren) {
        this.siren = siren;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdAdresse(Long idAdresse) {
        this.idAdresse = idAdresse;
    }

    public void setNumeroRue(String numeroRue) {
        this.numeroRue = numeroRue;
    }

    public void setBoitePostale(String boitePostale) {
        this.boitePostale = boitePostale;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getVille() {
        return ville;
    }

    @Override
    public String toString() {
        return "WrapperClientFournisseur{" +
            "idSociete=" + idSociete +
            ", id=" + id +
            ", nom='" + nom + '\'' +
            ", siren=" + siren +
            ", telephone='" + telephone + '\'' +
            ", email='" + email + '\'' +
            ", idAdresse=" + idAdresse +
            ", numeroRue='" + numeroRue + '\'' +
            ", boitePostale='" + boitePostale + '\'' +
            ", nomRue='" + nomRue + '\'' +
            ", codePostal='" + codePostal + '\'' +
            ", ville='" + ville + '\'' +
            '}';
    }
}
