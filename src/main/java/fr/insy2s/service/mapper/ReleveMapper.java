package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.ReleveDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Releve} and its DTO {@link ReleveDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtatReleveMapper.class, SocieteMapper.class})
public interface ReleveMapper extends EntityMapper<ReleveDTO, Releve> {

    @Mapping(source = "etatReleve.id", target = "etatReleveId")
    @Mapping(source = "societe.id", target = "societeId")
    ReleveDTO toDto(Releve releve);

    @Mapping(source = "etatReleveId", target = "etatReleve")
    @Mapping(target = "listeOperations", ignore = true)
    @Mapping(target = "removeListeOperations", ignore = true)
    @Mapping(source = "societeId", target = "societe")
    Releve toEntity(ReleveDTO releveDTO);

    default Releve fromId(Long id) {
        if (id == null) {
            return null;
        }
        Releve releve = new Releve();
        releve.setId(id);
        return releve;
    }
}
