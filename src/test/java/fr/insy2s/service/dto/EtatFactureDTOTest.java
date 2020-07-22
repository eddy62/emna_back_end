package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class EtatFactureDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatFactureDTO.class);
        EtatFactureDTO etatFactureDTO1 = new EtatFactureDTO();
        etatFactureDTO1.setId(1L);
        EtatFactureDTO etatFactureDTO2 = new EtatFactureDTO();
        assertThat(etatFactureDTO1).isNotEqualTo(etatFactureDTO2);
        etatFactureDTO2.setId(etatFactureDTO1.getId());
        assertThat(etatFactureDTO1).isEqualTo(etatFactureDTO2);
        etatFactureDTO2.setId(2L);
        assertThat(etatFactureDTO1).isNotEqualTo(etatFactureDTO2);
        etatFactureDTO1.setId(null);
        assertThat(etatFactureDTO1).isNotEqualTo(etatFactureDTO2);
    }
}
