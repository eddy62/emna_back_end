package fr.insy2s.service.mapper;

import fr.insy2s.service.dto.NoteDeFraisDTO;
import fr.insy2s.utils.wrapper.WrapperNoteDeFrais;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Mapper for the entity NoteDeFrais,  and the DTO WrapperNoteDeFrais.
 *
 */
@Service
public class WrapperNoteDeFraisMapper {

    /**
     * Builder WrapperNoteDeFrais
     *
     * @param noteDeFraisDTO the noteDeFraisDTO for building a WrapperNoteDeFrais.
     *
     * @return wrapperNoteDeFrais
     */
    public WrapperNoteDeFrais builderWrapperNoteDeFrais(NoteDeFraisDTO noteDeFraisDTO){
        final WrapperNoteDeFrais wrapperNoteDeFrais = new WrapperNoteDeFrais();
        // NoteDeFrais
        wrapperNoteDeFrais.setId(noteDeFraisDTO.getId());
        wrapperNoteDeFrais.setDate(noteDeFraisDTO.getDate());
        wrapperNoteDeFrais.setDesignation(noteDeFraisDTO.getDesignation());

        wrapperNoteDeFrais.setMontant(noteDeFraisDTO.getMontant());
        wrapperNoteDeFrais.setAnnee(noteDeFraisDTO.getAnnee());
        wrapperNoteDeFrais.setMois(noteDeFraisDTO.getMois());
        wrapperNoteDeFrais.setEmployeId(noteDeFraisDTO.getEmployeId());
        wrapperNoteDeFrais.setEtatVariablePaieId(noteDeFraisDTO.getEtatVariablePaieId());

        // return
        return wrapperNoteDeFrais;
    }

    /**
     * Mappe WrapperNoteDeFrais to NoteDeFraisDto
     *
     * @param wrapperNoteDeFrais the wrapperNoteDeFrais to map to NoteDeFraisDTO
     *
     * @return noteDeFraisDTO
     */
    public NoteDeFraisDTO toNoteDeFraisDTO(final WrapperNoteDeFrais wrapperNoteDeFrais){
        NoteDeFraisDTO noteDeFraisDTO = new NoteDeFraisDTO();

        noteDeFraisDTO.setId(wrapperNoteDeFrais.getId());
        noteDeFraisDTO.setDate(wrapperNoteDeFrais.getDate());
        noteDeFraisDTO.setDesignation(wrapperNoteDeFrais.getDesignation());
        noteDeFraisDTO.setMontant(wrapperNoteDeFrais.getMontant());
        noteDeFraisDTO.setAnnee(wrapperNoteDeFrais.getAnnee());
        noteDeFraisDTO.setMois(wrapperNoteDeFrais.getMois());
        noteDeFraisDTO.setEmployeId(wrapperNoteDeFrais.getEmployeId());
        noteDeFraisDTO.setEtatVariablePaieId(wrapperNoteDeFrais.getEtatVariablePaieId());

        return noteDeFraisDTO;
    }
}
