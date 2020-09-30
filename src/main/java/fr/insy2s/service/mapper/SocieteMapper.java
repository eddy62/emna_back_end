package fr.insy2s.service.mapper;


import fr.insy2s.domain.Societe;
import fr.insy2s.service.dto.SocieteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Societe} and its DTO {@link SocieteDTO}.
 */
@Mapper(componentModel = "spring", uses = {InfoEntrepriseMapper.class, UserMapper.class, AdresseMapper.class, ComptableMapper.class})
public interface SocieteMapper extends EntityMapper<SocieteDTO, Societe> {

    @Mapping(source = "infoEntreprise.id", target = "infoEntrepriseId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "adresse.id", target = "adresseId")
    @Mapping(source = "comptable.id", target = "comptableId")
    SocieteDTO toDto(Societe societe);

    @Mapping(source = "infoEntrepriseId", target = "infoEntreprise")
    @Mapping(source = "userId", target = "user")
    @Mapping(target = "listeFactures", ignore = true)
    @Mapping(target = "removeListeFactures", ignore = true)
    @Mapping(target = "listeReleves", ignore = true)
    @Mapping(target = "removeListeReleves", ignore = true)
    @Mapping(target = "listeClientsFournisseurs", ignore = true)
    @Mapping(target = "removeListeClientsFournisseurs", ignore = true)
    @Mapping(target = "listeEmployes", ignore = true)
    @Mapping(target = "removeListeEmployes", ignore = true)
    @Mapping(source = "adresseId", target = "adresse")
    @Mapping(source = "comptableId", target = "comptable")
    Societe toEntity(SocieteDTO societeDTO);

    default Societe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Societe societe = new Societe();
        societe.setId(id);
        return societe;
    }
}
