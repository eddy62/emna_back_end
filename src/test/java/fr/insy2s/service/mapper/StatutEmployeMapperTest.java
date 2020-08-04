package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StatutEmployeMapperTest {

    private StatutEmployeMapper statutEmployeMapper;

    @BeforeEach
    public void setUp() {
        statutEmployeMapper = new StatutEmployeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(statutEmployeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(statutEmployeMapper.fromId(null)).isNull();
    }
}
