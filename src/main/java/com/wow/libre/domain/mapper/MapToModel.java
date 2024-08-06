package com.wow.libre.domain.mapper;

import com.wow.libre.domain.model.GmTicket;
import com.wow.libre.infrastructure.entities.GmTicketEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MapToModel {

    private static final int GOLD_VALUE = 10000;
    private static final int SILVER_VALUE = 100;

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

    public static String calculateMoneyString(long money) {
        long gold = money / GOLD_VALUE;
        long remainingSilver = money % GOLD_VALUE;
        long silver = remainingSilver / SILVER_VALUE;

        String goldString = formatGold(gold);
        return goldString + " " + silver + "s " ;
    }

    private static String formatGold(long gold) {
        if (gold >= 1_000_000) {
            long millionGold = gold / 1_000_000;
            return millionGold + "M " + (gold % 1_000_000) / 1_000 + "K " + gold % 1_000 + "g";
        } else if (gold >= 1_000) {
            long thousandGold = gold / 1_000;
            return thousandGold + "K " + gold % 1_000 + "g";
        } else {
            return gold + "g";
        }
    }

}
