package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * WrapperEmploye
 *
 * @author De ? et Cédric Belot
 */
public class WrapperEmploye {

    //employe
    private Long id;
    private String matricule;
    private String civilite;
    private String nomNaissance;
    private String nomUsage;
    private String prenom;
    private LocalDate dateNaissance;
    private String villeNaissance;
    private String departementNaissance;
    private String paysNaissance;
    private String numeroSecuriteSociale;
    private String email;
    private String telephoneFixe;
    private String telephonePortable;
    private String fax;
    private BigDecimal salaireHoraire;
    private BigDecimal salaireBrutMensuel;
    private BigDecimal nbHeureMensuelle;
    private String categorie;
    private String poste;
    private LocalDate dateEmbauche;
    private LocalDate dateSortie;
    private String situationFamiliale;
    private Integer nbEnfantACharge;
    private Integer periodeEssai;

    //adresse
    private Long adresseId;
    private String numeroRue;
    private String nomRue;
    private String boitePostale;
    private String codePostal;
    private String ville;
    private String pays;

    //statutEmploye
    private Long statutEmployeId;
    private String codeRefStatut;
    private String libelle;

    //societe
    private Long societeId;

    //infoEntreprise
    private Long infoEntrepriseId;
    private String raisonSociale;

    //contrat
    private Long idContrat;
    private String titre;
    private LocalDate dateCreation;
    private Boolean signe;
    private Boolean archive;

    //typeContrat
    private Long idTypeContrat;
    private String codeRefContrat;
    private String intitule;

    /**
     * Empty constructor
     */
    public WrapperEmploye() {
        //empty method
    }

    //better use builderWrapperEmploye in WrapperEmployeMapper (centralization)
    /**
     * Parameterized constructor
     *
     * @param employeDTO        the DTO containing employe attributes
     * @param adresseDTO        the DTO containing adresse attributes
     * @param statutEmployeDTO  the DTO containing statutEmploye attributes
     * @param societeDTO        the DTO containing societe attributes
     * @param infoEntrepriseDTO the DTO containing infoEntreprise attributes
     * @param contratDTO        the DTO containing contrat attributes
     * @param typeContratDTO    the DTO containing typeContrat attributes
     */
    public WrapperEmploye(EmployeDTO employeDTO, AdresseDTO adresseDTO, StatutEmployeDTO statutEmployeDTO, SocieteDTO societeDTO, InfoEntrepriseDTO infoEntrepriseDTO, ContratDTO contratDTO, TypeContratDTO typeContratDTO) {
        super();
        //employe
        this.id = employeDTO.getId();
        this.matricule = employeDTO.getMatricule();
        this.civilite = employeDTO.getCivilite();
        this.nomNaissance = employeDTO.getNomNaissance();
        this.nomUsage = employeDTO.getNomUsage();
        this.prenom = employeDTO.getPrenom();
        this.dateNaissance = employeDTO.getDateNaissance();
        this.villeNaissance = employeDTO.getVilleNaissance();
        this.departementNaissance = employeDTO.getDepartementNaissance();
        this.paysNaissance = employeDTO.getPaysNaissance();
        this.numeroSecuriteSociale = employeDTO.getNumeroSecuriteSociale();
        this.email = employeDTO.getEmail();
        this.telephoneFixe = employeDTO.getTelephoneFixe();
        this.telephonePortable = employeDTO.getTelephonePortable();
        this.fax = employeDTO.getFax();
        this.salaireHoraire = employeDTO.getSalaireHoraire();
        this.salaireBrutMensuel = employeDTO.getSalaireBrutMensuel();
        this.nbHeureMensuelle = employeDTO.getNbHeureMensuelle();
        this.categorie = employeDTO.getCategorie();
        this.poste = employeDTO.getPoste();
        this.dateEmbauche = employeDTO.getDateEmbauche();
        this.dateSortie = employeDTO.getDateSortie();
        this.situationFamiliale = employeDTO.getSituationFamiliale();
        this.nbEnfantACharge = employeDTO.getNbEnfantACharge();
        this.periodeEssai = employeDTO.getPeriodeEssai();
        //adresse
        this.adresseId = employeDTO.getAdresseId();
        this.numeroRue = adresseDTO.getNumeroRue();
        this.nomRue = adresseDTO.getNomRue();
        this.boitePostale = adresseDTO.getBoitePostale();
        this.codePostal = adresseDTO.getCodePostal();
        this.ville = adresseDTO.getVille();
        this.pays = adresseDTO.getPays();
        //statutEmploye
        this.statutEmployeId = employeDTO.getStatutEmployeId();
        this.codeRefStatut = statutEmployeDTO.getCodeRef();
        this.libelle = statutEmployeDTO.getLibelle();
        //societe
        this.societeId = employeDTO.getSocieteId();
        //infoEntreprise
        this.infoEntrepriseId = societeDTO.getInfoEntrepriseId();
        this.raisonSociale = infoEntrepriseDTO.getRaisonSociale();
        //contrat
        this.idContrat = contratDTO.getId();
        this.titre = contratDTO.getTitre();
        this.dateCreation = contratDTO.getDateCreation();
        this.signe = contratDTO.isSigne();
        this.archive = contratDTO.isArchive();
        //typeContrat
        this.idTypeContrat = typeContratDTO.getId();
        this.codeRefContrat = typeContratDTO.getCodeRef();
        this.intitule = typeContratDTO.getIntitule();
    }

    // getters setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneFixe() {
        return telephoneFixe;
    }

    public void setTelephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
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

    public BigDecimal getSalaireHoraire() {
        return salaireHoraire;
    }

    public void setSalaireHoraire(BigDecimal salaireHoraire) {
        this.salaireHoraire = salaireHoraire;
    }

    public BigDecimal getSalaireBrutMensuel() {
        return salaireBrutMensuel;
    }

    public void setSalaireBrutMensuel(BigDecimal salaireBrutMensuel) {
        this.salaireBrutMensuel = salaireBrutMensuel;
    }

    public BigDecimal getNbHeureMensuelle() {
        return nbHeureMensuelle;
    }

    public void setNbHeureMensuelle(BigDecimal nbHeureMensuelle) {
        this.nbHeureMensuelle = nbHeureMensuelle;
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

    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public Integer getNbEnfantACharge() {
        return nbEnfantACharge;
    }

    public void setNbEnfantACharge(Integer nbEnfantACharge) {
        this.nbEnfantACharge = nbEnfantACharge;
    }

    public Integer getPeriodeEssai() {
        return periodeEssai;
    }

    public void setPeriodeEssai(Integer periodeEssai) {
        this.periodeEssai = periodeEssai;
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

    public String getCodeRefStatut() {
        return codeRefStatut;
    }

    public void setCodeRefStatut(String codeRefStatut) {
        this.codeRefStatut = codeRefStatut;
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

    public String getCodeRefContrat() {
        return codeRefContrat;
    }

    public void setCodeRefContrat(String codeRefContrat) {
        this.codeRefContrat = codeRefContrat;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

}
