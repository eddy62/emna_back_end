package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.DocumentDTO;
import fr.insy2s.service.dto.TypeDocumentDTO;

import java.util.Objects;

/**
 * Wrapper Document avec TypeDocument
 */
public class WrapperDocument {

    // Document
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

    // TypeDocument
    private Long idTypeDocument;
    private String codeRef;
    private String intitule;

    /**
     * Constructeur WrapperDocument par défaut
     *
     */
    public WrapperDocument(){
        // constructeur par défaut
    }

    /**
     *  Constructeur wrapperDocument avec paramètres
     *
     * @param documentDTO the documentDTO to construct the wrapperDocument
     * @param typeDocumentDTO the documentDTO to construct the wrapperDocument
     * @author Erik DUNAIS
     */
    public WrapperDocument(DocumentDTO documentDTO, TypeDocumentDTO typeDocumentDTO){

        super();
        // Document
        this.id = documentDTO.getId();
        this.cheminFichier = documentDTO.getCheminFichier();
        this.nom = documentDTO.getNom();
        this.typeDocumentId = documentDTO.getTypeDocumentId();
        this.factureId = documentDTO.getFactureId();
        this.releveId = documentDTO.getReleveId();
        this.contratId = documentDTO.getContratId();
        this.employeId = documentDTO.getEmployeId();
        this.depenseId = documentDTO.getDepenseId();
        this.absenceId = documentDTO.getAbsenceId();
        this.noteDeFraisId = documentDTO.getNoteDeFraisId();
        this.autresVariableId = documentDTO.getAutresVariableId();
        this.devisId = documentDTO.getDevisId();
        this.dpaeId = documentDTO.getDpaeId();
        this.fichePaieId = documentDTO.getFichePaieId();
        this.avenantId = documentDTO.getAvenantId();


        // TypePrime
        this.idTypeDocument = typeDocumentDTO.getId();
        this.codeRef = typeDocumentDTO.getCodeRef();
        this.intitule = typeDocumentDTO.getIntitule();

    }

    // Getters / Setters

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

    public Long getIdTypeDocument() {
        return idTypeDocument;
    }

    public void setIdTypeDocument(Long idTypeDocument) {
        this.idTypeDocument = idTypeDocument;
    }

    public String getCodeRef() {
        return codeRef;
    }

    public void setCodeRef(String codeRef) {
        this.codeRef = codeRef;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WrapperDocument)) return false;
        WrapperDocument that = (WrapperDocument) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCheminFichier(), that.getCheminFichier()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getNom(), that.getNom()) &&
                Objects.equals(getTypeDocumentId(), that.getTypeDocumentId()) &&
                Objects.equals(getFactureId(), that.getFactureId()) &&
                Objects.equals(getReleveId(), that.getReleveId()) &&
                Objects.equals(getContratId(), that.getContratId()) &&
                Objects.equals(getEmployeId(), that.getEmployeId()) &&
                Objects.equals(getDepenseId(), that.getDepenseId()) &&
                Objects.equals(getAbsenceId(), that.getAbsenceId()) &&
                Objects.equals(getNoteDeFraisId(), that.getNoteDeFraisId()) &&
                Objects.equals(getAutresVariableId(), that.getAutresVariableId()) &&
                Objects.equals(getDevisId(), that.getDevisId()) &&
                Objects.equals(getDpaeId(), that.getDpaeId()) &&
                Objects.equals(getFichePaieId(), that.getFichePaieId()) &&
                Objects.equals(getAvenantId(), that.getAvenantId()) &&
                Objects.equals(getIdTypeDocument(), that.getIdTypeDocument()) &&
                Objects.equals(getCodeRef(), that.getCodeRef()) &&
                Objects.equals(getIntitule(), that.getIntitule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCheminFichier(), getType(), getNom(), getTypeDocumentId(), getFactureId(), getReleveId(), getContratId(), getEmployeId(), getDepenseId(), getAbsenceId(), getNoteDeFraisId(), getAutresVariableId(), getDevisId(), getDpaeId(), getFichePaieId(), getAvenantId(), getIdTypeDocument(), getCodeRef(), getIntitule());
    }

    @Override
    public String toString() {
        return "WrapperDocument{" +
                "id=" + id +
                ", cheminFichier='" + cheminFichier + '\'' +
                ", type='" + type + '\'' +
                ", nom='" + nom + '\'' +
                ", typeDocumentId=" + typeDocumentId +
                ", factureId=" + factureId +
                ", releveId=" + releveId +
                ", contratId=" + contratId +
                ", employeId=" + employeId +
                ", depenseId=" + depenseId +
                ", absenceId=" + absenceId +
                ", noteDeFraisId=" + noteDeFraisId +
                ", autresVariableId=" + autresVariableId +
                ", devisId=" + devisId +
                ", dpaeId=" + dpaeId +
                ", fichePaieId=" + fichePaieId +
                ", avenantId=" + avenantId +
                ", idTypeDocument=" + idTypeDocument +
                ", codeRef='" + codeRef + '\'' +
                ", intitule='" + intitule + '\'' +
                '}';
    }
}
