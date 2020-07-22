package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class TypePrimeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypePrimeDTO.class);
        TypePrimeDTO typePrimeDTO1 = new TypePrimeDTO();
        typePrimeDTO1.setId(1L);
        TypePrimeDTO typePrimeDTO2 = new TypePrimeDTO();
        assertThat(typePrimeDTO1).isNotEqualTo(typePrimeDTO2);
        typePrimeDTO2.setId(typePrimeDTO1.getId());
        assertThat(typePrimeDTO1).isEqualTo(typePrimeDTO2);
        typePrimeDTO2.setId(2L);
        assertThat(typePrimeDTO1).isNotEqualTo(typePrimeDTO2);
        typePrimeDTO1.setId(null);
        assertThat(typePrimeDTO1).isNotEqualTo(typePrimeDTO2);
    }
}
