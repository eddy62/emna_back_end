package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.DevisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Devis} and its DTO {@link DevisDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtatDevisMapper.class, SocieteMapper.class, ClientFournisseurMapper.class})
public interface DevisMapper extends EntityMapper<DevisDTO, Devis> {

    @Mapping(source = "etatDevis.id", target = "etatDevisId")
    @Mapping(source = "societe.id", target = "societeId")
    @Mapping(source = "clientFournisseur.id", target = "clientFournisseurId")
    DevisDTO toDto(Devis devis);

    @Mapping(source = "etatDevisId", target = "etatDevis")
    @Mapping(source = "societeId", target = "societe")
    @Mapping(source = "clientFournisseurId", target = "clientFournisseur")
    @Mapping(target = "listeProduits", ignore = true)
    @Mapping(target = "removeListeProduits", ignore = true)
    Devis toEntity(DevisDTO devisDTO);

    default Devis fromId(Long id) {
        if (id == null) {
            return null;
        }
        Devis devis = new Devis();
        devis.setId(id);
        return devis;
    }
}
