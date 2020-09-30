package fr.insy2s.service.mapper;


import fr.insy2s.domain.Operation;
import fr.insy2s.service.dto.OperationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Operation} and its DTO {@link OperationDTO}.
 */
@Mapper(componentModel = "spring", uses = {ReleveMapper.class})
public interface OperationMapper extends EntityMapper<OperationDTO, Operation> {

    @Mapping(source = "releve.id", target = "releveId")
    OperationDTO toDto(Operation operation);

    @Mapping(target = "listeFactures", ignore = true)
    @Mapping(target = "removeListeFactures", ignore = true)
    @Mapping(source = "releveId", target = "releve")
    Operation toEntity(OperationDTO operationDTO);

    default Operation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Operation operation = new Operation();
        operation.setId(id);
        return operation;
    }
}
