package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class HeuresSupplementairesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HeuresSupplementairesDTO.class);
        HeuresSupplementairesDTO heuresSupplementairesDTO1 = new HeuresSupplementairesDTO();
        heuresSupplementairesDTO1.setId(1L);
        HeuresSupplementairesDTO heuresSupplementairesDTO2 = new HeuresSupplementairesDTO();
        assertThat(heuresSupplementairesDTO1).isNotEqualTo(heuresSupplementairesDTO2);
        heuresSupplementairesDTO2.setId(heuresSupplementairesDTO1.getId());
        assertThat(heuresSupplementairesDTO1).isEqualTo(heuresSupplementairesDTO2);
        heuresSupplementairesDTO2.setId(2L);
        assertThat(heuresSupplementairesDTO1).isNotEqualTo(heuresSupplementairesDTO2);
        heuresSupplementairesDTO1.setId(null);
        assertThat(heuresSupplementairesDTO1).isNotEqualTo(heuresSupplementairesDTO2);
    }
}
