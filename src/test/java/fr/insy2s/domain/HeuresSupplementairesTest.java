package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class HeuresSupplementairesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HeuresSupplementaires.class);
        HeuresSupplementaires heuresSupplementaires1 = new HeuresSupplementaires();
        heuresSupplementaires1.setId(1L);
        HeuresSupplementaires heuresSupplementaires2 = new HeuresSupplementaires();
        heuresSupplementaires2.setId(heuresSupplementaires1.getId());
        assertThat(heuresSupplementaires1).isEqualTo(heuresSupplementaires2);
        heuresSupplementaires2.setId(2L);
        assertThat(heuresSupplementaires1).isNotEqualTo(heuresSupplementaires2);
        heuresSupplementaires1.setId(null);
        assertThat(heuresSupplementaires1).isNotEqualTo(heuresSupplementaires2);
    }
}
