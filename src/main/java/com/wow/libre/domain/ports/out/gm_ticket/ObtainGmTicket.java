package com.wow.libre.domain.ports.out.gm_ticket;

import com.wow.libre.domain.model.GmTicket;

import java.util.List;

public interface ObtainGmTicket {
  List<GmTicket> findByTicket(Long characterId, String transactionId);
}
