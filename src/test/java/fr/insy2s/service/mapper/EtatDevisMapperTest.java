package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EtatDevisMapperTest {

    private EtatDevisMapper etatDevisMapper;

    @BeforeEach
    public void setUp() {
        etatDevisMapper = new EtatDevisMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etatDevisMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etatDevisMapper.fromId(null)).isNull();
    }
}
