package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class LigneProduitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LigneProduit.class);
        LigneProduit ligneProduit1 = new LigneProduit();
        ligneProduit1.setId(1L);
        LigneProduit ligneProduit2 = new LigneProduit();
        ligneProduit2.setId(ligneProduit1.getId());
        assertThat(ligneProduit1).isEqualTo(ligneProduit2);
        ligneProduit2.setId(2L);
        assertThat(ligneProduit1).isNotEqualTo(ligneProduit2);
        ligneProduit1.setId(null);
        assertThat(ligneProduit1).isNotEqualTo(ligneProduit2);
    }
}
