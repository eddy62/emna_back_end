package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.ClientFournisseurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClientFournisseur} and its DTO {@link ClientFournisseurDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdresseMapper.class, SocieteMapper.class})
public interface ClientFournisseurMapper extends EntityMapper<ClientFournisseurDTO, ClientFournisseur> {

    @Mapping(source = "adresse.id", target = "adresseId")
    @Mapping(source = "societe.id", target = "societeId")
    ClientFournisseurDTO toDto(ClientFournisseur clientFournisseur);

    @Mapping(target = "listeFactures", ignore = true)
    @Mapping(target = "removeListeFactures", ignore = true)
    @Mapping(target = "listeDevis", ignore = true)
    @Mapping(target = "removeListeDevis", ignore = true)
    @Mapping(source = "adresseId", target = "adresse")
    @Mapping(source = "societeId", target = "societe")
    ClientFournisseur toEntity(ClientFournisseurDTO clientFournisseurDTO);

    default ClientFournisseur fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClientFournisseur clientFournisseur = new ClientFournisseur();
        clientFournisseur.setId(id);
        return clientFournisseur;
    }
}
