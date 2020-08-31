package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.TypeContratDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeContrat} and its DTO {@link TypeContratDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeContratMapper extends EntityMapper<TypeContratDTO, TypeContrat> {



    default TypeContrat fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeContrat typeContrat = new TypeContrat();
        typeContrat.setId(id);
        return typeContrat;
    }
}
