package fr.insy2s.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A InfoEntreprise.
 */
@Entity
@Table(name = "info_entreprise")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfoEntreprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "forme_juridique")
    private String formeJuridique;

    @Column(name = "date_de_creation")
    private LocalDate dateDeCreation;

    @Column(name = "siren")
    private String siren;

    @Column(name = "siret")
    private String siret;

    @Column(name = "domaine_dactivite")
    private String domaineDactivite;

    @Column(name = "description")
    private String description;

    @Column(name = "email")
    private String email;

    @Column(name = "code_urssaf")
    private String codeUrssaf;

    @Column(name = "service_sante_travail")
    private String serviceSanteTravail;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public InfoEntreprise raisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getTelephone() {
        return telephone;
    }

    public InfoEntreprise telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public InfoEntreprise fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFormeJuridique() {
        return formeJuridique;
    }

    public InfoEntreprise formeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
        return this;
    }

    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public LocalDate getDateDeCreation() {
        return dateDeCreation;
    }

    public InfoEntreprise dateDeCreation(LocalDate dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
        return this;
    }

    public void setDateDeCreation(LocalDate dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public String getSiren() {
        return siren;
    }

    public InfoEntreprise siren(String siren) {
        this.siren = siren;
        return this;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getSiret() {
        return siret;
    }

    public InfoEntreprise siret(String siret) {
        this.siret = siret;
        return this;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getDomaineDactivite() {
        return domaineDactivite;
    }

    public InfoEntreprise domaineDactivite(String domaineDactivite) {
        this.domaineDactivite = domaineDactivite;
        return this;
    }

    public void setDomaineDactivite(String domaineDactivite) {
        this.domaineDactivite = domaineDactivite;
    }

    public String getDescription() {
        return description;
    }

    public InfoEntreprise description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public InfoEntreprise email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodeUrssaf() {
        return codeUrssaf;
    }

    public InfoEntreprise codeUrssaf(String codeUrssaf) {
        this.codeUrssaf = codeUrssaf;
        return this;
    }

    public void setCodeUrssaf(String codeUrssaf) {
        this.codeUrssaf = codeUrssaf;
    }

    public String getServiceSanteTravail() {
        return serviceSanteTravail;
    }

    public InfoEntreprise serviceSanteTravail(String serviceSanteTravail) {
        this.serviceSanteTravail = serviceSanteTravail;
        return this;
    }

    public void setServiceSanteTravail(String serviceSanteTravail) {
        this.serviceSanteTravail = serviceSanteTravail;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InfoEntreprise)) {
            return false;
        }
        return id != null && id.equals(((InfoEntreprise) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InfoEntreprise{" +
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
