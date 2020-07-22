package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypePrimeMapperTest {

    private TypePrimeMapper typePrimeMapper;

    @BeforeEach
    public void setUp() {
        typePrimeMapper = new TypePrimeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typePrimeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typePrimeMapper.fromId(null)).isNull();
    }
}
