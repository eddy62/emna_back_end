package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class EtatDevisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatDevis.class);
        EtatDevis etatDevis1 = new EtatDevis();
        etatDevis1.setId(1L);
        EtatDevis etatDevis2 = new EtatDevis();
        etatDevis2.setId(etatDevis1.getId());
        assertThat(etatDevis1).isEqualTo(etatDevis2);
        etatDevis2.setId(2L);
        assertThat(etatDevis1).isNotEqualTo(etatDevis2);
        etatDevis1.setId(null);
        assertThat(etatDevis1).isNotEqualTo(etatDevis2);
    }
}
