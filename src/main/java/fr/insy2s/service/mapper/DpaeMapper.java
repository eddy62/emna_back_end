package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.DpaeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dpae} and its DTO {@link DpaeDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeMapper.class, SocieteMapper.class})
public interface DpaeMapper extends EntityMapper<DpaeDTO, Dpae> {

    @Mapping(source = "employe.id", target = "employeId")
    @Mapping(source = "societe.id", target = "societeId")
    DpaeDTO toDto(Dpae dpae);

    @Mapping(source = "employeId", target = "employe")
    @Mapping(source = "societeId", target = "societe")
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
