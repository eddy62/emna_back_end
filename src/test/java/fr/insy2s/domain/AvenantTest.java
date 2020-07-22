package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class AvenantTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Avenant.class);
        Avenant avenant1 = new Avenant();
        avenant1.setId(1L);
        Avenant avenant2 = new Avenant();
        avenant2.setId(avenant1.getId());
        assertThat(avenant1).isEqualTo(avenant2);
        avenant2.setId(2L);
        assertThat(avenant1).isNotEqualTo(avenant2);
        avenant1.setId(null);
        assertThat(avenant1).isNotEqualTo(avenant2);
    }
}
