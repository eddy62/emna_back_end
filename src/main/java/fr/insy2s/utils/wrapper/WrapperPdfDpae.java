package fr.insy2s.utils.wrapper;

import java.time.LocalDate;
import java.time.LocalTime;

public class WrapperPdfDpae {

    public WrapperPdfDpae() {

    }

    private Long id;
    // employeur
    private String designation; // Dénomination
    private String siret; // n° SIRET
    private String apeCode; // Code NAF
    private String urssafCode; // Code Urssaf
    private String streetDesignation; // Adresse de l'établissement
    private String town; // Commune
    private String postalCode; // Code postal
    private String phoneNumber; // n° de téléphone

    // salarie
    private String surname; // Nom
    private String christianName; // Prénoms
    private String sex; // Sexe
    private String nir; // n° Sécurité sociale
    private String nirKey; //
    private LocalDate birthDate; // Date de naissance
    private String birthTown; // Commune de naissance
    private String departmentBirth; // Département de naissance
    private String countryBirth; // Pays de naissance

    // contrat
    private LocalDate startContractDate; // Date d'embauche
    private String startContractTime; // Heure d'embauche
    private String nature; // Type de contrat
    private LocalDate endContractDate; // Fin du contrat
    private Double trialTime; // Durée de la période d'essai
    private String healthService; // Service de santé au travail
    private String comment; // Commentaire

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getApeCode() {
        return apeCode;
    }

    public void setApeCode(String apeCode) {
        this.apeCode = apeCode;
    }

    public String getUrssafCode() {
        return urssafCode;
    }

    public void setUrssafCode(String urssafCode) {
        this.urssafCode = urssafCode;
    }

    public String getStreetDesignation() {
        return streetDesignation;
    }

    public void setStreetDesignation(String streetDesignation) {
        this.streetDesignation = streetDesignation;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getChristianName() {
        return christianName;
    }

    public void setChristianName(String christianName) {
        this.christianName = christianName;
    }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    public String getNir() {
        return nir;
    }

    public void setNir(String nir) {
        this.nir = nir;
    }

    public String getNirKey() {
        return nirKey;
    }

    public void setNirKey(String nirKey) {
        this.nirKey = nirKey;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthTown() {
        return birthTown;
    }

    public void setBirthTown(String birthTown) {
        this.birthTown = birthTown;
    }

    public String getDepartmentBirth() {
        return departmentBirth;
    }

    public void setDepartmentBirth(String departmentBirth) {
        this.departmentBirth = departmentBirth;
    }

    public String getCountryBirth() {
        return countryBirth;
    }

    public void setCountryBirth(String countryBirth) {
        this.countryBirth = countryBirth;
    }

    public LocalDate getStartContractDate() {
        return startContractDate;
    }

    public void setStartContractDate(LocalDate startContractDate) {
        this.startContractDate = startContractDate;
    }

    public String getStartContractTime() { return startContractTime; }

    public void setStartContractTime(String startContractTime) { this.startContractTime = startContractTime; }

    public String getNature() { return nature; }

    public void setNature(String nature) { this.nature = nature; }

    public LocalDate getEndContractDate() {
        return endContractDate;
    }

    public void setEndContractDate(LocalDate endContractDate) {
        this.endContractDate = endContractDate;
    }

    public Double getTrialTime() { return trialTime; }

    public void setTrialTime(Double trialTime) { this.trialTime = trialTime; }

    public String getHealthService() {
        return healthService;
    }

    public void setHealthService(String healthService) {
        this.healthService = healthService;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

