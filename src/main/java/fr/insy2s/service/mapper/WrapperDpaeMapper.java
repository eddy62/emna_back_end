package fr.insy2s.service.mapper;

import fr.insy2s.domain.Employe;
import fr.insy2s.domain.InfoEntreprise;
import fr.insy2s.service.dto.*;
import fr.insy2s.utils.wrapper.WrapperDpae;
import org.springframework.stereotype.Service;


/**
 * Mapper for the entity Dpae,  and the DTO WrapperDpae.
 *
 */
@Service
public class WrapperDpaeMapper {


    /**
     * Builder WrapperDpae
     *
     * @param dpaeDTO the dpaDTO for building the WrapperDpae
     * @param infoEntrepriseDTO the infoEntrepriseDTO for building the WrapperDpae
     * @param employeDTO the employeDTO for building the WrapperDpae
     * @param adresseDTO the adressseDTO for building the WrapperDpae
     * @param typeContratDTO the typeContratDTO for building the WrapperDpae
     *
     * @return wrapperDpae
     */
    public WrapperDpae buildWrapperDpae(DpaeDTO dpaeDTO, InfoEntrepriseDTO infoEntrepriseDTO, EmployeDTO employeDTO, AdresseDTO adresseDTO, TypeContratDTO typeContratDTO){
        final WrapperDpae wrapperDpae = new WrapperDpae();

        //info dpae
        wrapperDpae.setId(dpaeDTO.getId());
        wrapperDpae.setLieu(dpaeDTO.getLieu());
        wrapperDpae.setDate(dpaeDTO.getDate());
        wrapperDpae.setEmployeId(dpaeDTO.getEmployeId());
        wrapperDpae.setHeureEmbauche(dpaeDTO.getHeureEmbauche());
        wrapperDpae.setCommentaire(dpaeDTO.getCommentaire());
        wrapperDpae.setRetourApiUrssaf(dpaeDTO.getRetourApiUrssaf());


        //info entreprise
        wrapperDpae.setRaisonSociale(infoEntrepriseDTO.getRaisonSociale());
        wrapperDpae.setTelephone(infoEntrepriseDTO.getTelephone());
        wrapperDpae.setFax(infoEntrepriseDTO.getFax());
        wrapperDpae.setSiret(infoEntrepriseDTO.getSiret());
        wrapperDpae.setDomaineDactivite(infoEntrepriseDTO.getDomaineDactivite());
        wrapperDpae.setCodeUrssaf(infoEntrepriseDTO.getCodeUrssaf());
        wrapperDpae.setServiceSanteTravail(infoEntrepriseDTO.getServiceSanteTravail());

        //adresse
        wrapperDpae.setNumeroRue(adresseDTO.getNumeroRue());
        wrapperDpae.setNomRue(adresseDTO.getNomRue());
        wrapperDpae.setCodePostal(adresseDTO.getCodePostal());
        wrapperDpae.setVille(adresseDTO.getVille());


        // info salarie
        wrapperDpae.setCivilite(employeDTO.getCivilite());
        wrapperDpae.setNomNaissance(employeDTO.getNomNaissance());
        wrapperDpae.setNomUsage(employeDTO.getNomUsage());
        wrapperDpae.setPrenom(employeDTO.getPrenom());
        wrapperDpae.setDateNaissance(employeDTO.getDateNaissance());
        wrapperDpae.setVilleNaissance(employeDTO.getVilleNaissance());
        wrapperDpae.setDepartementNaissance(employeDTO.getDepartementNaissance());
        wrapperDpae.setPaysNaisance(employeDTO.getPaysNaisance());
        wrapperDpae.setNumeroSecuriteSociale(employeDTO.getNumeroSecuriteSociale());
        wrapperDpae.setDateEmbauche(employeDTO.getDateEmbauche());
        wrapperDpae.setDateSortie(employeDTO.getDateSortie());
        wrapperDpae.setPeriodeEssai(employeDTO.getPeriodeEssai());

        // info contrat
        wrapperDpae.setCodeRef(typeContratDTO.getCodeRef());

        return wrapperDpae;
    }

    /**
     * Mappe WrapperDpae to DpaeDto
     *
     * @param wrapperDpae the wrapperAbsence to map to a dpaeDTO
     *
     * @return dpaeDTO
     */
    public DpaeDTO toDpaeDto (final WrapperDpae wrapperDpae) {
      DpaeDTO dpaeDTO = new DpaeDTO();

        //info dpae
        dpaeDTO.setId(wrapperDpae.getId());
        dpaeDTO.setLieu(wrapperDpae.getLieu());
        dpaeDTO.setDate(wrapperDpae.getDate());
        dpaeDTO.setEmployeId(wrapperDpae.getEmployeId());
        dpaeDTO.setHeureEmbauche(wrapperDpae.getHeureEmbauche());
        dpaeDTO.setCommentaire(wrapperDpae.getCommentaire());
        dpaeDTO.setRetourApiUrssaf(wrapperDpae.getRetourApiUrssaf());


        return dpaeDTO;
    }

    /**
     * Mappe WrapperDpae to InfoEntrepriseDto
     *
     * @param wrapperDpae the wrapperAbsence to map to a infoEntrepriseDto
     *
     * @return InfoEntrepriseDto
     */
    public InfoEntrepriseDTO toInfoEntrepriseDto (final WrapperDpae wrapperDpae) {
        InfoEntrepriseDTO infoEntrepriseDTO = new InfoEntrepriseDTO();

        infoEntrepriseDTO.setRaisonSociale(wrapperDpae.getRaisonSociale());
        infoEntrepriseDTO.setTelephone(wrapperDpae.getTelephone());
        infoEntrepriseDTO.setFax(wrapperDpae.getFax());
        infoEntrepriseDTO.setSiret(wrapperDpae.getSiret());
        infoEntrepriseDTO.setDomaineDactivite(wrapperDpae.getDomaineDactivite());
        infoEntrepriseDTO.setCodeUrssaf(wrapperDpae.getCodeUrssaf());
        infoEntrepriseDTO.setServiceSanteTravail(wrapperDpae.getServiceSanteTravail());

        return infoEntrepriseDTO;
    }

    /**
     * Mappe WrapperDpae to AdresseDto
     *
     * @param wrapperDpae the wrapperAbsence to map to a adresseDto
     *
     * @return AdresseDto
     */

    public AdresseDTO toAdresseDto (final WrapperDpae wrapperDpae) {
        AdresseDTO adresseDTO = new AdresseDTO();

        adresseDTO.setNumeroRue(wrapperDpae.getNumeroRue());
        adresseDTO.setNomRue(wrapperDpae.getNomRue());
        adresseDTO.setCodePostal(wrapperDpae.getCodePostal());
        adresseDTO.setVille(wrapperDpae.getVille());

        return adresseDTO;
    }

    /**
     * Mappe WrapperDpae to EmployeDto
     *
     * @param wrapperDpae the wrapperDpae to map to a employeDto
     *
     * @return EmployeDto
     */
    public EmployeDTO toEmployeDto (final WrapperDpae wrapperDpae) {
        EmployeDTO employeDTO = new EmployeDTO();

        employeDTO.setCivilite(wrapperDpae.getCivilite());
        employeDTO.setNomNaissance(wrapperDpae.getNomNaissance());
        employeDTO.setNomUsage(wrapperDpae.getNomUsage());
        employeDTO.setPrenom(wrapperDpae.getPrenom());
        employeDTO.setDateNaissance(wrapperDpae.getDateNaissance());
        employeDTO.setVilleNaissance(wrapperDpae.getVilleNaissance());
        employeDTO.setDepartementNaissance(wrapperDpae.getDepartementNaissance());
        employeDTO.setPaysNaisance(wrapperDpae.getPaysNaisance());
        employeDTO.setNumeroSecuriteSociale(wrapperDpae.getNumeroSecuriteSociale());
        employeDTO.setDateEmbauche(wrapperDpae.getDateEmbauche());
        employeDTO.setDateSortie(wrapperDpae.getDateSortie());
        employeDTO.setPeriodeEssai(wrapperDpae.getPeriodeEssai());

        return employeDTO;
    }

    /**
     * Mappe WrapperDpae to TypeContratDto
     *
     * @param wrapperDpae the wrapperDpae to map to a typeContratDto
     *
     * @return TypeContratDto
     */
    public TypeContratDTO toTypeContratDto (final WrapperDpae wrapperDpae) {
        TypeContratDTO typeContratDTO = new TypeContratDTO();

        typeContratDTO.setCodeRef(wrapperDpae.getCodeRef());

        return typeContratDTO;
    }

}


