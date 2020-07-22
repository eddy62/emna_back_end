package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class TypeAbsenceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeAbsenceDTO.class);
        TypeAbsenceDTO typeAbsenceDTO1 = new TypeAbsenceDTO();
        typeAbsenceDTO1.setId(1L);
        TypeAbsenceDTO typeAbsenceDTO2 = new TypeAbsenceDTO();
        assertThat(typeAbsenceDTO1).isNotEqualTo(typeAbsenceDTO2);
        typeAbsenceDTO2.setId(typeAbsenceDTO1.getId());
        assertThat(typeAbsenceDTO1).isEqualTo(typeAbsenceDTO2);
        typeAbsenceDTO2.setId(2L);
        assertThat(typeAbsenceDTO1).isNotEqualTo(typeAbsenceDTO2);
        typeAbsenceDTO1.setId(null);
        assertThat(typeAbsenceDTO1).isNotEqualTo(typeAbsenceDTO2);
    }
}
