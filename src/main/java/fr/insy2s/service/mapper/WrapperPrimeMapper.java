package fr.insy2s.service.mapper;

import fr.insy2s.service.dto.PrimeDTO;
import fr.insy2s.service.dto.TypePrimeDTO;
import fr.insy2s.utils.wrapper.WrapperPrime;
import org.springframework.stereotype.Service;

/**
 * Mapper for the entity Prime,  and the DTO WrapperPrime.
 *
 */
@Service
public class WrapperPrimeMapper {

    /**
     * Builder WrapperPrime
     *
     * @param primeDTO
     * @param typePrimeDTO
     *
     * @return wrapperPrime
     */
    public WrapperPrime builderWrapperPrime(final PrimeDTO primeDTO, final TypePrimeDTO typePrimeDTO){
        final WrapperPrime wrapperPrime = new WrapperPrime();
        // Prime
        wrapperPrime.setId(primeDTO.getId());
        wrapperPrime.setMontant(primeDTO.getMontant());
        wrapperPrime.setAnnee(primeDTO.getAnnee());
        wrapperPrime.setMois(primeDTO.getMois());
        wrapperPrime.setEmployeId(primeDTO.getEmployeId());
        wrapperPrime.setEtatVariablePaieId(primeDTO.getEtatVariablePaieId());
        wrapperPrime.setTypePrimeId(primeDTO.getTypePrimeId());
        // TypePrime
        wrapperPrime.setIdTypePrime(typePrimeDTO.getId());
        wrapperPrime.setCodeRef(typePrimeDTO.getCodeRef());
        wrapperPrime.setIntitule(typePrimeDTO.getIntitule());
        // return
        return wrapperPrime;
    }

    /**
     * Mappe WrapperPrime to PrimeDto
     *
     * @param wrapperPrime
     *
     * @return primeDTO
     */
    public PrimeDTO toPrimeDTO(final WrapperPrime wrapperPrime){
        PrimeDTO primeDTO = new PrimeDTO();

        primeDTO.setId(wrapperPrime.getId());
        primeDTO.setMontant(wrapperPrime.getMontant());
        primeDTO.setAnnee(wrapperPrime.getAnnee());
        primeDTO.setMois(wrapperPrime.getMois());
        primeDTO.setEmployeId(wrapperPrime.getEmployeId());
        primeDTO.setEtatVariablePaieId(wrapperPrime.getEtatVariablePaieId());
        primeDTO.setTypePrimeId(wrapperPrime.getTypePrimeId());

        return primeDTO;
    }

    /**
     * Mappe WrapperPrime to TypePrimeDto
     *
     * @param wrapperPrime
     *
     * @return typePrimeDTO
     */    public TypePrimeDTO toTypePrimeDTO(final WrapperPrime wrapperPrime){
        TypePrimeDTO typePrimeDTO = new TypePrimeDTO();

        typePrimeDTO.setId(wrapperPrime.getIdTypePrime());
        typePrimeDTO.setCodeRef(wrapperPrime.getCodeRef());
        typePrimeDTO.setIntitule(wrapperPrime.getIntitule());

        return typePrimeDTO;
    }

}
