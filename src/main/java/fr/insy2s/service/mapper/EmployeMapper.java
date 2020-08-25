package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.EmployeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employe} and its DTO {@link EmployeDTO}.
 */
@Mapper(componentModel = "spring", uses = {StatutEmployeMapper.class, AdresseMapper.class, TypeContratMapper.class, SocieteMapper.class})
public interface EmployeMapper extends EntityMapper<EmployeDTO, Employe> {

    @Mapping(source = "statutEmploye.id", target = "statutEmployeId")
    @Mapping(source = "adresse.id", target = "adresseId")
    @Mapping(source = "typeContrat.id", target = "typeContratId")
    @Mapping(source = "societe.id", target = "societeId")
    EmployeDTO toDto(Employe employe);

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
    @Mapping(target = "listeAvanceRappelSalaires", ignore = true)
    @Mapping(target = "removeListeAvanceRappelSalaire", ignore = true)
    @Mapping(target = "listeAutresVariables", ignore = true)
    @Mapping(target = "removeListeAutresVariables", ignore = true)
    @Mapping(target = "listeDocuments", ignore = true)
    @Mapping(target = "removeListeDocuments", ignore = true)
    @Mapping(target = "listeDpaes", ignore = true)
    @Mapping(target = "removeListeDpae", ignore = true)
    @Mapping(source = "statutEmployeId", target = "statutEmploye")
    @Mapping(source = "adresseId", target = "adresse")
    @Mapping(source = "typeContratId", target = "typeContrat")
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
