package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.PrimeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Prime} and its DTO {@link PrimeDTO}.
 */
@Mapper(componentModel = "spring", uses = {TypePrimeMapper.class, EtatVariablePaieMapper.class, EmployeMapper.class})
public interface PrimeMapper extends EntityMapper<PrimeDTO, Prime> {

    @Mapping(source = "typePrime.id", target = "typePrimeId")
    @Mapping(source = "etatVariablePaie.id", target = "etatVariablePaieId")
    @Mapping(source = "employe.id", target = "employeId")
    PrimeDTO toDto(Prime prime);

    @Mapping(source = "typePrimeId", target = "typePrime")
    @Mapping(source = "etatVariablePaieId", target = "etatVariablePaie")
    @Mapping(source = "employeId", target = "employe")
    Prime toEntity(PrimeDTO primeDTO);

    default Prime fromId(Long id) {
        if (id == null) {
            return null;
        }
        Prime prime = new Prime();
        prime.setId(id);
        return prime;
    }
}
