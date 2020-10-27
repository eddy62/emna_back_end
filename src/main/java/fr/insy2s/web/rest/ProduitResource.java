package fr.insy2s.web.rest;

import fr.insy2s.security.AuthoritiesConstants;
import fr.insy2s.security.model.CustomUser;
import fr.insy2s.service.ProduitService;
import fr.insy2s.service.SocieteService;
import fr.insy2s.service.dto.SocieteDTO;
import fr.insy2s.utils.wrapper.WrapperSociete;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.ProduitDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.Produit}.
 */
@RestController
@RequestMapping("/api")
public class ProduitResource {

    private final Logger log = LoggerFactory.getLogger(ProduitResource.class);

    private static final String ENTITY_NAME = "produit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProduitService produitService;
    private final SocieteService societeService;

    public ProduitResource(ProduitService produitService, SocieteService societeService) {
        this.produitService = produitService;
        this.societeService = societeService;
    }

    /**
     * {@code POST  /produits} : Create a new produit.
     *
     * @param produitDTO the produitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new produitDTO, or with status {@code 400 (Bad Request)} if the produit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/produits")
    public ResponseEntity<ProduitDTO> createProduit(@RequestBody ProduitDTO produitDTO) throws URISyntaxException {
        log.debug("REST request to save Produit : {}", produitDTO);
        if (produitDTO.getId() != null) {
            throw new BadRequestAlertException("A new produit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProduitDTO result = produitService.save(produitDTO);
        return ResponseEntity.created(new URI("/api/produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    /**
     * {@code GET  /produits} : get all the produits.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of produits in body.
     */
    @GetMapping("/produits")
    public List<ProduitDTO> getAllProduits(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Produits");
        return produitService.findAll();
    }

    /**
     * {@code GET  /produits/:id} : get the "id" produit.
     *
     * @param id the id of the produitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the produitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/produits/{id}")
    public ResponseEntity<ProduitDTO> getProduit(@PathVariable Long id) {
        log.debug("REST request to get Produit : {}", id);
        Optional<ProduitDTO> produitDTO = produitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(produitDTO);
    }

    /**
     * {@code GET  /produits/{keyWord} : get the list of products.
     *
     * @param keyWord.
     * @param principal (current user).
     * @return list of products.
     */
    @GetMapping("/products/q/{keyWord}")
    public  ResponseEntity<List<ProduitDTO>> getProductByNameOrReferenceAndSocietyId(@PathVariable String keyWord, Principal principal) {
        log.debug("REST request to get Produit : {}", keyWord);
        String login = principal.getName();
        Long idSociety = societeService.findByUserLogin(login).getId();
        List<ProduitDTO> produits = produitService.findProductByNameOrReferenceAndIdSociety(keyWord, idSociety);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(produits));

    }

    /**
     * {@code DELETE  /produits/:id} : delete the "id" produit.
     *
     * @param id the id of the produitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/produits/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        log.debug("REST request to delete Produit : {}", id);
        produitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /produits/societe/id} : get all the produits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of produits in body.
     */
    @GetMapping("/produits/societe/{id}")
    public List<ProduitDTO> getAllProduitBySociete(@PathVariable Long id) {
        log.debug("REST request to get all Produit");
        return produitService.findAllBySocieteId(id);
    }


    /**
     * {@code PUT  /produits/update} : Updates an existing produit.
     *
     * @param produitDTO the produitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated produitDTO,
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.SOCIETY + "')" +
        " || hasAuthority('" + AuthoritiesConstants.ADMIN + "')")
    @PutMapping("/produits")
    public ResponseEntity<ProduitDTO> update(@RequestBody @Valid ProduitDTO produitDTO, Authentication authentication) {
        log.debug("REST request to update Produit : {}", produitDTO);
        boolean hasAdminRole = authentication.getAuthorities().stream()
            .anyMatch(r -> r.getAuthority().equals(AuthoritiesConstants.ADMIN));
        Long userId = ((CustomUser) authentication.getPrincipal()).getUserId();
        if (!hasAdminRole) {
            if (!produitService.userCanUpdateProduct(produitDTO.getSocieteId(), userId, produitDTO.getId())) {
                throw new BadRequestAlertException("Le produit n'existe pas dans votre société", ENTITY_NAME,
                    "null product");
            }
        }
        Optional<ProduitDTO> result = Optional.ofNullable(produitService.save(produitDTO));
        return ResponseUtil.wrapOrNotFound(result,
            HeaderUtil.createAlert(applicationName, "productManagement.updated",
                produitDTO.getId().toString()));
    }


    /**
     * {@code DELETE  /produits/:produitId/user/:userId} : delete the "id" produit.
     *
     * @param produitId the id of the produitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code @put204 (NO_CONTENT)}.
     */
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.SOCIETY + "')" +
        " || hasAuthority('" + AuthoritiesConstants.ADMIN + "')")
    @DeleteMapping("/produits/{produitId}/user/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long produitId, @PathVariable Long userId,
                                       Authentication authentication) {
        log.debug("REST request to delete Produit : {}", produitId);
        boolean hasAdminRole = authentication.getAuthorities().stream()
            .anyMatch(r -> r.getAuthority().equals(AuthoritiesConstants.ADMIN));
        if (!hasAdminRole) {
            if (!produitService.connectedUserIsSociete() || !produitService.verfyIdOfUserConnected(userId)) {
                throw new BadRequestAlertException("Vous n'avez pas le droit de supprimer ",
                    ENTITY_NAME, "pas le droit");
            }
        }
        if (!produitService.delete(produitId)) {
            throw new BadRequestAlertException("Vous ne pouvez pas supprimer un produit déja commandé ",
                ENTITY_NAME, "pas le droit");
        }
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(
            applicationName, true, ENTITY_NAME, produitId.toString())).build();
    }
}
