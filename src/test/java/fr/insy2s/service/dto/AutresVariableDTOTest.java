package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class AutresVariableDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AutresVariableDTO.class);
        AutresVariableDTO autresVariableDTO1 = new AutresVariableDTO();
        autresVariableDTO1.setId(1L);
        AutresVariableDTO autresVariableDTO2 = new AutresVariableDTO();
        assertThat(autresVariableDTO1).isNotEqualTo(autresVariableDTO2);
        autresVariableDTO2.setId(autresVariableDTO1.getId());
        assertThat(autresVariableDTO1).isEqualTo(autresVariableDTO2);
        autresVariableDTO2.setId(2L);
        assertThat(autresVariableDTO1).isNotEqualTo(autresVariableDTO2);
        autresVariableDTO1.setId(null);
        assertThat(autresVariableDTO1).isNotEqualTo(autresVariableDTO2);
    }
}
