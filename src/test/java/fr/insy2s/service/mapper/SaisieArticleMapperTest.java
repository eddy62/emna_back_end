package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SaisieArticleMapperTest {

    private SaisieArticleMapper saisieArticleMapper;

    @BeforeEach
    public void setUp() {
        saisieArticleMapper = new SaisieArticleMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(saisieArticleMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(saisieArticleMapper.fromId(null)).isNull();
    }
}
