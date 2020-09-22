package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class SaisieArticleDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SaisieArticleDTO.class);
        SaisieArticleDTO saisieArticleDTO1 = new SaisieArticleDTO();
        saisieArticleDTO1.setId(1L);
        SaisieArticleDTO saisieArticleDTO2 = new SaisieArticleDTO();
        assertThat(saisieArticleDTO1).isNotEqualTo(saisieArticleDTO2);
        saisieArticleDTO2.setId(saisieArticleDTO1.getId());
        assertThat(saisieArticleDTO1).isEqualTo(saisieArticleDTO2);
        saisieArticleDTO2.setId(2L);
        assertThat(saisieArticleDTO1).isNotEqualTo(saisieArticleDTO2);
        saisieArticleDTO1.setId(null);
        assertThat(saisieArticleDTO1).isNotEqualTo(saisieArticleDTO2);
    }
}
