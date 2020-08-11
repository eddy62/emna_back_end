package fr.insy2s.utils.wrapper;

import java.time.LocalDate;

public class WrapperSociete {

    //Societe
    private Long      id;
    private String    civilite;

    //adresse
    private Long      adresseId;
    private String    numeroRue;
    private String    nomRue;
    private String    boitePostale;
    private String    codePostal;
    private String    ville;
    private String    pays;

    //InfoEntreprise
    private Long      infoEntrepriseId;
    private String    raisonSociale;
    private String    telephone;
    private String    fax;
    private String    formeJuridique;
    private LocalDate dateDeCreation;
    private String    siren;
    private String    siret;
    private String    domaineDactivite;
    private String    description;
    private String    email;

    //private Long      userId;
    //private Long      comptableId;

    public WrapperSociete() {
        // empty method
    }

    /**
     * @param id
     * @param civilite
     * @param adresseId
     * @param numeroRue
     * @param nomRue
     * @param boitePostale
     * @param codePostal
     * @param ville
     * @param pays
     * @param infoEntrepriseId
     * @param raisonSociale
     * @param telephone
     * @param fax
     * @param formeJuridique
     * @param dateDeCreation
     * @param siren
     * @param siret
     * @param domaineDactivite
     * @param description
     * @param email
     */
    public WrapperSociete(Long id, String civilite, Long adresseId, String numeroRue, String nomRue, String boitePostale, String codePostal, String ville, String pays, Long infoEntrepriseId,
                    String raisonSociale, String telephone, String fax, String formeJuridique, LocalDate dateDeCreation, String siren, String siret, String domaineDactivite, String description,
                    String email) {
        super();
        this.id = id;
        this.civilite = civilite;
        this.adresseId = adresseId;
        this.numeroRue = numeroRue;
        this.nomRue = nomRue;
        this.boitePostale = boitePostale;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.infoEntrepriseId = infoEntrepriseId;
        this.raisonSociale = raisonSociale;
        this.telephone = telephone;
        this.fax = fax;
        this.formeJuridique = formeJuridique;
        this.dateDeCreation = dateDeCreation;
        this.siren = siren;
        this.siret = siret;
        this.domaineDactivite = domaineDactivite;
        this.description = description;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
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

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public String getBoitePostale() {
        return boitePostale;
    }

    public void setBoitePostale(String boitePostale) {
        this.boitePostale = boitePostale;
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

    public Long getInfoEntrepriseId() {
        return infoEntrepriseId;
    }

    public void setInfoEntrepriseId(Long infoEntrepriseId) {
        this.infoEntrepriseId = infoEntrepriseId;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFormeJuridique() {
        return formeJuridique;
    }

    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public LocalDate getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(LocalDate dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getDomaineDactivite() {
        return domaineDactivite;
    }

    public void setDomaineDactivite(String domaineDactivite) {
        this.domaineDactivite = domaineDactivite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
