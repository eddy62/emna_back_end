package fr.insy2s.service.mapper;


import fr.insy2s.domain.Devis;
import fr.insy2s.service.dto.DevisDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Devis} and its DTO {@link DevisDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtatDevisMapper.class, ClientFournisseurMapper.class})
public interface DevisMapper extends EntityMapper<DevisDTO, Devis> {

    @Mapping(source = "etatDevis.id", target = "etatDevisId")
    @Mapping(source = "clientFournisseur.id", target = "clientFournisseurId")
    DevisDTO toDto(Devis devis);

    @Mapping(target = "listeLigneProduits", ignore = true)
    @Mapping(target = "removeListeLigneProduit", ignore = true)
    @Mapping(target = "listeDocuments", ignore = true)
    @Mapping(target = "removeListeDocuments", ignore = true)
    @Mapping(source = "etatDevisId", target = "etatDevis")
    @Mapping(source = "clientFournisseurId", target = "clientFournisseur")
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
