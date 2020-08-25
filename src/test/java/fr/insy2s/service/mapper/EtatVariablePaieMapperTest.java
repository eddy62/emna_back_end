package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EtatVariablePaieMapperTest {

    private EtatVariablePaieMapper etatVariablePaieMapper;

    @BeforeEach
    public void setUp() {
        etatVariablePaieMapper = new EtatVariablePaieMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etatVariablePaieMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etatVariablePaieMapper.fromId(null)).isNull();
    }
}
