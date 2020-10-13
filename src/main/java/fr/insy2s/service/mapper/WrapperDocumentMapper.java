package fr.insy2s.service.mapper;

import fr.insy2s.domain.Document;
import fr.insy2s.service.dto.DocumentDTO;
import fr.insy2s.service.dto.TypeDocumentDTO;
import fr.insy2s.utils.wrapper.WrapperDocument;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper for the entity Document,  and the DTO WrapperDocument.
 *
 */
@Service
public class WrapperDocumentMapper {

    private final DocumentMapper documentMapper;
    private final TypeDocumentMapper typeDocumentMapper;

    public WrapperDocumentMapper(DocumentMapper documentMapper, TypeDocumentMapper typeDocumentMapper) {
        this.documentMapper = documentMapper;
        this.typeDocumentMapper = typeDocumentMapper;
    }

    /**
     * Builder WrapperDocument
     *
     * @param documentDTO the documentDTO for building a WrapperDocument
     * @param typeDocumentDTO the typeDocumentDTO for building a WrapperDocument
     *
     * @return wrapperDocument
     */
    public WrapperDocument builderWrapperDocument(final DocumentDTO documentDTO, final TypeDocumentDTO typeDocumentDTO){
        final WrapperDocument wrapperDocument = new WrapperDocument();
        // Document
        wrapperDocument.setId(documentDTO.getId());
        wrapperDocument.setCheminFichier(documentDTO.getCheminFichier());
        wrapperDocument.setNom(documentDTO.getNom());
        wrapperDocument.setTypeDocumentId(documentDTO.getTypeDocumentId());
        wrapperDocument.setFactureId(documentDTO.getFactureId());
        wrapperDocument.setReleveId(documentDTO.getReleveId());
        wrapperDocument.setContratId(documentDTO.getContratId());
        wrapperDocument.setEmployeId(documentDTO.getEmployeId());
        wrapperDocument.setDepenseId(documentDTO.getDepenseId());
        wrapperDocument.setAbsenceId(documentDTO.getAbsenceId());
        wrapperDocument.setNoteDeFraisId(documentDTO.getNoteDeFraisId());
        wrapperDocument.setAutresVariableId(documentDTO.getAutresVariableId());
        wrapperDocument.setDevisId(documentDTO.getDevisId());
        wrapperDocument.setDpaeId(documentDTO.getDpaeId());
        wrapperDocument.setFichePaieId(documentDTO.getFichePaieId());
        wrapperDocument.setAvenantId(documentDTO.getAvenantId());
        // TypeDocument
        wrapperDocument.setIdTypeDocument(typeDocumentDTO.getId());
        wrapperDocument.setCodeRef(typeDocumentDTO.getCodeRef());
        wrapperDocument.setIntitule(typeDocumentDTO.getIntitule());
        // return
        return wrapperDocument;
    }

    /**
     * Mappe WrapperDocument to DocumentDto
     *
     * @param wrapperDocument the wrapperDocument to map to documentDTO
     *
     * @return documentDTO a DTO of Document
     */
    public DocumentDTO toDocumentDTO(final WrapperDocument wrapperDocument){
        DocumentDTO documentDTO = new DocumentDTO();

        documentDTO.setId(wrapperDocument.getId());
        documentDTO.setCheminFichier(wrapperDocument.getCheminFichier());
        documentDTO.setNom(wrapperDocument.getNom());
        documentDTO.setTypeDocumentId(wrapperDocument.getTypeDocumentId());
        documentDTO.setFactureId(wrapperDocument.getFactureId());
        documentDTO.setReleveId(wrapperDocument.getReleveId());
        documentDTO.setContratId(wrapperDocument.getContratId());
        documentDTO.setEmployeId(wrapperDocument.getEmployeId());
        documentDTO.setDepenseId(wrapperDocument.getDepenseId());
        documentDTO.setAbsenceId(wrapperDocument.getAbsenceId());
        documentDTO.setNoteDeFraisId(wrapperDocument.getNoteDeFraisId());
        documentDTO.setAutresVariableId(wrapperDocument.getAutresVariableId());
        documentDTO.setDevisId(wrapperDocument.getDevisId());
        documentDTO.setDpaeId(wrapperDocument.getDpaeId());
        documentDTO.setFichePaieId(wrapperDocument.getFichePaieId());
        documentDTO.setAvenantId(wrapperDocument.getAvenantId());

        return documentDTO;
    }

    /**
     * Mappe WrapperDocument to TypeDocumentDto
     *
     * @param wrapperDocument the wrapperDocument to map to typeDocumentDTO
     *
     * @return typeDocumentDTO a DTO of TypeDocument
     */    public TypeDocumentDTO toTypeDocumentDTO(final WrapperDocument wrapperDocument){
        TypeDocumentDTO typeDocumentDTO = new TypeDocumentDTO();

        typeDocumentDTO.setId(wrapperDocument.getIdTypeDocument());
        typeDocumentDTO.setCodeRef(wrapperDocument.getCodeRef());
        typeDocumentDTO.setIntitule(wrapperDocument.getIntitule());

        return typeDocumentDTO;
    }


    /**
     * Mappe une liste de Document en liste de WrapperDocument
     *
     * @param documentList the documentList to map to WrapperDocumentList
     * @return wrapperDocumentList a list of wrapperDocument
     * @author Erik DUNAIS
     */
    public List<WrapperDocument> documentListToWrapperDocumentList(List<Document> documentList){
        List<WrapperDocument> wrapperDocumentList = new ArrayList<>();
            for (Document document : documentList){
                WrapperDocument wrapperDocument
                        = this.builderWrapperDocument(
                        documentMapper.toDto(document),
                        typeDocumentMapper.toDto(document.getTypeDocument()));
                wrapperDocumentList.add(wrapperDocument);
            }
            return wrapperDocumentList;

    }

}
