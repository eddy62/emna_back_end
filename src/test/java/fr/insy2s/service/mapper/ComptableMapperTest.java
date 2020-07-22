package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ComptableMapperTest {

    private ComptableMapper comptableMapper;

    @BeforeEach
    public void setUp() {
        comptableMapper = new ComptableMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(comptableMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(comptableMapper.fromId(null)).isNull();
    }
}
