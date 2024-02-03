package com.wow.libre.infrastructure.controller;

import com.wow.libre.domain.dto.CharactersDto;
import com.wow.libre.domain.ports.in.character_social.CharacterSocialPort;
import com.wow.libre.domain.shared.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wow.libre.domain.model.Constants.HEADER_ACCOUNT_ID_JWT;
import static com.wow.libre.domain.model.Constants.HEADER_TRANSACTION_ID;

@RestController
@RequestMapping("/api/social")
public class CharactersSocialController {
  private final CharacterSocialPort characterSocialPort;

  public CharactersSocialController(CharacterSocialPort characterSocialPort) {
    this.characterSocialPort = characterSocialPort;
  }

  @DeleteMapping("/{guid}/{friendGuid}")
  public ResponseEntity<GenericResponse<CharactersDto>> deleteFriend(
      @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId,
      @RequestHeader(name = HEADER_ACCOUNT_ID_JWT) final Long accountId,
      @PathVariable final Long friendGuid, @PathVariable final Long guid) {

    characterSocialPort.deleteFriend(accountId, guid, friendGuid, transactionId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
