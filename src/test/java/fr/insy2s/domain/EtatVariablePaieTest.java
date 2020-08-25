package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class EtatVariablePaieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatVariablePaie.class);
        EtatVariablePaie etatVariablePaie1 = new EtatVariablePaie();
        etatVariablePaie1.setId(1L);
        EtatVariablePaie etatVariablePaie2 = new EtatVariablePaie();
        etatVariablePaie2.setId(etatVariablePaie1.getId());
        assertThat(etatVariablePaie1).isEqualTo(etatVariablePaie2);
        etatVariablePaie2.setId(2L);
        assertThat(etatVariablePaie1).isNotEqualTo(etatVariablePaie2);
        etatVariablePaie1.setId(null);
        assertThat(etatVariablePaie1).isNotEqualTo(etatVariablePaie2);
    }
}
