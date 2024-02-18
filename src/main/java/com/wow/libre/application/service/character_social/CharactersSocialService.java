package com.wow.libre.application.service.character_social;


import com.wow.libre.domain.model.CharacterSocial;
import com.wow.libre.domain.ports.in.character_social.CharacterSocialPort;
import com.wow.libre.domain.ports.out.character_social.ObtainCharacterSocial;
import com.wow.libre.domain.ports.out.characters.ObtainCharacters;
import com.wow.libre.infrastructure.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharactersSocialService implements CharacterSocialPort {


  private final ObtainCharacterSocial obtainCharacterSocial;
  private final ObtainCharacters obtainCharacters;

  public CharactersSocialService(ObtainCharacterSocial obtainCharacterSocial, ObtainCharacters obtainCharacters) {
    this.obtainCharacterSocial = obtainCharacterSocial;
    this.obtainCharacters = obtainCharacters;
  }

  @Override
  public List<CharacterSocial> getFriends(Long guid, Long accountId, String transactionId) {
    return obtainCharacterSocial.getFriends(guid, transactionId);
  }

  @Override
  public void deleteFriend(Long accountId, Long guid, Long friendGuid, String transactionId) {

    obtainCharacters.getCharacters(accountId, transactionId).stream()
        .filter(character -> character.guid.equals(guid))
        .findFirst().orElseThrow(() -> new BadRequestException("Character not found", transactionId));

    obtainCharacterSocial.getFriends(guid, transactionId).stream()
        .filter(characterSocial -> characterSocial.friend.equals(friendGuid))
        .findFirst().orElseThrow(() -> new BadRequestException("Friend not found", transactionId));

    obtainCharacterSocial.deleteFriend(guid, friendGuid, transactionId);
  }
}
