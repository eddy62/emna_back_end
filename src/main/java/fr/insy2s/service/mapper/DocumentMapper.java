package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.DocumentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Document} and its DTO {@link DocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {FactureMapper.class, ReleveMapper.class, ContratMapper.class, EmployeMapper.class})
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {

    @Mapping(source = "facture.id", target = "factureId")
    @Mapping(source = "releve.id", target = "releveId")
    @Mapping(source = "contrat.id", target = "contratId")
    @Mapping(source = "employe.id", target = "employeId")
    DocumentDTO toDto(Document document);

    @Mapping(source = "factureId", target = "facture")
    @Mapping(source = "releveId", target = "releve")
    @Mapping(source = "contratId", target = "contrat")
    @Mapping(source = "employeId", target = "employe")
    Document toEntity(DocumentDTO documentDTO);

    default Document fromId(Long id) {
        if (id == null) {
            return null;
        }
        Document document = new Document();
        document.setId(id);
        return document;
    }
}
