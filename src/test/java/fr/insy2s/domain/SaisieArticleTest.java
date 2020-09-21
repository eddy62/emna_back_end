package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class SaisieArticleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SaisieArticle.class);
        SaisieArticle saisieArticle1 = new SaisieArticle();
        saisieArticle1.setId(1L);
        SaisieArticle saisieArticle2 = new SaisieArticle();
        saisieArticle2.setId(saisieArticle1.getId());
        assertThat(saisieArticle1).isEqualTo(saisieArticle2);
        saisieArticle2.setId(2L);
        assertThat(saisieArticle1).isNotEqualTo(saisieArticle2);
        saisieArticle1.setId(null);
        assertThat(saisieArticle1).isNotEqualTo(saisieArticle2);
    }
}
