package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EtatReleveMapperTest {

    private EtatReleveMapper etatReleveMapper;

    @BeforeEach
    public void setUp() {
        etatReleveMapper = new EtatReleveMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etatReleveMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etatReleveMapper.fromId(null)).isNull();
    }
}
