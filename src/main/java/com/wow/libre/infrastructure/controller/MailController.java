package com.wow.libre.infrastructure.controller;

import com.wow.libre.domain.ports.in.mail.MailPort;
import com.wow.libre.domain.shared.GenericResponse;
import com.wow.libre.domain.shared.GenericResponseBuilder;
import com.wow.libre.domain.dto.MailsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.wow.libre.domain.model.Constants.HEADER_ACCOUNT_WEB_ID_JWT;
import static com.wow.libre.domain.model.Constants.HEADER_TRANSACTION_ID;

@RestController
@RequestMapping("/api/mails")
public class MailController {

    private final MailPort mailPort;

    public MailController(MailPort mailPort) {
        this.mailPort = mailPort;
    }

    @GetMapping("/{guid}")
    public ResponseEntity<GenericResponse<MailsDto>> mails(
            @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
            @RequestHeader(name = HEADER_ACCOUNT_WEB_ID_JWT) final Long accountWebId,
            @PathVariable final Long guid) {

        MailsDto mails = mailPort.getMails(guid, accountWebId, transactionId);

        return ResponseEntity.status(HttpStatus.OK).body(new GenericResponseBuilder<MailsDto>(transactionId)
                .ok(mails).build());
    }

}
