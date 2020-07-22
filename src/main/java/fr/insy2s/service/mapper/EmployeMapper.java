package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.EmployeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employe} and its DTO {@link EmployeDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdresseMapper.class, SocieteMapper.class})
public interface EmployeMapper extends EntityMapper<EmployeDTO, Employe> {

    @Mapping(source = "adresse.id", target = "adresseId")
    @Mapping(source = "societe.id", target = "societeId")
    EmployeDTO toDto(Employe employe);

    @Mapping(source = "adresseId", target = "adresse")
    @Mapping(target = "listeContrats", ignore = true)
    @Mapping(target = "removeListeContrats", ignore = true)
    @Mapping(target = "listeAbsences", ignore = true)
    @Mapping(target = "removeListeAbsences", ignore = true)
    @Mapping(target = "listePrimes", ignore = true)
    @Mapping(target = "removeListePrimes", ignore = true)
    @Mapping(target = "listeFichePaies", ignore = true)
    @Mapping(target = "removeListeFichePaies", ignore = true)
    @Mapping(target = "listeHeureSupplementaires", ignore = true)
    @Mapping(target = "removeListeHeureSupplementaires", ignore = true)
    @Mapping(target = "listeNoteDeFrais", ignore = true)
    @Mapping(target = "removeListeNoteDeFrais", ignore = true)
    @Mapping(target = "listeAutresVariables", ignore = true)
    @Mapping(target = "removeListeAutresVariables", ignore = true)
    @Mapping(source = "societeId", target = "societe")
    Employe toEntity(EmployeDTO employeDTO);

    default Employe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employe employe = new Employe();
        employe.setId(id);
        return employe;
    }
}
