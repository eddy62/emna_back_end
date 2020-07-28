package fr.insy2s.service;

import fr.insy2s.service.dto.ClientFournisseurDTO;
import fr.insy2s.utils.wrapper.WrapperClientFournisseur;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.ClientFournisseur}.
 */
public interface ClientFournisseurService {

    /**
     * Save a clientFournisseur.
     *
     * @param clientFournisseurDTO the entity to save.
     * @return the persisted entity.
     */
    ClientFournisseurDTO save(ClientFournisseurDTO clientFournisseurDTO);

    /**
     * Get all the clientFournisseurs.
     *
     * @return the list of entities.
     */
    List<ClientFournisseurDTO> findAll();

    /**
     * Get the "id" clientFournisseur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClientFournisseurDTO> findOne(Long id);

    /**
     * Delete the "id" clientFournisseur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Save a clientFournisseur
     *
     * @param wrapperclientFournisseur the entity to save.
     * @return the persisted entity
     */
    ClientFournisseurDTO saveWrapperClientFournisseur(WrapperClientFournisseur wrapperclientFournisseur);


    /**
     * Get the "id" WrapperclientFournisseur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
     Optional<WrapperClientFournisseur> getClientById(Long id);

    /**
     * Get all the clientFournisseursWrapper.
     *
     * @return the list of entitiesWrapper.
     */
    public List<WrapperClientFournisseur> findAllBySocieteId(Long id );
}
