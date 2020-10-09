package fr.insy2s.service.impl;

import fr.insy2s.domain.Absence;
import fr.insy2s.domain.Document;
import fr.insy2s.repository.AbsenceRepository;
import fr.insy2s.service.AbsenceService;
import fr.insy2s.service.dto.AbsenceDTO;
import fr.insy2s.service.mapper.*;
import fr.insy2s.utils.wrapper.WrapperAbsence;
import fr.insy2s.utils.wrapper.WrapperDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Absence}.
 */
@Service
@Transactional
public class AbsenceServiceImpl implements AbsenceService {

    private final Logger log = LoggerFactory.getLogger(AbsenceServiceImpl.class);

    private final AbsenceRepository absenceRepository;

    private final AbsenceMapper absenceMapper;

    private final TypeAbsenceMapper typeAbsenceMapper;

    private final DocumentMapper documentMapper;

    private final TypeDocumentMapper typeDocumentMapper;

    private final WrapperDocumentMapper wrapperDocumentMapper;

    public AbsenceServiceImpl(AbsenceRepository absenceRepository, AbsenceMapper absenceMapper, TypeAbsenceMapper typeAbsenceMapper, DocumentMapper documentMapper, TypeDocumentMapper typeDocumentMapper, WrapperDocumentMapper wrapperDocumentMapper) {
        this.absenceRepository = absenceRepository;
        this.absenceMapper = absenceMapper;
        this.typeAbsenceMapper = typeAbsenceMapper;
        this.documentMapper = documentMapper;
        this.typeDocumentMapper = typeDocumentMapper;
        this.wrapperDocumentMapper = wrapperDocumentMapper;
    }

    @Override
    public AbsenceDTO save(AbsenceDTO absenceDTO) {
        log.debug("Request to save Absence : {}", absenceDTO);
        Absence absence = absenceMapper.toEntity(absenceDTO);
        absence = absenceRepository.save(absence);
        return absenceMapper.toDto(absence);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AbsenceDTO> findAll() {
        log.debug("Request to get all Absences");
        return absenceRepository.findAll().stream()
                .map(absenceMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AbsenceDTO> findOne(Long id) {
        log.debug("Request to get Absence : {}", id);
        return absenceRepository.findById(id)
                .map(absenceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Absence : {}", id);
        absenceRepository.deleteById(id);
    }

    @Override
    public List<WrapperAbsence> findAllWrapperAbsenceByIdEmployeAndAnneeAndMois(Long idEmploye, Integer annee, Integer mois) {
        log.debug("Request to get all WrapperAbsence with IdEmploye:{}, Annee:{}, Mois:{}", idEmploye, annee, mois);
        List<Absence> absenceList = absenceRepository.findAllAbsenceByIdEmployeAndAnneeAndMois(idEmploye, annee, mois);
        List<WrapperAbsence> wrapperAbsenceList = new ArrayList<>();

        for (Absence absence : absenceList){
            List<WrapperDocument> wrapperDocumentList = new ArrayList<>();
            for (Document document : absence.getListeDocuments()){
                WrapperDocument wrapperDocument
                        = wrapperDocumentMapper.builderWrapperDocument(
                                documentMapper.toDto(document),
                                typeDocumentMapper.toDto(document.getTypeDocument()));
                wrapperDocumentList.add(wrapperDocument);
            };
            WrapperAbsence wrapperAbsence = new WrapperAbsence(absenceMapper.toDto(absence), typeAbsenceMapper.toDto(absence.getTypeAbsence()), wrapperDocumentList);
            wrapperAbsenceList.add(wrapperAbsence);
        }
        return wrapperAbsenceList;
    }

    @Override
    public Optional<AbsenceDTO> findAllOverlappingAbsencesByIdEmploye(Long idEmploye, LocalDate debutAbsence, LocalDate finAbsence) {
        List<Absence> absenceList = absenceRepository.findAllOverlappingAbsencesByIdEmploye(idEmploye, debutAbsence, finAbsence);
        Optional<AbsenceDTO> absenceDTO = Optional.empty();
        if(!absenceList.isEmpty()) {
            absenceDTO = Optional.ofNullable(absenceMapper.toDto(absenceList.get(0)));
            return absenceDTO;
        }
        else {
            return absenceDTO;
        }
    }
}
