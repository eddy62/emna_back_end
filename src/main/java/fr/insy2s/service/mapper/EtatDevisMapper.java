package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.EtatDevisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtatDevis} and its DTO {@link EtatDevisDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtatDevisMapper extends EntityMapper<EtatDevisDTO, EtatDevis> {



    default EtatDevis fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtatDevis etatDevis = new EtatDevis();
        etatDevis.setId(id);
        return etatDevis;
    }
}
