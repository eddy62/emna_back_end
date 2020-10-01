package fr.insy2s.utils.wrapper;

import java.time.LocalDate;

import fr.insy2s.service.dto.AdresseDTO;
import fr.insy2s.service.dto.EmployeDTO;
import fr.insy2s.service.dto.InfoEntrepriseDTO;
import fr.insy2s.service.dto.SocieteDTO;
import fr.insy2s.service.dto.StatutEmployeDTO;
import fr.insy2s.service.dto.TypeContratDTO;

/**
 * WrappeurEmploye
 *
 */
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
    private String    situationFamiliale;
    private Integer   enfantsACharge;
    private Double periodeEssai;

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

    //TypeContrat
    private Long      typeContratId;
    private String    codeTypeContrat;
    private String    intituleTypeContrat;

    /**
     * Constructeur par defaut
     */
    public WrapperEmploye() {
        //empty method

    }

    /**
     * Constructeur avec field
     *
     * @param employeDTO
     * @param adresseDTO
     * @param statutEmployeDTO
     * @param societeDTO
     * @param infoEntrepriseDTO
     */
    public WrapperEmploye(final EmployeDTO employeDTO, final AdresseDTO adresseDTO, final StatutEmployeDTO statutEmployeDTO, final SocieteDTO societeDTO, final InfoEntrepriseDTO infoEntrepriseDTO,
                    final TypeContratDTO typeContratDTO) {
        super();
        //employe
        this.id = employeDTO.getAdresseId();
        this.matricule = employeDTO.getMatricule();
        this.civilite = employeDTO.getCivilite();
        this.nomNaissance = employeDTO.getNomNaissance();
        this.nomUsage = employeDTO.getNomUsage();
        this.prenom = employeDTO.getPrenom();
        this.dateNaissance = employeDTO.getDateNaissance();
        this.villeNaissance = employeDTO.getVilleNaissance();
        this.departementNaissance = employeDTO.getDepartementNaissance();
        this.paysNaisance = employeDTO.getPaysNaisance();
        this.numeroSecuriteSociale = employeDTO.getNumeroSecuriteSociale();
        this.email = employeDTO.getEmail();
        this.telephoneFix = employeDTO.getTelephoneFix();
        this.telephonePortable = employeDTO.getTelephonePortable();
        this.fax = employeDTO.getFax();
        this.salaireHoraire = employeDTO.getSalaireHoraire().doubleValue();
        this.salaireBrutMensuelle = employeDTO.getSalaireBrutMensuelle().doubleValue();
        this.heuresMensuelle = employeDTO.getHeuresMensuelle().doubleValue();
        this.categorie = employeDTO.getCategorie();
        this.poste = employeDTO.getPoste();
        this.dateEmbauche = employeDTO.getDateEmbauche();
        this.dateSortie = employeDTO.getDateSortie();
        this.situationFamiliale = employeDTO.getSituationFamiliale();
        this.enfantsACharge = employeDTO.getEnfantsACharge();
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
        this.codeRef = statutEmployeDTO.getCodeRef();
        this.libelle = statutEmployeDTO.getLibelle();
        //societe
        this.societeId = employeDTO.getSocieteId();
        //infoEntreprise
        this.infoEntrepriseId = societeDTO.getInfoEntrepriseId();
        this.raisonSociale = infoEntrepriseDTO.getRaisonSociale();
        //typeContrat
        this.typeContratId = typeContratDTO.getId();
        this.codeTypeContrat = typeContratDTO.getCodeRef();
        this.intituleTypeContrat = typeContratDTO.getIntitule();

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

    public Long getTypeContratId() {
        return typeContratId;
    }

    public void setTypeContratId(Long typeContratId) {
        this.typeContratId = typeContratId;
    }

    public String getCodeTypeContrat() {
        return codeTypeContrat;
    }

    public void setCodeTypeContrat(String codeTypeContrat) {
        this.codeTypeContrat = codeTypeContrat;
    }

    public String getIntituleTypeContrat() {
        return intituleTypeContrat;
    }

    public void setIntituleTypeContrat(String intituleTypeContrat) {
        this.intituleTypeContrat = intituleTypeContrat;
    }

    public Double getPeriodeEssai() {
        return periodeEssai;
    }

    public void setPeriodeEssai(Double periodeEssai) {
        this.periodeEssai = periodeEssai;
    }



}
