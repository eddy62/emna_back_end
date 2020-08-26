package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class AvanceRappelSalaireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvanceRappelSalaire.class);
        AvanceRappelSalaire avanceRappelSalaire1 = new AvanceRappelSalaire();
        avanceRappelSalaire1.setId(1L);
        AvanceRappelSalaire avanceRappelSalaire2 = new AvanceRappelSalaire();
        avanceRappelSalaire2.setId(avanceRappelSalaire1.getId());
        assertThat(avanceRappelSalaire1).isEqualTo(avanceRappelSalaire2);
        avanceRappelSalaire2.setId(2L);
        assertThat(avanceRappelSalaire1).isNotEqualTo(avanceRappelSalaire2);
        avanceRappelSalaire1.setId(null);
        assertThat(avanceRappelSalaire1).isNotEqualTo(avanceRappelSalaire2);
    }
}
