package fr.insy2s.service.impl;

import fr.insy2s.domain.*;
import fr.insy2s.repository.*;
import fr.insy2s.service.VariableDePaieService;
import fr.insy2s.service.dto.AvanceRappelSalaireDTO;
import fr.insy2s.service.dto.HeuresSupplementairesDTO;
import fr.insy2s.service.mapper.*;
import fr.insy2s.utils.wrapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VariableDePaieServiceImpl implements VariableDePaieService {

    private final Logger log = LoggerFactory.getLogger(VariableDePaieServiceImpl.class);

    private final AbsenceRepository absenceRepository;
    private final AbsenceMapper absenceMapper;
    private final TypeAbsenceMapper typeAbsenceMapper;
    private final AutresVariableRepository autresVariableRepository;
    private final AutresVariableMapper autresVariableMapper;
    private final AvanceRappelSalaireRepository avanceRappelSalaireRepository;
    private final AvanceRappelSalaireMapper avanceRappelSalaireMapper;
    private final HeuresSupplementairesRepository heuresSupplementairesRepository;
    private final HeuresSupplementairesMapper heuresSupplementairesMapper;
    private final NoteDeFraisRepository noteDeFraisRepository;
    private final NoteDeFraisMapper noteDeFraisMapper;
    private final PrimeRepository primeRepository;
    private final PrimeMapper primeMapper;
    private final TypePrimeMapper typePrimeMapper;
    private final WrapperDocumentMapper wrapperDocumentMapper;


    public VariableDePaieServiceImpl(AbsenceRepository absenceRepository,
                                     AbsenceMapper absenceMapper,
                                     TypeAbsenceMapper typeAbsenceMapper,
                                     AutresVariableRepository autresVariableRepository,
                                     AutresVariableMapper autresVariableMapper,
                                     AvanceRappelSalaireRepository avanceRappelSalaireRepository,
                                     AvanceRappelSalaireMapper avanceRappelSalaireMapper,
                                     HeuresSupplementairesRepository heuresSupplementairesRepository,
                                     HeuresSupplementairesMapper heuresSupplementairesMapper,
                                     NoteDeFraisRepository noteDeFraisRepository,
                                     NoteDeFraisMapper noteDeFraisMapper,
                                     PrimeRepository primeRepository,
                                     PrimeMapper primeMapper,
                                     TypePrimeMapper typePrimeMapper,
                                     WrapperDocumentMapper wrapperDocumentMapper) {

        this.absenceRepository = absenceRepository;
        this.absenceMapper = absenceMapper;
        this.typeAbsenceMapper = typeAbsenceMapper;
        this.autresVariableRepository = autresVariableRepository;
        this.autresVariableMapper = autresVariableMapper;
        this.avanceRappelSalaireRepository = avanceRappelSalaireRepository;
        this.avanceRappelSalaireMapper = avanceRappelSalaireMapper;
        this.heuresSupplementairesRepository = heuresSupplementairesRepository;
        this.heuresSupplementairesMapper = heuresSupplementairesMapper;
        this.noteDeFraisRepository = noteDeFraisRepository;
        this.noteDeFraisMapper = noteDeFraisMapper;
        this.primeRepository = primeRepository;
        this.primeMapper = primeMapper;
        this.typePrimeMapper = typePrimeMapper;
        this.wrapperDocumentMapper = wrapperDocumentMapper;
    }

    /**
     * Get one wrapperVariablesPaie by one employe, by one year and by one month.
     *
     * @param idEmploye id of the Employe in all VariablesPaie
     * @param annee     year in all VariablesPaie
     * @param mois      month in all VariablesPaie
     * @return the WrapperVariablesPaie
     * @author Erik DUNAIS
     */
    @Override
    public WrapperVariablesPaie findOneWrapperVariablesPaieByIdEmployeAndAnneeAndMois(Long idEmploye, Integer annee, Integer mois) {
        log.debug("Request to get one WrapperVariablesPaie with Employe:{}, Annee:{}, Mois:{}", idEmploye, annee, mois);
        // Absences
        List<Absence> absenceList = absenceRepository.findAllAbsenceByIdEmployeAndAnneeAndMois(idEmploye, annee, mois);
        List<WrapperAbsence> wrapperAbsenceList = new ArrayList<>();
        absenceList.forEach(absence -> {
            List<Document> documentList = new ArrayList<>(absence.getListeDocuments());
            List<WrapperDocument> wrapperDocumentList = wrapperDocumentMapper.documentListToWrapperDocumentList(documentList);
            WrapperAbsence wrapperAbsence = new WrapperAbsence(absenceMapper.toDto(absence), typeAbsenceMapper.toDto(absence.getTypeAbsence()), wrapperDocumentList);
            wrapperAbsenceList.add(wrapperAbsence);
        });
        // Autres Variable
        List<AutresVariable> autresVariableList = autresVariableRepository.findAllAutresVariablesByIdEmployeAndAnneeAndMois(idEmploye, annee, mois);
        List<WrapperAutresVariable> wrapperAutresVariableList = new ArrayList<>();
        autresVariableList.forEach(autresVariable -> {
            List<Document> documentList = new ArrayList<>(autresVariable.getListeDocuments());
            List<WrapperDocument> wrapperDocumentList = wrapperDocumentMapper.documentListToWrapperDocumentList(documentList);
            WrapperAutresVariable wrapperAutresVariable = new WrapperAutresVariable(autresVariableMapper.toDto(autresVariable), wrapperDocumentList);
            wrapperAutresVariableList.add(wrapperAutresVariable);
        });
        // Avance/RappelSalaire
        List<AvanceRappelSalaireDTO> avanceRappelSalaireDTOList = avanceRappelSalaireRepository.findAllAvanceRappelSalaireByIdEmployeAndAnneeAndMois(idEmploye, annee, mois).stream()
                .map(avanceRappelSalaireMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        // Heures supplémentaires
        List<HeuresSupplementairesDTO> heuresSupplementairesDTOList = heuresSupplementairesRepository.findAllHeuresSupplementairesByIdEmployeAndAnneeAndMois(idEmploye, annee, mois).stream()
                .map(heuresSupplementairesMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        // Notes de Frais
        List<NoteDeFrais> noteDeFraisList = noteDeFraisRepository.findAllNoteDeFraisByIdEmployeAndAnneeAndMois(idEmploye, annee, mois);
        List<WrapperNoteDeFrais> wrapperNoteDeFraisList = new ArrayList<>();
        noteDeFraisList.forEach(noteDeFrais -> {
            List<Document> documentList = new ArrayList<>(noteDeFrais.getListeDocuments());
            List<WrapperDocument> wrapperDocumentList = wrapperDocumentMapper.documentListToWrapperDocumentList(documentList);
            WrapperNoteDeFrais wrapperNoteDeFrais = new WrapperNoteDeFrais(noteDeFraisMapper.toDto(noteDeFrais), wrapperDocumentList);
            wrapperNoteDeFraisList.add(wrapperNoteDeFrais);
        });
        // Primes
        List<Prime> primeList = primeRepository.findAllPrimeByIdEmployeAndAnneeAndMois(idEmploye, annee, mois);
        List<WrapperPrime> wrapperPrimeList = new ArrayList<>();
        for (Prime prime : primeList) {
            WrapperPrime wrapperPrime = new WrapperPrime(primeMapper.toDto(prime), typePrimeMapper.toDto(prime.getTypePrime()));
            wrapperPrimeList.add(wrapperPrime);
        }

        return new WrapperVariablesPaie(wrapperAbsenceList, wrapperAutresVariableList, avanceRappelSalaireDTOList, heuresSupplementairesDTOList, wrapperNoteDeFraisList,
                wrapperPrimeList);
    }

}
