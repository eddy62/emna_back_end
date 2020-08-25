package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.SocieteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Societe} and its DTO {@link SocieteDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdresseMapper.class, InfoEntrepriseMapper.class, UserMapper.class, ComptableMapper.class})
public interface SocieteMapper extends EntityMapper<SocieteDTO, Societe> {

    @Mapping(source = "adresse.id", target = "adresseId")
    @Mapping(source = "infoEntreprise.id", target = "infoEntrepriseId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "comptable.id", target = "comptableId")
    SocieteDTO toDto(Societe societe);

    @Mapping(source = "adresseId", target = "adresse")
    @Mapping(source = "infoEntrepriseId", target = "infoEntreprise")
    @Mapping(source = "userId", target = "user")
    @Mapping(target = "listeFactures", ignore = true)
    @Mapping(target = "removeListeFactures", ignore = true)
    @Mapping(target = "listeDevis", ignore = true)
    @Mapping(target = "removeListeDevis", ignore = true)
    @Mapping(target = "listeReleves", ignore = true)
    @Mapping(target = "removeListeReleves", ignore = true)
    @Mapping(target = "listeProduits", ignore = true)
    @Mapping(target = "removeListeProduits", ignore = true)
    @Mapping(target = "listeContrats", ignore = true)
    @Mapping(target = "removeListeContrats", ignore = true)
    @Mapping(target = "listeClientsFournisseurs", ignore = true)
    @Mapping(target = "removeListeClientsFournisseurs", ignore = true)
    @Mapping(target = "listeClauses", ignore = true)
    @Mapping(target = "removeListeClauses", ignore = true)
    @Mapping(target = "listeArticles", ignore = true)
    @Mapping(target = "removeListeArticles", ignore = true)
    @Mapping(target = "listeEmployes", ignore = true)
    @Mapping(target = "removeListeEmployes", ignore = true)
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
