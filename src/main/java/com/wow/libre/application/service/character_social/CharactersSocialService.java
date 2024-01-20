package com.wow.libre.application.service.character_social;

import com.wow.libre.domain.model.CharacterSocial;
import com.wow.libre.domain.ports.in.character_social.CharacterSocialPort;
import com.wow.libre.domain.ports.out.character_social.ObtainCharacterSocial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharactersSocialService implements CharacterSocialPort {


  private final ObtainCharacterSocial obtainCharacterSocial;

  public CharactersSocialService(ObtainCharacterSocial obtainCharacterSocial) {
    this.obtainCharacterSocial = obtainCharacterSocial;
  }

  @Override
  public List<CharacterSocial> getFriends(Long guid, Long accountId, String transactionId) {
    return obtainCharacterSocial.getFriends(guid, transactionId);
  }
}
