package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.FactureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Facture} and its DTO {@link FactureDTO}.
 */
@Mapper(componentModel = "spring", uses = {AdresseMapper.class, EtatFactureMapper.class, SocieteMapper.class, OperationMapper.class, ClientFournisseurMapper.class})
public interface FactureMapper extends EntityMapper<FactureDTO, Facture> {

    @Mapping(source = "adresse.id", target = "adresseId")
    @Mapping(source = "etatFacture.id", target = "etatFactureId")
    @Mapping(source = "societe.id", target = "societeId")
    @Mapping(source = "operation.id", target = "operationId")
    @Mapping(source = "clientFournisseur.id", target = "clientFournisseurId")
    FactureDTO toDto(Facture facture);

    @Mapping(source = "adresseId", target = "adresse")
    @Mapping(source = "etatFactureId", target = "etatFacture")
    @Mapping(source = "societeId", target = "societe")
    @Mapping(source = "operationId", target = "operation")
    @Mapping(source = "clientFournisseurId", target = "clientFournisseur")
    @Mapping(target = "listeProduits", ignore = true)
    @Mapping(target = "removeListeProduits", ignore = true)
    Facture toEntity(FactureDTO factureDTO);

    default Facture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Facture facture = new Facture();
        facture.setId(id);
        return facture;
    }
}
