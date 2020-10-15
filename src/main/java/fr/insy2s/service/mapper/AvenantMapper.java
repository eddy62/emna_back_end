package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.AvenantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Avenant} and its DTO {@link AvenantDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AvenantMapper extends EntityMapper<AvenantDTO, Avenant> {


    @Mapping(target = "listeSaisieArticles", ignore = true)
    @Mapping(target = "removeListeSaisieArticle", ignore = true)
    @Mapping(target = "listeDocuments", ignore = true)
    @Mapping(target = "removeListeDocuments", ignore = true)
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
