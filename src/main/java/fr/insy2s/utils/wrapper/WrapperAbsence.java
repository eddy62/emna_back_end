package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.AbsenceDTO;
import fr.insy2s.service.dto.DocumentDTO;
import fr.insy2s.service.dto.TypeAbsenceDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Wrapper Absence avec TypeAbsence
 *
 */
public class WrapperAbsence {

    // Absence
    private Long id;
    private LocalDate debutAbsence;
    private LocalDate finAbsence;
    private Integer annee;
    private Integer mois;
    private Long employeId;
    private Long etatVariablePaieId;
    private Long typeAbsenceId;


    // TypeAbsence
    private Long idTypeAbsence;
    private String codeRef;
    private String intitule;

    // Documents
    private List<DocumentDTO> documentDTOList;

    /**
     * Constructeur WrapperAbsence par défaut
     *
     */
    public WrapperAbsence(){
        // constructeur par défaut
    }

    /**
     * Constructeur WrapperAbsence avec paramètres
     *
     * @param absenceDTO the absenceDTO
     * @param typeAbsenceDTO the typeAbsenceDTO
     * @param documentDTOList the list of documentDTOList
     */
    public  WrapperAbsence(AbsenceDTO absenceDTO, TypeAbsenceDTO typeAbsenceDTO, List<DocumentDTO> documentDTOList){

        super();
        // Absence
        this.id = absenceDTO.getId();
        this.debutAbsence = absenceDTO.getDebutAbsence();
        this.finAbsence = absenceDTO.getFinAbsence();
        this.annee = absenceDTO.getAnnee();
        this.mois = absenceDTO.getMois();
        this.employeId = absenceDTO.getEmployeId();
        this.etatVariablePaieId = absenceDTO.getEtatVariablePaieId();
        this.typeAbsenceId = absenceDTO.getTypeAbsenceId();

        // TypeAbsence
        this.idTypeAbsence = typeAbsenceDTO.getId();
        this.codeRef = typeAbsenceDTO.getCodeRef();
        this.intitule = typeAbsenceDTO.getIntitule();

        // Documents
        this.documentDTOList = documentDTOList;


    }

    // Getters / Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDebutAbsence() {
        return debutAbsence;
    }

    public void setDebutAbsence(LocalDate debutAbsence) {
        this.debutAbsence = debutAbsence;
    }

    public LocalDate getFinAbsence() {
        return finAbsence;
    }

    public void setFinAbsence(LocalDate finAbsence) {
        this.finAbsence = finAbsence;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getMois() {
        return mois;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public Long getEtatVariablePaieId() {
        return etatVariablePaieId;
    }

    public void setEtatVariablePaieId(Long etatVariablePaieId) {
        this.etatVariablePaieId = etatVariablePaieId;
    }

    public Long getTypeAbsenceId() {
        return typeAbsenceId;
    }

    public void setTypeAbsenceId(Long typeAbsenceId) {
        this.typeAbsenceId = typeAbsenceId;
    }

    public Long getIdTypeAbsence() {
        return idTypeAbsence;
    }

    public void setIdTypeAbsence(Long idTypeAbsence) {
        this.idTypeAbsence = idTypeAbsence;
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

    public List<DocumentDTO> getDocumentDTOList() {
        return documentDTOList;
    }

    public void setDocumentDTOList(List<DocumentDTO> documentDTOList) {
        this.documentDTOList = documentDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrapperAbsence that = (WrapperAbsence) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(debutAbsence, that.debutAbsence) &&
                Objects.equals(finAbsence, that.finAbsence) &&
                Objects.equals(annee, that.annee) &&
                Objects.equals(mois, that.mois) &&
                Objects.equals(employeId, that.employeId) &&
                Objects.equals(etatVariablePaieId, that.etatVariablePaieId) &&
                Objects.equals(typeAbsenceId, that.typeAbsenceId) &&
                Objects.equals(idTypeAbsence, that.idTypeAbsence) &&
                Objects.equals(codeRef, that.codeRef) &&
                Objects.equals(intitule, that.intitule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debutAbsence, finAbsence, annee, mois, employeId, etatVariablePaieId, typeAbsenceId, idTypeAbsence, codeRef, intitule);
    }

    @Override
    public String toString() {
        return "WrapperAbsence{" +
                "id=" + id +
                ", debutAbsence=" + debutAbsence +
                ", finAbsence=" + finAbsence +
                ", annee=" + annee +
                ", mois=" + mois +
                ", employeId=" + employeId +
                ", etatVariablePaieId=" + etatVariablePaieId +
                ", typeAbsenceId=" + typeAbsenceId +
                ", idTypeAbsence=" + idTypeAbsence +
                ", codeRef='" + codeRef + '\'' +
                ", intitule='" + intitule + '\'' +
                '}';
    }
}
