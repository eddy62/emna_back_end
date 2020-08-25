package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeContratMapperTest {

    private TypeContratMapper typeContratMapper;

    @BeforeEach
    public void setUp() {
        typeContratMapper = new TypeContratMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeContratMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeContratMapper.fromId(null)).isNull();
    }
}
