package fr.insy2s.service.impl;

import fr.insy2s.service.DpaeService;
import fr.insy2s.domain.Dpae;
import fr.insy2s.repository.DpaeRepository;
import fr.insy2s.service.dto.DpaeDTO;
import fr.insy2s.service.mapper.DpaeMapper;
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

    public DpaeServiceImpl(DpaeRepository dpaeRepository, DpaeMapper dpaeMapper) {
        this.dpaeRepository = dpaeRepository;
        this.dpaeMapper = dpaeMapper;
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
}
