package fr.insy2s.repository;

import fr.insy2s.domain.Employe;
import fr.insy2s.domain.Societe;

import fr.insy2s.service.dto.SocieteDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Societe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocieteRepository extends JpaRepository<Societe, Long> {
    List<Societe> findByComptableId(@Param(value = "id") Long id);

    Optional<Societe> findByUserId(Long id);

    @Query("select (count(s) > 0) from Societe s where s.id = :societyId and s.user.id = :userId")
    boolean existByIdAndUser_Id(@Param("societyId") Long societyId, @Param("userId")Long userId);
}
