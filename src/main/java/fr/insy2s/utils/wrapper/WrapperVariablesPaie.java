package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.AvanceRappelSalaireDTO;
import fr.insy2s.service.dto.HeuresSupplementairesDTO;

import java.util.List;

/**
 * Wrapper VariablesPaie
 *  avec wrapperAbsenceList + wrapperAutresVariableList + avanceRappelSalaireDTOList
 *  + heuresSupplementairesDTOList + noteDeFraisDTOList + wrapperPrimeList
 *
 * @author Erik DUNAIS
 */
public class WrapperVariablesPaie {

    // Absences
    private List<WrapperAbsence> wrapperAbsenceList;

    // Autres Variables
    private List<WrapperAutresVariable> wrapperAutresVariableList;

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
     * @param wrapperAbsenceList the list of WrapperAbsence
     * @param wrapperAutresVariableList the list of WrapperAutresVariable
     * @param avanceRappelSalaireDTOList the list of AvanceRappelSalaireDTO
     * @param heuresSupplementairesDTOList the list of HeuresSupplementairesDTO
     * @param wrapperNoteDeFraisList the list of WrapperNoteDeFrais
     * @param wrapperPrimeList the list of WrapperPrime
     */
    public WrapperVariablesPaie(List<WrapperAbsence> wrapperAbsenceList,
                                List<WrapperAutresVariable> wrapperAutresVariableList,
                                List<AvanceRappelSalaireDTO> avanceRappelSalaireDTOList,
                                List<HeuresSupplementairesDTO> heuresSupplementairesDTOList,
                                List<WrapperNoteDeFrais> wrapperNoteDeFraisList,
                                List<WrapperPrime> wrapperPrimeList) {

        super();
        this.wrapperAbsenceList = wrapperAbsenceList;
        this.wrapperAutresVariableList = wrapperAutresVariableList;
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

    public List<WrapperAutresVariable> getWrapperAutresVariableList() {
        return wrapperAutresVariableList;
    }

    public void setWrapperAutresVariableList(List<WrapperAutresVariable> wrapperAutresVariableList) {
        this.wrapperAutresVariableList = wrapperAutresVariableList;
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
