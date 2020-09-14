package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.EtatDepenseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtatDepense} and its DTO {@link EtatDepenseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtatDepenseMapper extends EntityMapper<EtatDepenseDTO, EtatDepense> {



    default EtatDepense fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtatDepense etatDepense = new EtatDepense();
        etatDepense.setId(id);
        return etatDepense;
    }
}
