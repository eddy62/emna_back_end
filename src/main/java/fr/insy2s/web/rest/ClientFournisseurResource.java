package fr.insy2s.web.rest;

import fr.insy2s.security.AuthoritiesConstants;
import fr.insy2s.service.ClientFournisseurService;
import fr.insy2s.service.SocieteService;
import fr.insy2s.utils.wrapper.WrapperClientFournisseur;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.ClientFournisseurDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
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

    private final SocieteService societeService;

    public ClientFournisseurResource(ClientFournisseurService clientFournisseurService, SocieteService societeService) {
        this.clientFournisseurService = clientFournisseurService;
        this.societeService = societeService;
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
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")"+" || hasAuthority(\"" + AuthoritiesConstants.SOCIETY +"\")")
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

    /**
     * {@code POST  /client-fournisseurs/new} : Create a new clientFournisseur.
     *
     * @param clientFournisseur the wrapperclientFournisseur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wrapperclientFournisseur, or with status {@code 400 (Bad Request)} if the clientFournisseur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/client-fournisseurs/new")
    public ResponseEntity<ClientFournisseurDTO> create(@RequestBody WrapperClientFournisseur  clientFournisseur) throws URISyntaxException {
        log.debug("REST request to save ClientFournisseur : {}", clientFournisseur);
        if (clientFournisseur.getId() != null) {
            throw new BadRequestAlertException("A new clientFournisseur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClientFournisseurDTO result = clientFournisseurService.saveWrapperClientFournisseur(clientFournisseur);
        return ResponseEntity.created(new URI("/api/client-fournisseurs/new" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /client-fournisseurs/wrapper/:id} : get  the "id" clientFournisseurWrapper.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientFournisseursWrapper in body.or with status {@code 404 (Not Found)}
     */
    @GetMapping("/client-fournisseurs/wrapper/{id}")
    public ResponseEntity<WrapperClientFournisseur> getClientFournisseursWrapper(@PathVariable Long id) {
        Optional<WrapperClientFournisseur> clientFournisseur = clientFournisseurService.getClientById(id);
        return ResponseUtil.wrapOrNotFound(clientFournisseur);
    }


    /**
     * {@code GET  /client-fournisseurs/wrapper} : get all the clientFournisseursWrapper.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientFournisseursWrapper in body.
     */
    @GetMapping("/client-fournisseurs/societe/{id}")
    public List<WrapperClientFournisseur> getAllWrapperClientFournisseurs(@PathVariable Long id) {
        log.debug("REST request to get all ClientFournisseurs");
        return clientFournisseurService.findAllBySocieteId(id);
    }



    /**
     * {@code PUT  /client-fournisseurs} : Updates an existing clientFournisseur.
     *
     * @param wrapperClientFournisseur the clientFournisseurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wrapperClientFournisseur,
     * or with status {@code 400 (Bad Request)} if the wrapperClientFournisseur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wrapperClientFournisseur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/client-fournisseurs/wrapper")
    public ResponseEntity<WrapperClientFournisseur> updateClientFournisseur(@RequestBody WrapperClientFournisseur wrapperClientFournisseur, Principal currentUser) throws URISyntaxException {
    	Long userId = Long.valueOf((String) ((UsernamePasswordAuthenticationToken) currentUser).getPrincipal());
        log.debug("REST request to update WrapperClientFournisseur : {}", wrapperClientFournisseur);
        if (wrapperClientFournisseur.getId() == null || !clientFournisseurService.connectedUserIsSociete() || !clientFournisseurService.verfyIdOfUserConnected(userId)) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        verificationsClientFournisseur(wrapperClientFournisseur);
        WrapperClientFournisseur result = clientFournisseurService.updateWrapperClientFournisseur(wrapperClientFournisseur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, wrapperClientFournisseur.getId().toString()))
            .body(result);
    }


    /**
     * Permet de vérifier si les données entrées dans l'objet passé en paramètre
     * sont valides et permettent  de modifier un clientFournisseur
     *
     * @param clientFournisseur : l'objet à vérifier
     */
    private void verificationsClientFournisseur(WrapperClientFournisseur clientFournisseur)  {
        if (clientFournisseur.getNom() == null || clientFournisseur.getNom().isEmpty()) {
            throw new BadRequestAlertException("Le nome de client founisseur est  vide", ENTITY_NAME, " nom null");
        }

        if (clientFournisseur.getSiret() == null || clientFournisseur.getSiret().isEmpty()) {

            throw new BadRequestAlertException("SIREN de client founisseur est vide", ENTITY_NAME, " SIREN null");
        }
        if (clientFournisseur.getEmail() == null || "".equals(clientFournisseur.getEmail())){
            throw new BadRequestAlertException("L'email est vide", ENTITY_NAME, "Email null");
        }
        if (clientFournisseur.getIdAdresse() == null || "".equals(clientFournisseur.getIdAdresse())) {
            throw new BadRequestAlertException("l'id d'adresse de client fournisseur est vide", ENTITY_NAME, "idAdresse null");
        }
        if (clientFournisseur.getIdSociete() == null || "".equals(clientFournisseur.getIdSociete())) {
            throw new BadRequestAlertException("l'id société de client fournisseur est vide", ENTITY_NAME, "idSociété null");
        }
        if (clientFournisseur.getNomRue() == null || "".equals(clientFournisseur.getNomRue() )) {
            throw new BadRequestAlertException("la rue de  client fournisseur est vide", ENTITY_NAME, "Rue null");

        }
        if (clientFournisseur.getCodePostal() == null || "".equals(clientFournisseur.getCodePostal())) {
            throw new BadRequestAlertException("Le code postal de client fournisseur est vide", ENTITY_NAME, "Code postal null");
        }
        if (clientFournisseur.getVille() == null || "".equals(clientFournisseur.getVille())) {
            throw new BadRequestAlertException("La ville de client fournisseur est vide", ENTITY_NAME, "Ville null");
        }
        if (clientFournisseur.getPays() == null || "".equals(clientFournisseur.getPays())) {
            throw new BadRequestAlertException("Le pays de client fournisseur est vide", ENTITY_NAME, "Pays null");
        }
        }

    /**
     * {@code GET  /client-fournisseurs/nom/:nom} : get the "nom" clientFournisseur.
     *
     * @param nom the id of the clientFournisseurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientFournisseurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/client-fournisseurs/nom/{nom}")
    public ResponseEntity<ClientFournisseurDTO> getClientFournisseurByNom(@PathVariable String nom) {
        log.debug("REST request to get ClientFournisseur : {}", nom);
        Optional<ClientFournisseurDTO> clientFournisseurDTO = clientFournisseurService.findByNom(nom);
        return ResponseUtil.wrapOrNotFound(clientFournisseurDTO);
    }

    /**
     * {@code GET  /produits/{keyWord} : get the list of products.
     *
     * @param keyWord.
     * @param principal (current user).
     * @return list of products.
     */
    @GetMapping("/clients-fournisseurs/siret/{siret}")
    public  ResponseEntity<List<ClientFournisseurDTO>> getClientFournisseurBySiret(@PathVariable String siret, Principal principal) {
        log.debug("REST request to get ClientFournisseur : {}", siret);
        String login = principal.getName();
        Long idSociety = societeService.findByUserLogin(login).getId();
        List<ClientFournisseurDTO> clientsFournisseurs = clientFournisseurService.findBySiretAndSocietyId(siret, idSociety);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(clientsFournisseurs));

    }

    /**
     * {@code DELETE  /client-fournisseurs/:clientId/user/:userId} : delete the "clientId" client .
     *
     * @param clientId the id of the clientFournisseurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.SOCIETY + "\")")
    @DeleteMapping("/client-fournisseurs/{clientId}/user/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId,@PathVariable Long userId) {
        log.debug("REST request to delete client-fournisseur : {}", clientId);
        if(!clientFournisseurService.connectedUserIsSociete() || !clientFournisseurService.verfyIdOfUserConnected(userId)){
            throw new BadRequestAlertException("Vous n'avez pas le droit de supprimer ", ENTITY_NAME, "pas le droit");
        }
        log.debug("REST request to delete ClientFournisseur : {}", clientId);
        clientFournisseurService.delete(clientId);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, clientId.toString())).build();
    }

}
