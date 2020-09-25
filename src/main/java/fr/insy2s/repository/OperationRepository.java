package fr.insy2s.repository;

import fr.insy2s.domain.Operation;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;

/**
 * Spring Data  repository for the Operation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
	List <Operation> findAllByReleveId(Long id);

	@Modifying
    @Query("update Operation o set o.rapproche=:boolean where o.id=:idOperation")
    void updateRapprochementOperation(@Param ("idOperation") Long idOperation,@Param("boolean") Boolean test);
}
