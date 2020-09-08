package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.AbsenceDTO;
import fr.insy2s.service.dto.TypeAbsenceDTO;

import java.time.LocalDate;

/**
 * Wrapper Absence avec TypeAbsence
 *
 */
public class WrapperAbsence {

    // Absence
    private Long id;
    private LocalDate debutAbsence;
    private LocalDate finAbsence;
    private String justificatif;
    private Integer annee;
    private Integer mois;
    private Long employeId;
    private Long etatVariablePaieId;


    // TypeAbsence
    private Long idTypeAbsence;
    private String codeRef;
    private String intitule;

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
     * @param absenceDTO
     * @param typeAbsenceDTO
     */
    public  WrapperAbsence(AbsenceDTO absenceDTO, TypeAbsenceDTO typeAbsenceDTO){

        super();
        // Absence
        this.id = absenceDTO.getId();
        this.debutAbsence = absenceDTO.getDebutAbsence();
        this.finAbsence = absenceDTO.getFinAbsence();
        this.justificatif = absenceDTO.getJustificatif();
        this.annee = absenceDTO.getAnnee();
        this.mois = absenceDTO.getMois();
        this.employeId = absenceDTO.getEmployeId();
        this.etatVariablePaieId = absenceDTO.getEtatVariablePaieId();

        // TypeAbsence
        this.idTypeAbsence = typeAbsenceDTO.getId();
        this.codeRef = typeAbsenceDTO.getCodeRef();
        this.intitule = typeAbsenceDTO.getIntitule();

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

    public String getJustificatif() {
        return justificatif;
    }

    public void setJustificatif(String justificatif) {
        this.justificatif = justificatif;
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
}
