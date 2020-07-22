package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClauseMapperTest {

    private ClauseMapper clauseMapper;

    @BeforeEach
    public void setUp() {
        clauseMapper = new ClauseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(clauseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(clauseMapper.fromId(null)).isNull();
    }
}
