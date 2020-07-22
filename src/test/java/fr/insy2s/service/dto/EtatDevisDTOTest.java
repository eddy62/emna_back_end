package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class EtatDevisDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatDevisDTO.class);
        EtatDevisDTO etatDevisDTO1 = new EtatDevisDTO();
        etatDevisDTO1.setId(1L);
        EtatDevisDTO etatDevisDTO2 = new EtatDevisDTO();
        assertThat(etatDevisDTO1).isNotEqualTo(etatDevisDTO2);
        etatDevisDTO2.setId(etatDevisDTO1.getId());
        assertThat(etatDevisDTO1).isEqualTo(etatDevisDTO2);
        etatDevisDTO2.setId(2L);
        assertThat(etatDevisDTO1).isNotEqualTo(etatDevisDTO2);
        etatDevisDTO1.setId(null);
        assertThat(etatDevisDTO1).isNotEqualTo(etatDevisDTO2);
    }
}
