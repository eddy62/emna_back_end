package fr.insy2s.service.mapper;

import org.springframework.stereotype.Service;

import fr.insy2s.service.dto.AdresseDTO;
import fr.insy2s.service.dto.EmployeDTO;
import fr.insy2s.service.dto.InfoEntrepriseDTO;
import fr.insy2s.service.dto.SocieteDTO;
import fr.insy2s.service.dto.StatutEmployeDTO;
import fr.insy2s.utils.wrapper.WrapperEmploye;

/**
 * Mapper for the entity Employe, Adresse, StatutEmploye, Societe, InfoEntreprise and the DTO WrapperEmploye.
 *
 */
@Service
public class WrapperEmployeMapper {

    /**
     * Builder WrapperEmploye
     *
     * @param employe
     * @param adresse
     * @param statutEmploye
     * @param societe
     * @param infoEntreprise
     * @return
     */
    public WrapperEmploye builderWrapperEmploye(final EmployeDTO employe, final AdresseDTO adresse, final StatutEmployeDTO statutEmploye, final SocieteDTO societe,
                    final InfoEntrepriseDTO infoEntreprise) {
        final WrapperEmploye wrapperEmploye = new WrapperEmploye();

        wrapperEmploye.setId(employe.getId());
        wrapperEmploye.setMatricule(employe.getMatricule());
        wrapperEmploye.setCivilite(employe.getCivilite());
        wrapperEmploye.setNomNaissance(employe.getNomNaissance());
        wrapperEmploye.setNomUsage(employe.getNomUsage());
        wrapperEmploye.setPrenom(employe.getPrenom());
        wrapperEmploye.setDateNaissance(employe.getDateNaissance());
        wrapperEmploye.setVilleNaissance(employe.getVilleNaissance());
        wrapperEmploye.setDepartementNaissance(employe.getDepartementNaissance());
        wrapperEmploye.setPaysNaisance(employe.getPaysNaisance());
        wrapperEmploye.setNumeroSecuriteSociale(employe.getNumeroSecuriteSociale());
        wrapperEmploye.setEmail(employe.getEmail());
        wrapperEmploye.setTelephoneFix(employe.getTelephoneFix());
        wrapperEmploye.setTelephonePortable(employe.getTelephonePortable());
        wrapperEmploye.setFax(employe.getFax());
        wrapperEmploye.setSalaireHoraire(employe.getSalaireHoraire());
        wrapperEmploye.setSalaireBrutMensuelle(employe.getSalaireBrutMensuelle());
        wrapperEmploye.setHeuresMensuelle(employe.getHeuresMensuelle());
        wrapperEmploye.setCategorie(employe.getCategorie());
        wrapperEmploye.setPoste(employe.getPoste());
        wrapperEmploye.setDateEmbauche(employe.getDateEmbauche());
        wrapperEmploye.setDateSortie(employe.getDateSortie());
        wrapperEmploye.setSituationFamiliale(employe.getSituationFamiliale());
        wrapperEmploye.setEnfantsACharge(employe.getEnfantsACharge());

        wrapperEmploye.setAdresseId(adresse.getId());
        wrapperEmploye.setNumeroRue(adresse.getNumeroRue());
        wrapperEmploye.setNomRue(adresse.getNomRue());
        wrapperEmploye.setBoitePostale(adresse.getBoitePostale());
        wrapperEmploye.setCodePostal(adresse.getCodePostal());
        wrapperEmploye.setVille(adresse.getVille());
        wrapperEmploye.setPays(adresse.getPays());

        wrapperEmploye.setStatutEmployeId(statutEmploye.getId());
        wrapperEmploye.setCodeRef(statutEmploye.getCodeRef());
        wrapperEmploye.setLibelle(statutEmploye.getLibelle());

        wrapperEmploye.setSocieteId(societe.getId());

        wrapperEmploye.setInfoEntrepriseId(infoEntreprise.getId());
        wrapperEmploye.setRaisonSociale(infoEntreprise.getRaisonSociale());

        return wrapperEmploye;
    }

    //ListEmploye to ListWrapperEmploye

    /**
     * Mappe WrapperEmploye to EmployeDto
     *
     * @param wrapperEmploye
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
        employeDTO.setPaysNaisance(wrapperEmploye.getPaysNaisance());
        employeDTO.setNumeroSecuriteSociale(wrapperEmploye.getNumeroSecuriteSociale());
        employeDTO.setEmail(wrapperEmploye.getEmail());
        employeDTO.setTelephoneFix(wrapperEmploye.getTelephoneFix());
        employeDTO.setTelephonePortable(wrapperEmploye.getTelephonePortable());
        employeDTO.setFax(wrapperEmploye.getFax());
        employeDTO.setSalaireHoraire(wrapperEmploye.getSalaireHoraire());
        employeDTO.setSalaireBrutMensuelle(wrapperEmploye.getSalaireBrutMensuelle());
        employeDTO.setHeuresMensuelle(wrapperEmploye.getHeuresMensuelle());
        employeDTO.setCategorie(wrapperEmploye.getCategorie());
        employeDTO.setPoste(wrapperEmploye.getPoste());
        employeDTO.setDateEmbauche(wrapperEmploye.getDateEmbauche());
        employeDTO.setDateSortie(wrapperEmploye.getDateSortie());
        employeDTO.setSituationFamiliale(wrapperEmploye.getSituationFamiliale());
        employeDTO.setEnfantsACharge(wrapperEmploye.getEnfantsACharge());

        employeDTO.setAdresseId(wrapperEmploye.getAdresseId());
        employeDTO.setStatutEmployeId(wrapperEmploye.getStatutEmployeId());
        employeDTO.setSocieteId(wrapperEmploye.getSocieteId());

        return employeDTO;

    }

    //WrapperEmploye to AdresseDTO
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
     * Mappe wrapperEmploye to StatutEmploye
     * @param wrapperEmploye
     * @return
     */
    public StatutEmployeDTO toStatutEmploye(final WrapperEmploye wrapperEmploye) {

        final StatutEmployeDTO statutEmployeDTO = new StatutEmployeDTO();

        statutEmployeDTO.setId(wrapperEmploye.getStatutEmployeId());
        statutEmployeDTO.setCodeRef(wrapperEmploye.getCodeRef());
        statutEmployeDTO.setLibelle(wrapperEmploye.getLibelle());

        return statutEmployeDTO;

    }

    //WrapperEmploye to SocieteDTO
    /**
     * Mappe wrapperEmploye to societeDTO
     * @param wrapperEmploye
     * @return
     */
    public SocieteDTO toSocieteDto(final WrapperEmploye wrapperEmploye) {
        final SocieteDTO societeDTO = new SocieteDTO();

        societeDTO.setId(wrapperEmploye.getSocieteId());

        return societeDTO;

    }

    //WrapperEmploye to InfoEntreprise
    public InfoEntrepriseDTO toInfoEntrepriseDto(final WrapperEmploye wrapperEmploye) {

        final InfoEntrepriseDTO infoEntrepriseDTO= new InfoEntrepriseDTO();

        infoEntrepriseDTO.setId(wrapperEmploye.getInfoEntrepriseId());
        infoEntrepriseDTO.setRaisonSociale(wrapperEmploye.getRaisonSociale());

        return infoEntrepriseDTO;

    }


}
