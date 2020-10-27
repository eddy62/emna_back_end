package fr.insy2s.service.impl;

import fr.insy2s.domain.Dpae;
import fr.insy2s.domain.Employe;
import fr.insy2s.domain.SaisieArticle;
import fr.insy2s.repository.DpaeRepository;
import fr.insy2s.service.*;
import fr.insy2s.service.dto.*;
import fr.insy2s.service.mapper.*;
import fr.insy2s.utils.wrapper.WrapperDpae;
import fr.insy2s.utils.wrapper.WrapperPdfDpae;
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
 * Service Implementation for managing {@link Dpae}.
 */

@Service
@Transactional
public class DpaeServiceImpl implements DpaeService {

    private final Logger log = LoggerFactory.getLogger(DpaeServiceImpl.class);

    private final DpaeRepository dpaeRepository;
    private final DpaeMapper dpaeMapper;
    private final EmployeMapper employeMapper;
    private final SocieteMapper societeMapper;
    private final AdresseMapper adresseMapper;
    private final InfoEntrepriseMapper infoEntrepriseMapper;
    private final SaisieArticleMapper saisieArticleMapper;
    private final ContratMapper contratMapper;
    private final TypeContratMapper typeContratMapper;
    private final WrapperDpaeMapper wrapperDpaeMapper;
    private final WrapperPdfDpaeMapper wrapperPdfDpaeMapper;
    private final EmployeService employeService;
    private final SocieteService societeService;
    private final InfoEntrepriseService infoEntrepriseService;
    private final AdresseService adresseService;
    private final ContratService contratService;
    private final TypeContratService typeContratService;
    private final SaisieArticleService saisieArticleService;

    public DpaeServiceImpl(DpaeRepository dpaeRepository,
                           DpaeMapper dpaeMapper,
                           EmployeMapper employeMapper,
                           SocieteMapper societeMapper,
                           AdresseMapper adresseMapper,
                           InfoEntrepriseMapper infoEntrepriseMapper,
                           SaisieArticleMapper saisieArticleMapper, ContratMapper contratMapper, TypeContratMapper typeContratMapper, WrapperDpaeMapper wrapperDpaeMapper, WrapperPdfDpaeMapper wrapperPdfDpaeMapper,
                           EmployeService employeService,
                           SocieteService societeService,
                           InfoEntrepriseService infoEntrepriseService,
                           AdresseService adresseService,
                           ContratService contratService,
                           TypeContratService typeContratService,
                           SaisieArticleService saisieArticleService) {
        this.dpaeRepository = dpaeRepository;
        this.dpaeMapper = dpaeMapper;
        this.employeMapper = employeMapper;
        this.societeMapper = societeMapper;
        this.adresseMapper = adresseMapper;
        this.infoEntrepriseMapper = infoEntrepriseMapper;
        this.saisieArticleMapper = saisieArticleMapper;
        this.contratMapper = contratMapper;
        this.typeContratMapper = typeContratMapper;
        this.wrapperDpaeMapper = wrapperDpaeMapper;
        this.wrapperPdfDpaeMapper = wrapperPdfDpaeMapper;
        this.employeService = employeService;
        this.societeService = societeService;
        this.infoEntrepriseService = infoEntrepriseService;
        this.adresseService = adresseService;
        this.contratService = contratService;
        this.typeContratService = typeContratService;
        this.saisieArticleService = saisieArticleService;
    }

    @Override
    public DpaeDTO save(DpaeDTO dpaeDTO) {
        log.debug("Request to save Dpae : {}", dpaeDTO);
        Dpae dpae = dpaeMapper.toEntity(dpaeDTO);
        dpae = dpaeRepository.save(dpae);
        return dpaeMapper.toDto(dpae);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DpaeDTO> findAll() {
        log.debug("Request to get all Dpaes");
        return dpaeRepository.findAll().stream()
            .map(dpaeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DpaeDTO> findOne(Long id) {
        log.debug("Request to get Dpae : {}", id);
        return dpaeRepository.findById(id)
            .map(dpaeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dpae : {}", id);
        dpaeRepository.deleteById(id);
    }

    public WrapperPdfDpae getWrapperPdfDpae(Long id) {
        WrapperPdfDpae wrapperPdfDpae = new WrapperPdfDpae();
        final DpaeDTO dpaeDTO = this.findOne(id).orElse(null);
        if (dpaeDTO != null) {
            final ContratDTO contratDTO = contratService.findOne(dpaeDTO.getContratId()).get();
            final TypeContratDTO typeContratDTO =typeContratService.findOne(contratDTO.getTypeContratId()).get();
            final EmployeDTO employeDTO = employeService.findOne(contratDTO.getEmployeId()).get();
            final SocieteDTO societeDTO = societeService.findOne(employeDTO.getSocieteId()).get();
            final InfoEntrepriseDTO infoEntrepriseDTO = infoEntrepriseService.findOne(societeDTO.getInfoEntrepriseId()).get();
            final AdresseDTO adresseDTO = adresseService.findOne(societeDTO.getAdresseId()).get();

            wrapperPdfDpae = wrapperPdfDpaeMapper.builderWrapperPdfDpae(dpaeDTO, typeContratDTO,
                employeDTO, societeDTO, infoEntrepriseDTO, adresseDTO);
        }
        return wrapperPdfDpae;
    }

    @Override
    public Optional<WrapperDpae> findWrapperDpaeById(Long id) {
        Optional<WrapperDpae> wrapperDpae;
        final DpaeDTO dpaeDTO = this.findOne(id).orElse(null);
        if (dpaeDTO != null) {
            final ContratDTO contratDTO = contratService.findOne(dpaeDTO.getContratId()).get();
            final TypeContratDTO typeContratDTO =typeContratService.findOne(contratDTO.getTypeContratId()).get();
            final EmployeDTO employeDTO = employeService.findOne(contratDTO.getEmployeId()).get();
            final SocieteDTO societeDTO = societeService.findOne(employeDTO.getSocieteId()).get();
            final InfoEntrepriseDTO infoEntrepriseDTO = infoEntrepriseService.findOne(societeDTO.getInfoEntrepriseId()).get();
            final AdresseDTO adresseDTO = adresseService.findOne(societeDTO.getAdresseId()).get();
            final SaisieArticleDTO dateDebutDTO = saisieArticleService.findDateDebutbyContratId(dpaeDTO.getContratId());
            // tester nullité d'un élément au moins
            wrapperDpae = Optional.of(new WrapperDpae(dpaeDTO, societeDTO, infoEntrepriseDTO, employeDTO, adresseDTO, contratDTO, typeContratDTO, dateDebutDTO));
        } else {
            wrapperDpae = Optional.empty();
        }
        return wrapperDpae;
    }

    @Override
    public List<WrapperDpae> findAllWrapperDpaesToDoBySociety(Long societyId) {
        List<WrapperDpae> wrapperDpaes = new ArrayList<>();

        // get liste employés, contient employeDTO, societeDTO, infoEntrepriseDTO, adresseDTO
        List<Employe> employees = employeService.findAllEmployeesWithDpaeToDoBySociety(societyId);
        for (Employe employee : employees) {
            EmployeDTO employeDTO = employeMapper.toDto(employee);
            SocieteDTO societeDTO = societeMapper.toDto(employee.getSociete());
            InfoEntrepriseDTO infoEntrepriseDTO = infoEntrepriseMapper.toDto(employee.getSociete().getInfoEntreprise());
            AdresseDTO adresseDTO = adresseMapper.toDto(employee.getSociete().getAdresse());

            // get date de début, contient dateDebutDTO, contratDTO, typeContratDTO
            SaisieArticle dateDebut = saisieArticleService.findActiveStartDateByEmployee(employee.getId());
            SaisieArticleDTO dateDebutDTO = saisieArticleMapper.toDto(dateDebut);
            ContratDTO contratDTO = contratMapper.toDto(dateDebut.getContrat());
            TypeContratDTO typeContratDTO = typeContratMapper.toDto(dateDebut.getContrat().getTypeContrat());

            WrapperDpae wrapperDpae = wrapperDpaeMapper.buildWrapperDpaeWithoutDpaeDto(societeDTO, infoEntrepriseDTO, employeDTO, adresseDTO, contratDTO, typeContratDTO, dateDebutDTO);
            wrapperDpaes.add(wrapperDpae);
        }

        return wrapperDpaes;
    }

    @Override
    public List<DpaeDTO> findAllDpaeByEmployeIdMonthStartMonthEnd(Long idEmploye, Integer year, Integer monthStart, Integer monthEnd) {

        //création d'une plage de date temporaire
        LocalDate startDate = LocalDate.of(year, monthStart,1);
        LocalDate endDate = LocalDate.of(year, monthEnd,1);;
        endDate = endDate.withDayOfMonth(endDate.lengthOfMonth());

        log.debug("Request to get all Dpae with IdEmploye:{}, startDate:{}, endDate:{}", idEmploye, startDate, endDate );
        List<Dpae> dpaeList = dpaeRepository.findAllDpaeByEmployeIdMonthStartMonthEnd(idEmploye, startDate, endDate );
        List<DpaeDTO> dpaeDTOList = new ArrayList<>();
        DpaeDTO dpaeDTOtmp ;
        if(!dpaeList.isEmpty()) {
            for (Dpae dpae : dpaeList) {
                dpaeDTOtmp = new DpaeDTO();
                dpaeDTOtmp.setId(dpae.getId());
                dpaeDTOtmp.setLieu(dpae.getLieu());
                dpaeDTOtmp.setDate(dpae.getDate());
                dpaeDTOtmp.setHeureEmbauche(dpae.getHeureEmbauche());
                dpaeDTOtmp.setCommentaire(dpae.getCommentaire());
                dpaeDTOtmp.setRetourApiUrssaf(dpae.getRetourApiUrssaf());
                dpaeDTOtmp.setContratId(dpae.getContrat().getId());
                dpaeDTOList.add(dpaeDTOtmp);
            }
        }
        return dpaeDTOList;
    }

}
