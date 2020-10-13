package fr.insy2s.service.mapper;

import fr.insy2s.service.dto.ContratDTO;
import fr.insy2s.utils.wrapper.WrapperContrat;
import org.springframework.stereotype.Service;

@Service
public class WrapperContratMapper {

    public ContratDTO toContratDto(final WrapperContrat wrapperContrat) {
        ContratDTO contratDTO = new ContratDTO();
        contratDTO.setId(wrapperContrat.getId());
        contratDTO.setArchive(wrapperContrat.getArchive());
        contratDTO.setDateCreation(wrapperContrat.getDateCreation());
        contratDTO.setEmployeId(wrapperContrat.getIdEmploye());
        contratDTO.setSigne(wrapperContrat.getSigne());
        contratDTO.setTitre(wrapperContrat.getTitre());
        contratDTO.setTypeContratId(wrapperContrat.getIdTypeContrat());

        return contratDTO;
    }
}
