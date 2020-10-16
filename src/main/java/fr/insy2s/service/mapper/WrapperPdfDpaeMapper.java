package fr.insy2s.service.mapper;

import fr.insy2s.service.dto.DpaeDTO;
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
     * @param wrapperEmploye the wrapperEmploye for building a wrapperPdfDpae
     * @param wrapperSociete the wrapperSociete for building a wrapperPdfDpae
     *
     * @return wrapperPdfDpae
     */
    public WrapperPdfDpae builderWrapperPdfDpae(final DpaeDTO dpaeDTO,
                                                final WrapperEmploye wrapperEmploye,
                                                final WrapperSociete wrapperSociete){
        final WrapperPdfDpae wrapperPdfDpae = new WrapperPdfDpae();

        wrapperPdfDpae.setId(dpaeDTO.getId());
        //employeur
        wrapperPdfDpae.setDesignation(wrapperSociete.getCivilite());
        wrapperPdfDpae.setSiret(wrapperSociete.getSiret());
        wrapperPdfDpae.setApeCode(wrapperSociete.getDomaineDactivite());
        wrapperPdfDpae.setUrssafCode(wrapperSociete.getCodeUrssaf());
        wrapperPdfDpae.setStreetDesignation(wrapperSociete.getNumeroRue() + " " + wrapperSociete.getNomRue());
        wrapperPdfDpae.setTown(wrapperSociete.getVille());
        wrapperPdfDpae.setPostalCode(wrapperSociete.getBoitePostale());
        wrapperPdfDpae.setPhoneNumber(wrapperSociete.getTelephone());
        //salarie
        wrapperPdfDpae.setSurname(wrapperEmploye.getNomNaissance());
        wrapperPdfDpae.setChristianName(wrapperEmploye.getPrenom());
        wrapperPdfDpae.setSex(wrapperEmploye.getCivilite());
        wrapperPdfDpae.setNir(wrapperEmploye.getNumeroSecuriteSociale());
        //wrapperPdfDpae.setNirKey();
        wrapperPdfDpae.setBirthDate(wrapperEmploye.getDateNaissance());
        wrapperPdfDpae.setBirthTown(wrapperEmploye.getVilleNaissance());
        wrapperPdfDpae.setDepartmentBirth(wrapperEmploye.getDepartementNaissance());
        wrapperPdfDpae.setCountryBirth(wrapperEmploye.getPaysNaissance());
        //contrat
        wrapperPdfDpae.setStartContractDate(wrapperEmploye.getDateEmbauche());
        wrapperPdfDpae.setStartContractTime(dpaeDTO.getHeureEmbauche());
        wrapperPdfDpae.setNature(wrapperEmploye.getCodeRefContrat());
        wrapperPdfDpae.setEndContractDate(wrapperEmploye.getDateSortie());
        wrapperPdfDpae.setTrialTime(wrapperEmploye.getPeriodeEssai());
        wrapperPdfDpae.setHealthService(wrapperSociete.getServiceSanteTravail());
        wrapperPdfDpae.setComment(dpaeDTO.getCommentaire());

        return wrapperPdfDpae;
    }
}
