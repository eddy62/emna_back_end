package fr.insy2s.service.mapper;


import fr.insy2s.domain.Contrat;
import fr.insy2s.service.dto.ContratDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Contrat} and its DTO {@link ContratDTO}.
 */
@Mapper(componentModel = "spring", uses = {TypeContratMapper.class, EmployeMapper.class})
public interface ContratMapper extends EntityMapper<ContratDTO, Contrat> {

    @Mapping(source = "typeContrat.id", target = "typeContratId")
    @Mapping(source = "employe.id", target = "employeId")
    ContratDTO toDto(Contrat contrat);

    @Mapping(target = "listeAvenants", ignore = true)
    @Mapping(target = "removeListeAvenants", ignore = true)
    @Mapping(target = "listeDocuments", ignore = true)
    @Mapping(target = "removeListeDocuments", ignore = true)
    @Mapping(source = "typeContratId", target = "typeContrat")
    @Mapping(source = "employeId", target = "employe")
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
