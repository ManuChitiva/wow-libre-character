package com.wow.libre.application.service.gm_ticket;

import com.wow.libre.domain.dto.GmTicketDto;
import com.wow.libre.domain.exception.BadRequestException;
import com.wow.libre.domain.model.CharacterDetail;
import com.wow.libre.domain.model.GmTicket;
import com.wow.libre.domain.ports.in.characters.CharactersPort;
import com.wow.libre.domain.ports.in.gm_ticket.GmTicketPort;
import com.wow.libre.domain.ports.out.gm_ticket.ObtainGmTicket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GmTicketService implements GmTicketPort {

    private final ObtainGmTicket obtainGmTicket;
    private final CharactersPort obtainCharacters;

    public GmTicketService(ObtainGmTicket obtainGmTicket, CharactersPort obtainCharacters) {
        this.obtainGmTicket = obtainGmTicket;
        this.obtainCharacters = obtainCharacters;
    }

    @Override
    public List<GmTicketDto> tickets(Long accountId, Long accountWebId, Long characterId, String transactionId) {


        if (obtainCharacters.getCharacter(characterId, accountId, accountWebId, transactionId) == null) {
            throw new BadRequestException("Character Invalid", transactionId);
        }

        List<GmTicket> gmTickets = obtainGmTicket.findByTicket(characterId, transactionId);

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
            return Optional.ofNullable(obtainCharacters.getCharacter(assignedTo, transactionId))
                    .map(CharacterDetail::getName).orElse("Support");
        }

        return "Unassigned";
    }
}
