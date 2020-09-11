package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EtatDepenseMapperTest {

    private EtatDepenseMapper etatDepenseMapper;

    @BeforeEach
    public void setUp() {
        etatDepenseMapper = new EtatDepenseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etatDepenseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etatDepenseMapper.fromId(null)).isNull();
    }
}
