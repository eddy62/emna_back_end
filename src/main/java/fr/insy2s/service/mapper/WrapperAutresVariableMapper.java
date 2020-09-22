package fr.insy2s.service.mapper;

import fr.insy2s.service.dto.AutresVariableDTO;
import fr.insy2s.utils.wrapper.WrapperAutresVariable;
import org.springframework.stereotype.Service;

/**
 * Mapper for the entity NoteDeFrais,  and the DTO WrapperAutresVariable.
 *
 */
@Service
public class WrapperAutresVariableMapper {

    /**
     * Builder WrapperAutresVariable
     *
     * @param autresVariableDTO : the autresVariableDTO for building a wrapperAutresVariable
     *
     * @return wrapperAutresVariable
     */
    public WrapperAutresVariable builderWrapperAutresVariable(AutresVariableDTO autresVariableDTO){
        final WrapperAutresVariable wrapperAutresVariable = new WrapperAutresVariable();
        // AutresVariable
        wrapperAutresVariable.setId(autresVariableDTO.getId());
        wrapperAutresVariable.setDate(autresVariableDTO.getDate());
        wrapperAutresVariable.setDescription(autresVariableDTO.getDescription());
        wrapperAutresVariable.setMontant(autresVariableDTO.getMontant());
        wrapperAutresVariable.setJustificatif(autresVariableDTO.getJustificatif());
        wrapperAutresVariable.setAnnee(autresVariableDTO.getAnnee());
        wrapperAutresVariable.setMois(autresVariableDTO.getMois());
        wrapperAutresVariable.setEmployeId(autresVariableDTO.getEmployeId());
        wrapperAutresVariable.setEtatVariablePaieId(autresVariableDTO.getEtatVariablePaieId());

        // return
        return wrapperAutresVariable;
    }

    /**
     * Mappe WrapperAutresVariable to NoteDeFraisDto
     *
     * @param wrapperAutresVariable : the wrapperAutresVariable to map to autresVariableDTO
     *
     * @return autresVariableDTO
     */
    public AutresVariableDTO toAutresVariableDTO(final WrapperAutresVariable wrapperAutresVariable){
        AutresVariableDTO autresVariableDTO = new AutresVariableDTO();

        autresVariableDTO.setId(wrapperAutresVariable.getId());
        autresVariableDTO.setDate(wrapperAutresVariable.getDate());
        autresVariableDTO.setDescription(wrapperAutresVariable.getDescription());
        autresVariableDTO.setMontant(wrapperAutresVariable.getMontant());
        autresVariableDTO.setJustificatif(wrapperAutresVariable.getJustificatif());
        autresVariableDTO.setAnnee(wrapperAutresVariable.getAnnee());
        autresVariableDTO.setMois(wrapperAutresVariable.getMois());
        autresVariableDTO.setEmployeId(wrapperAutresVariable.getEmployeId());
        autresVariableDTO.setEtatVariablePaieId(wrapperAutresVariable.getEtatVariablePaieId());

        return autresVariableDTO;
    }
}
