package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Document.
 */
@Entity
@Table(name = "document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "chemin_fichier")
    private String cheminFichier;

    @Column(name = "nom")
    private String nom;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "documents", allowSetters = true)
    private TypeDocument typeDocument;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDocuments", allowSetters = true)
    private Facture facture;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDocuments", allowSetters = true)
    private Releve releve;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDocuments", allowSetters = true)
    private Contrat contrat;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDocuments", allowSetters = true)
    private Employe employe;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDocuments", allowSetters = true)
    private Depense depense;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDocuments", allowSetters = true)
    private Absence absence;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDocuments", allowSetters = true)
    private NoteDeFrais noteDeFrais;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDocuments", allowSetters = true)
    private AutresVariable autresVariable;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDocuments", allowSetters = true)
    private Devis devis;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDocuments", allowSetters = true)
    private Dpae dpae;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDocuments", allowSetters = true)
    private FichePaie fichePaie;

    @ManyToOne
    @JsonIgnoreProperties(value = "listeDocuments", allowSetters = true)
    private Avenant avenant;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public Document cheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
        return this;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public String getNom() {
        return nom;
    }

    public Document nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public Document typeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
        return this;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }

    public Facture getFacture() {
        return facture;
    }

    public Document facture(Facture facture) {
        this.facture = facture;
        return this;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Releve getReleve() {
        return releve;
    }

    public Document releve(Releve releve) {
        this.releve = releve;
        return this;
    }

    public void setReleve(Releve releve) {
        this.releve = releve;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public Document contrat(Contrat contrat) {
        this.contrat = contrat;
        return this;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Document employe(Employe employe) {
        this.employe = employe;
        return this;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Depense getDepense() {
        return depense;
    }

    public Document depense(Depense depense) {
        this.depense = depense;
        return this;
    }

    public void setDepense(Depense depense) {
        this.depense = depense;
    }

    public Absence getAbsence() {
        return absence;
    }

    public Document absence(Absence absence) {
        this.absence = absence;
        return this;
    }

    public void setAbsence(Absence absence) {
        this.absence = absence;
    }

    public NoteDeFrais getNoteDeFrais() {
        return noteDeFrais;
    }

    public Document noteDeFrais(NoteDeFrais noteDeFrais) {
        this.noteDeFrais = noteDeFrais;
        return this;
    }

    public void setNoteDeFrais(NoteDeFrais noteDeFrais) {
        this.noteDeFrais = noteDeFrais;
    }

    public AutresVariable getAutresVariable() {
        return autresVariable;
    }

    public Document autresVariable(AutresVariable autresVariable) {
        this.autresVariable = autresVariable;
        return this;
    }

    public void setAutresVariable(AutresVariable autresVariable) {
        this.autresVariable = autresVariable;
    }

    public Devis getDevis() {
        return devis;
    }

    public Document devis(Devis devis) {
        this.devis = devis;
        return this;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public Dpae getDpae() {
        return dpae;
    }

    public Document dpae(Dpae dpae) {
        this.dpae = dpae;
        return this;
    }

    public void setDpae(Dpae dpae) {
        this.dpae = dpae;
    }

    public FichePaie getFichePaie() {
        return fichePaie;
    }

    public Document fichePaie(FichePaie fichePaie) {
        this.fichePaie = fichePaie;
        return this;
    }

    public void setFichePaie(FichePaie fichePaie) {
        this.fichePaie = fichePaie;
    }

    public Avenant getAvenant() {
        return avenant;
    }

    public Document avenant(Avenant avenant) {
        this.avenant = avenant;
        return this;
    }

    public void setAvenant(Avenant avenant) {
        this.avenant = avenant;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Document)) {
            return false;
        }
        return id != null && id.equals(((Document) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Document{" +
            "id=" + getId() +
            ", cheminFichier='" + getCheminFichier() + "'" +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
