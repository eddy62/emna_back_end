package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class ClientFournisseurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientFournisseur.class);
        ClientFournisseur clientFournisseur1 = new ClientFournisseur();
        clientFournisseur1.setId(1L);
        ClientFournisseur clientFournisseur2 = new ClientFournisseur();
        clientFournisseur2.setId(clientFournisseur1.getId());
        assertThat(clientFournisseur1).isEqualTo(clientFournisseur2);
        clientFournisseur2.setId(2L);
        assertThat(clientFournisseur1).isNotEqualTo(clientFournisseur2);
        clientFournisseur1.setId(null);
        assertThat(clientFournisseur1).isNotEqualTo(clientFournisseur2);
    }
}
