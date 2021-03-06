package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class SocieteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocieteDTO.class);
        SocieteDTO societeDTO1 = new SocieteDTO();
        societeDTO1.setId(1L);
        SocieteDTO societeDTO2 = new SocieteDTO();
        assertThat(societeDTO1).isNotEqualTo(societeDTO2);
        societeDTO2.setId(societeDTO1.getId());
        assertThat(societeDTO1).isEqualTo(societeDTO2);
        societeDTO2.setId(2L);
        assertThat(societeDTO1).isNotEqualTo(societeDTO2);
        societeDTO1.setId(null);
        assertThat(societeDTO1).isNotEqualTo(societeDTO2);
    }
}
