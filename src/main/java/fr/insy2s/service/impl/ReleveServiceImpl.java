package fr.insy2s.service.impl;

import fr.insy2s.service.ReleveService;
import fr.insy2s.domain.Releve;
import fr.insy2s.repository.ReleveRepository;
import fr.insy2s.service.dto.ReleveDTO;
import fr.insy2s.service.mapper.ReleveMapper;
import fr.insy2s.utils.EtatReleveConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Releve}.
 */
@Service
@Transactional
public class ReleveServiceImpl implements ReleveService {

    private final Logger log = LoggerFactory.getLogger(ReleveServiceImpl.class);

    private final ReleveRepository releveRepository;

    private final ReleveMapper releveMapper;

    public ReleveServiceImpl(ReleveRepository releveRepository, ReleveMapper releveMapper) {
        this.releveRepository = releveRepository;
        this.releveMapper = releveMapper;
    }

    @Override
    public ReleveDTO save(ReleveDTO releveDTO) {
        log.debug("Request to save Releve : {}", releveDTO);
        Releve releve = releveMapper.toEntity(releveDTO);
        releve = releveRepository.save(releve);
        return releveMapper.toDto(releve);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleveDTO> findAll() {
        log.debug("Request to get all Releves");
        return releveRepository.findAll().stream()
            .map(releveMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ReleveDTO> findOne(Long id) {
        log.debug("Request to get Releve : {}", id);
        return releveRepository.findById(id)
            .map(releveMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Releve : {}", id);
        releveRepository.deleteById(id);
    }
    public List<ReleveDTO> findAllBySocieteId(Long id) {
  		 log.debug("Request to get all Releves by Societe Id");
  		return releveRepository.findAllBySocieteId(id).stream().map(releveMapper::toDto)
  	            .collect(Collectors.toCollection(LinkedList::new));
  	}
   public List<ReleveDTO> findAllByEtatReleveId(Long id) {
 		 log.debug("Request to get all Releves by Societe Id");
 		return releveRepository.findAllByEtatReleveId(id).stream().map(releveMapper::toDto)
 	            .collect(Collectors.toCollection(LinkedList::new));
 	}
   public List<ReleveDTO> findAllByEtatReleveIdAndSocieteId(Long idEtat,Long idSociete) {
		 log.debug("Request to get all Releves by Societe Id");
		return releveRepository.findAllByEtatReleveIdAndSocieteId( idEtat,idSociete).stream().map(releveMapper::toDto)
	            .collect(Collectors.toCollection(LinkedList::new));
	}

    @Override
    public boolean validateReleve(Long id) {
        log.debug("REST request to validate Releve");
        Integer result = releveRepository.validateRelever(id, EtatReleveConstants.RELEVE_NON_ARCHIVE);
        return result != 0;
    }

    @Override
    public Optional<BigDecimal> getReleveSoldeById(Long id)
    {
        log.debug("Request to get solde by Releve Id");
        return releveRepository.getReleveSoldeById(id);
    }
}
