package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class EtatVariablePaieDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatVariablePaieDTO.class);
        EtatVariablePaieDTO etatVariablePaieDTO1 = new EtatVariablePaieDTO();
        etatVariablePaieDTO1.setId(1L);
        EtatVariablePaieDTO etatVariablePaieDTO2 = new EtatVariablePaieDTO();
        assertThat(etatVariablePaieDTO1).isNotEqualTo(etatVariablePaieDTO2);
        etatVariablePaieDTO2.setId(etatVariablePaieDTO1.getId());
        assertThat(etatVariablePaieDTO1).isEqualTo(etatVariablePaieDTO2);
        etatVariablePaieDTO2.setId(2L);
        assertThat(etatVariablePaieDTO1).isNotEqualTo(etatVariablePaieDTO2);
        etatVariablePaieDTO1.setId(null);
        assertThat(etatVariablePaieDTO1).isNotEqualTo(etatVariablePaieDTO2);
    }
}
