package fr.insy2s.service.mapper;


import fr.insy2s.domain.AutresVariable;
import fr.insy2s.service.dto.AutresVariableDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link AutresVariable} and its DTO {@link AutresVariableDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtatVariablePaieMapper.class, EmployeMapper.class})
public interface AutresVariableMapper extends EntityMapper<AutresVariableDTO, AutresVariable> {

    @Mapping(source = "etatVariablePaie.id", target = "etatVariablePaieId")
    @Mapping(source = "employe.id", target = "employeId")
    AutresVariableDTO toDto(AutresVariable autresVariable);

    @Mapping(target = "listeDocuments", ignore = true)
    @Mapping(target = "removeListeDocuments", ignore = true)
    @Mapping(source = "etatVariablePaieId", target = "etatVariablePaie")
    @Mapping(source = "employeId", target = "employe")
    AutresVariable toEntity(AutresVariableDTO autresVariableDTO);

    default AutresVariable fromId(Long id) {
        if (id == null) {
            return null;
        }
        AutresVariable autresVariable = new AutresVariable();
        autresVariable.setId(id);
        return autresVariable;
    }
}
