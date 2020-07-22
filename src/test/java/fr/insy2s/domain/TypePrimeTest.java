package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class TypePrimeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypePrime.class);
        TypePrime typePrime1 = new TypePrime();
        typePrime1.setId(1L);
        TypePrime typePrime2 = new TypePrime();
        typePrime2.setId(typePrime1.getId());
        assertThat(typePrime1).isEqualTo(typePrime2);
        typePrime2.setId(2L);
        assertThat(typePrime1).isNotEqualTo(typePrime2);
        typePrime1.setId(null);
        assertThat(typePrime1).isNotEqualTo(typePrime2);
    }
}
