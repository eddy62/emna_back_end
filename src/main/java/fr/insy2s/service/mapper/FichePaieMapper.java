package fr.insy2s.service.mapper;


import fr.insy2s.domain.FichePaie;
import fr.insy2s.service.dto.FichePaieDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link FichePaie} and its DTO {@link FichePaieDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeMapper.class})
public interface FichePaieMapper extends EntityMapper<FichePaieDTO, FichePaie> {

    @Mapping(source = "employe.id", target = "employeId")
    FichePaieDTO toDto(FichePaie fichePaie);

    @Mapping(target = "listeDocuments", ignore = true)
    @Mapping(target = "removeListeDocuments", ignore = true)
    @Mapping(source = "employeId", target = "employe")
    FichePaie toEntity(FichePaieDTO fichePaieDTO);

    default FichePaie fromId(Long id) {
        if (id == null) {
            return null;
        }
        FichePaie fichePaie = new FichePaie();
        fichePaie.setId(id);
        return fichePaie;
    }
}
