package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class LigneProduitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LigneProduitDTO.class);
        LigneProduitDTO ligneProduitDTO1 = new LigneProduitDTO();
        ligneProduitDTO1.setId(1L);
        LigneProduitDTO ligneProduitDTO2 = new LigneProduitDTO();
        assertThat(ligneProduitDTO1).isNotEqualTo(ligneProduitDTO2);
        ligneProduitDTO2.setId(ligneProduitDTO1.getId());
        assertThat(ligneProduitDTO1).isEqualTo(ligneProduitDTO2);
        ligneProduitDTO2.setId(2L);
        assertThat(ligneProduitDTO1).isNotEqualTo(ligneProduitDTO2);
        ligneProduitDTO1.setId(null);
        assertThat(ligneProduitDTO1).isNotEqualTo(ligneProduitDTO2);
    }
}
