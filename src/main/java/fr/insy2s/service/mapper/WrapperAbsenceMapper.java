package fr.insy2s.service.mapper;

import fr.insy2s.service.dto.AbsenceDTO;
import fr.insy2s.service.dto.TypeAbsenceDTO;
import fr.insy2s.utils.wrapper.WrapperAbsence;
import org.springframework.stereotype.Service;

/**
 * Mapper for the entity Absence,  and the DTO WrapperAbsence.
 *
 */
@Service
public class WrapperAbsenceMapper {

    /**
     * Builder WrapperAbsence
     *
     * @param absenceDTO the absenceDTO for building the WrapperAbsence
     * @param typeAbsenceDTO the typeAbsenceDTO for building the WrapperAbsence
     *
     * @return wrapperAbsence
     */
    public WrapperAbsence builderWrapperAbsence(AbsenceDTO absenceDTO, TypeAbsenceDTO typeAbsenceDTO){
        final WrapperAbsence wrapperAbsence = new WrapperAbsence();
        // Absence
        wrapperAbsence.setId(absenceDTO.getId());
        wrapperAbsence.setDebutAbsence(absenceDTO.getDebutAbsence());
        wrapperAbsence.setFinAbsence(absenceDTO.getFinAbsence());

        wrapperAbsence.setAnnee(absenceDTO.getAnnee());
        wrapperAbsence.setMois(absenceDTO.getMois());
        wrapperAbsence.setEmployeId(absenceDTO.getEmployeId());
        wrapperAbsence.setEtatVariablePaieId(absenceDTO.getEtatVariablePaieId());
        wrapperAbsence.setTypeAbsenceId(absenceDTO.getTypeAbsenceId());
        // TypeAbsence
        wrapperAbsence.setIdTypeAbsence(typeAbsenceDTO.getId());
        wrapperAbsence.setCodeRef(typeAbsenceDTO.getCodeRef());
        wrapperAbsence.setIntitule(typeAbsenceDTO.getIntitule());
        // return
        return wrapperAbsence;
    }

    /**
     * Mappe WrapperAbsence to AbsenceDto
     *
     * @param wrapperAbsence the wrapperAbsence to map to a absenceDTO
     *
     * @return absenceDTO
     */
    public AbsenceDTO toAbsenceDTO(final WrapperAbsence wrapperAbsence){
        AbsenceDTO absenceDTO = new AbsenceDTO();

        absenceDTO.setId(wrapperAbsence.getId());
        absenceDTO.setDebutAbsence(wrapperAbsence.getDebutAbsence());
        absenceDTO.setFinAbsence(wrapperAbsence.getFinAbsence());

        absenceDTO.setAnnee(wrapperAbsence.getAnnee());
        absenceDTO.setMois(wrapperAbsence.getMois());
        absenceDTO.setEmployeId(wrapperAbsence.getEmployeId());
        absenceDTO.setEtatVariablePaieId(wrapperAbsence.getEtatVariablePaieId());
        absenceDTO.setTypeAbsenceId(wrapperAbsence.getTypeAbsenceId());

        return absenceDTO;
    }

    /**
     * Mappe WrapperAbsence to TypeAbsenceDto
     *
     * @param wrapperAbsence the wrapperAbsence to map to a typeAbsenceDTO
     *
     * @return typeAbsenceDTO
     */    public TypeAbsenceDTO toTypeAbsenceDTO(final WrapperAbsence wrapperAbsence){
        TypeAbsenceDTO typeAbsenceDTO = new TypeAbsenceDTO();

        typeAbsenceDTO.setId(wrapperAbsence.getIdTypeAbsence());
        typeAbsenceDTO.setCodeRef(wrapperAbsence.getCodeRef());
        typeAbsenceDTO.setIntitule(wrapperAbsence.getIntitule());

        return typeAbsenceDTO;
    }

}
