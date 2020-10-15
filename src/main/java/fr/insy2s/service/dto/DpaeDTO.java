package fr.insy2s.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link fr.insy2s.domain.Dpae} entity.
 */
public class DpaeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String lieu;

    @NotNull
    private LocalDate date;

    @NotNull
    private String heureEmbauche;

    private String commentaire;

    private String retourApiUrssaf;


    private Long contratId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHeureEmbauche() {
        return heureEmbauche;
    }

    public void setHeureEmbauche(String heureEmbauche) {
        this.heureEmbauche = heureEmbauche;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getRetourApiUrssaf() {
        return retourApiUrssaf;
    }

    public void setRetourApiUrssaf(String retourApiUrssaf) {
        this.retourApiUrssaf = retourApiUrssaf;
    }

    public Long getContratId() {
        return contratId;
    }

    public void setContratId(Long contratId) {
        this.contratId = contratId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DpaeDTO)) {
            return false;
        }

        return id != null && id.equals(((DpaeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DpaeDTO{" +
            "id=" + getId() +
            ", lieu='" + getLieu() + "'" +
            ", date='" + getDate() + "'" +
            ", heureEmbauche='" + getHeureEmbauche() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            ", retourApiUrssaf='" + getRetourApiUrssaf() + "'" +
            ", contratId=" + getContratId() +
            "}";
    }
}
