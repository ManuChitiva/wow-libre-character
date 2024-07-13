package com.wow.libre.domain.mapper;

import com.wow.libre.domain.model.GmTicket;
import com.wow.libre.infrastructure.entities.GmTicketEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MapToModel {

    public static GmTicket ticket(GmTicketEntity ticket) {
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
