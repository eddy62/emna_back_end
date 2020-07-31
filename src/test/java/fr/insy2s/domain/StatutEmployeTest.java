package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class StatutEmployeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatutEmploye.class);
        StatutEmploye statutEmploye1 = new StatutEmploye();
        statutEmploye1.setId(1L);
        StatutEmploye statutEmploye2 = new StatutEmploye();
        statutEmploye2.setId(statutEmploye1.getId());
        assertThat(statutEmploye1).isEqualTo(statutEmploye2);
        statutEmploye2.setId(2L);
        assertThat(statutEmploye1).isNotEqualTo(statutEmploye2);
        statutEmploye1.setId(null);
        assertThat(statutEmploye1).isNotEqualTo(statutEmploye2);
    }
}
