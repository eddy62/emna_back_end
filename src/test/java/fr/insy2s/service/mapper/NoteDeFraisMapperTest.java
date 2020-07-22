package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NoteDeFraisMapperTest {

    private NoteDeFraisMapper noteDeFraisMapper;

    @BeforeEach
    public void setUp() {
        noteDeFraisMapper = new NoteDeFraisMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(noteDeFraisMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(noteDeFraisMapper.fromId(null)).isNull();
    }
}
