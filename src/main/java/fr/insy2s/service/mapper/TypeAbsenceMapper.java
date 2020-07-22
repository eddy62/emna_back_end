package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.TypeAbsenceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeAbsence} and its DTO {@link TypeAbsenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeAbsenceMapper extends EntityMapper<TypeAbsenceDTO, TypeAbsence> {



    default TypeAbsence fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeAbsence typeAbsence = new TypeAbsence();
        typeAbsence.setId(id);
        return typeAbsence;
    }
}
