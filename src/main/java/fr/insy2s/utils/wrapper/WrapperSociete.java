package fr.insy2s.utils.wrapper;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import fr.insy2s.service.dto.*;

public class WrapperSociete {

    //Societe
    private Long        id;
    private String      civilite;

    //Adresse
    private Long        idAdresse;
    private String      numeroRue;
    private String      boitePostale;
    private String      nomRue;
    private String      codePostal;
    private String      ville;
    private String      pays;

    //InfoEntreprise
    private Long        idInfoEntreprise;
    private String      raisonSociale;
    private String      telephone;
    private String      fax;
    private String      formeJuridique;
    private LocalDate   dateDeCreation;
    private String      siren;
    private String      siret;
    private String      domaineDactivite;
    private String      description;
    private String      emailPro;
    private String      codeUrssaf;
    private String      serviceSanteTravail;

    //User
    private Long        idUser;
    private String      login;
    private String      firstName;
    private String      lastName;
    private String      email;
    private String      imageUrl;
    private boolean     activated = false;
    private String      langKey;
    private String      createdBy;
    private Instant     createdDate;
    private String      lastModifiedBy;
    private Instant     lastModifiedDate;
    private Set<String> authorities;

    public WrapperSociete() {
        // empty method
    }

    public WrapperSociete(SocieteDTO societeDTO, AdresseDTO adresseDTO, InfoEntrepriseDTO infoEntrepriseDTO, UserDTO userDTO) {

        //comptable
        this.id = societeDTO.getId();
        this.civilite = societeDTO.getCivilite();

        //adresse
        this.idAdresse = adresseDTO.getId();
        this.numeroRue = adresseDTO.getNumeroRue();
        this.boitePostale = adresseDTO.getBoitePostale();
        this.codePostal = adresseDTO.getCodePostal();
        this.nomRue = adresseDTO.getNomRue();
        this.ville = adresseDTO.getVille();
        this.pays = adresseDTO.getPays();

        //info entreprise
        this.idInfoEntreprise = infoEntrepriseDTO.getId();
        this.raisonSociale = infoEntrepriseDTO.getRaisonSociale();
        this.telephone = infoEntrepriseDTO.getTelephone();
        this.fax = infoEntrepriseDTO.getFax();
        this.formeJuridique = infoEntrepriseDTO.getFormeJuridique();
        this.dateDeCreation = infoEntrepriseDTO.getDateDeCreation();
        this.siren = infoEntrepriseDTO.getSiren();
        this.siret = infoEntrepriseDTO.getSiret();
        this.domaineDactivite = infoEntrepriseDTO.getDomaineDactivite();
        this.description = infoEntrepriseDTO.getDescription();
        this.emailPro = infoEntrepriseDTO.getEmail();
        this.codeUrssaf = infoEntrepriseDTO.getCodeUrssaf();
        this.serviceSanteTravail = infoEntrepriseDTO.getServiceSanteTravail();

        //user
        this.idUser = userDTO.getId();
        this.login = userDTO.getLogin();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.imageUrl = userDTO.getImageUrl();
        this.activated = userDTO.isActivated();
        this.langKey = userDTO.getLangKey();
        this.createdBy = userDTO.getCreatedBy();
        this.createdDate = userDTO.getCreatedDate();
        this.lastModifiedBy = userDTO.getLastModifiedBy();
        this.lastModifiedDate = userDTO.getLastModifiedDate();
        this.authorities = userDTO.getAuthorities();

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

    public Long getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Long idAdresse) {
        this.idAdresse = idAdresse;
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

    public Long getIdInfoEntreprise() {
        return idInfoEntreprise;
    }

    public void setIdInfoEntreprise(Long idInfoEntreprise) {
        this.idInfoEntreprise = idInfoEntreprise;
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

    public String getEmailPro() {
        return emailPro;
    }

    public void setEmailPro(String emailPro) {
        this.emailPro = emailPro;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public String getCodeUrssaf() { return codeUrssaf; }

    public void setCodeUrssaf(String codeUrssaf) { this.codeUrssaf = codeUrssaf; }

    public String getServiceSanteTravail() { return serviceSanteTravail; }

    public void setServiceSanteTravail(String serviceSanteTravail) { this.serviceSanteTravail = serviceSanteTravail; }
}
