package com.wow.libre.infrastructure.controller;

import com.wow.libre.domain.dto.CharacterDetail;
import com.wow.libre.domain.dto.CharactersDto;
import com.wow.libre.domain.ports.in.characters.CharactersPort;
import com.wow.libre.domain.shared.GenericResponse;
import com.wow.libre.domain.shared.GenericResponseBuilder;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.wow.libre.domain.model.Constants.HEADER_ACCOUNT_ID_JWT;
import static com.wow.libre.domain.model.Constants.HEADER_TRANSACTION_ID;

@RestController
@RequestMapping("/api/characters")
public class CharactersController {
  private final CharactersPort charactersPort;

  public CharactersController(CharactersPort charactersPort) {
    this.charactersPort = charactersPort;
  }

  @GetMapping
  public ResponseEntity<GenericResponse<CharactersDto>> getCharacters(
          @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
          @RequestHeader(name = HEADER_ACCOUNT_ID_JWT) final Long accountId) {

    CharactersDto characters = charactersPort.getCharacters(accountId, transactionId);

    if (characters != null) {
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(new GenericResponseBuilder<CharactersDto>(transactionId).ok(characters).build());
    }

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("/{guid}")
  public ResponseEntity<GenericResponse<CharacterDetail>> getCharacter(
          @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
          @RequestHeader(name = HEADER_ACCOUNT_ID_JWT) final Long accountId,
          @PathVariable  final Long guid) {

    CharacterDetail character = charactersPort.getCharacter(guid, accountId, transactionId);

    if (character != null) {
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(new GenericResponseBuilder<CharacterDetail>(transactionId).ok(character).build());
    }

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
