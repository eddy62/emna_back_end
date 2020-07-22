package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class EtatReleveTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatReleve.class);
        EtatReleve etatReleve1 = new EtatReleve();
        etatReleve1.setId(1L);
        EtatReleve etatReleve2 = new EtatReleve();
        etatReleve2.setId(etatReleve1.getId());
        assertThat(etatReleve1).isEqualTo(etatReleve2);
        etatReleve2.setId(2L);
        assertThat(etatReleve1).isNotEqualTo(etatReleve2);
        etatReleve1.setId(null);
        assertThat(etatReleve1).isNotEqualTo(etatReleve2);
    }
}
