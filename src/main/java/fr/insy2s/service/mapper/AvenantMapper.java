package fr.insy2s.service.mapper;


import fr.insy2s.domain.Avenant;
import fr.insy2s.service.dto.AvenantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Avenant} and its DTO {@link AvenantDTO}.
 */
@Mapper(componentModel = "spring", uses = {ContratMapper.class})
public interface AvenantMapper extends EntityMapper<AvenantDTO, Avenant> {

    @Mapping(source = "contrat.id", target = "contratId")
    AvenantDTO toDto(Avenant avenant);

    @Mapping(target = "listeDocuments", ignore = true)
    @Mapping(target = "removeListeDocuments", ignore = true)
    @Mapping(source = "contratId", target = "contrat")
    Avenant toEntity(AvenantDTO avenantDTO);

    default Avenant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Avenant avenant = new Avenant();
        avenant.setId(id);
        return avenant;
    }
}
