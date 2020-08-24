package fr.insy2s.service.mapper;

import fr.insy2s.service.dto.*;

;

public class ComptableInfoEntrepriseAdresseUserMapper {

    public UserDTO comptableInfoEntrepriseAdresseUserDtoToUserDto(ComptableInfoEntrepriseAdresseUserDTO comptableInfoEntrepriseAdresseUserDTO){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(comptableInfoEntrepriseAdresseUserDTO.getIdUser());
        userDTO.setLogin(comptableInfoEntrepriseAdresseUserDTO.getLogin());
        userDTO.setFirstName(comptableInfoEntrepriseAdresseUserDTO.getFirstName());
        userDTO.setLastName(comptableInfoEntrepriseAdresseUserDTO.getLastName());
        userDTO.setEmail(comptableInfoEntrepriseAdresseUserDTO.getEmail());
        userDTO.setImageUrl(comptableInfoEntrepriseAdresseUserDTO.getImageUrl());
        userDTO.setActivated(comptableInfoEntrepriseAdresseUserDTO.isActivated());
        userDTO.setLangKey(comptableInfoEntrepriseAdresseUserDTO.getLangKey());
        userDTO.setCreatedBy(comptableInfoEntrepriseAdresseUserDTO.getCreatedBy());
        userDTO.setCreatedDate(comptableInfoEntrepriseAdresseUserDTO.getCreatedDate());
        userDTO.setLastModifiedBy(comptableInfoEntrepriseAdresseUserDTO.getLastModifiedBy());
        userDTO.setLastModifiedDate(comptableInfoEntrepriseAdresseUserDTO.getLastModifiedDate());
        userDTO.setAuthorities(comptableInfoEntrepriseAdresseUserDTO.getAuthorities());
        return userDTO;
    }

    public ComptableDTO comptableInfoEntrepriseAdresseUserDtoToComptableDto(ComptableInfoEntrepriseAdresseUserDTO comptableInfoEntrepriseAdresseUserDTO){
        ComptableDTO comptableDTO = new ComptableDTO();
        comptableDTO.setId(comptableInfoEntrepriseAdresseUserDTO.getId());
        comptableDTO.setAdresseId(comptableInfoEntrepriseAdresseUserDTO.getIdAdresse());
        comptableDTO.setCivilite(comptableInfoEntrepriseAdresseUserDTO.getCivilite());
        comptableDTO.setUserId(comptableInfoEntrepriseAdresseUserDTO.getIdUser());
        comptableDTO.setInfoEntrepriseId(comptableInfoEntrepriseAdresseUserDTO.getIdInfoEntreprise());

        return  comptableDTO;
    }



    public InfoEntrepriseDTO comptableInfoEntrepriseAdresseUserDtoToInfoEntrepriseDto(ComptableInfoEntrepriseAdresseUserDTO comptableInfoEntrepriseAdresseUserDTO){

        InfoEntrepriseDTO infoEntrepriseDTO = new InfoEntrepriseDTO();
        infoEntrepriseDTO.setId(comptableInfoEntrepriseAdresseUserDTO.getIdInfoEntreprise());
        infoEntrepriseDTO.setRaisonSociale(comptableInfoEntrepriseAdresseUserDTO.getRaisonSociale());
        infoEntrepriseDTO.setTelephone(comptableInfoEntrepriseAdresseUserDTO.getTelephone());
        infoEntrepriseDTO.setFax(comptableInfoEntrepriseAdresseUserDTO.getFax());
        infoEntrepriseDTO.setFormeJuridique(comptableInfoEntrepriseAdresseUserDTO.getFormeJuridique());
        infoEntrepriseDTO.setDateDeCreation(comptableInfoEntrepriseAdresseUserDTO.getDateDeCreation());
        infoEntrepriseDTO.setSiren(comptableInfoEntrepriseAdresseUserDTO.getSiren());
        infoEntrepriseDTO.setSiret(comptableInfoEntrepriseAdresseUserDTO.getSiret());
        infoEntrepriseDTO.setDomaineDactivite(comptableInfoEntrepriseAdresseUserDTO.getDomaineDactivite());
        infoEntrepriseDTO.setDescription(comptableInfoEntrepriseAdresseUserDTO.getDescription());
        infoEntrepriseDTO.setEmail(comptableInfoEntrepriseAdresseUserDTO.getEmailPro());

        return infoEntrepriseDTO;
    }

    public AdresseDTO comptableInfoEntrepriseAdresseUserDtoToAdresseDto(ComptableInfoEntrepriseAdresseUserDTO comptableInfoEntrepriseAdresseUserDTO){

        AdresseDTO adresseDTO = new AdresseDTO();
        adresseDTO.setId(comptableInfoEntrepriseAdresseUserDTO.getIdAdresse());
        adresseDTO.setNomRue(comptableInfoEntrepriseAdresseUserDTO.getNomRue());
        adresseDTO.setBoitePostale(comptableInfoEntrepriseAdresseUserDTO.getBoitePostale());
        adresseDTO.setNumeroRue(comptableInfoEntrepriseAdresseUserDTO.getNumeroRue());
        adresseDTO.setCodePostal(comptableInfoEntrepriseAdresseUserDTO.getCodePostal());
        adresseDTO.setVille(comptableInfoEntrepriseAdresseUserDTO.getVille());
        adresseDTO.setPays(comptableInfoEntrepriseAdresseUserDTO.getPays());
        return adresseDTO;

    }



}
