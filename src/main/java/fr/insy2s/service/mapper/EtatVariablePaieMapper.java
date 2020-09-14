package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.EtatVariablePaieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtatVariablePaie} and its DTO {@link EtatVariablePaieDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtatVariablePaieMapper extends EntityMapper<EtatVariablePaieDTO, EtatVariablePaie> {



    default EtatVariablePaie fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtatVariablePaie etatVariablePaie = new EtatVariablePaie();
        etatVariablePaie.setId(id);
        return etatVariablePaie;
    }
}
