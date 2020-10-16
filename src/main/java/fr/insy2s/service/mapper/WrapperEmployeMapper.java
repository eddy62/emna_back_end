package fr.insy2s.service.mapper;

import fr.insy2s.service.dto.*;
import fr.insy2s.utils.wrapper.WrapperEmploye;
import org.springframework.stereotype.Service;

/**
 * Mapper for the entity Employe, Adresse, StatutEmploye, Societe, InfoEntreprise and the DTO WrapperEmploye.
 */
@Service
public class WrapperEmployeMapper {

    /**
     * Builder WrapperEmploye
     *
     * @param employe        the DTO containing employe attributes
     * @param adresse        the DTO containing adresse attributes
     * @param statutEmploye  the DTO containing statutEmploye attributes
     * @param societe        the DTO containing societe attributes
     * @param infoEntreprise the DTO containing infoEntreprise attributes
     * @param contrat        the DTO containing contrat attributes
     * @param typeContrat    the DTO containing typeContrat attributes
     * @return
     */
    public WrapperEmploye builderWrapperEmployeWithContrat(final EmployeDTO employe, final AdresseDTO adresse, final StatutEmployeDTO statutEmploye, final SocieteDTO societe,
                                                           final InfoEntrepriseDTO infoEntreprise, final ContratDTO contrat, final TypeContratDTO typeContrat) {

        final WrapperEmploye wrapperEmploye = new WrapperEmploye();

        //employe
        wrapperEmploye.setId(employe.getId());
        wrapperEmploye.setMatricule(employe.getMatricule());
        wrapperEmploye.setCivilite(employe.getCivilite());
        wrapperEmploye.setNomNaissance(employe.getNomNaissance());
        wrapperEmploye.setNomUsage(employe.getNomUsage());
        wrapperEmploye.setPrenom(employe.getPrenom());
        wrapperEmploye.setDateNaissance(employe.getDateNaissance());
        wrapperEmploye.setVilleNaissance(employe.getVilleNaissance());
        wrapperEmploye.setDepartementNaissance(employe.getDepartementNaissance());
        wrapperEmploye.setPaysNaissance(employe.getPaysNaissance());
        wrapperEmploye.setNumeroSecuriteSociale(employe.getNumeroSecuriteSociale());
        wrapperEmploye.setEmail(employe.getEmail());
        wrapperEmploye.setTelephoneFixe(employe.getTelephoneFixe());
        wrapperEmploye.setTelephonePortable(employe.getTelephonePortable());
        wrapperEmploye.setFax(employe.getFax());
        wrapperEmploye.setSalaireHoraire(employe.getSalaireHoraire());
        wrapperEmploye.setSalaireBrutMensuel(employe.getSalaireBrutMensuel());
        wrapperEmploye.setNbHeureMensuelle(employe.getNbHeureMensuelle());
        wrapperEmploye.setCategorie(employe.getCategorie());
        wrapperEmploye.setPoste(employe.getPoste());
        wrapperEmploye.setDateEmbauche(employe.getDateEmbauche());
        wrapperEmploye.setDateSortie(employe.getDateSortie());
        wrapperEmploye.setSituationFamiliale(employe.getSituationFamiliale());
        wrapperEmploye.setNbEnfantACharge(employe.getNbEnfantACharge());
        wrapperEmploye.setPeriodeEssai(employe.getPeriodeEssai());

        //adresse
        wrapperEmploye.setAdresseId(adresse.getId());
        wrapperEmploye.setNumeroRue(adresse.getNumeroRue());
        wrapperEmploye.setNomRue(adresse.getNomRue());
        wrapperEmploye.setBoitePostale(adresse.getBoitePostale());
        wrapperEmploye.setCodePostal(adresse.getCodePostal());
        wrapperEmploye.setVille(adresse.getVille());
        wrapperEmploye.setPays(adresse.getPays());

        //statutEmploye
        wrapperEmploye.setStatutEmployeId(statutEmploye.getId());
        wrapperEmploye.setCodeRefStatut(statutEmploye.getCodeRef());
        wrapperEmploye.setLibelle(statutEmploye.getLibelle());

        //societe
        wrapperEmploye.setSocieteId(societe.getId());

        //infoEntreprise
        wrapperEmploye.setInfoEntrepriseId(infoEntreprise.getId());
        wrapperEmploye.setRaisonSociale(infoEntreprise.getRaisonSociale());

        //contrat
        wrapperEmploye.setIdContrat(contrat.getId());
        wrapperEmploye.setTitre(contrat.getTitre());
        wrapperEmploye.setDateCreation(contrat.getDateCreation());
        wrapperEmploye.setSigne(contrat.isSigne());
        wrapperEmploye.setArchive(contrat.isArchive());

        //typeContrat
        wrapperEmploye.setIdTypeContrat(typeContrat.getId());
        wrapperEmploye.setCodeRefContrat(typeContrat.getCodeRef());
        wrapperEmploye.setIntitule(typeContrat.getIntitule());

        return wrapperEmploye;
    }

    /**
     * Builder WrapperEmploye
     *
     * @param employe        the DTO containing employe attributes
     * @param adresse        the DTO containing adresse attributes
     * @param statutEmploye  the DTO containing statutEmploye attributes
     * @param societe        the DTO containing societe attributes
     * @param infoEntreprise the DTO containing infoEntreprise attributes
     * @return
     */
    public WrapperEmploye builderWrapperEmploye(final EmployeDTO employe, final AdresseDTO adresse, final StatutEmployeDTO statutEmploye, final SocieteDTO societe,
                                                           final InfoEntrepriseDTO infoEntreprise) {

        final WrapperEmploye wrapperEmploye = new WrapperEmploye();

        //employe
        wrapperEmploye.setId(employe.getId());
        wrapperEmploye.setMatricule(employe.getMatricule());
        wrapperEmploye.setCivilite(employe.getCivilite());
        wrapperEmploye.setNomNaissance(employe.getNomNaissance());
        wrapperEmploye.setNomUsage(employe.getNomUsage());
        wrapperEmploye.setPrenom(employe.getPrenom());
        wrapperEmploye.setDateNaissance(employe.getDateNaissance());
        wrapperEmploye.setVilleNaissance(employe.getVilleNaissance());
        wrapperEmploye.setDepartementNaissance(employe.getDepartementNaissance());
        wrapperEmploye.setPaysNaissance(employe.getPaysNaissance());
        wrapperEmploye.setNumeroSecuriteSociale(employe.getNumeroSecuriteSociale());
        wrapperEmploye.setEmail(employe.getEmail());
        wrapperEmploye.setTelephoneFixe(employe.getTelephoneFixe());
        wrapperEmploye.setTelephonePortable(employe.getTelephonePortable());
        wrapperEmploye.setFax(employe.getFax());
        wrapperEmploye.setSalaireHoraire(employe.getSalaireHoraire());
        wrapperEmploye.setSalaireBrutMensuel(employe.getSalaireBrutMensuel());
        wrapperEmploye.setNbHeureMensuelle(employe.getNbHeureMensuelle());
        wrapperEmploye.setCategorie(employe.getCategorie());
        wrapperEmploye.setPoste(employe.getPoste());
        wrapperEmploye.setDateEmbauche(employe.getDateEmbauche());
        wrapperEmploye.setDateSortie(employe.getDateSortie());
        wrapperEmploye.setSituationFamiliale(employe.getSituationFamiliale());
        wrapperEmploye.setNbEnfantACharge(employe.getNbEnfantACharge());
        wrapperEmploye.setPeriodeEssai(employe.getPeriodeEssai());

        //adresse
        wrapperEmploye.setAdresseId(adresse.getId());
        wrapperEmploye.setNumeroRue(adresse.getNumeroRue());
        wrapperEmploye.setNomRue(adresse.getNomRue());
        wrapperEmploye.setBoitePostale(adresse.getBoitePostale());
        wrapperEmploye.setCodePostal(adresse.getCodePostal());
        wrapperEmploye.setVille(adresse.getVille());
        wrapperEmploye.setPays(adresse.getPays());

        //statutEmploye
        wrapperEmploye.setStatutEmployeId(statutEmploye.getId());
        wrapperEmploye.setCodeRefStatut(statutEmploye.getCodeRef());
        wrapperEmploye.setLibelle(statutEmploye.getLibelle());

        //societe
        wrapperEmploye.setSocieteId(societe.getId());

        //infoEntreprise
        wrapperEmploye.setInfoEntrepriseId(infoEntreprise.getId());
        wrapperEmploye.setRaisonSociale(infoEntreprise.getRaisonSociale());

        return wrapperEmploye;
    }

    //ListEmploye to ListWrapperEmploye
    /**
     * Mappe WrapperEmploye to EmployeDto
     *
     * @param wrapperEmploye the wrapper containing employe attributes
     * @return
     */
    public EmployeDTO toEmployeDto(final WrapperEmploye wrapperEmploye) {
        EmployeDTO employeDTO = new EmployeDTO();

        employeDTO.setId(wrapperEmploye.getId());
        employeDTO.setMatricule(wrapperEmploye.getMatricule());
        employeDTO.setCivilite(wrapperEmploye.getCivilite());
        employeDTO.setNomNaissance(wrapperEmploye.getNomNaissance());
        employeDTO.setNomUsage(wrapperEmploye.getNomUsage());
        employeDTO.setPrenom(wrapperEmploye.getPrenom());
        employeDTO.setDateNaissance(wrapperEmploye.getDateNaissance());
        employeDTO.setVilleNaissance(wrapperEmploye.getVilleNaissance());
        employeDTO.setDepartementNaissance(wrapperEmploye.getDepartementNaissance());
        employeDTO.setPaysNaissance(wrapperEmploye.getPaysNaissance());
        employeDTO.setNumeroSecuriteSociale(wrapperEmploye.getNumeroSecuriteSociale());
        employeDTO.setEmail(wrapperEmploye.getEmail());
        employeDTO.setTelephoneFixe(wrapperEmploye.getTelephoneFixe());
        employeDTO.setTelephonePortable(wrapperEmploye.getTelephonePortable());
        employeDTO.setFax(wrapperEmploye.getFax());
        employeDTO.setSalaireHoraire(wrapperEmploye.getSalaireHoraire());
        employeDTO.setSalaireBrutMensuel(wrapperEmploye.getSalaireBrutMensuel());
        employeDTO.setNbHeureMensuelle(wrapperEmploye.getNbHeureMensuelle());
        employeDTO.setCategorie(wrapperEmploye.getCategorie());
        employeDTO.setPoste(wrapperEmploye.getPoste());
        employeDTO.setDateEmbauche(wrapperEmploye.getDateEmbauche());
        employeDTO.setDateSortie(wrapperEmploye.getDateSortie());
        employeDTO.setSituationFamiliale(wrapperEmploye.getSituationFamiliale());
        employeDTO.setNbEnfantACharge(wrapperEmploye.getNbEnfantACharge());
        employeDTO.setPeriodeEssai(wrapperEmploye.getPeriodeEssai());

        employeDTO.setAdresseId(wrapperEmploye.getAdresseId());
        employeDTO.setStatutEmployeId(wrapperEmploye.getStatutEmployeId());
        employeDTO.setSocieteId(wrapperEmploye.getSocieteId());

        return employeDTO;
    }

    //WrapperEmploye to AdresseDTO
    /**
     * Mappe wrapperEmploye to adresseDto
     *
     * @param wrapperEmploye the wrapper containing adresse attributes
     * @return adresseDTO
     */
    public AdresseDTO toAdresseDto(final WrapperEmploye wrapperEmploye) {
        final AdresseDTO adresseDTO = new AdresseDTO();

        adresseDTO.setId(wrapperEmploye.getAdresseId());
        adresseDTO.setNumeroRue(wrapperEmploye.getNumeroRue());
        adresseDTO.setNomRue(wrapperEmploye.getNomRue());
        adresseDTO.setBoitePostale(wrapperEmploye.getBoitePostale());
        adresseDTO.setCodePostal(wrapperEmploye.getCodePostal());
        adresseDTO.setVille(wrapperEmploye.getVille());
        adresseDTO.setPays(wrapperEmploye.getPays());

        return adresseDTO;
    }

    //WrapperEmploye to StatutEmployeDTO
    /**
     * Mappe wrapperEmploye to StatutEmployeDto
     *
     * @param wrapperEmploye the wrapper containing statutEmploye attributes
     * @return statutEmployeDTO
     */
    public StatutEmployeDTO toStatutEmploye(final WrapperEmploye wrapperEmploye) {

        final StatutEmployeDTO statutEmployeDTO = new StatutEmployeDTO();

        statutEmployeDTO.setId(wrapperEmploye.getStatutEmployeId());
        statutEmployeDTO.setCodeRef(wrapperEmploye.getCodeRefStatut());
        statutEmployeDTO.setLibelle(wrapperEmploye.getLibelle());

        return statutEmployeDTO;
    }

    //WrapperEmploye to SocieteDTO
    /**
     * Mappe wrapperEmploye to societeDTO
     *
     * @param wrapperEmploye the wrapper containing societe attributes
     * @return societeDTO
     */
    public SocieteDTO toSocieteDto(final WrapperEmploye wrapperEmploye) {
        final SocieteDTO societeDTO = new SocieteDTO();

        societeDTO.setId(wrapperEmploye.getSocieteId());

        return societeDTO;
    }

    //WrapperEmploye to InfoEntreprise
    /**
     * Mappe wrapperEmploye to infoEntrepriseDTO
     *
     * @param wrapperEmploye the wrapper containing infoEntreprise attributes
     * @return infoEntrepriseDTO
     */
    public InfoEntrepriseDTO toInfoEntrepriseDto(final WrapperEmploye wrapperEmploye) {

        final InfoEntrepriseDTO infoEntrepriseDTO = new InfoEntrepriseDTO();

        infoEntrepriseDTO.setId(wrapperEmploye.getInfoEntrepriseId());
        infoEntrepriseDTO.setRaisonSociale(wrapperEmploye.getRaisonSociale());

        return infoEntrepriseDTO;
    }

    //wrapperEmploye to contratDTO
    /**
     * Mappe wrapperEmploye to contratDTO
     *
     * @param wrapperEmploye the wrapper containing contrat attributes
     * @return contratDTO
     */
    public ContratDTO toContratDto(final WrapperEmploye wrapperEmploye) {

        final  ContratDTO contratDTO = new ContratDTO();

        contratDTO.setId(wrapperEmploye.getIdContrat());
        contratDTO.setTitre((wrapperEmploye.getTitre()));
        contratDTO.setDateCreation(wrapperEmploye.getDateCreation());
        contratDTO.setSigne(wrapperEmploye.getSigne());
        contratDTO.setArchive(wrapperEmploye.getArchive());

        return contratDTO;
    }

    //wrapperEmploye to typeContratDTO
    /**
     * Mappe wrapperEmploye to contratDTO
     *
     * @param wrapperEmploye the wrapper containing typeContrat attributes
     * @return typeContratDTO
     */
    public TypeContratDTO toTypeContratDto(final WrapperEmploye wrapperEmploye) {

        final TypeContratDTO typeContratDTO = new TypeContratDTO();

        typeContratDTO.setId(wrapperEmploye.getIdTypeContrat());
        typeContratDTO.setCodeRef(wrapperEmploye.getCodeRefContrat());
        typeContratDTO.setIntitule(wrapperEmploye.getIntitule());

        return typeContratDTO;
    }

}
