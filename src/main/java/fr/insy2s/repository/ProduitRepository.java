package fr.insy2s.repository;

import fr.insy2s.domain.Produit;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Produit entity.
 */
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    /**
     * get liste th clientFounisseur by id societe
     *
     * @param id the societe
     * @return liste the clientFournisseur
     */
    List<Produit> findBySocieteId(@Param(value = "id") Long id);


    @Query("" +
        "select (count(p) > 0) from Produit p " +
        "left join Societe s on p.societe.id = s.id " +
        "left join User u on u.id =s.user.id where u.id = :userId " +
        "and p.id = :productId and s.id = :societyId"
    )
    boolean canUserUpdateProduct(@Param("societyId") Long societyId , @Param("userId") Long userId, @Param("productId")
        Long productId);
    @Query("select p from Produit as p where p.societe.id=:idSociety and (p.nom like concat('%',:keyWord,'%') or p.reference like concat('%',:keyWord,'%'))")
    List<Produit> getByNomOrReferenceAndSociete_Id(@Param("keyWord") String keyWord, @Param("idSociety") Long idSociety);
}

