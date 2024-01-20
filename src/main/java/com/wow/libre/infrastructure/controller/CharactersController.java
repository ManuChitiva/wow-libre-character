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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wow.libre.domain.model.Constants.HEADER_ACCOUNT_ID_JWT;
import static com.wow.libre.domain.model.Constants.HEADER_TRANSACTION_ID;

@RestController
@RequestMapping("/api/character")
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
      return ResponseEntity.status(HttpStatus.OK).body(new GenericResponseBuilder<CharactersDto>(transactionId)
          .ok(characters).build());
    }

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("/{guid}")
  public ResponseEntity<GenericResponse<CharacterDetail>> getCharacter(
      @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
      @RequestHeader(name = HEADER_ACCOUNT_ID_JWT) final Long accountId,
      @PathVariable final Long guid) {

    CharacterDetail character = charactersPort.getCharacter(guid, accountId, transactionId);

    if (character != null) {
      return ResponseEntity.status(HttpStatus.OK).body(new GenericResponseBuilder<CharacterDetail>(transactionId)
          .ok(character).build());
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

  @GetMapping("/{guid}/friends")
  public ResponseEntity<GenericResponse<CharacterSocialDto>> getFriends(
      @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
      @RequestHeader(name = HEADER_ACCOUNT_ID_JWT) final Long accountId
      , @PathVariable final Long guid) {

    CharacterSocialDto character = charactersPort.getFriends(guid, accountId, transactionId);

    if (character != null) {
      return ResponseEntity.status(HttpStatus.OK).body(new GenericResponseBuilder<CharacterSocialDto>(transactionId)
          .ok(character).build());
    }

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
