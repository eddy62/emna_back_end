package fr.insy2s.repository;

import fr.insy2s.domain.Operation;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Operation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
	List <Operation> findAllByReleveId(Long id);
}
