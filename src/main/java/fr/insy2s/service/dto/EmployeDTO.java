package fr.insy2s.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A DTO for the {@link fr.insy2s.domain.Employe} entity.
 */
public class EmployeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String matricule;

    @NotNull
    private String civilite;

    @NotNull
    private String nomNaissance;

    private String nomUsage;

    @NotNull
    private String prenom;

    @NotNull
    private LocalDate dateNaissance;

    @NotNull
    private String villeNaissance;

    @NotNull
    private String departementNaissance;

    @NotNull
    private String paysNaissance;

    @NotNull
    private String numeroSecuriteSociale;

    private String email;

    private String telephoneFixe;

    @NotNull
    private String telephonePortable;

    private String fax;

    @NotNull
    private BigDecimal salaireHoraire;

    @NotNull
    private BigDecimal salaireBrutMensuel;

    @NotNull
    private BigDecimal nbHeureMensuelle;

    @NotNull
    private String categorie;

    @NotNull
    private String poste;

    @NotNull
    private LocalDate dateEmbauche;

    private LocalDate dateSortie;

    @NotNull
    private Integer periodeEssai;

    @NotNull
    private String situationFamiliale;

    private Integer nbEnfantACharge;


    private Long statutEmployeId;

    private Long adresseId;

    private Long societeId;
    
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

    public Integer getPeriodeEssai() {
        return periodeEssai;
    }

    public void setPeriodeEssai(Integer periodeEssai) {
        this.periodeEssai = periodeEssai;
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

    public Long getStatutEmployeId() {
        return statutEmployeId;
    }

    public void setStatutEmployeId(Long statutEmployeId) {
        this.statutEmployeId = statutEmployeId;
    }

    public Long getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Long adresseId) {
        this.adresseId = adresseId;
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
        if (!(o instanceof EmployeDTO)) {
            return false;
        }

        return id != null && id.equals(((EmployeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeDTO{" +
            "id=" + getId() +
            ", matricule='" + getMatricule() + "'" +
            ", civilite='" + getCivilite() + "'" +
            ", nomNaissance='" + getNomNaissance() + "'" +
            ", nomUsage='" + getNomUsage() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", villeNaissance='" + getVilleNaissance() + "'" +
            ", departementNaissance='" + getDepartementNaissance() + "'" +
            ", paysNaissance='" + getPaysNaissance() + "'" +
            ", numeroSecuriteSociale='" + getNumeroSecuriteSociale() + "'" +
            ", email='" + getEmail() + "'" +
            ", telephoneFixe='" + getTelephoneFixe() + "'" +
            ", telephonePortable='" + getTelephonePortable() + "'" +
            ", fax='" + getFax() + "'" +
            ", salaireHoraire=" + getSalaireHoraire() +
            ", salaireBrutMensuel=" + getSalaireBrutMensuel() +
            ", nbHeureMensuelle=" + getNbHeureMensuelle() +
            ", categorie='" + getCategorie() + "'" +
            ", poste='" + getPoste() + "'" +
            ", dateEmbauche='" + getDateEmbauche() + "'" +
            ", dateSortie='" + getDateSortie() + "'" +
            ", periodeEssai=" + getPeriodeEssai() +
            ", situationFamiliale='" + getSituationFamiliale() + "'" +
            ", nbEnfantACharge=" + getNbEnfantACharge() +
            ", statutEmployeId=" + getStatutEmployeId() +
            ", adresseId=" + getAdresseId() +
            ", societeId=" + getSocieteId() +
            "}";
    }
}
