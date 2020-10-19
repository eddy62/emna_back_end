package fr.insy2s.service.impl;

import fr.insy2s.service.AvenantService;
import fr.insy2s.domain.Avenant;
import fr.insy2s.repository.AvenantRepository;
import fr.insy2s.service.dto.AvenantDTO;
import fr.insy2s.service.mapper.AvenantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Avenant}.
 */
@Service
@Transactional
public class AvenantServiceImpl implements AvenantService {

    private final Logger log = LoggerFactory.getLogger(AvenantServiceImpl.class);

    private final AvenantRepository avenantRepository;

    private final AvenantMapper avenantMapper;

    public AvenantServiceImpl(AvenantRepository avenantRepository, AvenantMapper avenantMapper) {
        this.avenantRepository = avenantRepository;
        this.avenantMapper = avenantMapper;
    }

    @Override
    public AvenantDTO save(AvenantDTO avenantDTO) {
        log.debug("Request to save Avenant : {}", avenantDTO);
        Avenant avenant = avenantMapper.toEntity(avenantDTO);
        avenant = avenantRepository.save(avenant);
        return avenantMapper.toDto(avenant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AvenantDTO> findAll() {
        log.debug("Request to get all Avenants");
        return avenantRepository.findAll().stream()
            .map(avenantMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AvenantDTO> findOne(Long id) {
        log.debug("Request to get Avenant : {}", id);
        return avenantRepository.findById(id)
            .map(avenantMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Avenant : {}", id);
        avenantRepository.deleteById(id);
    }

    @Override
    public List<AvenantDTO> getAllAmendmentByContractId(long idContract) {
        log.debug("Request to ge all Avenants by contrat id");
        return avenantRepository.getAllAmendmentByIdContract(idContract)
            .stream().map(avenantMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public byte[] getPDFAmendement(Long idAmendment) {
        return new byte[0];
    }

    @Override
    public String getNamePdf(Long idAmendment) {
        return null;
    }
}
