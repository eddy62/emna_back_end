package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.NoteDeFraisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NoteDeFrais} and its DTO {@link NoteDeFraisDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtatVariablePaieMapper.class, EmployeMapper.class})
public interface NoteDeFraisMapper extends EntityMapper<NoteDeFraisDTO, NoteDeFrais> {

    @Mapping(source = "etatVariablePaie.id", target = "etatVariablePaieId")
    @Mapping(source = "employe.id", target = "employeId")
    NoteDeFraisDTO toDto(NoteDeFrais noteDeFrais);

    @Mapping(source = "etatVariablePaieId", target = "etatVariablePaie")
    @Mapping(source = "employeId", target = "employe")
    NoteDeFrais toEntity(NoteDeFraisDTO noteDeFraisDTO);

    default NoteDeFrais fromId(Long id) {
        if (id == null) {
            return null;
        }
        NoteDeFrais noteDeFrais = new NoteDeFrais();
        noteDeFrais.setId(id);
        return noteDeFrais;
    }
}
