package fr.insy2s.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.insy2s.domain.Absence;
import fr.insy2s.domain.AutresVariable;
import fr.insy2s.domain.Contrat;
import fr.insy2s.domain.Document;
import fr.insy2s.domain.Dpae;
import fr.insy2s.domain.Employe;
import fr.insy2s.domain.FichePaie;
import fr.insy2s.domain.HeuresSupplementaires;
import fr.insy2s.domain.NoteDeFrais;
import fr.insy2s.domain.Prime;
import fr.insy2s.repository.AbsenceRepository;
import fr.insy2s.repository.AutresVariableRepository;
import fr.insy2s.repository.AvanceRappelSalaireRepository;
import fr.insy2s.repository.EmployeRepository;
import fr.insy2s.repository.HeuresSupplementairesRepository;
import fr.insy2s.repository.NoteDeFraisRepository;
import fr.insy2s.repository.PrimeRepository;
import fr.insy2s.repository.projection.IEmployeContratProjection;
import fr.insy2s.service.AbsenceService;
import fr.insy2s.service.AdresseService;
import fr.insy2s.service.AutresVariableService;
import fr.insy2s.service.ContratService;
import fr.insy2s.service.DocumentService;
import fr.insy2s.service.DpaeService;
import fr.insy2s.service.EmployeService;
import fr.insy2s.service.FichePaieService;
import fr.insy2s.service.HeuresSupplementairesService;
import fr.insy2s.service.InfoEntrepriseService;
import fr.insy2s.service.NoteDeFraisService;
import fr.insy2s.service.PrimeService;
import fr.insy2s.service.SocieteService;
import fr.insy2s.service.StatutEmployeService;
import fr.insy2s.service.TypeContratService;
import fr.insy2s.service.dto.AdresseDTO;
import fr.insy2s.service.dto.AutresVariableDTO;
import fr.insy2s.service.dto.AvanceRappelSalaireDTO;
import fr.insy2s.service.dto.EmployeDTO;
import fr.insy2s.service.dto.HeuresSupplementairesDTO;
import fr.insy2s.service.dto.InfoEntrepriseDTO;
import fr.insy2s.service.dto.NoteDeFraisDTO;
import fr.insy2s.service.dto.SocieteDTO;
import fr.insy2s.service.dto.StatutEmployeDTO;
import fr.insy2s.service.dto.TypeContratDTO;
import fr.insy2s.service.mapper.AbsenceMapper;
import fr.insy2s.service.mapper.AutresVariableMapper;
import fr.insy2s.service.mapper.AvanceRappelSalaireMapper;
import fr.insy2s.service.mapper.EmployeMapper;
import fr.insy2s.service.mapper.HeuresSupplementairesMapper;
import fr.insy2s.service.mapper.NoteDeFraisMapper;
import fr.insy2s.service.mapper.PrimeMapper;
import fr.insy2s.service.mapper.TypeAbsenceMapper;
import fr.insy2s.service.mapper.TypePrimeMapper;
import fr.insy2s.service.mapper.WrapperEmployeMapper;
import fr.insy2s.utils.wrapper.WrapperAbsence;
import fr.insy2s.utils.wrapper.WrapperEmploye;
import fr.insy2s.utils.wrapper.WrapperPrime;
import fr.insy2s.utils.wrapper.WrapperVariablesPaie;

/**
 * Service Implementation for managing {@link Employe}.
 */
@Service
@Transactional
public class EmployeServiceImpl implements EmployeService {

    private final Logger                          log = LoggerFactory.getLogger(EmployeServiceImpl.class);

    private final EmployeRepository               employeRepository;

    private final EmployeMapper                   employeMapper;

    private final WrapperEmployeMapper            wrapperEmployeMapper;
    private final AdresseService                  adresseService;
    private final StatutEmployeService            statutEmployeService;
    private final SocieteService                  societeService;
    private final InfoEntrepriseService           infoEntrepriseService;
    private final TypeContratService              typeContratService;
    private final AbsenceService                  absenceService;
    private final PrimeService                    primeService;
    private final FichePaieService                fichePaieService;
    private final HeuresSupplementairesService    heuresSupplementairesService;
    private final NoteDeFraisService              noteDeFraisService;
    private final AutresVariableService           autresVariableService;
    private final DocumentService                 documentService;
    private final DpaeService                     dpaeService;
    private final ContratService                  contratService;

    /* Variables de Paie */
    private final AbsenceMapper                   absenceMapper;
    private final TypeAbsenceMapper               typeAbsenceMapper;
    private final AbsenceRepository               absenceRepository;
    private final NoteDeFraisMapper               noteDeFraisMapper;
    private final NoteDeFraisRepository           noteDeFraisRepository;
    private final AutresVariableMapper            autresVariableMapper;
    private final AutresVariableRepository        autresVariableRepository;
    private final AvanceRappelSalaireMapper       avanceRappelSalaireMapper;
    private final AvanceRappelSalaireRepository   avanceRappelSalaireRepository;
    private final HeuresSupplementairesMapper     heuresSupplementairesMapper;
    private final HeuresSupplementairesRepository heuresSupplementairesRepository;
    private final PrimeMapper                     primeMapper;
    private final TypePrimeMapper                 typePrimeMapper;
    private final PrimeRepository                 primeRepository;

    public EmployeServiceImpl(EmployeRepository employeRepository, EmployeMapper employeMapper, WrapperEmployeMapper wrapperEmployeMapper, AdresseService adresseService,
                    StatutEmployeService statutEmployeService, SocieteService societeService, InfoEntrepriseService infoEntrepriseService, TypeContratService typeContratService,
                    AbsenceMapper absenceMapper, TypeAbsenceMapper typeAbsenceMapper,  AbsenceRepository absenceRepository,
                    NoteDeFraisMapper noteDeFraisMapper, NoteDeFraisRepository noteDeFraisRepository, AutresVariableMapper autresVariableMapper, AutresVariableRepository autresVariableRepository,
                    AvanceRappelSalaireMapper avanceRappelSalaireMapper, AvanceRappelSalaireRepository avanceRappelSalaireRepository, HeuresSupplementairesMapper heuresSupplementairesMapper,
                    HeuresSupplementairesRepository heuresSupplementairesRepository, PrimeMapper primeMapper, TypePrimeMapper typePrimeMapper,
                    PrimeRepository primeRepository, AbsenceService absenceService, PrimeService primeService, FichePaieService fichePaieService,
                    HeuresSupplementairesService heuresSupplementairesService, NoteDeFraisService noteDeFraisService, AutresVariableService autresVariableService, DocumentService documentService,
                    DpaeService dpaeService, ContratService contratService) {
        this.employeRepository = employeRepository;
        this.employeMapper = employeMapper;
        this.wrapperEmployeMapper = wrapperEmployeMapper;
        this.adresseService = adresseService;
        this.statutEmployeService = statutEmployeService;
        this.societeService = societeService;
        this.infoEntrepriseService = infoEntrepriseService;
        this.typeContratService = typeContratService;

        // Variables de Paie
        this.absenceMapper = absenceMapper;
        this.typeAbsenceMapper = typeAbsenceMapper;
        this.absenceRepository = absenceRepository;
        this.noteDeFraisMapper = noteDeFraisMapper;
        this.noteDeFraisRepository = noteDeFraisRepository;
        this.autresVariableMapper = autresVariableMapper;
        this.autresVariableRepository = autresVariableRepository;
        this.avanceRappelSalaireMapper = avanceRappelSalaireMapper;
        this.avanceRappelSalaireRepository = avanceRappelSalaireRepository;
        this.heuresSupplementairesMapper = heuresSupplementairesMapper;
        this.heuresSupplementairesRepository = heuresSupplementairesRepository;
        this.primeMapper = primeMapper;
        this.typePrimeMapper = typePrimeMapper;
        this.primeRepository = primeRepository;
        this.absenceService = absenceService;
        this.primeService = primeService;
        this.fichePaieService = fichePaieService;
        this.heuresSupplementairesService = heuresSupplementairesService;
        this.noteDeFraisService = noteDeFraisService;
        this.autresVariableService = autresVariableService;
        this.documentService = documentService;
        this.dpaeService = dpaeService;
        this.contratService = contratService;

    }

    @Override
    public EmployeDTO save(EmployeDTO employeDTO) {
        log.debug("Request to save Employe : {}", employeDTO);
        Employe employe = employeMapper.toEntity(employeDTO);
        employe = employeRepository.save(employe);
        return employeMapper.toDto(employe);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeDTO> findAll() {
        log.debug("Request to get all Employes");
        return employeRepository.findAll().stream().map(employeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeDTO> findOne(Long id) {
        log.debug("Request to get Employe : {}", id);
        return employeRepository.findById(id).map(employeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Employe : {}", id);
        employeRepository.deleteById(id);
    }

    @Override
    public List<WrapperEmploye> findAllWrapperEmploye() {
        final List<WrapperEmploye> listWrapperEmployes = new ArrayList<>();
        final List<EmployeDTO> listEmployeDTOs = findAll();
        for (EmployeDTO employeDTO : listEmployeDTOs) {
            WrapperEmploye wrapperEmploye = findById(employeDTO.getId()).get();
            if (wrapperEmploye != null) {
                listWrapperEmployes.add(wrapperEmploye);
            }
        }

        return listWrapperEmployes;
    }

    @Override
    public List<WrapperEmploye> findAllWrapperEmployeBySociete(final Long societeId) {
        final List<WrapperEmploye> listWrapperEmployes = new ArrayList<>();
        final List<EmployeDTO> listEmployeDTOs = employeMapper.toDto(employeRepository.findBySocieteId(societeId));
        for (EmployeDTO employeDTO : listEmployeDTOs) {
            WrapperEmploye wrapperEmploye = findById(employeDTO.getId()).get();
            if (wrapperEmploye != null) {
                listWrapperEmployes.add(wrapperEmploye);
            }
        }

        return listWrapperEmployes;
    }

    @Override
    public Optional<WrapperEmploye> findById(final Long id) {
        log.debug("Request to get WrapperEmploye : {}", id);
        final EmployeDTO employeDTO = findOne(id).get();
        final AdresseDTO adresseDTO = adresseService.findOne(employeDTO.getAdresseId()).get();
        final StatutEmployeDTO statutEmployeDTO = statutEmployeService.findOne(employeDTO.getStatutEmployeId()).get();
        final SocieteDTO societeDTO = societeService.findOne(employeDTO.getSocieteId()).get();
        final InfoEntrepriseDTO infoEntrepriseDTO = infoEntrepriseService.findOne(societeDTO.getInfoEntrepriseId()).get();
        final TypeContratDTO typeContratDTO = typeContratService.findOne(employeDTO.getTypeContratId()).get();
        final Optional<WrapperEmploye> wrapperEmploye = Optional
                        .of(wrapperEmployeMapper.builderWrapperEmploye(employeDTO, adresseDTO, statutEmployeDTO, societeDTO, infoEntrepriseDTO, typeContratDTO));
        return wrapperEmploye.isPresent() ? Optional.of(wrapperEmploye.get()) : Optional.empty();
    }

    @Override
    public Optional<WrapperEmploye> createWrapperEmploye(@Valid WrapperEmploye wrapperEmploye) {
        final SocieteDTO societeDTO = societeService.findOne(wrapperEmploye.getSocieteId()).get();
        final InfoEntrepriseDTO infoEntrepriseDTO = infoEntrepriseService.findOne(societeDTO.getInfoEntrepriseId()).get();
        final AdresseDTO newAdresseDTO = adresseService.save(wrapperEmployeMapper.toAdresseDto(wrapperEmploye));
        final StatutEmployeDTO statutEmployeDTO = statutEmployeService.findByCodeRef(wrapperEmploye.getCodeRef());
        final TypeContratDTO typeContratDTO = typeContratService.findByCodeRef(wrapperEmploye.getCodeTypeContrat());
        final EmployeDTO employeDTO = wrapperEmployeMapper.toEmployeDto(wrapperEmploye);
        employeDTO.setAdresseId(newAdresseDTO.getId());
        employeDTO.setStatutEmployeId(statutEmployeDTO.getId());
        employeDTO.setTypeContratId(typeContratDTO.getId());
        if (employeDTO.getDateSortie() == null) {
            employeDTO.setDateSortie(LocalDate.of(2100, 12, 31));
        }
        if (isEmployeExist(employeDTO.getMatricule())) {
            return Optional.empty();
        }
        final EmployeDTO newEmployeDTO = employeMapper.toDto(employeRepository.save(employeMapper.toEntity(employeDTO)));
        final WrapperEmploye newWrapperEmploye = wrapperEmployeMapper.builderWrapperEmploye(newEmployeDTO, newAdresseDTO, statutEmployeDTO, societeDTO, infoEntrepriseDTO, typeContratDTO);
        return Optional.of(newWrapperEmploye);
    }

    @Override
    public WrapperEmploye updateWrapperEmploye(@Valid WrapperEmploye wrapperEmploye) {
        final SocieteDTO societeDTO = societeService.findOne(wrapperEmploye.getSocieteId()).get();
        final InfoEntrepriseDTO infoEntrepriseDTO = infoEntrepriseService.findOne(societeDTO.getInfoEntrepriseId()).get();
        final AdresseDTO newAdresseDTO = adresseService.save(wrapperEmployeMapper.toAdresseDto(wrapperEmploye));
        final StatutEmployeDTO statutEmployeDTO = statutEmployeService.findByCodeRef(wrapperEmploye.getCodeRef());
        final TypeContratDTO typeContratDTO = typeContratService.findOne(wrapperEmploye.getTypeContratId()).get();
        final EmployeDTO employeDTO = wrapperEmployeMapper.toEmployeDto(wrapperEmploye);
        employeDTO.setAdresseId(newAdresseDTO.getId());
        employeDTO.setStatutEmployeId(statutEmployeDTO.getId());
        employeDTO.setTypeContratId(typeContratDTO.getId());
        final EmployeDTO newEmployeDTO = employeMapper.toDto(employeRepository.save(employeMapper.toEntity(employeDTO)));
        final WrapperEmploye newWrapperEmploye = wrapperEmployeMapper.builderWrapperEmploye(newEmployeDTO, newAdresseDTO, statutEmployeDTO, societeDTO, infoEntrepriseDTO, typeContratDTO);
        return newWrapperEmploye;
    }

    @Override
    public boolean deleteWrapperEmploye(Long id) {
        log.debug("Request to delete WrapperEmploye : {}", id);
        final Employe employe = employeRepository.findById(id).get();
        final Period period = Period.between(employe.getDateSortie(), LocalDate.now());
        final int diff = period.getYears();
        /** RGPD : Conservation des données pendant 5 ans maximum */
        if (diff >= 5 && employe.getStatutEmploye().getId() == 3) {
            //listeContrats
            if (!employe.getListeContrats().isEmpty()) {
                for (final Contrat contrat : employe.getListeContrats()) {
                    contratService.delete(contrat.getId());
                }
            }
            //listeAbsence
            if (!employe.getListeAbsences().isEmpty()) {
                for (final Absence absence : employe.getListeAbsences()) {
                    absenceService.delete(absence.getId());
                }
            }
            //listePrimes
            if (!employe.getListePrimes().isEmpty()) {
                for (final Prime prime : employe.getListePrimes()) {
                    primeService.delete(prime.getId());
                }
            }
            //listeFichePaie
            if (!employe.getListeFichePaies().isEmpty()) {
                for (final FichePaie fichePaie : employe.getListeFichePaies()) {
                    fichePaieService.delete(fichePaie.getId());
                }
            }
            //listeHeuresSupp
            if (!employe.getListeHeureSupplementaires().isEmpty()) {
                for (final HeuresSupplementaires heuresSupplementaires : employe.getListeHeureSupplementaires()) {
                    heuresSupplementairesService.delete(heuresSupplementaires.getId());
                }
            }
            //listeNoteDeFrais
            if (!employe.getListeNoteDeFrais().isEmpty()) {
                for (NoteDeFrais noteDeFrais : employe.getListeNoteDeFrais()) {
                    noteDeFraisService.delete(noteDeFrais.getId());
                }
            }
            //listeAutresVariables
            if (!employe.getListeAutresVariables().isEmpty()) {
                for (AutresVariable autresVariable : employe.getListeAutresVariables()) {
                    autresVariableService.delete(autresVariable.getId());
                }
            }
            //listeDocuments
            if (!employe.getListeDocuments().isEmpty()) {
                for (Document document : employe.getListeDocuments()) {
                    documentService.delete(document.getId());
                }
            }
            //listeDpaes
            if (!employe.getListeDpaes().isEmpty()) {
                for (Dpae dpae : employe.getListeDpaes()) {
                    dpaeService.delete(dpae.getId());
                }
            }

            employeRepository.deleteById(id);
            return true;

        }
        return false;
    }

    @Override
    public List<IEmployeContratProjection> getAllEmployeArticleClauseBySocieteId(Long id) {
        return this.employeRepository.getAllEmployeArticleClauseBySocieteId(id);
    }

    @Override
    public WrapperEmploye archiveWrapperEmploye(@Valid WrapperEmploye wrapperEmploye) {
        wrapperEmploye.setCodeRef("EMPEND");
        wrapperEmploye.setDateSortie(LocalDate.now());
        final WrapperEmploye archivedWrapperemploye = updateWrapperEmploye(wrapperEmploye);
        return archivedWrapperemploye;
    }

    private boolean isEmployeExist(final String matricule) {
        final Employe employe = employeRepository.findByMatricule(matricule);
        if (employe != null) {
            return true;
        }
        return false;
    }

    @Override
    public WrapperVariablesPaie findOneWrapperVariablesPaieByIdEmployeAndAnneeAndMois(Long idEmploye, Integer annee, Integer mois) {
        log.debug("Request to get all NoteDeFrais by employe {}, annee {}, mois {}", idEmploye, annee, mois);
        // Absences
        List<Absence> absenceList = absenceRepository.findAllAbsenceByIdEmployeAndAnneeAndMois(idEmploye, annee, mois);
        List<WrapperAbsence> wrapperAbsenceList = new ArrayList<>();
        for (Absence absence : absenceList) {
            WrapperAbsence wrapperAbsence = new WrapperAbsence(absenceMapper.toDto(absence), typeAbsenceMapper.toDto(absence.getTypeAbsence()));
            wrapperAbsenceList.add(wrapperAbsence);
        }
        // Autres Variables
        List<AutresVariableDTO> autresVariableDTOList = autresVariableRepository.findAllAutresVariableByIdEmployeAndAnneeAndMois(idEmploye, annee, mois).stream().map(autresVariableMapper::toDto)
                        .collect(Collectors.toCollection(LinkedList::new));
        // Avance/RappelSalaire
        List<AvanceRappelSalaireDTO> avanceRappelSalaireDTOList = avanceRappelSalaireRepository.findAllAvanceRappelSalaireByIdEmployeAndAnneeAndMois(idEmploye, annee, mois).stream()
                        .map(avanceRappelSalaireMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        // Heures supplémentaires
        List<HeuresSupplementairesDTO> heuresSupplementairesDTOList = heuresSupplementairesRepository.findAllHeuresSupplementairesByIdEmployeAndAnneeAndMois(idEmploye, annee, mois).stream()
                        .map(heuresSupplementairesMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        // Notes de Frais
        List<NoteDeFraisDTO> noteDeFraisDTOList = noteDeFraisRepository.findAllNoteDeFraisByIdEmployeAndAnneeAndMois(idEmploye, annee, mois).stream().map(noteDeFraisMapper::toDto)
                        .collect(Collectors.toCollection(LinkedList::new));
        // Primes
        List<Prime> primeList = primeRepository.findAllPrimeByIdEmployeAndAnneeAndMois(idEmploye, annee, mois);
        List<WrapperPrime> wrapperPrimeList = new ArrayList<>();
        for (Prime prime : primeList) {
            WrapperPrime wrapperPrime = new WrapperPrime(primeMapper.toDto(prime), typePrimeMapper.toDto(prime.getTypePrime()));
            wrapperPrimeList.add(wrapperPrime);
        }

        WrapperVariablesPaie wrapperVariablesPaie = new WrapperVariablesPaie(wrapperAbsenceList, autresVariableDTOList, avanceRappelSalaireDTOList, heuresSupplementairesDTOList, noteDeFraisDTOList,
                        wrapperPrimeList);
        return wrapperVariablesPaie;
    }

}
