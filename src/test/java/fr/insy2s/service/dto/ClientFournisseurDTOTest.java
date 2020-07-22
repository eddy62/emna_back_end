package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class ClientFournisseurDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientFournisseurDTO.class);
        ClientFournisseurDTO clientFournisseurDTO1 = new ClientFournisseurDTO();
        clientFournisseurDTO1.setId(1L);
        ClientFournisseurDTO clientFournisseurDTO2 = new ClientFournisseurDTO();
        assertThat(clientFournisseurDTO1).isNotEqualTo(clientFournisseurDTO2);
        clientFournisseurDTO2.setId(clientFournisseurDTO1.getId());
        assertThat(clientFournisseurDTO1).isEqualTo(clientFournisseurDTO2);
        clientFournisseurDTO2.setId(2L);
        assertThat(clientFournisseurDTO1).isNotEqualTo(clientFournisseurDTO2);
        clientFournisseurDTO1.setId(null);
        assertThat(clientFournisseurDTO1).isNotEqualTo(clientFournisseurDTO2);
    }
}
