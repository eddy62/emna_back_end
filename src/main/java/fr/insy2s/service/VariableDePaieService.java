package fr.insy2s.service;

import fr.insy2s.utils.wrapper.WrapperVariablesPaie;

public interface VariableDePaieService {

    /**
     * Get one wrapperVariablesPaie by one employe, by one year and by one month.
     *
     * @param idEmploye id of the Employe in all VariablesPaie
     * @param annee     year in all VariablesPaie
     * @param mois      month in all VariablesPaie
     * @return the archived WrapperEmploye
     */
    WrapperVariablesPaie findOneWrapperVariablesPaieByIdEmployeAndAnneeAndMois(Long idEmploye, Integer annee, Integer mois);



}
