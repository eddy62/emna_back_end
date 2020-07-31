package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.StatutEmployeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link StatutEmploye} and its DTO {@link StatutEmployeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StatutEmployeMapper extends EntityMapper<StatutEmployeDTO, StatutEmploye> {



    default StatutEmploye fromId(Long id) {
        if (id == null) {
            return null;
        }
        StatutEmploye statutEmploye = new StatutEmploye();
        statutEmploye.setId(id);
        return statutEmploye;
    }
}
