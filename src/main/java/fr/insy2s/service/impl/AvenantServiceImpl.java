package fr.insy2s.service.impl;

import fr.insy2s.domain.SaisieArticle;
import fr.insy2s.service.AvenantService;
import fr.insy2s.domain.Avenant;
import fr.insy2s.repository.AvenantRepository;
import fr.insy2s.service.dto.AvenantDTO;
import fr.insy2s.service.dto.SaisieArticleDTO;
import fr.insy2s.service.mapper.AvenantMapper;
import fr.insy2s.utils.files.PdfUtil;
import fr.insy2s.utils.wrapper.WrapperAmendment;
import fr.insy2s.utils.wrapper.WrapperSingleInputAmendment;
import net.sf.jasperreports.engine.JRException;
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
import java.util.stream.Stream;

/**
 * Service Implementation for managing {@link Avenant}.
 */
@Service
@Transactional
public class AvenantServiceImpl implements AvenantService {

    private final Logger log = LoggerFactory.getLogger(AvenantServiceImpl.class);

    private final AvenantRepository avenantRepository;

    private final AvenantMapper avenantMapper;

    private final SaisieArticleServiceImpl saisieArticleService;

    public AvenantServiceImpl(AvenantRepository avenantRepository, AvenantMapper avenantMapper, SaisieArticleServiceImpl saisieArticleService) {
        this.avenantRepository = avenantRepository;
        this.avenantMapper = avenantMapper;
        this.saisieArticleService = saisieArticleService;
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
    public byte[] getPDFAmendment(Long idAmendment) throws JRException {
        log.debug("Request to get wrapperAmendment id: " + idAmendment);
        Avenant amendment = avenantRepository.getAmendmentById(idAmendment);
        SaisieArticle sA = new ArrayList<>(amendment.getListeSaisieArticles()).get(0);
        List<SaisieArticle> saisieArticles = new ArrayList<>(amendment.getListeSaisieArticles());
        Stream<WrapperSingleInputAmendment> stream = saisieArticles.stream().map(s -> new WrapperSingleInputAmendment(s));
        List<WrapperSingleInputAmendment> w = stream.collect(Collectors.toList());
        WrapperAmendment wrapperAmendment = new WrapperAmendment(sA.getContrat(), sA.getContrat().getEmploye().getSociete(), w);
        return PdfUtil.generateAmendmentAsBytes(wrapperAmendment);
    }

    @Override
    public Boolean signAmendment(Long id) {
        Optional<Avenant> avenant = avenantRepository.findById(id);
       if (avenant.isPresent()){
            avenant.get().setSigne(true);
            avenantRepository.save(avenant.get());
            return avenant.get().isSigne();
        }else {
           return false;
       }
    }

    @Override
    public AvenantDTO saveFromListeSaisieArticle(List<SaisieArticleDTO> listeSaisieArticle) {
        AvenantDTO avenantDTO = new AvenantDTO();
        avenantDTO.setDateDeCreation(LocalDate.now());
        avenantDTO.setReference("AVN");
        avenantDTO.setSigne(false);
        try{
            AvenantDTO avenantSaved = save(avenantDTO);
            for(int i = 0; i < listeSaisieArticle.size(); i++){
                SaisieArticleDTO saisieArticleDTO = listeSaisieArticle.get(i);
                saisieArticleDTO.setAvenantId(avenantSaved.getId());
                saisieArticleService.save(saisieArticleDTO);
            }
//            listeSaisieArticle.forEach(saisieArticleDTO -> {
//                saisieArticleDTO.setAvenantId(avenantSaved.getId());
//                SaisieArticleDTO saisieArticleSave = saisieArticleService.save(saisieArticleDTO);
//                saisieArticleService.saveSaisieArticle(saisieArticleSave);
//            });
            return avenantSaved;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
