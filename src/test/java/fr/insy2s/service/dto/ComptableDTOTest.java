package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class ComptableDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComptableDTO.class);
        ComptableDTO comptableDTO1 = new ComptableDTO();
        comptableDTO1.setId(1L);
        ComptableDTO comptableDTO2 = new ComptableDTO();
        assertThat(comptableDTO1).isNotEqualTo(comptableDTO2);
        comptableDTO2.setId(comptableDTO1.getId());
        assertThat(comptableDTO1).isEqualTo(comptableDTO2);
        comptableDTO2.setId(2L);
        assertThat(comptableDTO1).isNotEqualTo(comptableDTO2);
        comptableDTO1.setId(null);
        assertThat(comptableDTO1).isNotEqualTo(comptableDTO2);
    }
}
