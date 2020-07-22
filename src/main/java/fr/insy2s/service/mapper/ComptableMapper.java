package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.ComptableDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comptable} and its DTO {@link ComptableDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdresseMapper.class, InfoEntrepriseMapper.class, UserMapper.class})
public interface ComptableMapper extends EntityMapper<ComptableDTO, Comptable> {

    @Mapping(source = "adresse.id", target = "adresseId")
    @Mapping(source = "infoEntreprise.id", target = "infoEntrepriseId")
    @Mapping(source = "user.id", target = "userId")
    ComptableDTO toDto(Comptable comptable);

    @Mapping(source = "adresseId", target = "adresse")
    @Mapping(source = "infoEntrepriseId", target = "infoEntreprise")
    @Mapping(source = "userId", target = "user")
    @Mapping(target = "listeSocietes", ignore = true)
    @Mapping(target = "removeListeSocietes", ignore = true)
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
