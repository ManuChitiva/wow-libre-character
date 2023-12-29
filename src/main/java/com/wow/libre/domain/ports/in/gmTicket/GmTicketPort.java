package com.wow.libre.domain.ports.in.gmTicket;

import com.wow.libre.domain.dto.GmTicketDto;
import com.wow.libre.domain.model.GmTicket;

import java.util.List;

public interface GmTicketPort {
  List<GmTicketDto> getObtain(Long accountId, Long guid, String transactionId);
}
