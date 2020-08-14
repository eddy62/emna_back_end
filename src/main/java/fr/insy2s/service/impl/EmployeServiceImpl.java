package fr.insy2s.service.impl;

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

import fr.insy2s.domain.Employe;
import fr.insy2s.repository.EmployeRepository;
import fr.insy2s.repository.projection.IEmployeContratProjection;
import fr.insy2s.service.AdresseService;
import fr.insy2s.service.EmployeService;
import fr.insy2s.service.InfoEntrepriseService;
import fr.insy2s.service.SocieteService;
import fr.insy2s.service.StatutEmployeService;
import fr.insy2s.service.dto.AdresseDTO;
import fr.insy2s.service.dto.EmployeDTO;
import fr.insy2s.service.dto.InfoEntrepriseDTO;
import fr.insy2s.service.dto.SocieteDTO;
import fr.insy2s.service.dto.StatutEmployeDTO;
import fr.insy2s.service.mapper.EmployeMapper;
import fr.insy2s.service.mapper.WrapperEmployeMapper;
import fr.insy2s.utils.wrapper.WrapperEmploye;

/**
 * Service Implementation for managing {@link Employe}.
 */
@Service
@Transactional
public class EmployeServiceImpl implements EmployeService {

    private final Logger                log = LoggerFactory.getLogger(EmployeServiceImpl.class);

    private final EmployeRepository     employeRepository;

    private final EmployeMapper         employeMapper;

    private final WrapperEmployeMapper  wrapperEmployeMapper;
    private final AdresseService        adresseService;
    private final StatutEmployeService  statutEmployeService;
    private final SocieteService        societeService;
    private final InfoEntrepriseService infoEntrepriseService;

    public EmployeServiceImpl(EmployeRepository employeRepository, EmployeMapper employeMapper, WrapperEmployeMapper wrapperEmployeMapper, AdresseService adresseService,
                    StatutEmployeService statutEmployeService, SocieteService societeService, InfoEntrepriseService infoEntrepriseService) {
        this.employeRepository = employeRepository;
        this.employeMapper = employeMapper;
        this.wrapperEmployeMapper = wrapperEmployeMapper;
        this.adresseService = adresseService;
        this.statutEmployeService = statutEmployeService;
        this.societeService = societeService;
        this.infoEntrepriseService = infoEntrepriseService;
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
        final Optional<WrapperEmploye> wrapperEmploye = Optional.of(wrapperEmployeMapper.builderWrapperEmploye(employeDTO, adresseDTO, statutEmployeDTO, societeDTO, infoEntrepriseDTO));
        return wrapperEmploye.isPresent() ? Optional.of(wrapperEmploye.get()) : Optional.empty();
    }

    @Override
    public WrapperEmploye createWrapperEmploye(@Valid WrapperEmploye wrapperEmploye) {
        final SocieteDTO societeDTO = societeService.findOne(wrapperEmploye.getSocieteId()).get();
        final InfoEntrepriseDTO infoEntrepriseDTO = infoEntrepriseService.findOne(societeDTO.getInfoEntrepriseId()).get();
        final AdresseDTO newAdresseDTO = adresseService.save(wrapperEmployeMapper.toAdresseDto(wrapperEmploye));
        final StatutEmployeDTO statutEmployeDTO = statutEmployeService.findByCodeRef(wrapperEmploye.getCodeRef());
        final EmployeDTO employeDTO = wrapperEmployeMapper.toEmployeDto(wrapperEmploye);
        employeDTO.setAdresseId(newAdresseDTO.getId());
        employeDTO.setStatutEmployeId(statutEmployeDTO.getId());
       
        final EmployeDTO newEmployeDTO = employeMapper.toDto(employeRepository.save(employeMapper.toEntity(employeDTO)));
        
        final WrapperEmploye newWrapperEmploye = wrapperEmployeMapper.builderWrapperEmploye(newEmployeDTO, newAdresseDTO, statutEmployeDTO, societeDTO, infoEntrepriseDTO);
        
        return newWrapperEmploye;
    }

    @Override
    public WrapperEmploye updateWrapperEmploye(@Valid WrapperEmploye wrapperEmploye) {
        final SocieteDTO societeDTO = societeService.findOne(wrapperEmploye.getSocieteId()).get();
        final InfoEntrepriseDTO infoEntrepriseDTO = infoEntrepriseService.findOne(societeDTO.getInfoEntrepriseId()).get();
        final AdresseDTO newAdresseDTO = adresseService.save(wrapperEmployeMapper.toAdresseDto(wrapperEmploye));
        final StatutEmployeDTO statutEmployeDTO = statutEmployeService.findByCodeRef(wrapperEmploye.getCodeRef());
        final EmployeDTO employeDTO = wrapperEmployeMapper.toEmployeDto(wrapperEmploye);
        employeDTO.setAdresseId(newAdresseDTO.getId());
        employeDTO.setStatutEmployeId(statutEmployeDTO.getId());
       
        final EmployeDTO newEmployeDTO = employeMapper.toDto(employeRepository.save(employeMapper.toEntity(employeDTO)));
        
        final WrapperEmploye newWrapperEmploye = wrapperEmployeMapper.builderWrapperEmploye(newEmployeDTO, newAdresseDTO, statutEmployeDTO, societeDTO, infoEntrepriseDTO);
        
        return newWrapperEmploye;
    }

    @Override
    public void deleteWrapperEmploye(Long id) {
        log.debug("Request to delete WrapperEmploye : {}", id);
        final Employe employe = employeRepository.getOne(id);
        final AdresseDTO adresseDTO = adresseService.findOne(employe.getAdresse().getId()).get();
        adresseService.delete(adresseDTO.getId());
        employeRepository.delete(employe);
        

    }
    
    @Override
    public List<IEmployeContratProjection> getAllEmployeArticleClauseBySocieteId(Long id) {
        return this.employeRepository.getAllEmployeArticleClauseBySocieteId(id);
    }
    
}
