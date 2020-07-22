package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EtatFactureMapperTest {

    private EtatFactureMapper etatFactureMapper;

    @BeforeEach
    public void setUp() {
        etatFactureMapper = new EtatFactureMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etatFactureMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etatFactureMapper.fromId(null)).isNull();
    }
}
