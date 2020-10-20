package fr.insy2s.service.impl;

import fr.insy2s.domain.Dpae;
import fr.insy2s.repository.DpaeRepository;
import fr.insy2s.service.*;
import fr.insy2s.service.dto.*;
import fr.insy2s.service.mapper.DpaeMapper;
import fr.insy2s.service.mapper.WrapperPdfDpaeMapper;
import fr.insy2s.utils.wrapper.WrapperDpae;
import fr.insy2s.utils.wrapper.WrapperPdfDpae;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final WrapperPdfDpaeMapper wrapperPdfDpaeMapper;
    private final EmployeService employeService;
    private final SocieteService societeService;
    private final InfoEntrepriseService infoEntrepriseService;
    private final AdresseService adresseService;
    private final ContratService contratService;
    private final TypeContratService typeContratService;

    public DpaeServiceImpl(DpaeRepository dpaeRepository,
                           DpaeMapper dpaeMapper,
                           WrapperPdfDpaeMapper wrapperPdfDpaeMapper,
                           EmployeService employeService,
                           SocieteService societeService,
                           InfoEntrepriseService infoEntrepriseService,
                           AdresseService adresseService,
                           ContratService contratService,
                           TypeContratService typeContratService) {
        this.dpaeRepository = dpaeRepository;
        this.dpaeMapper = dpaeMapper;
        this.wrapperPdfDpaeMapper = wrapperPdfDpaeMapper;
        this.employeService = employeService;
        this.societeService = societeService;
        this.infoEntrepriseService = infoEntrepriseService;
        this.adresseService = adresseService;
        this.contratService = contratService;
        this.typeContratService = typeContratService;
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
            // tester nullité d'un élément au moins
            wrapperDpae = Optional.of(new WrapperDpae(dpaeDTO, societeDTO, infoEntrepriseDTO, employeDTO, adresseDTO, contratDTO, typeContratDTO));
        }else{
            wrapperDpae = Optional.empty();
        }
        return wrapperDpae;
    }
}
