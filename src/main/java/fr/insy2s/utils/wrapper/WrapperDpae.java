package fr.insy2s.utils.wrapper;


import fr.insy2s.service.dto.*;

import java.time.LocalDate;

public class WrapperDpae {

    //dpae
    private Long id;
    private String lieu;
    private LocalDate date;
    private Long employeId;
    private String heureEmbauche;
    private String commentaire;
    private String retourApiUrssaf;


    // entreprise
    private String raisonSociale; // Dénomination
    private String telephone; // n° de téléphone
    private String fax;
    private String siret; // n° SIRET
    private String domaineDactivite; // Code NAF
    private String codeUrssaf; // Code Urssaf
    private String serviceSanteTravail;


    //adresse
    private String numeroRue;
    private String nomRue; // Adresse de l'établissement
    private String codePostal; // Code postal
    private String ville;// Commune



    // employe
    private String civilite;
    private String nomNaissance;
    private String nomUsage;
    private String prenom;
    private LocalDate dateNaissance;
    private String villeNaissance;
    private String departementNaissance;
    private String paysNaissance;
    private String numeroSecuriteSociale;
    private LocalDate dateEmbauche;
    private LocalDate dateSortie;
    private Integer periodeEssai;

    //Type contrat
    private String codeRef;



    public WrapperDpae(){

    };

    public WrapperDpae (DpaeDTO dpaeDTO, InfoEntrepriseDTO infoEntrepriseDTO, EmployeDTO employeDTO, AdresseDTO adresseDTO, TypeContratDTO typeContratDTO) {


        //info dpae
        this.id = dpaeDTO.getId();
        this.lieu = dpaeDTO.getLieu();
        this.date = dpaeDTO.getDate();
        //this.employeId = dpaeDTO.getEmployeId();
        this.heureEmbauche = dpaeDTO.getHeureEmbauche();
        this.commentaire = dpaeDTO.getCommentaire();
        this.retourApiUrssaf = dpaeDTO.getRetourApiUrssaf();


        //info entreprise
        this.raisonSociale = infoEntrepriseDTO.getRaisonSociale();
        this.telephone = infoEntrepriseDTO.getTelephone();
        this.fax = infoEntrepriseDTO.getFax();
        this.siret = infoEntrepriseDTO.getSiret();
        this.domaineDactivite = infoEntrepriseDTO.getDomaineDactivite();
        this.codeUrssaf = infoEntrepriseDTO.getCodeUrssaf();
        this.serviceSanteTravail = infoEntrepriseDTO.getServiceSanteTravail();

        //adresse
        this.numeroRue = adresseDTO.getNumeroRue();
        this.nomRue = adresseDTO.getNomRue();
        this.codePostal = adresseDTO.getCodePostal();
        this.ville = adresseDTO.getVille();


        // info salarie
        this.civilite = employeDTO.getCivilite();
        this.nomNaissance = employeDTO.getNomNaissance();
        this.nomUsage = employeDTO.getNomUsage();
        this.prenom = employeDTO.getPrenom();
        this.dateNaissance =employeDTO.getDateNaissance();
        this.villeNaissance = employeDTO.getVilleNaissance();
        this.departementNaissance = employeDTO.getDepartementNaissance();
        this.paysNaissance = employeDTO.getPaysNaissance();
        this.numeroSecuriteSociale = employeDTO.getNumeroSecuriteSociale();
        this.dateEmbauche = employeDTO.getDateEmbauche();
        this.dateSortie = employeDTO.getDateSortie();
        this.periodeEssai = employeDTO.getPeriodeEssai();

        // info contrat
        this.codeRef = typeContratDTO.getCodeRef();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public String getHeureEmbauche() {
        return heureEmbauche;
    }

    public void setHeureEmbauche(String heureEmbauche) {
        this.heureEmbauche = heureEmbauche;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getRetourApiUrssaf() {
        return retourApiUrssaf;
    }

    public void setRetourApiUrssaf(String retourApiUrssaf) {
        this.retourApiUrssaf = retourApiUrssaf;
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

    public String getCodeUrssaf() {
        return codeUrssaf;
    }

    public void setCodeUrssaf(String codeUrssaf) {
        this.codeUrssaf = codeUrssaf;
    }

    public String getServiceSanteTravail() {
        return serviceSanteTravail;
    }

    public void setServiceSanteTravail(String serviceSanteTravail) {
        this.serviceSanteTravail = serviceSanteTravail;
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

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNomNaissance() {
        return nomNaissance;
    }

    public void setNomNaissance(String nomNaissance) {
        this.nomNaissance = nomNaissance;
    }

    public String getNomUsage() {
        return nomUsage;
    }

    public void setNomUsage(String nomUsage) {
        this.nomUsage = nomUsage;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getVilleNaissance() {
        return villeNaissance;
    }

    public void setVilleNaissance(String villeNaissance) {
        this.villeNaissance = villeNaissance;
    }

    public String getDepartementNaissance() {
        return departementNaissance;
    }

    public void setDepartementNaissance(String departementNaissance) {
        this.departementNaissance = departementNaissance;
    }

    public String getPaysNaissance() {
        return paysNaissance;
    }

    public void setPaysNaissance(String paysNaissance) {
        this.paysNaissance = paysNaissance;
    }

    public String getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    public void setNumeroSecuriteSociale(String numeroSecuriteSociale) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public Integer getPeriodeEssai() {
        return periodeEssai;
    }

    public void setPeriodeEssai(Integer periodeEssai) {
        this.periodeEssai = periodeEssai;
    }

    public String getCodeRef() {
        return codeRef;
    }

    public void setCodeRef(String codeRef) {
        this.codeRef = codeRef;
    }
}
