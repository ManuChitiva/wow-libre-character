package com.wow.libre.infrastructure.controller;

import com.wow.libre.domain.dto.GuildDto;
import com.wow.libre.domain.dto.GuildsDto;
import com.wow.libre.domain.ports.in.guild.GuildPort;
import com.wow.libre.domain.shared.GenericResponse;
import com.wow.libre.domain.shared.GenericResponseBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.wow.libre.domain.model.Constants.HEADER_TRANSACTION_ID;

@RestController
@RequestMapping("/api/guilds")
public class GuildController {

    private final GuildPort guildPort;

    public GuildController(GuildPort guildPort) {
        this.guildPort = guildPort;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<GuildsDto>> guilds(
            @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
            @RequestParam final Integer size, @RequestParam final Integer page) {

        GuildsDto guilds = guildPort.findAll(size, page, transactionId);

        if (guilds == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GenericResponseBuilder<GuildsDto>(transactionId).ok(guilds).build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GenericResponse<GuildDto>> guild(
            @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
            @RequestParam(required = false) final Long account_id,
            @PathVariable final Long id,
            @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) final String authorizationHeader) {

        GuildDto guild = guildPort.detail(id, account_id, authorizationHeader, transactionId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GenericResponseBuilder<GuildDto>(transactionId).ok(guild).build());
    }
}
