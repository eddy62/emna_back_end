package fr.insy2s.service.mapper;


import fr.insy2s.domain.TypeDocument;
import fr.insy2s.service.dto.TypeDocumentDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link TypeDocument} and its DTO {@link TypeDocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeDocumentMapper extends EntityMapper<TypeDocumentDTO, TypeDocument> {



    default TypeDocument fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeDocument typeDocument = new TypeDocument();
        typeDocument.setId(id);
        return typeDocument;
    }
}
