package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.TypePrimeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypePrime} and its DTO {@link TypePrimeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypePrimeMapper extends EntityMapper<TypePrimeDTO, TypePrime> {



    default TypePrime fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypePrime typePrime = new TypePrime();
        typePrime.setId(id);
        return typePrime;
    }
}
