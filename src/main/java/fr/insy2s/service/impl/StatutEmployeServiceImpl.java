package fr.insy2s.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.insy2s.domain.StatutEmploye;
import fr.insy2s.repository.StatutEmployeRepository;
import fr.insy2s.service.StatutEmployeService;
import fr.insy2s.service.dto.StatutEmployeDTO;
import fr.insy2s.service.mapper.StatutEmployeMapper;

/**
 * Service Implementation for managing {@link StatutEmploye}.
 */
@Service
@Transactional
public class StatutEmployeServiceImpl implements StatutEmployeService {

    private final Logger                  log = LoggerFactory.getLogger(StatutEmployeServiceImpl.class);

    private final StatutEmployeRepository statutEmployeRepository;

    private final StatutEmployeMapper     statutEmployeMapper;

    public StatutEmployeServiceImpl(StatutEmployeRepository statutEmployeRepository, StatutEmployeMapper statutEmployeMapper) {
        this.statutEmployeRepository = statutEmployeRepository;
        this.statutEmployeMapper = statutEmployeMapper;
    }

    /**
     * Save a statutEmploye.
     *
     * @param statutEmployeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public StatutEmployeDTO save(StatutEmployeDTO statutEmployeDTO) {
        log.debug("Request to save StatutEmploye : {}", statutEmployeDTO);
        StatutEmploye statutEmploye = statutEmployeMapper.toEntity(statutEmployeDTO);
        statutEmploye = statutEmployeRepository.save(statutEmploye);
        return statutEmployeMapper.toDto(statutEmploye);
    }

    /**
     * Get all the statutEmployes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<StatutEmployeDTO> findAll() {
        log.debug("Request to get all StatutEmployes");
        return statutEmployeRepository.findAll().stream().map(statutEmployeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one statutEmploye by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StatutEmployeDTO> findOne(Long id) {
        log.debug("Request to get StatutEmploye : {}", id);
        return statutEmployeRepository.findById(id).map(statutEmployeMapper::toDto);
    }

    /**
     * Delete the statutEmploye by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StatutEmploye : {}", id);
        statutEmployeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public StatutEmployeDTO findByCodeRef(String codeRef) {
        log.debug("Request to get StatutEmploye : {}", codeRef);
        List<StatutEmployeDTO> liste = findAll();

        for (StatutEmployeDTO statutEmployeDTO : liste) {
            if (statutEmployeDTO.getCodeRef().equals(codeRef)) {
                return statutEmployeDTO;
            }
        }

        return null;
    }
}
