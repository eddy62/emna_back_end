package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientFournisseurMapperTest {

    private ClientFournisseurMapper clientFournisseurMapper;

    @BeforeEach
    public void setUp() {
        clientFournisseurMapper = new ClientFournisseurMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(clientFournisseurMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(clientFournisseurMapper.fromId(null)).isNull();
    }
}
