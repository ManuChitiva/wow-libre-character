package com.wow.libre.infrastructure.controller;

import com.wow.libre.domain.dto.GmTicketDto;
import com.wow.libre.domain.model.GmTicket;
import com.wow.libre.domain.ports.in.gmTicket.GmTicketPort;
import com.wow.libre.domain.shared.GenericResponse;
import com.wow.libre.domain.shared.GenericResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wow.libre.domain.model.Constants.HEADER_ACCOUNT_ID_JWT;
import static com.wow.libre.domain.model.Constants.HEADER_TRANSACTION_ID;

@RestController
@RequestMapping("/api/ticket")
public class GmTicketController {
  private final GmTicketPort gmTicketPort;

  public GmTicketController(GmTicketPort gmTicketPort) {
    this.gmTicketPort = gmTicketPort;
  }

  @GetMapping("/{guid}")
  public ResponseEntity<GenericResponse<List<GmTicketDto>>> get(
          @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
          @RequestHeader(name = HEADER_ACCOUNT_ID_JWT) final Long accountId,
          @PathVariable final Long guid) {

    List<GmTicketDto> tickets = gmTicketPort.getObtain(accountId, guid, transactionId);

    if (tickets != null) {
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(new GenericResponseBuilder<List<GmTicketDto>>(transactionId).ok(tickets).build());
    }

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
