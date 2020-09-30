package fr.insy2s.service.mapper;


import fr.insy2s.domain.LigneProduit;
import fr.insy2s.service.dto.LigneProduitDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link LigneProduit} and its DTO {@link LigneProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class, FactureMapper.class, DevisMapper.class})
public interface LigneProduitMapper extends EntityMapper<LigneProduitDTO, LigneProduit> {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "facture.id", target = "factureId")
    @Mapping(source = "devis.id", target = "devisId")
    LigneProduitDTO toDto(LigneProduit ligneProduit);

    @Mapping(source = "produitId", target = "produit")
    @Mapping(source = "factureId", target = "facture")
    @Mapping(source = "devisId", target = "devis")
    LigneProduit toEntity(LigneProduitDTO ligneProduitDTO);

    default LigneProduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        LigneProduit ligneProduit = new LigneProduit();
        ligneProduit.setId(id);
        return ligneProduit;
    }
}
