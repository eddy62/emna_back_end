package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AvanceRappelSalaireMapperTest {

    private AvanceRappelSalaireMapper avanceRappelSalaireMapper;

    @BeforeEach
    public void setUp() {
        avanceRappelSalaireMapper = new AvanceRappelSalaireMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(avanceRappelSalaireMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(avanceRappelSalaireMapper.fromId(null)).isNull();
    }
}
