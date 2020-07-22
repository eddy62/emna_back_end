package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class ReleveTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Releve.class);
        Releve releve1 = new Releve();
        releve1.setId(1L);
        Releve releve2 = new Releve();
        releve2.setId(releve1.getId());
        assertThat(releve1).isEqualTo(releve2);
        releve2.setId(2L);
        assertThat(releve1).isNotEqualTo(releve2);
        releve1.setId(null);
        assertThat(releve1).isNotEqualTo(releve2);
    }
}
