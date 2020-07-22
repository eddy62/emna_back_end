package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ReleveMapperTest {

    private ReleveMapper releveMapper;

    @BeforeEach
    public void setUp() {
        releveMapper = new ReleveMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(releveMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(releveMapper.fromId(null)).isNull();
    }
}
