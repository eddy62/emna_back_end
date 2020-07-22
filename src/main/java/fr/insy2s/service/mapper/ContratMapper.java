package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.ContratDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contrat} and its DTO {@link ContratDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeMapper.class})
public interface ContratMapper extends EntityMapper<ContratDTO, Contrat> {

    @Mapping(source = "employe.id", target = "employeId")
    ContratDTO toDto(Contrat contrat);

    @Mapping(target = "listeAvenants", ignore = true)
    @Mapping(target = "removeListeAvenants", ignore = true)
    @Mapping(source = "employeId", target = "employe")
    @Mapping(target = "listeArticles", ignore = true)
    @Mapping(target = "removeListeArticles", ignore = true)
    @Mapping(target = "listeClauses", ignore = true)
    @Mapping(target = "removeListeClauses", ignore = true)
    Contrat toEntity(ContratDTO contratDTO);

    default Contrat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contrat contrat = new Contrat();
        contrat.setId(id);
        return contrat;
    }
}
