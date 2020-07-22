package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.AvenantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Avenant} and its DTO {@link AvenantDTO}.
 */
@Mapper(componentModel = "spring", uses = {ContratMapper.class})
public interface AvenantMapper extends EntityMapper<AvenantDTO, Avenant> {

    @Mapping(source = "contrat.id", target = "contratId")
    AvenantDTO toDto(Avenant avenant);

    @Mapping(source = "contratId", target = "contrat")
    @Mapping(target = "listeArticles", ignore = true)
    @Mapping(target = "removeListeArticles", ignore = true)
    @Mapping(target = "listeClauses", ignore = true)
    @Mapping(target = "removeListeClauses", ignore = true)
    Avenant toEntity(AvenantDTO avenantDTO);

    default Avenant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Avenant avenant = new Avenant();
        avenant.setId(id);
        return avenant;
    }
}
