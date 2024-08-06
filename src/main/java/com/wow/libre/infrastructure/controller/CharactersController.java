package com.wow.libre.infrastructure.controller;

import com.wow.libre.domain.dto.CharacterFactionDto;
import com.wow.libre.domain.dto.CharacterSocialDto;
import com.wow.libre.domain.dto.CharactersDto;
import com.wow.libre.domain.model.CharacterDetail;
import com.wow.libre.domain.ports.in.characters.CharactersPort;
import com.wow.libre.domain.shared.GenericResponse;
import com.wow.libre.domain.shared.GenericResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.wow.libre.domain.model.Constants.HEADER_ACCOUNT_WEB_ID_JWT;
import static com.wow.libre.domain.model.Constants.HEADER_TRANSACTION_ID;

@RestController
@RequestMapping("/api/characters")
public class CharactersController {
    private final CharactersPort charactersPort;

    public CharactersController(CharactersPort charactersPort) {
        this.charactersPort = charactersPort;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<CharactersDto>> characters(
            @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
            @RequestParam(name = "account_id") final Long accountId,
            @RequestHeader(name = HEADER_ACCOUNT_WEB_ID_JWT) final Long accountWebId) {

        CharactersDto characters = charactersPort.getCharacters(accountId, accountWebId, transactionId);

        if (characters != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new GenericResponseBuilder<CharactersDto>
                            (transactionId).ok(characters).build());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{guid}")
    public ResponseEntity<GenericResponse<CharacterDetail>> character(
            @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
            @RequestParam(name = "account_id") final Long accountId,
            @PathVariable final Long guid,
            @RequestHeader(name = HEADER_ACCOUNT_WEB_ID_JWT) final Long accountWebId) {

        CharacterDetail character = charactersPort.getCharacter(guid, accountId, accountWebId, transactionId);

        if (character != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new GenericResponseBuilder<CharacterDetail>(transactionId)
                    .ok(character).build());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/{guid}/friends")
    public ResponseEntity<GenericResponse<CharacterSocialDto>> friends(
            @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
            @RequestParam final Long account_id,
            @PathVariable final Long guid,
            @RequestHeader(name = HEADER_ACCOUNT_WEB_ID_JWT, required = false) final String accountWebId) {

        CharacterSocialDto character = charactersPort.getFriends(guid, account_id, transactionId);

        if (character != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new GenericResponseBuilder<CharacterSocialDto>
                            (transactionId).ok(character).build());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/number/online")
    public ResponseEntity<GenericResponse<CharacterFactionDto>> getNumberCharactersOnline(
            @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId) {

        CharacterFactionDto character = charactersPort.getCharactersOnline(transactionId);

        if (character != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new GenericResponseBuilder<CharacterFactionDto>(transactionId)
                    .ok(character).build());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
