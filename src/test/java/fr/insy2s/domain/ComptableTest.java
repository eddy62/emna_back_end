package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class ComptableTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Comptable.class);
        Comptable comptable1 = new Comptable();
        comptable1.setId(1L);
        Comptable comptable2 = new Comptable();
        comptable2.setId(comptable1.getId());
        assertThat(comptable1).isEqualTo(comptable2);
        comptable2.setId(2L);
        assertThat(comptable1).isNotEqualTo(comptable2);
        comptable1.setId(null);
        assertThat(comptable1).isNotEqualTo(comptable2);
    }
}
