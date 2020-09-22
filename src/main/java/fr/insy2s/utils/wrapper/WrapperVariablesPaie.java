package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.AutresVariableDTO;
import fr.insy2s.service.dto.AvanceRappelSalaireDTO;
import fr.insy2s.service.dto.HeuresSupplementairesDTO;

import java.util.List;

/**
 * Wrapper VariablesPaie
 *  avec wrapperAbsenceList + autresVariableDTOList + avanceRappelSalaireDTOList
 *  + heuresSupplementairesDTOList + noteDeFraisDTOList + wrapperPrimeList
 *
 */
public class WrapperVariablesPaie {

    // Absences
    private List<WrapperAbsence> wrapperAbsenceList;

    // Autres Variables
    private List<AutresVariableDTO> autresVariableDTOList;

    // Avances/Rappels Salaire
    private List<AvanceRappelSalaireDTO> avanceRappelSalaireDTOList;

    // Heures Supplementaires
    private List<HeuresSupplementairesDTO> heuresSupplementairesDTOList;

    // Notes de Frais
    private List<WrapperNoteDeFrais> wrapperNoteDeFraisList;

    // Primes
    private List<WrapperPrime> wrapperPrimeList;

    /**
     * Constructeur WrapperVariablesPaie par défaut
     */
    public WrapperVariablesPaie() {
        // constructeur par défaut
    }

    /**
     * Constructeur WrapperVariablesPaie avec paramètres
     *
     * @param wrapperAbsenceList
     * @param autresVariableDTOList
     * @param avanceRappelSalaireDTOList
     * @param heuresSupplementairesDTOList
     * @param wrapperNoteDeFraisList
     * @param wrapperPrimeList
     */
    public WrapperVariablesPaie(List<WrapperAbsence> wrapperAbsenceList,
                                List<AutresVariableDTO> autresVariableDTOList,
                                List<AvanceRappelSalaireDTO> avanceRappelSalaireDTOList,
                                List<HeuresSupplementairesDTO> heuresSupplementairesDTOList,
                                List<WrapperNoteDeFrais> wrapperNoteDeFraisList,
                                List<WrapperPrime> wrapperPrimeList) {

        super();
        this.wrapperAbsenceList = wrapperAbsenceList;
        this.autresVariableDTOList = autresVariableDTOList;
        this.avanceRappelSalaireDTOList = avanceRappelSalaireDTOList;
        this.heuresSupplementairesDTOList = heuresSupplementairesDTOList;
        this.wrapperNoteDeFraisList = wrapperNoteDeFraisList;
        this.wrapperPrimeList = wrapperPrimeList;
    }

    // Getters / Setters

    public List<WrapperAbsence> getWrapperAbsenceList() {
        return wrapperAbsenceList;
    }

    public void setWrapperAbsenceList(List<WrapperAbsence> wrapperAbsenceList) {
        this.wrapperAbsenceList = wrapperAbsenceList;
    }

    public List<AutresVariableDTO> getAutresVariableDTOList() {
        return autresVariableDTOList;
    }

    public void setAutresVariableDTOList(List<AutresVariableDTO> autresVariableDTOList) {
        this.autresVariableDTOList = autresVariableDTOList;
    }

    public List<AvanceRappelSalaireDTO> getAvanceRappelSalaireDTOList() {
        return avanceRappelSalaireDTOList;
    }

    public void setAvanceRappelSalaireDTOList(List<AvanceRappelSalaireDTO> avanceRappelSalaireDTOList) {
        this.avanceRappelSalaireDTOList = avanceRappelSalaireDTOList;
    }

    public List<HeuresSupplementairesDTO> getHeuresSupplementairesDTOList() {
        return heuresSupplementairesDTOList;
    }

    public void setHeuresSupplementairesDTOList(List<HeuresSupplementairesDTO> heuresSupplementairesDTOList) {
        this.heuresSupplementairesDTOList = heuresSupplementairesDTOList;
    }

    public List<WrapperNoteDeFrais> getWrapperNoteDeFraisList() {
        return wrapperNoteDeFraisList;
    }

    public void setWrapperNoteDeFraisList(List<WrapperNoteDeFrais> wrapperNoteDeFraisList) {
        this.wrapperNoteDeFraisList = wrapperNoteDeFraisList;
    }

    public List<WrapperPrime> getWrapperPrimeList() {
        return wrapperPrimeList;
    }

    public void setWrapperPrimeList(List<WrapperPrime> wrapperPrimeList) {
        this.wrapperPrimeList = wrapperPrimeList;
    }
}
