package com.wow.libre.domain.ports.in.gm_ticket;

import com.wow.libre.domain.dto.GmTicketDto;

import java.util.List;

public interface GmTicketPort {
    List<GmTicketDto> tickets(Long accountId, Long accountWebId, Long characterId, String transactionId);
}
