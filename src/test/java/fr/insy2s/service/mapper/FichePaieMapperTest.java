package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FichePaieMapperTest {

    private FichePaieMapper fichePaieMapper;

    @BeforeEach
    public void setUp() {
        fichePaieMapper = new FichePaieMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fichePaieMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fichePaieMapper.fromId(null)).isNull();
    }
}
