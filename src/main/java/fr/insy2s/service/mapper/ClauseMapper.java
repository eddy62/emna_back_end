package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.ClauseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Clause} and its DTO {@link ClauseDTO}.
 */
@Mapper(componentModel = "spring", uses = {ContratMapper.class, AvenantMapper.class, SocieteMapper.class})
public interface ClauseMapper extends EntityMapper<ClauseDTO, Clause> {

    @Mapping(source = "societe.id", target = "societeId")
    ClauseDTO toDto(Clause clause);

    @Mapping(target = "removeListeContrats", ignore = true)
    @Mapping(target = "removeListeAvenants", ignore = true)
    @Mapping(source = "societeId", target = "societe")
    Clause toEntity(ClauseDTO clauseDTO);

    default Clause fromId(Long id) {
        if (id == null) {
            return null;
        }
        Clause clause = new Clause();
        clause.setId(id);
        return clause;
    }
}
