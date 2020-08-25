package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DpaeMapperTest {

    private DpaeMapper dpaeMapper;

    @BeforeEach
    public void setUp() {
        dpaeMapper = new DpaeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dpaeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dpaeMapper.fromId(null)).isNull();
    }
}
