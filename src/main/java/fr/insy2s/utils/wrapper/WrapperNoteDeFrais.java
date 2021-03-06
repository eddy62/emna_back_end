package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.NoteDeFraisDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Wrapper NoteDeFrais with wrapperDocumentList
 *
 */
public class WrapperNoteDeFrais {

    // NoteDeFrais
    private Long id;
    private String designation;
    private LocalDate date;

    private BigDecimal montant;
    private Integer mois;
    private Integer annee;
    private Long etatVariablePaieId;
    private Long employeId;

    // Documents
    private List<WrapperDocument> wrapperDocumentList;

    /**
     * Constructeur WrapperNoteDeFrais par défaut
     *
     */
    public WrapperNoteDeFrais(){
        // constructeur par défaut
    }

    /**
     * Constructeur WrapperNoteDeFrais avec paramètres
     *
     * @param noteDeFraisDTO the noteDeFraisDTO
     * @param wrapperDocumentList the list of wrapperDocument
     */
    public WrapperNoteDeFrais(NoteDeFraisDTO noteDeFraisDTO, List<WrapperDocument> wrapperDocumentList){

        super();
        // NoteDeFrais
        this.id = noteDeFraisDTO.getId();
        this.designation = noteDeFraisDTO.getDesignation();
        this.date = noteDeFraisDTO.getDate();

        this.montant = noteDeFraisDTO.getMontant();
        this.mois = noteDeFraisDTO.getMois();
        this.annee = noteDeFraisDTO.getAnnee();
        this.etatVariablePaieId = noteDeFraisDTO.getEtatVariablePaieId();
        this.employeId = noteDeFraisDTO.getEtatVariablePaieId();

        // Documents
        this.wrapperDocumentList = wrapperDocumentList;


    }

    // Getters / Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public List<WrapperDocument> getWrapperDocumentList() {
        return wrapperDocumentList;
    }

    public void setWrapperDocumentList(List<WrapperDocument> wrapperDocumentList) {
        this.wrapperDocumentList = wrapperDocumentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WrapperNoteDeFrais)) return false;
        WrapperNoteDeFrais that = (WrapperNoteDeFrais) o;
        return getId().equals(that.getId()) &&
                getDesignation().equals(that.getDesignation()) &&
                getDate().equals(that.getDate()) &&
                getMontant().equals(that.getMontant()) &&
                getMois().equals(that.getMois()) &&
                getAnnee().equals(that.getAnnee()) &&
                getEtatVariablePaieId().equals(that.getEtatVariablePaieId()) &&
                getEmployeId().equals(that.getEmployeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDesignation(), getDate(), getMontant(), getMois(), getAnnee(), getEtatVariablePaieId(), getEmployeId());
    }

    @Override
    public String toString() {
        return "WrapperNoteDeFrais{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", date=" + date +
                ", montant=" + montant +
                ", mois=" + mois +
                ", annee=" + annee +
                ", etatVariablePaieId=" + etatVariablePaieId +
                ", employeId=" + employeId +
                '}';
    }
}
