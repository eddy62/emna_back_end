package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PrimeMapperTest {

    private PrimeMapper primeMapper;

    @BeforeEach
    public void setUp() {
        primeMapper = new PrimeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(primeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(primeMapper.fromId(null)).isNull();
    }
}
