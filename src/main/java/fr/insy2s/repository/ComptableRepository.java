package fr.insy2s.repository;

import fr.insy2s.domain.Comptable;

import fr.insy2s.service.dto.ComptableDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Comptable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComptableRepository extends JpaRepository<Comptable, Long> {

    Optional<Comptable> findByUserId(Long id);

}
