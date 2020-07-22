package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SocieteMapperTest {

    private SocieteMapper societeMapper;

    @BeforeEach
    public void setUp() {
        societeMapper = new SocieteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(societeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(societeMapper.fromId(null)).isNull();
    }
}
