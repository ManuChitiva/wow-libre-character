package com.wow.libre.infrastructure.repositories.gmTicket;

import com.wow.libre.domain.model.GmTicket;
import com.wow.libre.domain.ports.out.gmTicket.ObtainGmTicket;
import com.wow.libre.infrastructure.entities.GmTicketEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JpaGmTicketAdapter implements ObtainGmTicket {
  private final long UNRESOLVED_TICKET = 0L;

  private final GmTicketRepository gmTicketRepository;

  public JpaGmTicketAdapter(GmTicketRepository gmTicketRepository) {
    this.gmTicketRepository = gmTicketRepository;
  }

  @Override
  public List<GmTicket> findByTicket(Long guid, String transactionId) {
    return gmTicketRepository
            .findByPlayerGuidAndResolvedAndClosedByOrderByIdDesc(guid, UNRESOLVED_TICKET, UNRESOLVED_TICKET)
            .stream().filter(Objects::nonNull).map(this::mapToModel).collect(Collectors.toList());
  }

  @Override
  public Long getTheNumberOfUnresolvedTickets(Long guid, String transactionId) {
    return gmTicketRepository.countByPlayerGuidAndResolved(guid, UNRESOLVED_TICKET);
  }


  private GmTicket mapToModel(GmTicketEntity ticket) {
    return GmTicket.builder()
            .id(ticket.getId())
            .type(ticket.getType())
            .guid(ticket.getPlayerGuid())
            .name(ticket.getName())
            .description(ticket.getDescription())
            .createTime(ticket.getCreateTime() != null
                    ? LocalDateTime.ofEpochSecond(ticket.getCreateTime(), 0, ZoneOffset.UTC)
                    : null)
            .lastModifiedTime(ticket.getLastModifiedTime() != null
                    ? LocalDateTime.ofEpochSecond(ticket.getLastModifiedTime(), 0, ZoneOffset.UTC)
                    : null)
            .closedBy(ticket.getClosedBy())
            .assignedTo(ticket.getAssignedTo())
            .comment(ticket.getComment())
            .response(ticket.getResponse())
            .completed(ticket.getCompleted() == 1)
            .escalated(ticket.getEscalated() == 1)
            .viewed(ticket.getViewed() == 1)
            .resolvedBy(ticket.getResolved() == 1)
            .build();
  }
}
