package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Comptable.
 */
@Entity
@Table(name = "comptable")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comptable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "civilite")
    private String civilite;

    @OneToOne
    @JoinColumn(unique = true)
    private InfoEntreprise infoEntreprise;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "comptable")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Societe> listeSocietes = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "comptables", allowSetters = true)
    private Adresse adresse;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCivilite() {
        return civilite;
    }

    public Comptable civilite(String civilite) {
        this.civilite = civilite;
        return this;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public InfoEntreprise getInfoEntreprise() {
        return infoEntreprise;
    }

    public Comptable infoEntreprise(InfoEntreprise infoEntreprise) {
        this.infoEntreprise = infoEntreprise;
        return this;
    }

    public void setInfoEntreprise(InfoEntreprise infoEntreprise) {
        this.infoEntreprise = infoEntreprise;
    }

    public User getUser() {
        return user;
    }

    public Comptable user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Societe> getListeSocietes() {
        return listeSocietes;
    }

    public Comptable listeSocietes(Set<Societe> societes) {
        this.listeSocietes = societes;
        return this;
    }

    public Comptable addListeSocietes(Societe societe) {
        this.listeSocietes.add(societe);
        societe.setComptable(this);
        return this;
    }

    public Comptable removeListeSocietes(Societe societe) {
        this.listeSocietes.remove(societe);
        societe.setComptable(null);
        return this;
    }

    public void setListeSocietes(Set<Societe> societes) {
        this.listeSocietes = societes;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public Comptable adresse(Adresse adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comptable)) {
            return false;
        }
        return id != null && id.equals(((Comptable) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Comptable{" +
            "id=" + getId() +
            ", civilite='" + getCivilite() + "'" +
            "}";
    }
}
