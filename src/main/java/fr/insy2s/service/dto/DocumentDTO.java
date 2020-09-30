package fr.insy2s.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.Document} entity.
 */
public class DocumentDTO implements Serializable {
    
    private Long id;

    private String cheminFichier;

    private String type;

    private String nom;


    private Long typeDocumentId;

    private Long factureId;

    private Long releveId;

    private Long contratId;

    private Long employeId;

    private Long depenseId;

    private Long absenceId;

    private Long noteDeFraisId;

    private Long autresVariableId;

    private Long devisId;

    private Long dpaeId;

    private Long fichePaieId;

    private Long avenantId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getTypeDocumentId() {
        return typeDocumentId;
    }

    public void setTypeDocumentId(Long typeDocumentId) {
        this.typeDocumentId = typeDocumentId;
    }

    public Long getFactureId() {
        return factureId;
    }

    public void setFactureId(Long factureId) {
        this.factureId = factureId;
    }

    public Long getReleveId() {
        return releveId;
    }

    public void setReleveId(Long releveId) {
        this.releveId = releveId;
    }

    public Long getContratId() {
        return contratId;
    }

    public void setContratId(Long contratId) {
        this.contratId = contratId;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public Long getDepenseId() {
        return depenseId;
    }

    public void setDepenseId(Long depenseId) {
        this.depenseId = depenseId;
    }

    public Long getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(Long absenceId) {
        this.absenceId = absenceId;
    }

    public Long getNoteDeFraisId() {
        return noteDeFraisId;
    }

    public void setNoteDeFraisId(Long noteDeFraisId) {
        this.noteDeFraisId = noteDeFraisId;
    }

    public Long getAutresVariableId() {
        return autresVariableId;
    }

    public void setAutresVariableId(Long autresVariableId) {
        this.autresVariableId = autresVariableId;
    }

    public Long getDevisId() {
        return devisId;
    }

    public void setDevisId(Long devisId) {
        this.devisId = devisId;
    }

    public Long getDpaeId() {
        return dpaeId;
    }

    public void setDpaeId(Long dpaeId) {
        this.dpaeId = dpaeId;
    }

    public Long getFichePaieId() {
        return fichePaieId;
    }

    public void setFichePaieId(Long fichePaieId) {
        this.fichePaieId = fichePaieId;
    }

    public Long getAvenantId() {
        return avenantId;
    }

    public void setAvenantId(Long avenantId) {
        this.avenantId = avenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentDTO)) {
            return false;
        }

        return id != null && id.equals(((DocumentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentDTO{" +
            "id=" + getId() +
            ", cheminFichier='" + getCheminFichier() + "'" +
            ", type='" + getType() + "'" +
            ", nom='" + getNom() + "'" +
            ", typeDocumentId=" + getTypeDocumentId() +
            ", factureId=" + getFactureId() +
            ", releveId=" + getReleveId() +
            ", contratId=" + getContratId() +
            ", employeId=" + getEmployeId() +
            ", depenseId=" + getDepenseId() +
            ", absenceId=" + getAbsenceId() +
            ", noteDeFraisId=" + getNoteDeFraisId() +
            ", autresVariableId=" + getAutresVariableId() +
            ", devisId=" + getDevisId() +
            ", dpaeId=" + getDpaeId() +
            ", fichePaieId=" + getFichePaieId() +
            ", avenantId=" + getAvenantId() +
            "}";
    }
}
