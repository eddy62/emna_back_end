package fr.insy2s.service;

import fr.insy2s.service.dto.ProduitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.Produit}.
 */
public interface ProduitService {

    /**
     * Save a produit.
     *
     * @param produitDTO the entity to save.
     * @return the persisted entity.
     */
    ProduitDTO save(ProduitDTO produitDTO);

    /**
     * Get all the produits.
     *
     * @return the list of entities.
     */
    List<ProduitDTO> findAll();


    /**
     * Get the "id" produit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProduitDTO> findOne(Long id);

    /**
     * Delete the "id" produit.
     *
     * @param id the id of the entity.
     * @return
     */
    boolean delete(Long id);

    /**
     * Get all the produits.
     *
     * @return the list of entities.
     */
    List<ProduitDTO> findAllBySocieteId(Long id);

    /**
     * check id of current user
     *
     * @param id
     * @return
     */
    public Boolean verfyIdOfUserConnected(Long id);

    /**
     * check role of current user
     *
     * @return
     */
    public Boolean connectedUserIsSociete();

    boolean userCanUpdateProduct(Long societyId, Long userId, Long productId);

    /**
     * find list of products by id of user's society and the product's label or his refeerence
     * @param keyWord
     * @param idSociety
     * @return list of produitDTO
     */
    List<ProduitDTO> findProductByNameOrReferenceAndIdSociety(String keyWord, Long idSociety);
}

