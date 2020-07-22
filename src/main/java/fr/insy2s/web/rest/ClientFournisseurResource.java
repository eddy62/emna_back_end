package fr.insy2s.web.rest;

import fr.insy2s.service.ClientFournisseurService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.ClientFournisseurDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.ClientFournisseur}.
 */
@RestController
@RequestMapping("/api")
public class ClientFournisseurResource {

    private final Logger log = LoggerFactory.getLogger(ClientFournisseurResource.class);

    private static final String ENTITY_NAME = "clientFournisseur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientFournisseurService clientFournisseurService;

    public ClientFournisseurResource(ClientFournisseurService clientFournisseurService) {
        this.clientFournisseurService = clientFournisseurService;
    }

    /**
     * {@code POST  /client-fournisseurs} : Create a new clientFournisseur.
     *
     * @param clientFournisseurDTO the clientFournisseurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clientFournisseurDTO, or with status {@code 400 (Bad Request)} if the clientFournisseur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/client-fournisseurs")
    public ResponseEntity<ClientFournisseurDTO> createClientFournisseur(@RequestBody ClientFournisseurDTO clientFournisseurDTO) throws URISyntaxException {
        log.debug("REST request to save ClientFournisseur : {}", clientFournisseurDTO);
        if (clientFournisseurDTO.getId() != null) {
            throw new BadRequestAlertException("A new clientFournisseur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClientFournisseurDTO result = clientFournisseurService.save(clientFournisseurDTO);
        return ResponseEntity.created(new URI("/api/client-fournisseurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /client-fournisseurs} : Updates an existing clientFournisseur.
     *
     * @param clientFournisseurDTO the clientFournisseurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientFournisseurDTO,
     * or with status {@code 400 (Bad Request)} if the clientFournisseurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clientFournisseurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/client-fournisseurs")
    public ResponseEntity<ClientFournisseurDTO> updateClientFournisseur(@RequestBody ClientFournisseurDTO clientFournisseurDTO) throws URISyntaxException {
        log.debug("REST request to update ClientFournisseur : {}", clientFournisseurDTO);
        if (clientFournisseurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClientFournisseurDTO result = clientFournisseurService.save(clientFournisseurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientFournisseurDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /client-fournisseurs} : get all the clientFournisseurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientFournisseurs in body.
     */
    @GetMapping("/client-fournisseurs")
    public List<ClientFournisseurDTO> getAllClientFournisseurs() {
        log.debug("REST request to get all ClientFournisseurs");
        return clientFournisseurService.findAll();
    }

    /**
     * {@code GET  /client-fournisseurs/:id} : get the "id" clientFournisseur.
     *
     * @param id the id of the clientFournisseurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientFournisseurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/client-fournisseurs/{id}")
    public ResponseEntity<ClientFournisseurDTO> getClientFournisseur(@PathVariable Long id) {
        log.debug("REST request to get ClientFournisseur : {}", id);
        Optional<ClientFournisseurDTO> clientFournisseurDTO = clientFournisseurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clientFournisseurDTO);
    }

    /**
     * {@code DELETE  /client-fournisseurs/:id} : delete the "id" clientFournisseur.
     *
     * @param id the id of the clientFournisseurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/client-fournisseurs/{id}")
    public ResponseEntity<Void> deleteClientFournisseur(@PathVariable Long id) {
        log.debug("REST request to delete ClientFournisseur : {}", id);
        clientFournisseurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
