package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class StatutEmployeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatutEmployeDTO.class);
        StatutEmployeDTO statutEmployeDTO1 = new StatutEmployeDTO();
        statutEmployeDTO1.setId(1L);
        StatutEmployeDTO statutEmployeDTO2 = new StatutEmployeDTO();
        assertThat(statutEmployeDTO1).isNotEqualTo(statutEmployeDTO2);
        statutEmployeDTO2.setId(statutEmployeDTO1.getId());
        assertThat(statutEmployeDTO1).isEqualTo(statutEmployeDTO2);
        statutEmployeDTO2.setId(2L);
        assertThat(statutEmployeDTO1).isNotEqualTo(statutEmployeDTO2);
        statutEmployeDTO1.setId(null);
        assertThat(statutEmployeDTO1).isNotEqualTo(statutEmployeDTO2);
    }
}
