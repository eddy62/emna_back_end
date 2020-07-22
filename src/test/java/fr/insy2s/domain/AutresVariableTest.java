package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class AutresVariableTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AutresVariable.class);
        AutresVariable autresVariable1 = new AutresVariable();
        autresVariable1.setId(1L);
        AutresVariable autresVariable2 = new AutresVariable();
        autresVariable2.setId(autresVariable1.getId());
        assertThat(autresVariable1).isEqualTo(autresVariable2);
        autresVariable2.setId(2L);
        assertThat(autresVariable1).isNotEqualTo(autresVariable2);
        autresVariable1.setId(null);
        assertThat(autresVariable1).isNotEqualTo(autresVariable2);
    }
}
