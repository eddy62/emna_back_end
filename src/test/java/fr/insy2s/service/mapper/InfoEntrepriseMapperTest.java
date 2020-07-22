package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InfoEntrepriseMapperTest {

    private InfoEntrepriseMapper infoEntrepriseMapper;

    @BeforeEach
    public void setUp() {
        infoEntrepriseMapper = new InfoEntrepriseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(infoEntrepriseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(infoEntrepriseMapper.fromId(null)).isNull();
    }
}
