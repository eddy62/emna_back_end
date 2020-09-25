package fr.insy2s.service.impl;

import fr.insy2s.service.OperationService;
import fr.insy2s.domain.Operation;
import fr.insy2s.repository.OperationRepository;
import fr.insy2s.service.dto.OperationDTO;
import fr.insy2s.service.mapper.OperationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Operation}.
 */
@Service
@Transactional
public class OperationServiceImpl implements OperationService {

    private final Logger log = LoggerFactory.getLogger(OperationServiceImpl.class);

    private final OperationRepository operationRepository;

    private final OperationMapper operationMapper;

    public OperationServiceImpl(OperationRepository operationRepository, OperationMapper operationMapper) {
        this.operationRepository = operationRepository;
        this.operationMapper = operationMapper;
    }

    @Override
    public OperationDTO save(OperationDTO operationDTO) {
        log.debug("Request to save Operation : {}", operationDTO);
        Operation operation = operationMapper.toEntity(operationDTO);
        operation = operationRepository.save(operation);
        return operationMapper.toDto(operation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationDTO> findAll() {
        log.debug("Request to get all Operations");
        return operationRepository.findAll().stream()
            .map(operationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OperationDTO> findOne(Long id) {
        log.debug("Request to get Operation : {}", id);
        return operationRepository.findById(id)
            .map(operationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Operation : {}", id);
        operationRepository.deleteById(id);
    }
    public List<OperationDTO> findAllByReleveId(Long id) {
		 log.debug("Request to get all Operations by Releve Id");
		return operationRepository.findAllByReleveId(id).stream().map(operationMapper::toDto)
	            .collect(Collectors.toCollection(LinkedList::new));
	}

    @Override
    public void updateRapprochementOperation(Long idOperation) {
        log.debug("Request to update rapprochement");
        operationRepository.updateRapprochementOperation(idOperation,true);
    }
}
