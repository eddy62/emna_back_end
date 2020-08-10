package fr.insy2s.utils.wrapper;

import fr.insy2s.config.Constants;
import fr.insy2s.service.dto.AdresseDTO;
import fr.insy2s.service.dto.ComptableDTO;
import fr.insy2s.service.dto.InfoEntrepriseDTO;
import fr.insy2s.service.dto.UserDTO;

import javax.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

public class WrapperComptable {



    //comptable
    private Long id;
    private String civilite;

    //adresse
    private Long idAdresse;
    private String numeroRue;
    private String boitePostale;
    private String nomRue;
    private String codePostal;
    private String ville;

    //info entreprise
    private Long idInfoEntreprise;
    private String raisonSociale;
    private String telephone;
    private String fax;
    private String formeJuridique;
    private LocalDate dateDeCreation;
    private String siren;
    private String siret;
    private String domaineDactivite;
    private String description;
    private String emailPro;


    //user
    private Long idUser;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;
    private boolean activated = false;
    private String langKey;
    private String createdBy;
    private Instant createdDate;
    private String lastModifiedBy;
    private Instant lastModifiedDate;
    private Set<String> authorities;


    public  WrapperComptable(ComptableDTO comptableDTO , AdresseDTO adresseDTO, InfoEntrepriseDTO infoEntrepriseDTO, UserDTO userDTO){

        //comptable
        this.id = comptableDTO.getId();
        this.civilite = comptableDTO.getCivilite();


        //adresse
        this.idAdresse = adresseDTO.getId();
        this.numeroRue = adresseDTO.getNumeroRue();
        this.boitePostale = adresseDTO.getBoitePostale();
        this.codePostal = adresseDTO.getCodePostal();
        this.nomRue = adresseDTO.getNomRue();
        this.ville = adresseDTO.getVille();


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

    public String getCivilite() {
        return civilite;
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

    public String getVille() {
        return ville;
    }

    public Long getIdInfoEntreprise() {
        return idInfoEntreprise;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getFax() {
        return fax;
    }

    public String getFormeJuridique() {
        return formeJuridique;
    }

    public LocalDate getDateDeCreation() {
        return dateDeCreation;
    }

    public String getSiren() {
        return siren;
    }

    public String getSiret() {
        return siret;
    }

    public String getDomaineDactivite() {
        return domaineDactivite;
    }

    public String getDescription() {
        return description;
    }

    public String getEmailPro() {
        return emailPro;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }
}
