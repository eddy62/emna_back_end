package fr.insy2s.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.InfoEntreprise} entity.
 */
public class InfoEntrepriseDTO implements Serializable {
    
    private Long id;

    private String raisonSociale;

    private String telephone;

    private String fax;

    private String formeJuridique;

    private LocalDate dateDeCreation;

    private String siren;

    private String siret;

    private String domaineDactivite;

    private String description;

    private String email;

    private String codeUrssaf;

    private String serviceSanteTravail;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFormeJuridique() {
        return formeJuridique;
    }

    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public LocalDate getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(LocalDate dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InfoEntrepriseDTO)) {
            return false;
        }

        return id != null && id.equals(((InfoEntrepriseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InfoEntrepriseDTO{" +
            "id=" + getId() +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", fax='" + getFax() + "'" +
            ", formeJuridique='" + getFormeJuridique() + "'" +
            ", dateDeCreation='" + getDateDeCreation() + "'" +
            ", siren='" + getSiren() + "'" +
            ", siret='" + getSiret() + "'" +
            ", domaineDactivite='" + getDomaineDactivite() + "'" +
            ", description='" + getDescription() + "'" +
            ", email='" + getEmail() + "'" +
            ", codeUrssaf='" + getCodeUrssaf() + "'" +
            ", serviceSanteTravail='" + getServiceSanteTravail() + "'" +
            "}";
    }
}
