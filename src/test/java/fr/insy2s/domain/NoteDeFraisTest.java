package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class NoteDeFraisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoteDeFrais.class);
        NoteDeFrais noteDeFrais1 = new NoteDeFrais();
        noteDeFrais1.setId(1L);
        NoteDeFrais noteDeFrais2 = new NoteDeFrais();
        noteDeFrais2.setId(noteDeFrais1.getId());
        assertThat(noteDeFrais1).isEqualTo(noteDeFrais2);
        noteDeFrais2.setId(2L);
        assertThat(noteDeFrais1).isNotEqualTo(noteDeFrais2);
        noteDeFrais1.setId(null);
        assertThat(noteDeFrais1).isNotEqualTo(noteDeFrais2);
    }
}
