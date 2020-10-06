package fr.insy2s.utils.wrapper;import fr.insy2s.service.dto.*;import java.math.BigDecimal;import java.time.LocalDate;/** * WrapperEmploye * * @author De ? et Cédric Belot */public class WrapperEmploye {    //employe    private Long id;    private String matricule;    private String civilite;    private String nomNaissance;    private String nomUsage;    private String prenom;    private LocalDate dateNaissance;    private String villeNaissance;    private String departementNaissance;    private String paysNaisance;    private String numeroSecuriteSociale;    private String email;    private String telephoneFix;    private String telephonePortable;    private String fax;    private BigDecimal salaireHoraire;    private BigDecimal salaireBrutMensuelle;    private BigDecimal heuresMensuelle;    private String categorie;    private String poste;    private LocalDate dateEmbauche;    private LocalDate dateSortie;    private String situationFamiliale;    private Integer enfantsACharge;    private Double periodeEssai;    //adresse    private Long adresseId;    private String numeroRue;    private String nomRue;    private String boitePostale;    private String codePostal;    private String ville;    private String pays;    //statutEmploye    private Long statutEmployeId;    private String codeRefStatut;    private String libelle;    //societe    private Long societeId;    //infoEntreprise    private Long infoEntrepriseId;    private String raisonSociale;    //wrapperContrat    //contrat    private Long idContrat;    private String titre;    private LocalDate dateCreation;    private Boolean signe;    private Boolean archive;    //typeContrat    private Long idTypeContrat;    private String codeRefContrat;    private String intitule;    /**     * Empty constructor     */    public WrapperEmploye() {        //empty method    }    /**     * Parameterized constructor     *     * @param employeDTO        the DTO containing employe attributes     * @param adresseDTO        the DTO containing adresse attributes     * @param statutEmployeDTO  the DTO containing statutEmploye attributes     * @param societeDTO        the DTO containing societe attributes     * @param infoEntrepriseDTO the DTO containing infoEntreprise attributes     * @param wrapperContrat    the wrapper containing contrat and typeContrat attributes     */    public WrapperEmploye(EmployeDTO employeDTO, AdresseDTO adresseDTO, StatutEmployeDTO statutEmployeDTO, SocieteDTO societeDTO, InfoEntrepriseDTO infoEntrepriseDTO, WrapperContrat wrapperContrat) {        super();        //employe        this.id = employeDTO.getId();        this.matricule = employeDTO.getMatricule();        this.civilite = employeDTO.getCivilite();        this.nomNaissance = employeDTO.getNomNaissance();        this.nomUsage = employeDTO.getNomUsage();        this.prenom = employeDTO.getPrenom();        this.dateNaissance = employeDTO.getDateNaissance();        this.villeNaissance = employeDTO.getVilleNaissance();        this.departementNaissance = employeDTO.getDepartementNaissance();        this.paysNaisance = employeDTO.getPaysNaisance();        this.numeroSecuriteSociale = employeDTO.getNumeroSecuriteSociale();        this.email = employeDTO.getEmail();        this.telephoneFix = employeDTO.getTelephoneFix();        this.telephonePortable = employeDTO.getTelephonePortable();        this.fax = employeDTO.getFax();        this.salaireHoraire = employeDTO.getSalaireHoraire();        this.salaireBrutMensuelle = employeDTO.getSalaireBrutMensuelle();        this.heuresMensuelle = employeDTO.getHeuresMensuelle();        this.categorie = employeDTO.getCategorie();        this.poste = employeDTO.getPoste();        this.dateEmbauche = employeDTO.getDateEmbauche();        this.dateSortie = employeDTO.getDateSortie();        this.situationFamiliale = employeDTO.getSituationFamiliale();        this.enfantsACharge = employeDTO.getEnfantsACharge();        this.periodeEssai = employeDTO.getPeriodeEssai();        //adresse        this.adresseId = employeDTO.getAdresseId();        this.numeroRue = adresseDTO.getNumeroRue();        this.nomRue = adresseDTO.getNomRue();        this.boitePostale = adresseDTO.getBoitePostale();        this.codePostal = adresseDTO.getCodePostal();        this.ville = adresseDTO.getVille();        this.pays = adresseDTO.getPays();        //statutEmploye        this.statutEmployeId = employeDTO.getStatutEmployeId();        this.codeRefStatut = statutEmployeDTO.getCodeRef();        this.libelle = statutEmployeDTO.getLibelle();        //societe        this.societeId = employeDTO.getSocieteId();        //infoEntreprise        this.infoEntrepriseId = societeDTO.getInfoEntrepriseId();        this.raisonSociale = infoEntrepriseDTO.getRaisonSociale();        //wrapperContrat        //contrat        this.idContrat = wrapperContrat.getId();        this.titre = wrapperContrat.getTitre();        this.dateCreation = wrapperContrat.getDateCreation();        this.signe = wrapperContrat.getSigne();        this.archive = wrapperContrat.getArchive();        //typeContrat        this.idTypeContrat = wrapperContrat.getIdTypeContrat();        this.codeRefContrat = wrapperContrat.getCodeRef();        this.intitule = wrapperContrat.getIntitule();    }    // getters setters    public Long getId() {        return id;    }    public void setId(Long id) {        this.id = id;    }    public String getMatricule() {        return matricule;    }    public void setMatricule(String matricule) {        this.matricule = matricule;    }    public String getCivilite() {        return civilite;    }    public void setCivilite(String civilite) {        this.civilite = civilite;    }    public String getNomNaissance() {        return nomNaissance;    }    public void setNomNaissance(String nomNaissance) {        this.nomNaissance = nomNaissance;    }    public String getNomUsage() {        return nomUsage;    }    public void setNomUsage(String nomUsage) {        this.nomUsage = nomUsage;    }    public String getPrenom() {        return prenom;    }    public void setPrenom(String prenom) {        this.prenom = prenom;    }    public LocalDate getDateNaissance() {        return dateNaissance;    }    public void setDateNaissance(LocalDate dateNaissance) {        this.dateNaissance = dateNaissance;    }    public String getVilleNaissance() {        return villeNaissance;    }    public void setVilleNaissance(String villeNaissance) {        this.villeNaissance = villeNaissance;    }    public String getDepartementNaissance() {        return departementNaissance;    }    public void setDepartementNaissance(String departementNaissance) {        this.departementNaissance = departementNaissance;    }    public String getPaysNaisance() {        return paysNaisance;    }    public void setPaysNaisance(String paysNaisance) {        this.paysNaisance = paysNaisance;    }    public String getNumeroSecuriteSociale() {        return numeroSecuriteSociale;    }    public void setNumeroSecuriteSociale(String numeroSecuriteSociale) {        this.numeroSecuriteSociale = numeroSecuriteSociale;    }    public String getEmail() {        return email;    }    public void setEmail(String email) {        this.email = email;    }    public String getTelephoneFix() {        return telephoneFix;    }    public void setTelephoneFix(String telephoneFix) {        this.telephoneFix = telephoneFix;    }    public String getTelephonePortable() {        return telephonePortable;    }    public void setTelephonePortable(String telephonePortable) {        this.telephonePortable = telephonePortable;    }    public String getFax() {        return fax;    }    public void setFax(String fax) {        this.fax = fax;    }    public BigDecimal getSalaireHoraire() {        return salaireHoraire;    }    public void setSalaireHoraire(BigDecimal salaireHoraire) {        this.salaireHoraire = salaireHoraire;    }    public BigDecimal getSalaireBrutMensuelle() {        return salaireBrutMensuelle;    }    public void setSalaireBrutMensuelle(BigDecimal salaireBrutMensuelle) {        this.salaireBrutMensuelle = salaireBrutMensuelle;    }    public BigDecimal getHeuresMensuelle() {        return heuresMensuelle;    }    public void setHeuresMensuelle(BigDecimal heuresMensuelle) {        this.heuresMensuelle = heuresMensuelle;    }    public String getCategorie() {        return categorie;    }    public void setCategorie(String categorie) {        this.categorie = categorie;    }    public String getPoste() {        return poste;    }    public void setPoste(String poste) {        this.poste = poste;    }    public LocalDate getDateEmbauche() {        return dateEmbauche;    }    public void setDateEmbauche(LocalDate dateEmbauche) {        this.dateEmbauche = dateEmbauche;    }    public LocalDate getDateSortie() {        return dateSortie;    }    public void setDateSortie(LocalDate dateSortie) {        this.dateSortie = dateSortie;    }    public String getSituationFamiliale() {        return situationFamiliale;    }    public void setSituationFamiliale(String situationFamiliale) {        this.situationFamiliale = situationFamiliale;    }    public Integer getEnfantsACharge() {        return enfantsACharge;    }    public void setEnfantsACharge(Integer enfantsACharge) {        this.enfantsACharge = enfantsACharge;    }    public Double getPeriodeEssai() {        return periodeEssai;    }    public void setPeriodeEssai(Double periodeEssai) {        this.periodeEssai = periodeEssai;    }    public Long getAdresseId() {        return adresseId;    }    public void setAdresseId(Long adresseId) {        this.adresseId = adresseId;    }    public String getNumeroRue() {        return numeroRue;    }    public void setNumeroRue(String numeroRue) {        this.numeroRue = numeroRue;    }    public String getNomRue() {        return nomRue;    }    public void setNomRue(String nomRue) {        this.nomRue = nomRue;    }    public String getBoitePostale() {        return boitePostale;    }    public void setBoitePostale(String boitePostale) {        this.boitePostale = boitePostale;    }    public String getCodePostal() {        return codePostal;    }    public void setCodePostal(String codePostal) {        this.codePostal = codePostal;    }    public String getVille() {        return ville;    }    public void setVille(String ville) {        this.ville = ville;    }    public String getPays() {        return pays;    }    public void setPays(String pays) {        this.pays = pays;    }    public Long getStatutEmployeId() {        return statutEmployeId;    }    public void setStatutEmployeId(Long statutEmployeId) {        this.statutEmployeId = statutEmployeId;    }    public String getCodeRefStatut() {        return codeRefStatut;    }    public void setCodeRefStatut(String codeRefStatut) {        this.codeRefStatut = codeRefStatut;    }    public String getLibelle() {        return libelle;    }    public void setLibelle(String libelle) {        this.libelle = libelle;    }    public Long getSocieteId() {        return societeId;    }    public void setSocieteId(Long societeId) {        this.societeId = societeId;    }    public Long getInfoEntrepriseId() {        return infoEntrepriseId;    }    public void setInfoEntrepriseId(Long infoEntrepriseId) {        this.infoEntrepriseId = infoEntrepriseId;    }    public String getRaisonSociale() {        return raisonSociale;    }    public void setRaisonSociale(String raisonSociale) {        this.raisonSociale = raisonSociale;    }    public Long getIdContrat() {        return idContrat;    }    public void setIdContrat(Long idContrat) {        this.idContrat = idContrat;    }    public String getTitre() {        return titre;    }    public void setTitre(String titre) {        this.titre = titre;    }    public LocalDate getDateCreation() {        return dateCreation;    }    public void setDateCreation(LocalDate dateCreation) {        this.dateCreation = dateCreation;    }    public Boolean getSigne() {        return signe;    }    public void setSigne(Boolean signe) {        this.signe = signe;    }    public Boolean getArchive() {        return archive;    }    public void setArchive(Boolean archive) {        this.archive = archive;    }    public Long getIdTypeContrat() {        return idTypeContrat;    }    public void setIdTypeContrat(Long idTypeContrat) {        this.idTypeContrat = idTypeContrat;    }    public String getCodeRefContrat() {        return codeRefContrat;    }    public void setCodeRefContrat(String codeRefContrat) {        this.codeRefContrat = codeRefContrat;    }    public String getIntitule() {        return intitule;    }    public void setIntitule(String intitule) {        this.intitule = intitule;    }}