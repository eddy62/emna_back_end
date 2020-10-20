package fr.insy2s.service.mapper;

import fr.insy2s.service.dto.*;
import fr.insy2s.utils.wrapper.WrapperEmploye;
import fr.insy2s.utils.wrapper.WrapperPdfDpae;
import fr.insy2s.utils.wrapper.WrapperSociete;
import org.springframework.stereotype.Service;

@Service
public class WrapperPdfDpaeMapper {

    /**
     * Builder wrapperPdfDpae
     *
     * @param dpaeDTO the dpaeDTO for building a wrapperPdfDpae
     * @param typeContratDTO the typeContratDTO for building a wrapperPdfDpae
     * @param employeDTO the employeDTO for building a wrapperPdfDpae
     * @param societeDTO the societeDTO for building a wrapperPdfDpae
     * @param infoEntrepriseDTO the infoEntrepriseDTO for building a wrapperPdfDpae
     * @param adresseDTO the adresseDTO for building a wrapperPdfDpae
     *
     * @return wrapperPdfDpae
     */
    public WrapperPdfDpae builderWrapperPdfDpae(final DpaeDTO dpaeDTO,
                                                final TypeContratDTO typeContratDTO,
                                                final EmployeDTO employeDTO,
                                                final SocieteDTO societeDTO,
                                                final InfoEntrepriseDTO infoEntrepriseDTO,
                                                final AdresseDTO adresseDTO){
        final WrapperPdfDpae wrapperPdfDpae = new WrapperPdfDpae();

        wrapperPdfDpae.setId(dpaeDTO.getId());
        //employeur
        wrapperPdfDpae.setDesignation(societeDTO.getCivilite());

        wrapperPdfDpae.setSiret(infoEntrepriseDTO.getSiret());
        wrapperPdfDpae.setApeCode(infoEntrepriseDTO.getDomaineDactivite());
        wrapperPdfDpae.setUrssafCode(infoEntrepriseDTO.getCodeUrssaf());
        wrapperPdfDpae.setPhoneNumber(infoEntrepriseDTO.getTelephone());

        wrapperPdfDpae.setStreetDesignation(adresseDTO.getNumeroRue() + " " + adresseDTO.getNomRue());
        wrapperPdfDpae.setTown(adresseDTO.getVille());
        wrapperPdfDpae.setPostalCode(adresseDTO.getBoitePostale());

        //salarie
        wrapperPdfDpae.setSurname(employeDTO.getNomNaissance());
        wrapperPdfDpae.setChristianName(employeDTO.getPrenom());
        wrapperPdfDpae.setSex(employeDTO.getCivilite());
        wrapperPdfDpae.setNir(employeDTO.getNumeroSecuriteSociale());
        //wrapperPdfDpae.setNirKey();
        wrapperPdfDpae.setBirthDate(employeDTO.getDateNaissance());
        wrapperPdfDpae.setBirthTown(employeDTO.getVilleNaissance());
        wrapperPdfDpae.setDepartmentBirth(employeDTO.getDepartementNaissance());
        wrapperPdfDpae.setCountryBirth(employeDTO.getPaysNaissance());
        //contrat
        wrapperPdfDpae.setStartContractDate(employeDTO.getDateEmbauche());
        wrapperPdfDpae.setStartContractTime(dpaeDTO.getHeureEmbauche());
        wrapperPdfDpae.setNature(typeContratDTO.getCodeRef());
        wrapperPdfDpae.setEndContractDate(employeDTO.getDateSortie());
        wrapperPdfDpae.setTrialTime(employeDTO.getPeriodeEssai());
        wrapperPdfDpae.setHealthService(infoEntrepriseDTO.getServiceSanteTravail());
        wrapperPdfDpae.setComment(dpaeDTO.getCommentaire());

        return wrapperPdfDpae;
    }
}
