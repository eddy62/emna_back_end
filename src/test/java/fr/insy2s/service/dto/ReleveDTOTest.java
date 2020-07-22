package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class ReleveDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReleveDTO.class);
        ReleveDTO releveDTO1 = new ReleveDTO();
        releveDTO1.setId(1L);
        ReleveDTO releveDTO2 = new ReleveDTO();
        assertThat(releveDTO1).isNotEqualTo(releveDTO2);
        releveDTO2.setId(releveDTO1.getId());
        assertThat(releveDTO1).isEqualTo(releveDTO2);
        releveDTO2.setId(2L);
        assertThat(releveDTO1).isNotEqualTo(releveDTO2);
        releveDTO1.setId(null);
        assertThat(releveDTO1).isNotEqualTo(releveDTO2);
    }
}
