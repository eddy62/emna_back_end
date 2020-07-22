package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class ClauseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClauseDTO.class);
        ClauseDTO clauseDTO1 = new ClauseDTO();
        clauseDTO1.setId(1L);
        ClauseDTO clauseDTO2 = new ClauseDTO();
        assertThat(clauseDTO1).isNotEqualTo(clauseDTO2);
        clauseDTO2.setId(clauseDTO1.getId());
        assertThat(clauseDTO1).isEqualTo(clauseDTO2);
        clauseDTO2.setId(2L);
        assertThat(clauseDTO1).isNotEqualTo(clauseDTO2);
        clauseDTO1.setId(null);
        assertThat(clauseDTO1).isNotEqualTo(clauseDTO2);
    }
}
