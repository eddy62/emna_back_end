package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class InfoEntrepriseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InfoEntreprise.class);
        InfoEntreprise infoEntreprise1 = new InfoEntreprise();
        infoEntreprise1.setId(1L);
        InfoEntreprise infoEntreprise2 = new InfoEntreprise();
        infoEntreprise2.setId(infoEntreprise1.getId());
        assertThat(infoEntreprise1).isEqualTo(infoEntreprise2);
        infoEntreprise2.setId(2L);
        assertThat(infoEntreprise1).isNotEqualTo(infoEntreprise2);
        infoEntreprise1.setId(null);
        assertThat(infoEntreprise1).isNotEqualTo(infoEntreprise2);
    }
}
