package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.AvenantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Avenant} and its DTO {@link AvenantDTO}.
 */
@Mapper(componentModel = "spring", uses = {SaisieArticleMapper.class})
public interface AvenantMapper extends EntityMapper<AvenantDTO, Avenant> {

    @Mapping(source = "saisieArticle.id", target = "saisieArticleId")
    AvenantDTO toDto(Avenant avenant);

    @Mapping(target = "listeDocuments", ignore = true)
    @Mapping(target = "removeListeDocuments", ignore = true)
    @Mapping(source = "saisieArticleId", target = "saisieArticle")
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
