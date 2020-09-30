package fr.insy2s.service.mapper;


import fr.insy2s.domain.Dpae;
import fr.insy2s.service.dto.DpaeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Dpae} and its DTO {@link DpaeDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeMapper.class})
public interface DpaeMapper extends EntityMapper<DpaeDTO, Dpae> {

    @Mapping(source = "employe.id", target = "employeId")
    DpaeDTO toDto(Dpae dpae);

    @Mapping(target = "listeDocuments", ignore = true)
    @Mapping(target = "removeListeDocuments", ignore = true)
    @Mapping(source = "employeId", target = "employe")
    Dpae toEntity(DpaeDTO dpaeDTO);

    default Dpae fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dpae dpae = new Dpae();
        dpae.setId(id);
        return dpae;
    }
}
