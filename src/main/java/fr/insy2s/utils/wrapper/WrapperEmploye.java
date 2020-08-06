package fr.insy2s.utils.wrapper;

import java.time.LocalDate;

public class WrapperEmploye {

    //Employe
    private Long      id;
    private String    matricule;
    private String    civilite;
    private String    nomNaissance;
    private String    nomUsage;
    private String    prenom;
    private LocalDate dateNaissance;
    private String    villeNaissance;
    private String    departementNaissance;
    private String    paysNaisance;
    private String    numeroSecuriteSociale;
    private String    email;
    private String    telephoneFix;
    private String    telephonePortable;
    private String    fax;
    private Double    salaireHoraire;
    private Double    salaireBrutMensuelle;
    private Double    heuresMensuelle;
    private String    categorie;
    private String    poste;
    private LocalDate dateEmbauche;
    private LocalDate dateSortie;
    private String    typeContrat;
    private String    situationFamiliale;
    private Integer   enfantsACharge;

    //Adresse
    private Long      adresseId;
    private String    numeroRue;
    private String    nomRue;
    private String    boitePostale;
    private String    codePostal;
    private String    ville;
    private String    pays;

    //Statut
    private Long      statutEmployeId;
    private String    codeRef;
    private String    libelle;

    //Sociéte
    private Long      societeId;

    //InfosEntreprise
    private Long      infoEntrepriseId;
    private String    raisonSociale;

    /**
     * Constructeur par defaut
     */
    public WrapperEmploye() {
        //empty method

    }

    /**
     * Constructeur avec field
     * 
     * @param id
     * @param matricule
     * @param civilite
     * @param nomNaissance
     * @param nomUsage
     * @param prenom
     * @param dateNaissance
     * @param villeNaissance
     * @param departementNaissance
     * @param paysNaisance
     * @param numeroSecuriteSociale
     * @param email
     * @param telephoneFix
     * @param telephonePortable
     * @param fax
     * @param salaireHoraire
     * @param salaireBrutMensuelle
     * @param heuresMensuelle
     * @param categorie
     * @param poste
     * @param dateEmbauche
     * @param dateSortie
     * @param typeContrat
     * @param situationFamiliale
     * @param enfantsACharge
     * @param adresseId
     * @param numeroRue
     * @param nomRue
     * @param boitePostale
     * @param codePostal
     * @param ville
     * @param pays
     * @param statutEmployeId
     * @param codeRef
     * @param libelle
     * @param societeId
     * @param infoEntrepriseId
     * @param raisonSociale
     */
    public WrapperEmploye(Long id, String matricule, String civilite, String nomNaissance, String nomUsage, String prenom, LocalDate dateNaissance, String villeNaissance, String departementNaissance,
                    String paysNaisance, String numeroSecuriteSociale, String email, String telephoneFix, String telephonePortable, String fax, Double salaireHoraire, Double salaireBrutMensuelle,
                    Double heuresMensuelle, String categorie, String poste, LocalDate dateEmbauche, LocalDate dateSortie, String typeContrat, String situationFamiliale, Integer enfantsACharge,
                    Long adresseId, String numeroRue, String nomRue, String boitePostale, String codePostal, String ville, String pays, Long statutEmployeId, String codeRef, String libelle,
                    Long societeId, Long infoEntrepriseId, String raisonSociale) {
        super();
        this.id = id;
        this.matricule = matricule;
        this.civilite = civilite;
        this.nomNaissance = nomNaissance;
        this.nomUsage = nomUsage;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.villeNaissance = villeNaissance;
        this.departementNaissance = departementNaissance;
        this.paysNaisance = paysNaisance;
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        this.email = email;
        this.telephoneFix = telephoneFix;
        this.telephonePortable = telephonePortable;
        this.fax = fax;
        this.salaireHoraire = salaireHoraire;
        this.salaireBrutMensuelle = salaireBrutMensuelle;
        this.heuresMensuelle = heuresMensuelle;
        this.categorie = categorie;
        this.poste = poste;
        this.dateEmbauche = dateEmbauche;
        this.dateSortie = dateSortie;
        this.typeContrat = typeContrat;
        this.situationFamiliale = situationFamiliale;
        this.enfantsACharge = enfantsACharge;
        this.adresseId = adresseId;
        this.numeroRue = numeroRue;
        this.nomRue = nomRue;
        this.boitePostale = boitePostale;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.statutEmployeId = statutEmployeId;
        this.codeRef = codeRef;
        this.libelle = libelle;
        this.societeId = societeId;
        this.infoEntrepriseId = infoEntrepriseId;
        this.raisonSociale = raisonSociale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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

    public String getPaysNaisance() {
        return paysNaisance;
    }

    public void setPaysNaisance(String paysNaisance) {
        this.paysNaisance = paysNaisance;
    }

    public String getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    public void setNumeroSecuriteSociale(String numeroSecuriteSociale) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneFix() {
        return telephoneFix;
    }

    public void setTelephoneFix(String telephoneFix) {
        this.telephoneFix = telephoneFix;
    }

    public String getTelephonePortable() {
        return telephonePortable;
    }

    public void setTelephonePortable(String telephonePortable) {
        this.telephonePortable = telephonePortable;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Double getSalaireHoraire() {
        return salaireHoraire;
    }

    public void setSalaireHoraire(Double salaireHoraire) {
        this.salaireHoraire = salaireHoraire;
    }

    public Double getSalaireBrutMensuelle() {
        return salaireBrutMensuelle;
    }

    public void setSalaireBrutMensuelle(Double salaireBrutMensuelle) {
        this.salaireBrutMensuelle = salaireBrutMensuelle;
    }

    public Double getHeuresMensuelle() {
        return heuresMensuelle;
    }

    public void setHeuresMensuelle(Double heuresMensuelle) {
        this.heuresMensuelle = heuresMensuelle;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
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

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public Integer getEnfantsACharge() {
        return enfantsACharge;
    }

    public void setEnfantsACharge(Integer enfantsACharge) {
        this.enfantsACharge = enfantsACharge;
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

    public Long getStatutEmployeId() {
        return statutEmployeId;
    }

    public void setStatutEmployeId(Long statutEmployeId) {
        this.statutEmployeId = statutEmployeId;
    }

    public String getCodeRef() {
        return codeRef;
    }

    public void setCodeRef(String codeRef) {
        this.codeRef = codeRef;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
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

}
