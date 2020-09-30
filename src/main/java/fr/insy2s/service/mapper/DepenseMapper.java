package fr.insy2s.service.mapper;


import fr.insy2s.domain.Depense;
import fr.insy2s.service.dto.DepenseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Depense} and its DTO {@link DepenseDTO}.
 */
@Mapper(componentModel = "spring", uses = {SocieteMapper.class, OperationMapper.class, ClientFournisseurMapper.class, EtatDepenseMapper.class})
public interface DepenseMapper extends EntityMapper<DepenseDTO, Depense> {

    @Mapping(source = "societe.id", target = "societeId")
    @Mapping(source = "operation.id", target = "operationId")
    @Mapping(source = "clientFournisseur.id", target = "clientFournisseurId")
    @Mapping(source = "etatDepense.id", target = "etatDepenseId")
    DepenseDTO toDto(Depense depense);

    @Mapping(target = "listeDocuments", ignore = true)
    @Mapping(target = "removeListeDocuments", ignore = true)
    @Mapping(source = "societeId", target = "societe")
    @Mapping(source = "operationId", target = "operation")
    @Mapping(source = "clientFournisseurId", target = "clientFournisseur")
    @Mapping(source = "etatDepenseId", target = "etatDepense")
    Depense toEntity(DepenseDTO depenseDTO);

    default Depense fromId(Long id) {
        if (id == null) {
            return null;
        }
        Depense depense = new Depense();
        depense.setId(id);
        return depense;
    }
}
