package fr.insy2s.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Dpae.
 */
@Entity
@Table(name = "dpae")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dpae implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "lieu", nullable = false)
    private String lieu;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "heure_embauche", nullable = false)
    private String heureEmbauche;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "retour_api_urssaf")
    private String retourApiUrssaf;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Contrat contrat;

    @OneToMany(mappedBy = "dpae")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> listeDocuments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public Dpae lieu(String lieu) {
        this.lieu = lieu;
        return this;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDate() {
        return date;
    }

    public Dpae date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHeureEmbauche() {
        return heureEmbauche;
    }

    public Dpae heureEmbauche(String heureEmbauche) {
        this.heureEmbauche = heureEmbauche;
        return this;
    }

    public void setHeureEmbauche(String heureEmbauche) {
        this.heureEmbauche = heureEmbauche;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Dpae commentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getRetourApiUrssaf() {
        return retourApiUrssaf;
    }

    public Dpae retourApiUrssaf(String retourApiUrssaf) {
        this.retourApiUrssaf = retourApiUrssaf;
        return this;
    }

    public void setRetourApiUrssaf(String retourApiUrssaf) {
        this.retourApiUrssaf = retourApiUrssaf;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public Dpae contrat(Contrat contrat) {
        this.contrat = contrat;
        return this;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Set<Document> getListeDocuments() {
        return listeDocuments;
    }

    public Dpae listeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
        return this;
    }

    public Dpae addListeDocuments(Document document) {
        this.listeDocuments.add(document);
        document.setDpae(this);
        return this;
    }

    public Dpae removeListeDocuments(Document document) {
        this.listeDocuments.remove(document);
        document.setDpae(null);
        return this;
    }

    public void setListeDocuments(Set<Document> documents) {
        this.listeDocuments = documents;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dpae)) {
            return false;
        }
        return id != null && id.equals(((Dpae) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Dpae{" +
            "id=" + getId() +
            ", lieu='" + getLieu() + "'" +
            ", date='" + getDate() + "'" +
            ", heureEmbauche='" + getHeureEmbauche() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            ", retourApiUrssaf='" + getRetourApiUrssaf() + "'" +
            "}";
    }
}
