package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ClientFournisseur.
 */
@Entity
@Table(name = "client_fournisseur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClientFournisseur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "siret")
    private String siret;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "clientFournisseur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Facture> listeFactures = new HashSet<>();

    @OneToMany(mappedBy = "clientFournisseur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Devis> listeDevis = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "clientFournisseurs", allowSetters = true)
    private Adresse adresse;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeClientsFournisseurs", allowSetters = true)
    private Societe societe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public ClientFournisseur nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSiret() {
        return siret;
    }

    public ClientFournisseur siret(String siret) {
        this.siret = siret;
        return this;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getTelephone() {
        return telephone;
    }

    public ClientFournisseur telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public ClientFournisseur email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Facture> getListeFactures() {
        return listeFactures;
    }

    public ClientFournisseur listeFactures(Set<Facture> factures) {
        this.listeFactures = factures;
        return this;
    }

    public ClientFournisseur addListeFactures(Facture facture) {
        this.listeFactures.add(facture);
        facture.setClientFournisseur(this);
        return this;
    }

    public ClientFournisseur removeListeFactures(Facture facture) {
        this.listeFactures.remove(facture);
        facture.setClientFournisseur(null);
        return this;
    }

    public void setListeFactures(Set<Facture> factures) {
        this.listeFactures = factures;
    }

    public Set<Devis> getListeDevis() {
        return listeDevis;
    }

    public ClientFournisseur listeDevis(Set<Devis> devis) {
        this.listeDevis = devis;
        return this;
    }

    public ClientFournisseur addListeDevis(Devis devis) {
        this.listeDevis.add(devis);
        devis.setClientFournisseur(this);
        return this;
    }

    public ClientFournisseur removeListeDevis(Devis devis) {
        this.listeDevis.remove(devis);
        devis.setClientFournisseur(null);
        return this;
    }

    public void setListeDevis(Set<Devis> devis) {
        this.listeDevis = devis;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public ClientFournisseur adresse(Adresse adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Societe getSociete() {
        return societe;
    }

    public ClientFournisseur societe(Societe societe) {
        this.societe = societe;
        return this;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientFournisseur)) {
            return false;
        }
        return id != null && id.equals(((ClientFournisseur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientFournisseur{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", siret='" + getSiret() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
