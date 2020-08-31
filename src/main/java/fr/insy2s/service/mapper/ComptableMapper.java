package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.ComptableDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comptable} and its DTO {@link ComptableDTO}.
 */
@Mapper(componentModel = "spring", uses = {InfoEntrepriseMapper.class, UserMapper.class, AdresseMapper.class})
public interface ComptableMapper extends EntityMapper<ComptableDTO, Comptable> {

    @Mapping(source = "infoEntreprise.id", target = "infoEntrepriseId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "adresse.id", target = "adresseId")
    ComptableDTO toDto(Comptable comptable);

    @Mapping(source = "infoEntrepriseId", target = "infoEntreprise")
    @Mapping(source = "userId", target = "user")
    @Mapping(target = "listeSocietes", ignore = true)
    @Mapping(target = "removeListeSocietes", ignore = true)
    @Mapping(source = "adresseId", target = "adresse")
    Comptable toEntity(ComptableDTO comptableDTO);

    default Comptable fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comptable comptable = new Comptable();
        comptable.setId(id);
        return comptable;
    }
}
