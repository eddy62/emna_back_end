package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Employe.
 */
@Entity
@Table(name = "employe")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "matricule", nullable = false)
    private String matricule;

    @NotNull
    @Column(name = "civilite", nullable = false)
    private String civilite;

    @NotNull
    @Column(name = "nom_naissance", nullable = false)
    private String nomNaissance;

    @Column(name = "nom_usage")
    private String nomUsage;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @NotNull
    @Column(name = "ville_naissance", nullable = false)
    private String villeNaissance;

    @NotNull
    @Column(name = "departement_naissance", nullable = false)
    private String departementNaissance;

    @NotNull
    @Column(name = "pays_naissance", nullable = false)
    private String paysNaissance;

    @NotNull
    @Column(name = "numero_securite_sociale", nullable = false)
    private String numeroSecuriteSociale;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone_fixe")
    private String telephoneFixe;

    @NotNull
    @Column(name = "telephone_portable", nullable = false)
    private String telephonePortable;

    @Column(name = "fax")
    private String fax;

    @NotNull
    @Column(name = "salaire_horaire", precision = 21, scale = 2, nullable = false)
    private BigDecimal salaireHoraire;

    @NotNull
    @Column(name = "salaire_brut_mensuel", precision = 21, scale = 2, nullable = false)
    private BigDecimal salaireBrutMensuel;

    @NotNull
    @Column(name = "nb_heure_mensuelle", precision = 21, scale = 2, nullable = false)
    private BigDecimal nbHeureMensuelle;

    @NotNull
    @Column(name = "categorie", nullable = false)
    private String categorie;

    @NotNull
    @Column(name = "poste", nullable = false)
    private String poste;

    @NotNull
    @Column(name = "date_embauche", nullable = false)
    private LocalDate dateEmbauche;

    @Column(name = "date_sortie")
    private LocalDate dateSortie;

    @NotNull
    @Column(name = "periode_essai", nullable = false)
    private Integer periodeEssai;

    @NotNull
    @Column(name = "situation_familiale", nullable = false)
    private String situationFamiliale;

    @Column(name = "nb_enfant_a_charge")
    private Integer nbEnfantACharge;

    @OneToMany(mappedBy = "employe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Contrat> listeContrats = new HashSet<>();

    @OneToMany(mappedBy = "employe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Absence> listeAbsences = new HashSet<>();

    @OneToMany(mappedBy = "employe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Prime> listePrimes = new HashSet<>();

    @OneToMany(mappedBy = "employe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<FichePaie> listeFichePaies = new HashSet<>();

    @OneToMany(mappedBy = "employe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<HeuresSupplementaires> listeHeureSupplementaires = new HashSet<>();

    @OneToMany(mappedBy = "employe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<NoteDeFrais> listeNoteDeFrais = new HashSet<>();

    @OneToMany(mappedBy = "employe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AvanceRappelSalaire> listeAvanceRappelSalaires = new HashSet<>();

    @OneToMany(mappedBy = "employe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AutresVariable> listeAutresVariables = new HashSet<>();

    @OneToMany(mappedBy = "employe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> listeDocuments = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "employes", allowSetters = true)
    private StatutEmploye statutEmploye;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "employes", allowSetters = true)
    private Adresse adresse;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeEmployes", allowSetters = true)
    private Societe societe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public Employe matricule(String matricule) {
        this.matricule = matricule;
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCivilite() {
        return civilite;
    }

    public Employe civilite(String civilite) {
        this.civilite = civilite;
        return this;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNomNaissance() {
        return nomNaissance;
    }

    public Employe nomNaissance(String nomNaissance) {
        this.nomNaissance = nomNaissance;
        return this;
    }

    public void setNomNaissance(String nomNaissance) {
        this.nomNaissance = nomNaissance;
    }

    public String getNomUsage() {
        return nomUsage;
    }

    public Employe nomUsage(String nomUsage) {
        this.nomUsage = nomUsage;
        return this;
    }

    public void setNomUsage(String nomUsage) {
        this.nomUsage = nomUsage;
    }

    public String getPrenom() {
        return prenom;
    }

    public Employe prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public Employe dateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getVilleNaissance() {
        return villeNaissance;
    }

    public Employe villeNaissance(String villeNaissance) {
        this.villeNaissance = villeNaissance;
        return this;
    }

    public void setVilleNaissance(String villeNaissance) {
        this.villeNaissance = villeNaissance;
    }

    public String getDepartementNaissance() {
        return departementNaissance;
    }

    public Employe departementNaissance(String departementNaissance) {
        this.departementNaissance = departementNaissance;
        return this;
    }

    public void setDepartementNaissance(String departementNaissance) {
        this.departementNaissance = departementNaissance;
    }

    public String getPaysNaissance() {
        return paysNaissance;
    }

    public Employe paysNaissance(String paysNaissance) {
        this.paysNaissance = paysNaissance;
        return this;
    }

    public void setPaysNaissance(String paysNaissance) {
        this.paysNaissance = paysNaissance;
    }

    public String getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    public Employe numeroSecuriteSociale(String numeroSecuriteSociale) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        return this;
    }

    public void setNumeroSecuriteSociale(String numeroSecuriteSociale) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    public String getEmail() {
        return email;
    }

    public Employe email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneFixe() {
        return telephoneFixe;
    }

    public Employe telephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
        return this;
    }

    public void setTelephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
    }

    public String getTelephonePortable() {
        return telephonePortable;
    }

    public Employe telephonePortable(String telephonePortable) {
        this.telephonePortable = telephonePortable;
        return this;
    }

    public void setTelephonePortable(String telephonePortable) {
        this.telephonePortable = telephonePortable;
    }

    public String getFax() {
        return fax;
    }

    public Employe fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public BigDecimal getSalaireHoraire() {
        return salaireHoraire;
    }

    public Employe salaireHoraire(BigDecimal salaireHoraire) {
        this.salaireHoraire = salaireHoraire;
        return this;
    }

    public void setSalaireHoraire(BigDecimal salaireHoraire) {
        this.salaireHoraire = salaireHoraire;
    }

    public BigDecimal getSalaireBrutMensuel() {
        return salaireBrutMensuel;
    }

    public Employe salaireBrutMensuel(BigDecimal salaireBrutMensuel) {
        this.salaireBrutMensuel = salaireBrutMensuel;
        return this;
    }

    public void setSalaireBrutMensuel(BigDecimal salaireBrutMensuel) {
        this.salaireBrutMensuel = salaireBrutMensuel;
    }

    public BigDecimal getNbHeureMensuelle() {
        return nbHeureMensuelle;
    }

    public Employe nbHeureMensuelle(BigDecimal nbHeureMensuelle) {
        this.nbHeureMensuelle = nbHeureMensuelle;
        return this;
    }

    public void setNbHeureMensuelle(BigDecimal nbHeureMensuelle) {
        this.nbHeureMensuelle = nbHeureMensuelle;
    }

    public String getCategorie() {
        return categorie;
    }

    public Employe categorie(String categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getPoste() {
        return poste;
    }

    public Employe poste(String poste) {
        this.poste = poste;
        return this;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public Employe dateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
        return this;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public Employe dateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
        return this;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public Integer getPeriodeEssai() {
        return periodeEssai;
    }

    public Employe periodeEssai(Integer periodeEssai) {
        this.periodeEssai = periodeEssai;
        return this;
    }

    public void setPeriodeEssai(Integer periodeEssai) {
        this.periodeEssai = periodeEssai;
    }

    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    public Employe situationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
        return this;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public Integer getNbEnfantACharge() {
        return nbEnfantACharge;
    }

    public Employe nbEnfantACharge(Integer nbEnfantACharge) {
        this.nbEnfantACharge = nbEnfantACharge;
        return this;
    }

    public void setNbEnfantACharge(Integer nbEnfantACharge) {
        this.nbEnfantACharge = nbEnfantACharge;
    }

    public Set<Contrat> getListeContrats() {
        return listeContrats;
    }

    public Employe listeContrats(Set<Contrat> contrats) {
        this.listeContrats = contrats;
        return this;
    }

    public Employe addListeContrats(Contrat contrat) {
        this.listeContrats.add(contrat);
        contrat.setEmploye(this);
        return this;
    }

    public Employe removeListeContrats(Contrat contrat) {
        this.listeContrats.remove(contrat);
        contrat.setEmploye(null);
        return this;
    }

    public void setListeContrats(Set<Contrat> contrats) {
        this.listeContrats = contrats;
    }

    public Set<Absence> getListeAbsences() {
        return listeAbsences;
    }

    public Employe listeAbsences(Set<Absence> absences) {
        this.listeAbsences = absences;
        return this;
    }

    public Employe addListeAbsences(Absence absence) {
        this.listeAbsences.add(absence);
        absence.setEmploye(this);
        return this;
    }

    public Employe removeListeAbsences(Absence absence) {
        this.listeAbsences.remove(absence);
        absence.setEmploye(null);
        return this;
    }

    public void setListeAbsences(Set<Absence> absences) {
        this.listeAbsences = absences;
    }

    public Set<Prime> getListePrimes() {
        return listePrimes;
    }

    public Employe listePrimes(Set<Prime> primes) {
        this.listePrimes = primes;
        return this;
    }

    public Employe addListePrimes(Prime prime) {
        this.listePrimes.add(prime);
        prime.setEmploye(this);
        return this;
    }

    public Employe removeListePrimes(Prime prime) {
        this.listePrimes.remove(prime);
        prime.setEmploye(null);
        return this;
    }

    public void setListePrimes(Set<Prime> primes) {
        this.listePrimes = primes;
    }

    public Set<FichePaie> getListeFichePaies() {
        return listeFichePaies;
    }

    public Employe listeFichePaies(Set<FichePaie> fichePaies) {
        this.listeFichePaies = fichePaies;
        return this;
    }

    public Employe addListeFichePaies(FichePaie fichePaie) {
        this.listeFichePaies.add(fichePaie);
        fichePaie.setEmploye(this);
        return this;
    }

    public Employe removeListeFichePaies(FichePaie fichePaie) {
        this.listeFichePaies.remove(fichePaie);
        fichePaie.setEmploye(null);
        return this;
    }

    public void setListeFichePaies(Set<FichePaie> fichePaies) {
        this.listeFichePaies = fichePaies;
    }

    public Set<HeuresSupplementaires> getListeHeureSupplementaires() {
        return listeHeureSupplementaires;
    }

    public Employe listeHeureSupplementaires(Set<HeuresSupplementaires> heuresSupplementaires) {
        this.listeHeureSupplementaires = heuresSupplementaires;
        return this;
    }

    public Employe addListeHeureSupplementaires(HeuresSupplementaires heuresSupplementaires) {
        this.listeHeureSupplementaires.add(heuresSupplementaires);
        heuresSupplementaires.setEmploye(this);
        return this;
    }

    public Employe removeListeHeureSupplementaires(HeuresSupplementaires heuresSupplementaires) {
        this.listeHeureSupplementaires.remove(heuresSupplementaires);
        heuresSupplementaires.setEmploye(null);
        return this;
    }

    public void setListeHeureSupplementaires(Set<HeuresSupplementaires> heuresSupplementaires) {
        this.listeHeureSupplementaires = heuresSupplementaires;
    }

    public Set<NoteDeFrais> getListeNoteDeFrais() {
        return listeNoteDeFrais;
    }

    public Employe listeNoteDeFrais(Set<NoteDeFrais> noteDeFrais) {
        this.listeNoteDeFrais = noteDeFrais;
        return this;
    }

    public Employe addListeNoteDeFrais(NoteDeFrais noteDeFrais) {
        this.listeNoteDeFrais.add(noteDeFrais);
        noteDeFrais.setEmploye(this);
        return this;
    }

    public Employe removeListeNoteDeFrais(NoteDeFrais noteDeFrais) {
        this.listeNoteDeFrais.remove(noteDeFrais);
        noteDeFrais.setEmploye(null);
        return this;
    }

    public void setListeNoteDeFrais(Set<NoteDeFrais> noteDeFrais) {
        this.listeNoteDeFrais = noteDeFrais;
    }

    public Set<AvanceRappelSalaire> getListeAvanceRappelSalaires() {
        return listeAvanceRappelSalaires;
    }

    public Employe listeAvanceRappelSalaires(Set<AvanceRappelSalaire> avanceRappelSalaires) {
        this.listeAvanceRappelSalaires = avanceRappelSalaires;
        return this;
    }

    public Employe addListeAvanceRappelSalaire(AvanceRappelSalaire avanceRappelSalaire) {
        this.listeAvanceRappelSalaires.add(avanceRappelSalaire);
        avanceRappelSalaire.setEmploye(this);
        return this;
    }

    public Employe removeListeAvanceRappelSalaire(AvanceRappelSalaire avanceRappelSalaire) {
        this.listeAvanceRappelSalaires.remove(avanceRappelSalaire);
        avanceRappelSalaire.setEmploye(null);
        return this;
    }

    public void setListeAvanceRappelSalaires(Set<AvanceRappelSalaire> avanceRappelSalaires) {
        this.listeAvanceRappelSalaires = avanceRappelSalaires;
    }

    public Set<AutresVariable> getListeAutresVariables() {
        return listeAutresVariables;
    }

    public Employe listeAutresVariables(Set<AutresVariable> autresVariables) {
        this.listeAutresVariables = autresVariables;
        return this;
    }

    public Employe addListeAutresVariables(AutresVariable autresVariable) {
        this.listeAutresVariables.add(autresVariable);
        autresVariable.setEmploye(this);
        return this;
    }

    public Employe removeListeAutresVariables(AutresVariable autresVariable) {
        this.listeAutresVariables.remove(autresVariable);
        autresVariable.setEmploye(null);
        return this;
    }

    public void setListeAutresVariables(Set<AutresVariable> autresVariables) {
        this.listeAutresVariables = autresVariables;
    }

    public Set<Document> getListeDocuments() {
        return listeDocuments;
    }

    public Employe listeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
        return this;
    }

    public Employe addListeDocuments(Document document) {
        this.listeDocuments.add(document);
        document.setEmploye(this);
        return this;
    }

    public Employe removeListeDocuments(Document document) {
        this.listeDocuments.remove(document);
        document.setEmploye(null);
        return this;
    }

    public void setListeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
    }

    public StatutEmploye getStatutEmploye() {
        return statutEmploye;
    }

    public Employe statutEmploye(StatutEmploye statutEmploye) {
        this.statutEmploye = statutEmploye;
        return this;
    }

    public void setStatutEmploye(StatutEmploye statutEmploye) {
        this.statutEmploye = statutEmploye;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public Employe adresse(Adresse adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Societe getSociete() {
        return societe;
    }

    public Employe societe(Societe societe) {
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
        if (!(o instanceof Employe)) {
            return false;
        }
        return id != null && id.equals(((Employe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employe{" +
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
            "}";
    }
}
