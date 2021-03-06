package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.*;

import java.time.LocalDate;
import java.util.Date;

public class WrapperDpae {

    //dpae
    private Long id;
    private String lieu;
    private LocalDate date;
    private Long employeId;
    private String heureEmbauche;
    private String commentaire;
    private String retourApiUrssaf;

    //societe
    private Long societeId;
    private String civiliteSociete;

    //info entreprise
    private Long infoEntrepriseId;
    private String raisonSociale; // Dénomination
    private String telephone; // n° de téléphone
    private String fax;
    private String siret; // n° SIRET
    private String domaineDactivite; // Code NAF
    private String codeUrssaf; // Code Urssaf
    private String serviceSanteTravail;

    //adresse
    private Long idAdresse;
    private String numeroRue;
    private String nomRue; // Adresse de l'établissement
    private String codePostal; // Code postal
    private String ville; // Commune

    //employe
    private Long idEmploye;
    private String civilite;
    private String nomNaissance;
    private String nomUsage;
    private String prenom;
    private LocalDate dateNaissance;
    private String villeNaissance;
    private String departementNaissance;
    private String paysNaissance;
    private String numeroSecuriteSociale;
    // TODO supprimer ?
    private LocalDate dateEmbauche;
    // TODO supprimer ?
    private LocalDate dateSortie;
    private Integer periodeEssai;

    //contrat
    private Long idContrat;
    private String titre;
    private LocalDate dateCreation;
    private Boolean signe;
    private Boolean archive;

    //type contrat
    private Long idTypeContrat;
    private String codeRef;
    private String intitule;

    //saisie article
    private LocalDate dateDebut;

    public WrapperDpae(){

    };

    public WrapperDpae (DpaeDTO dpaeDTO, SocieteDTO societeDTO, InfoEntrepriseDTO infoEntrepriseDTO, EmployeDTO employeDTO, AdresseDTO adresseDTO, ContratDTO contratDTO, TypeContratDTO typeContratDTO, SaisieArticleDTO saisieArticleDTO) {
        //dpae
        this.id = dpaeDTO.getId();
        this.lieu = dpaeDTO.getLieu();
        this.date = dpaeDTO.getDate();
        this.heureEmbauche = dpaeDTO.getHeureEmbauche();
        this.commentaire = dpaeDTO.getCommentaire();
        this.retourApiUrssaf = dpaeDTO.getRetourApiUrssaf();

        //societe
        this.societeId = societeDTO.getId();
        this.civiliteSociete = societeDTO.getCivilite();

        //info entreprise
        this.infoEntrepriseId = infoEntrepriseDTO.getId();
        this.raisonSociale = infoEntrepriseDTO.getRaisonSociale();
        this.telephone = infoEntrepriseDTO.getTelephone();
        this.fax = infoEntrepriseDTO.getFax();
        this.siret = infoEntrepriseDTO.getSiret();
        this.domaineDactivite = infoEntrepriseDTO.getDomaineDactivite();
        this.codeUrssaf = infoEntrepriseDTO.getCodeUrssaf();
        this.serviceSanteTravail = infoEntrepriseDTO.getServiceSanteTravail();

        //adresse
        this.idAdresse = adresseDTO.getId();
        this.numeroRue = adresseDTO.getNumeroRue();
        this.nomRue = adresseDTO.getNomRue();
        this.codePostal = adresseDTO.getCodePostal();
        this.ville = adresseDTO.getVille();

        //salarie
        this.idEmploye = employeDTO.getId();
        this.civilite = employeDTO.getCivilite();
        this.nomNaissance = employeDTO.getNomNaissance();
        this.nomUsage = employeDTO.getNomUsage();
        this.prenom = employeDTO.getPrenom();
        this.dateNaissance =employeDTO.getDateNaissance();
        this.villeNaissance = employeDTO.getVilleNaissance();
        this.departementNaissance = employeDTO.getDepartementNaissance();
        this.paysNaissance = employeDTO.getPaysNaissance();
        this.numeroSecuriteSociale = employeDTO.getNumeroSecuriteSociale();
        //plus utilisé, cf. dateDebut
        this.dateEmbauche = employeDTO.getDateEmbauche();
        // TODO à faire (plus utilisé, cf. dateFin)
        this.dateSortie = employeDTO.getDateSortie();
        this.periodeEssai = employeDTO.getPeriodeEssai();

        //contrat
        this.idContrat = contratDTO.getId();
        this.titre = contratDTO.getTitre();
        this.dateCreation = contratDTO.getDateCreation();
        this.signe = contratDTO.isSigne();
        this.archive = contratDTO.isArchive();

        //type contrat
        this.idTypeContrat = typeContratDTO.getId();
        this.codeRef = typeContratDTO.getCodeRef();
        this.intitule = typeContratDTO.getIntitule();

        //saisie article
        this.dateDebut = LocalDate.parse(saisieArticleDTO.getLibelle());
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

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
    }

    public String getCiviliteSociete() {
        return civiliteSociete;
    }

    public void setCiviliteSociete(String civiliteSociete) {
        this.civiliteSociete = civiliteSociete;
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

    public Long getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Long idEmploye) {
        this.idEmploye = idEmploye;
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

    public Long getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Long idContrat) {
        this.idContrat = idContrat;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getSigne() {
        return signe;
    }

    public void setSigne(Boolean signe) {
        this.signe = signe;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public Long getIdTypeContrat() {
        return idTypeContrat;
    }

    public void setIdTypeContrat(Long idTypeContrat) {
        this.idTypeContrat = idTypeContrat;
    }

    public String getCodeRef() {
        return codeRef;
    }

    public void setCodeRef(String codeRef) {
        this.codeRef = codeRef;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public LocalDate getDateDebut() { return dateDebut; }

    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

}
