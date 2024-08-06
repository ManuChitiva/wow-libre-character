package com.wow.libre.infrastructure.controller;

import com.wow.libre.domain.dto.GmTicketDto;
import com.wow.libre.domain.ports.in.gm_ticket.GmTicketPort;
import com.wow.libre.domain.shared.GenericResponse;
import com.wow.libre.domain.shared.GenericResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wow.libre.domain.model.Constants.HEADER_ACCOUNT_WEB_ID_JWT;
import static com.wow.libre.domain.model.Constants.HEADER_TRANSACTION_ID;

@RestController
@RequestMapping("/api/ticket")
public class GmTicketController {
    private final GmTicketPort gmTicketPort;

    public GmTicketController(GmTicketPort gmTicketPort) {
        this.gmTicketPort = gmTicketPort;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<GmTicketDto>>> tickets(
            @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
            @RequestHeader(name = HEADER_ACCOUNT_WEB_ID_JWT) final Long accountWebId,
            @RequestParam(name = "account_id") final Long accountId,
            @RequestParam(name = "character_id") final Long characterId) {

        List<GmTicketDto> tickets = gmTicketPort.tickets(accountId, accountWebId, characterId, transactionId);

        if (tickets != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new GenericResponseBuilder<List<GmTicketDto>>(transactionId).ok(tickets).build());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
