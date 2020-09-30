package fr.insy2s.service.mapper;


import fr.insy2s.domain.Document;
import fr.insy2s.service.dto.DocumentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Document} and its DTO {@link DocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {TypeDocumentMapper.class, FactureMapper.class, ReleveMapper.class, ContratMapper.class, EmployeMapper.class, DepenseMapper.class, AbsenceMapper.class, NoteDeFraisMapper.class, AutresVariableMapper.class, DevisMapper.class, DpaeMapper.class, FichePaieMapper.class, AvenantMapper.class})
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {

    @Mapping(source = "typeDocument.id", target = "typeDocumentId")
    @Mapping(source = "facture.id", target = "factureId")
    @Mapping(source = "releve.id", target = "releveId")
    @Mapping(source = "contrat.id", target = "contratId")
    @Mapping(source = "employe.id", target = "employeId")
    @Mapping(source = "depense.id", target = "depenseId")
    @Mapping(source = "absence.id", target = "absenceId")
    @Mapping(source = "noteDeFrais.id", target = "noteDeFraisId")
    @Mapping(source = "autresVariable.id", target = "autresVariableId")
    @Mapping(source = "devis.id", target = "devisId")
    @Mapping(source = "dpae.id", target = "dpaeId")
    @Mapping(source = "fichePaie.id", target = "fichePaieId")
    @Mapping(source = "avenant.id", target = "avenantId")
    DocumentDTO toDto(Document document);

    @Mapping(source = "typeDocumentId", target = "typeDocument")
    @Mapping(source = "factureId", target = "facture")
    @Mapping(source = "releveId", target = "releve")
    @Mapping(source = "contratId", target = "contrat")
    @Mapping(source = "employeId", target = "employe")
    @Mapping(source = "depenseId", target = "depense")
    @Mapping(source = "absenceId", target = "absence")
    @Mapping(source = "noteDeFraisId", target = "noteDeFrais")
    @Mapping(source = "autresVariableId", target = "autresVariable")
    @Mapping(source = "devisId", target = "devis")
    @Mapping(source = "dpaeId", target = "dpae")
    @Mapping(source = "fichePaieId", target = "fichePaie")
    @Mapping(source = "avenantId", target = "avenant")
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
