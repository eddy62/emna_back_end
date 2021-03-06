package fr.insy2s.service.mapper;


import fr.insy2s.domain.Facture;
import fr.insy2s.service.dto.FactureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Facture} and its DTO {@link FactureDTO}.
 */
@Mapper(componentModel = "spring", uses = {DevisMapper.class, EtatFactureMapper.class, AdresseMapper.class, SocieteMapper.class, OperationMapper.class, ClientFournisseurMapper.class})
public interface FactureMapper extends EntityMapper<FactureDTO, Facture> {

    @Mapping(source = "devis.id", target = "devisId")
    @Mapping(source = "etatFacture.id", target = "etatFactureId")
    @Mapping(source = "adresse.id", target = "adresseId")
    @Mapping(source = "societe.id", target = "societeId")
    @Mapping(source = "operation.id", target = "operationId")
    @Mapping(source = "clientFournisseur.id", target = "clientFournisseurId")
    FactureDTO toDto(Facture facture);

    @Mapping(source = "devisId", target = "devis")
    @Mapping(target = "listeDocuments", ignore = true)
    @Mapping(target = "removeListeDocuments", ignore = true)
    @Mapping(target = "listeLigneProduits", ignore = true)
    @Mapping(target = "removeListeLigneProduit", ignore = true)
    @Mapping(source = "etatFactureId", target = "etatFacture")
    @Mapping(source = "adresseId", target = "adresse")
    @Mapping(source = "societeId", target = "societe")
    @Mapping(source = "operationId", target = "operation")
    @Mapping(source = "clientFournisseurId", target = "clientFournisseur")
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
