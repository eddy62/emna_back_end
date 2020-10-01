package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.AutresVariableDTO;
import fr.insy2s.service.dto.DocumentDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Wrapper AutresVariable with documentDTOList
 *
 */
public class WrapperAutresVariable {

    // AutresVariable
    private Long id;
    private String description;
    private LocalDate date;
    private BigDecimal montant;
    private Integer mois;
    private Integer annee;
    private Long etatVariablePaieId;
    private Long employeId;

    // Documents
    private List<DocumentDTO> documentDTOList;

    /**
     * Constructeur WrapperAutresVariable par défaut
     *
     */
    public WrapperAutresVariable(){
        // constructeur par défaut
    }

    /**
     * Constructeur WrapperAutresVariable avec paramètres
     *
     * @param autresVariableDTO the autresVariableDTO
     * @param documentDTOList the list of DocumentDTO
     */
    public WrapperAutresVariable(AutresVariableDTO autresVariableDTO, List<DocumentDTO> documentDTOList){

        super();
        // AutresVariable
        this.id = autresVariableDTO.getId();
        this.description = autresVariableDTO.getDescription();
        this.date = autresVariableDTO.getDate();

        this.montant = autresVariableDTO.getMontant();
        this.mois = autresVariableDTO.getMois();
        this.annee = autresVariableDTO.getAnnee();
        this.etatVariablePaieId = autresVariableDTO.getEtatVariablePaieId();
        this.employeId = autresVariableDTO.getEtatVariablePaieId();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Integer getMois() {
        return mois;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Long getEtatVariablePaieId() {
        return etatVariablePaieId;
    }

    public void setEtatVariablePaieId(Long etatVariablePaieId) {
        this.etatVariablePaieId = etatVariablePaieId;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
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
        if (!(o instanceof WrapperAutresVariable)) return false;
        WrapperAutresVariable that = (WrapperAutresVariable) o;
        return getId().equals(that.getId()) &&
                getDescription().equals(that.getDescription()) &&
                getDate().equals(that.getDate()) &&
                getMontant().equals(that.getMontant()) &&
                getMois().equals(that.getMois()) &&
                getAnnee().equals(that.getAnnee()) &&
                getEtatVariablePaieId().equals(that.getEtatVariablePaieId()) &&
                getEmployeId().equals(that.getEmployeId()) &&
                getDocumentDTOList().equals(that.getDocumentDTOList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getDate(), getMontant(), getMois(), getAnnee(), getEtatVariablePaieId(), getEmployeId(), getDocumentDTOList());
    }

    @Override
    public String toString() {
        return "WrapperAutresVariable {" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", montant=" + montant +
                ", mois=" + mois +
                ", annee=" + annee +
                ", etatVariablePaieId=" + etatVariablePaieId +
                ", employeId=" + employeId +
                '}';
    }
}
