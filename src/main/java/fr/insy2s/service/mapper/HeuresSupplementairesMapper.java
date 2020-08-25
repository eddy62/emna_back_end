package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.HeuresSupplementairesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link HeuresSupplementaires} and its DTO {@link HeuresSupplementairesDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtatVariablePaieMapper.class, EmployeMapper.class})
public interface HeuresSupplementairesMapper extends EntityMapper<HeuresSupplementairesDTO, HeuresSupplementaires> {

    @Mapping(source = "etatVariablePaie.id", target = "etatVariablePaieId")
    @Mapping(source = "employe.id", target = "employeId")
    HeuresSupplementairesDTO toDto(HeuresSupplementaires heuresSupplementaires);

    @Mapping(source = "etatVariablePaieId", target = "etatVariablePaie")
    @Mapping(source = "employeId", target = "employe")
    HeuresSupplementaires toEntity(HeuresSupplementairesDTO heuresSupplementairesDTO);

    default HeuresSupplementaires fromId(Long id) {
        if (id == null) {
            return null;
        }
        HeuresSupplementaires heuresSupplementaires = new HeuresSupplementaires();
        heuresSupplementaires.setId(id);
        return heuresSupplementaires;
    }
}
