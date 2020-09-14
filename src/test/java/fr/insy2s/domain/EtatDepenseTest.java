package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class EtatDepenseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatDepense.class);
        EtatDepense etatDepense1 = new EtatDepense();
        etatDepense1.setId(1L);
        EtatDepense etatDepense2 = new EtatDepense();
        etatDepense2.setId(etatDepense1.getId());
        assertThat(etatDepense1).isEqualTo(etatDepense2);
        etatDepense2.setId(2L);
        assertThat(etatDepense1).isNotEqualTo(etatDepense2);
        etatDepense1.setId(null);
        assertThat(etatDepense1).isNotEqualTo(etatDepense2);
    }
}
