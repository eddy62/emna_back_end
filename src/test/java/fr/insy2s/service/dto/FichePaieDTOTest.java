package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class FichePaieDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FichePaieDTO.class);
        FichePaieDTO fichePaieDTO1 = new FichePaieDTO();
        fichePaieDTO1.setId(1L);
        FichePaieDTO fichePaieDTO2 = new FichePaieDTO();
        assertThat(fichePaieDTO1).isNotEqualTo(fichePaieDTO2);
        fichePaieDTO2.setId(fichePaieDTO1.getId());
        assertThat(fichePaieDTO1).isEqualTo(fichePaieDTO2);
        fichePaieDTO2.setId(2L);
        assertThat(fichePaieDTO1).isNotEqualTo(fichePaieDTO2);
        fichePaieDTO1.setId(null);
        assertThat(fichePaieDTO1).isNotEqualTo(fichePaieDTO2);
    }
}
