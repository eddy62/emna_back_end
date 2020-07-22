package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.InfoEntrepriseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InfoEntreprise} and its DTO {@link InfoEntrepriseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InfoEntrepriseMapper extends EntityMapper<InfoEntrepriseDTO, InfoEntreprise> {



    default InfoEntreprise fromId(Long id) {
        if (id == null) {
            return null;
        }
        InfoEntreprise infoEntreprise = new InfoEntreprise();
        infoEntreprise.setId(id);
        return infoEntreprise;
    }
}
