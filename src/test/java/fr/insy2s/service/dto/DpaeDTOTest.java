package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class DpaeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DpaeDTO.class);
        DpaeDTO dpaeDTO1 = new DpaeDTO();
        dpaeDTO1.setId(1L);
        DpaeDTO dpaeDTO2 = new DpaeDTO();
        assertThat(dpaeDTO1).isNotEqualTo(dpaeDTO2);
        dpaeDTO2.setId(dpaeDTO1.getId());
        assertThat(dpaeDTO1).isEqualTo(dpaeDTO2);
        dpaeDTO2.setId(2L);
        assertThat(dpaeDTO1).isNotEqualTo(dpaeDTO2);
        dpaeDTO1.setId(null);
        assertThat(dpaeDTO1).isNotEqualTo(dpaeDTO2);
    }
}
