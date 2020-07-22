package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.EtatFactureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtatFacture} and its DTO {@link EtatFactureDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtatFactureMapper extends EntityMapper<EtatFactureDTO, EtatFacture> {



    default EtatFacture fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtatFacture etatFacture = new EtatFacture();
        etatFacture.setId(id);
        return etatFacture;
    }
}
