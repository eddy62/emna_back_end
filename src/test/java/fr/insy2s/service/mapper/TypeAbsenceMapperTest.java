package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeAbsenceMapperTest {

    private TypeAbsenceMapper typeAbsenceMapper;

    @BeforeEach
    public void setUp() {
        typeAbsenceMapper = new TypeAbsenceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeAbsenceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeAbsenceMapper.fromId(null)).isNull();
    }
}
