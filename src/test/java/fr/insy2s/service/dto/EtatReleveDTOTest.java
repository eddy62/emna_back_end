package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class EtatReleveDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatReleveDTO.class);
        EtatReleveDTO etatReleveDTO1 = new EtatReleveDTO();
        etatReleveDTO1.setId(1L);
        EtatReleveDTO etatReleveDTO2 = new EtatReleveDTO();
        assertThat(etatReleveDTO1).isNotEqualTo(etatReleveDTO2);
        etatReleveDTO2.setId(etatReleveDTO1.getId());
        assertThat(etatReleveDTO1).isEqualTo(etatReleveDTO2);
        etatReleveDTO2.setId(2L);
        assertThat(etatReleveDTO1).isNotEqualTo(etatReleveDTO2);
        etatReleveDTO1.setId(null);
        assertThat(etatReleveDTO1).isNotEqualTo(etatReleveDTO2);
    }
}
