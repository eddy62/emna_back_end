package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class NoteDeFraisDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoteDeFraisDTO.class);
        NoteDeFraisDTO noteDeFraisDTO1 = new NoteDeFraisDTO();
        noteDeFraisDTO1.setId(1L);
        NoteDeFraisDTO noteDeFraisDTO2 = new NoteDeFraisDTO();
        assertThat(noteDeFraisDTO1).isNotEqualTo(noteDeFraisDTO2);
        noteDeFraisDTO2.setId(noteDeFraisDTO1.getId());
        assertThat(noteDeFraisDTO1).isEqualTo(noteDeFraisDTO2);
        noteDeFraisDTO2.setId(2L);
        assertThat(noteDeFraisDTO1).isNotEqualTo(noteDeFraisDTO2);
        noteDeFraisDTO1.setId(null);
        assertThat(noteDeFraisDTO1).isNotEqualTo(noteDeFraisDTO2);
    }
}
