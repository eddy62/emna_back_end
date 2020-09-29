package fr.insy2s.service.mapper;

import fr.insy2s.domain.Absence;
import fr.insy2s.service.dto.AbsenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Absence} and its DTO {@link AbsenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {TypeAbsenceMapper.class, EtatVariablePaieMapper.class, EmployeMapper.class})
public interface AbsenceMapper extends EntityMapper<AbsenceDTO, Absence> {

    @Mapping(source = "typeAbsence.id", target = "typeAbsenceId")
    @Mapping(source = "etatVariablePaie.id", target = "etatVariablePaieId")
    @Mapping(source = "employe.id", target = "employeId")
    AbsenceDTO toDto(Absence absence);

    @Mapping(source = "typeAbsenceId", target = "typeAbsence")
    @Mapping(source = "etatVariablePaieId", target = "etatVariablePaie")
    @Mapping(source = "employeId", target = "employe")
    Absence toEntity(AbsenceDTO absenceDTO);

    default Absence fromId(Long id) {
        if (id == null) {
            return null;
        }
        Absence absence = new Absence();
        absence.setId(id);
        return absence;
    }
}
