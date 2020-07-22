package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class FichePaieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FichePaie.class);
        FichePaie fichePaie1 = new FichePaie();
        fichePaie1.setId(1L);
        FichePaie fichePaie2 = new FichePaie();
        fichePaie2.setId(fichePaie1.getId());
        assertThat(fichePaie1).isEqualTo(fichePaie2);
        fichePaie2.setId(2L);
        assertThat(fichePaie1).isNotEqualTo(fichePaie2);
        fichePaie1.setId(null);
        assertThat(fichePaie1).isNotEqualTo(fichePaie2);
    }
}
