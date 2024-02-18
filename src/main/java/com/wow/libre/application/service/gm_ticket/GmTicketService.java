package com.wow.libre.application.service.gm_ticket;

import com.wow.libre.domain.dto.GmTicketDto;
import com.wow.libre.domain.model.Character;
import com.wow.libre.domain.model.GmTicket;
import com.wow.libre.domain.ports.in.gm_ticket.GmTicketPort;
import com.wow.libre.domain.ports.out.characters.ObtainCharacters;
import com.wow.libre.domain.ports.out.gm_ticket.ObtainGmTicket;
import com.wow.libre.infrastructure.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GmTicketService implements GmTicketPort {

  private final ObtainGmTicket obtainGmTicket;
  private final ObtainCharacters obtainCharacters;

  public GmTicketService(ObtainGmTicket obtainGmTicket, ObtainCharacters obtainCharacters) {
    this.obtainGmTicket = obtainGmTicket;
    this.obtainCharacters = obtainCharacters;
  }

  @Override
  public List<GmTicketDto> getObtain(Long accountId, Long guid, String transactionId) {

    obtainCharacters.getCharacters(accountId, transactionId)
        .stream()
        .filter(data -> data.guid.equals(guid))
        .findFirst()
        .orElseThrow(() ->
            new BadRequestException("No se ecuentra el personaje asociado a tu cuenta.", transactionId)
        );

    List<GmTicket> gmTickets = obtainGmTicket.findByTicket(guid, transactionId);

    return gmTickets.stream()
        .filter(Objects::nonNull)
        .map(gmTicket -> mapToModel(gmTicket, transactionId))
        .collect(Collectors.toList());
  }

  private GmTicketDto mapToModel(GmTicket ticket, String transactionId) {
    String assignment = getAssignment(ticket.assignedTo, transactionId);
    String assignmentClose = getAssignment(ticket.closedBy, transactionId);
    return GmTicketDto.builder()
        .id(ticket.id)
        .publishedBy(ticket.name)
        .assignedTo(assignment != null ? assignment : "Unassigned")
        .comment(ticket.comment)
        .description(ticket.description)
        .response(ticket.response)
        .viewed(ticket.viewed)
        .escalated(ticket.escalated)
        .completed(ticket.completed)
        .resolved(ticket.resolvedBy)
        .closedBy(assignmentClose)
        .createTime(ticket.createTime)
        .lastModifiedTime(ticket.lastModifiedTime)
        .build();
  }

  private String getAssignment(Long assignedTo, String transactionId) {
    if (assignedTo != null && assignedTo != 0) {
      Optional<Character> characterStaff =
          obtainCharacters.getCharacter(assignedTo, transactionId);
      return characterStaff.map(name -> name.name).orElse("Support");
    }

    return "Unassigned";
  }
}
