package com.wow.libre.domain.ports.in.gm_ticket;

import com.wow.libre.domain.dto.GmTicketDto;

import java.util.List;

public interface GmTicketPort {
  List<GmTicketDto> getObtain(Long accountId, Long guid, String transactionId);
}
