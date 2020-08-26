package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class DpaeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dpae.class);
        Dpae dpae1 = new Dpae();
        dpae1.setId(1L);
        Dpae dpae2 = new Dpae();
        dpae2.setId(dpae1.getId());
        assertThat(dpae1).isEqualTo(dpae2);
        dpae2.setId(2L);
        assertThat(dpae1).isNotEqualTo(dpae2);
        dpae1.setId(null);
        assertThat(dpae1).isNotEqualTo(dpae2);
    }
}
