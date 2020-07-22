package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class AvenantDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvenantDTO.class);
        AvenantDTO avenantDTO1 = new AvenantDTO();
        avenantDTO1.setId(1L);
        AvenantDTO avenantDTO2 = new AvenantDTO();
        assertThat(avenantDTO1).isNotEqualTo(avenantDTO2);
        avenantDTO2.setId(avenantDTO1.getId());
        assertThat(avenantDTO1).isEqualTo(avenantDTO2);
        avenantDTO2.setId(2L);
        assertThat(avenantDTO1).isNotEqualTo(avenantDTO2);
        avenantDTO1.setId(null);
        assertThat(avenantDTO1).isNotEqualTo(avenantDTO2);
    }
}
