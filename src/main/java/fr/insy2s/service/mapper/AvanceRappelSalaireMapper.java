package fr.insy2s.service.mapper;


import fr.insy2s.domain.AvanceRappelSalaire;
import fr.insy2s.service.dto.AvanceRappelSalaireDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link AvanceRappelSalaire} and its DTO {@link AvanceRappelSalaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtatVariablePaieMapper.class, EmployeMapper.class})
public interface AvanceRappelSalaireMapper extends EntityMapper<AvanceRappelSalaireDTO, AvanceRappelSalaire> {

    @Mapping(source = "etatVariablePaie.id", target = "etatVariablePaieId")
    @Mapping(source = "employe.id", target = "employeId")
    AvanceRappelSalaireDTO toDto(AvanceRappelSalaire avanceRappelSalaire);

    @Mapping(source = "etatVariablePaieId", target = "etatVariablePaie")
    @Mapping(source = "employeId", target = "employe")
    AvanceRappelSalaire toEntity(AvanceRappelSalaireDTO avanceRappelSalaireDTO);

    default AvanceRappelSalaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        AvanceRappelSalaire avanceRappelSalaire = new AvanceRappelSalaire();
        avanceRappelSalaire.setId(id);
        return avanceRappelSalaire;
    }
}
