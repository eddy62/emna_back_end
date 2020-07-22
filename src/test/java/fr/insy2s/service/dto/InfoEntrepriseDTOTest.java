package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class InfoEntrepriseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InfoEntrepriseDTO.class);
        InfoEntrepriseDTO infoEntrepriseDTO1 = new InfoEntrepriseDTO();
        infoEntrepriseDTO1.setId(1L);
        InfoEntrepriseDTO infoEntrepriseDTO2 = new InfoEntrepriseDTO();
        assertThat(infoEntrepriseDTO1).isNotEqualTo(infoEntrepriseDTO2);
        infoEntrepriseDTO2.setId(infoEntrepriseDTO1.getId());
        assertThat(infoEntrepriseDTO1).isEqualTo(infoEntrepriseDTO2);
        infoEntrepriseDTO2.setId(2L);
        assertThat(infoEntrepriseDTO1).isNotEqualTo(infoEntrepriseDTO2);
        infoEntrepriseDTO1.setId(null);
        assertThat(infoEntrepriseDTO1).isNotEqualTo(infoEntrepriseDTO2);
    }
}
