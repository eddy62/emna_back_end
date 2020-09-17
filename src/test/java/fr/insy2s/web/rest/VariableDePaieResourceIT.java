package fr.insy2s.web.rest;

import fr.insy2s.EmnaBackEndApp;
import fr.insy2s.service.*;
import fr.insy2s.service.mapper.WrapperAbsenceMapper;
import fr.insy2s.service.mapper.WrapperPrimeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the VariableDePaieResource REST controller.
 *
 * @see VariableDePaieResource
 */
@SpringBootTest(classes = EmnaBackEndApp.class)
public class VariableDePaieResourceIT {

    private MockMvc restMockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        VariableDePaieService variableDePaieService = null;
        AbsenceService absenceService = null;
        WrapperAbsenceMapper wrapperAbsenceMapper = null;
        AutresVariableService autresVariableService = null;
        AvanceRappelSalaireService avanceRappelSalaireService = null;
        HeuresSupplementairesService heuresSupplementairesService = null;
        NoteDeFraisService noteDeFraisService = null;
        PrimeService primeService = null;
        WrapperPrimeMapper wrapperPrimeMapper = null;
        VariableDePaieResource variableDePaieResource = new VariableDePaieResource(variableDePaieService, absenceService, wrapperAbsenceMapper, autresVariableService, avanceRappelSalaireService, heuresSupplementairesService, noteDeFraisService, primeService, wrapperPrimeMapper);
        restMockMvc = MockMvcBuilders
            .standaloneSetup(variableDePaieResource)
            .build();
    }

    /**
     * Test defaultAction
     */
    @Test
    public void testDefaultAction() throws Exception {
        restMockMvc.perform(get("/api/variable-de-paie-resource/default-action"))
            .andExpect(status().isOk());
    }
}
