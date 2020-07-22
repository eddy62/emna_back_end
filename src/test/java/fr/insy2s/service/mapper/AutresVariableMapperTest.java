package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AutresVariableMapperTest {

    private AutresVariableMapper autresVariableMapper;

    @BeforeEach
    public void setUp() {
        autresVariableMapper = new AutresVariableMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(autresVariableMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(autresVariableMapper.fromId(null)).isNull();
    }
}
