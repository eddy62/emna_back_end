package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AvenantMapperTest {

    private AvenantMapper avenantMapper;

    @BeforeEach
    public void setUp() {
        avenantMapper = new AvenantMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(avenantMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(avenantMapper.fromId(null)).isNull();
    }
}
