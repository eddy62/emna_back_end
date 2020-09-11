package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class EtatDepenseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatDepenseDTO.class);
        EtatDepenseDTO etatDepenseDTO1 = new EtatDepenseDTO();
        etatDepenseDTO1.setId(1L);
        EtatDepenseDTO etatDepenseDTO2 = new EtatDepenseDTO();
        assertThat(etatDepenseDTO1).isNotEqualTo(etatDepenseDTO2);
        etatDepenseDTO2.setId(etatDepenseDTO1.getId());
        assertThat(etatDepenseDTO1).isEqualTo(etatDepenseDTO2);
        etatDepenseDTO2.setId(2L);
        assertThat(etatDepenseDTO1).isNotEqualTo(etatDepenseDTO2);
        etatDepenseDTO1.setId(null);
        assertThat(etatDepenseDTO1).isNotEqualTo(etatDepenseDTO2);
    }
}
