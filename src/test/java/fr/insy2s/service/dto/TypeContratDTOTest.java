package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class TypeContratDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeContratDTO.class);
        TypeContratDTO typeContratDTO1 = new TypeContratDTO();
        typeContratDTO1.setId(1L);
        TypeContratDTO typeContratDTO2 = new TypeContratDTO();
        assertThat(typeContratDTO1).isNotEqualTo(typeContratDTO2);
        typeContratDTO2.setId(typeContratDTO1.getId());
        assertThat(typeContratDTO1).isEqualTo(typeContratDTO2);
        typeContratDTO2.setId(2L);
        assertThat(typeContratDTO1).isNotEqualTo(typeContratDTO2);
        typeContratDTO1.setId(null);
        assertThat(typeContratDTO1).isNotEqualTo(typeContratDTO2);
    }
}
