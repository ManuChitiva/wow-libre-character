package com.wow.libre.infrastructure.repositories.gm_ticket;

import com.wow.libre.infrastructure.entities.GmTicketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GmTicketRepository extends CrudRepository<GmTicketEntity, Long> {
  Long countByPlayerGuidAndResolved(Long guid, Long resolved);

  List<GmTicketEntity> findByPlayerGuidAndResolvedAndClosedByOrderByIdDesc(long guid, long resolvedBy, long close);
}
