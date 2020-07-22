package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.EtatReleveDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtatReleve} and its DTO {@link EtatReleveDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtatReleveMapper extends EntityMapper<EtatReleveDTO, EtatReleve> {



    default EtatReleve fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtatReleve etatReleve = new EtatReleve();
        etatReleve.setId(id);
        return etatReleve;
    }
}
