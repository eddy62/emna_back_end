package fr.insy2s.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link fr.insy2s.domain.Employe} entity.
 */
public class EmployeDTO implements Serializable {

    private Long      id;

    @NotNull
    private String    matricule;

    @NotNull
    private String    civilite;

    private String    nomNaissance;

    @NotNull
    private String    nomUsage;

    @NotNull
    private String    prenom;

    @NotNull
    private LocalDate dateNaissance;

    @NotNull
    private String    villeNaissance;

    private String    departementNaissance;

    @NotNull
    private String    paysNaisance;

    @NotNull
    private String    numeroSecuriteSociale;

    @NotNull
    private String    email;

    private String    telephoneFix;

    @NotNull
    private String    telephonePortable;

    @NotNull
    private String    fax;

    @NotNull
    private Double    salaireHoraire;

    @NotNull
    private Double    salaireBrutMensuelle;

    @NotNull
    private Double    heuresMensuelle;

    @NotNull
    private String    categorie;

    @NotNull
    private String    poste;

    @NotNull
    private LocalDate dateEmbauche;

    @NotNull
    private LocalDate dateSortie;

    @NotNull
    private String    typeContrat;

    @NotNull
    private String    situationFamiliale;

    @NotNull
    private Integer   enfantsACharge;

    private Long      adresseId;

    private Long      statutEmployeId;

    private Long      societeId;

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

    public Long getStatutEmployeId() {
        return statutEmployeId;
    }

    public void setStatutEmployeId(Long statutEmployeId) {
        this.statutEmployeId = statutEmployeId;
    }

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeDTO employeDTO = (EmployeDTO) o;
        if (employeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmployeDTO{" + "id=" + getId() + ", matricule='" + getMatricule() + "'" + ", civilite='" + getCivilite() + "'" + ", nomNaissance='" + getNomNaissance() + "'" + ", nomUsage='"
                        + getNomUsage() + "'" + ", prenom='" + getPrenom() + "'" + ", dateNaissance='" + getDateNaissance() + "'" + ", villeNaissance='" + getVilleNaissance() + "'"
                        + ", departementNaissance='" + getDepartementNaissance() + "'" + ", paysNaisance='" + getPaysNaisance() + "'" + ", numeroSecuriteSociale='" + getNumeroSecuriteSociale() + "'"
                        + ", email='" + getEmail() + "'" + ", telephoneFix='" + getTelephoneFix() + "'" + ", telephonePortable='" + getTelephonePortable() + "'" + ", fax='" + getFax() + "'"
                        + ", salaireHoraire=" + getSalaireHoraire() + ", salaireBrutMensuelle=" + getSalaireBrutMensuelle() + ", heuresMensuelle=" + getHeuresMensuelle() + ", categorie='"
                        + getCategorie() + "'" + ", poste='" + getPoste() + "'" + ", dateEmbauche='" + getDateEmbauche() + "'" + ", dateSortie='" + getDateSortie() + "'" + ", typeContrat='"
                        + getTypeContrat() + "'" + ", situationFamiliale='" + getSituationFamiliale() + "'" + ", enfantsACharge=" + getEnfantsACharge() + ", adresseId=" + getAdresseId()
                        + ", statutEmployeId=" + getStatutEmployeId() + ", societeId=" + getSocieteId() + "}";
    }
}
