package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class AvanceRappelSalaireDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvanceRappelSalaireDTO.class);
        AvanceRappelSalaireDTO avanceRappelSalaireDTO1 = new AvanceRappelSalaireDTO();
        avanceRappelSalaireDTO1.setId(1L);
        AvanceRappelSalaireDTO avanceRappelSalaireDTO2 = new AvanceRappelSalaireDTO();
        assertThat(avanceRappelSalaireDTO1).isNotEqualTo(avanceRappelSalaireDTO2);
        avanceRappelSalaireDTO2.setId(avanceRappelSalaireDTO1.getId());
        assertThat(avanceRappelSalaireDTO1).isEqualTo(avanceRappelSalaireDTO2);
        avanceRappelSalaireDTO2.setId(2L);
        assertThat(avanceRappelSalaireDTO1).isNotEqualTo(avanceRappelSalaireDTO2);
        avanceRappelSalaireDTO1.setId(null);
        assertThat(avanceRappelSalaireDTO1).isNotEqualTo(avanceRappelSalaireDTO2);
    }
}
