package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LigneProduitMapperTest {

    private LigneProduitMapper ligneProduitMapper;

    @BeforeEach
    public void setUp() {
        ligneProduitMapper = new LigneProduitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ligneProduitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ligneProduitMapper.fromId(null)).isNull();
    }
}
