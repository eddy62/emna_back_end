package fr.insy2s.service.mapper;

import fr.insy2s.service.dto.*;

public class SocieteInfoEntrepriseAdresseUserMapper {

    public UserDTO SocieteInfoEntrepriseAdresseUserDtoToUserDto(SocieteInfoEntrepriseAdresseUserDTO societeInfoEntrepriseAdresseUserDTO){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(societeInfoEntrepriseAdresseUserDTO.getIdUser());
        userDTO.setLogin(societeInfoEntrepriseAdresseUserDTO.getLogin());
        userDTO.setFirstName(societeInfoEntrepriseAdresseUserDTO.getFirstName());
        userDTO.setLastName(societeInfoEntrepriseAdresseUserDTO.getLastName());
        userDTO.setEmail(societeInfoEntrepriseAdresseUserDTO.getEmail());
        userDTO.setImageUrl(societeInfoEntrepriseAdresseUserDTO.getImageUrl());
        userDTO.setActivated(societeInfoEntrepriseAdresseUserDTO.isActivated());
        userDTO.setLangKey(societeInfoEntrepriseAdresseUserDTO.getLangKey());
        userDTO.setCreatedBy(societeInfoEntrepriseAdresseUserDTO.getCreatedBy());
        userDTO.setCreatedDate(societeInfoEntrepriseAdresseUserDTO.getCreatedDate());
        userDTO.setLastModifiedBy(societeInfoEntrepriseAdresseUserDTO.getLastModifiedBy());
        userDTO.setLastModifiedDate(societeInfoEntrepriseAdresseUserDTO.getLastModifiedDate());
        userDTO.setAuthorities(societeInfoEntrepriseAdresseUserDTO.getAuthorities());
        return userDTO;
    }

    public SocieteDTO SocieteInfoEntrepriseAdresseUserDtoToSocieteDto(SocieteInfoEntrepriseAdresseUserDTO societeInfoEntrepriseAdresseUserDTO){
        SocieteDTO societeDTO = new SocieteDTO();
        societeDTO.setId(societeInfoEntrepriseAdresseUserDTO.getId());
        societeDTO.setAdresseId(societeInfoEntrepriseAdresseUserDTO.getIdAdresse());
        societeDTO.setCivilite(societeInfoEntrepriseAdresseUserDTO.getCivilite());
        societeDTO.setUserId(societeInfoEntrepriseAdresseUserDTO.getIdUser());
        societeDTO.setInfoEntrepriseId(societeInfoEntrepriseAdresseUserDTO.getIdInfoEntreprise());
        return  societeDTO;
    }

    public InfoEntrepriseDTO SocieteInfoEntrepriseAdresseUserDtoToInfoEntrepriseDto(SocieteInfoEntrepriseAdresseUserDTO societeInfoEntrepriseAdresseUserDTO){
        InfoEntrepriseDTO infoEntrepriseDTO = new InfoEntrepriseDTO();
        infoEntrepriseDTO.setId(societeInfoEntrepriseAdresseUserDTO.getIdInfoEntreprise());
        infoEntrepriseDTO.setRaisonSociale(societeInfoEntrepriseAdresseUserDTO.getRaisonSociale());
        infoEntrepriseDTO.setTelephone(societeInfoEntrepriseAdresseUserDTO.getTelephone());
        infoEntrepriseDTO.setFax(societeInfoEntrepriseAdresseUserDTO.getFax());
        infoEntrepriseDTO.setFormeJuridique(societeInfoEntrepriseAdresseUserDTO.getFormeJuridique());
        infoEntrepriseDTO.setDateDeCreation(societeInfoEntrepriseAdresseUserDTO.getDateDeCreation());
        infoEntrepriseDTO.setSiren(societeInfoEntrepriseAdresseUserDTO.getSiren());
        infoEntrepriseDTO.setSiret(societeInfoEntrepriseAdresseUserDTO.getSiret());
        infoEntrepriseDTO.setDomaineDactivite(societeInfoEntrepriseAdresseUserDTO.getDomaineDactivite());
        infoEntrepriseDTO.setDescription(societeInfoEntrepriseAdresseUserDTO.getDescription());
        infoEntrepriseDTO.setEmail(societeInfoEntrepriseAdresseUserDTO.getEmailPro());
        return infoEntrepriseDTO;
    }

    public AdresseDTO SocieteInfoEntrepriseAdresseUserDtoToAdresseDto(SocieteInfoEntrepriseAdresseUserDTO societeInfoEntrepriseAdresseUserDTO){
        AdresseDTO adresseDTO = new AdresseDTO();
        adresseDTO.setId(societeInfoEntrepriseAdresseUserDTO.getIdAdresse());
        adresseDTO.setNomRue(societeInfoEntrepriseAdresseUserDTO.getNomRue());
        adresseDTO.setBoitePostale(societeInfoEntrepriseAdresseUserDTO.getBoitePostale());
        adresseDTO.setNumeroRue(societeInfoEntrepriseAdresseUserDTO.getNumeroRue());
        adresseDTO.setCodePostal(societeInfoEntrepriseAdresseUserDTO.getCodePostal());
        adresseDTO.setVille(societeInfoEntrepriseAdresseUserDTO.getVille());
        adresseDTO.setPays(societeInfoEntrepriseAdresseUserDTO.getPays());
        return adresseDTO;
    }
}
