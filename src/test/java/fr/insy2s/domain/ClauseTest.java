package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class ClauseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Clause.class);
        Clause clause1 = new Clause();
        clause1.setId(1L);
        Clause clause2 = new Clause();
        clause2.setId(clause1.getId());
        assertThat(clause1).isEqualTo(clause2);
        clause2.setId(2L);
        assertThat(clause1).isNotEqualTo(clause2);
        clause1.setId(null);
        assertThat(clause1).isNotEqualTo(clause2);
    }
}
