package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HeuresSupplementairesMapperTest {

    private HeuresSupplementairesMapper heuresSupplementairesMapper;

    @BeforeEach
    public void setUp() {
        heuresSupplementairesMapper = new HeuresSupplementairesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(heuresSupplementairesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(heuresSupplementairesMapper.fromId(null)).isNull();
    }
}
