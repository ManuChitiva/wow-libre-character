package com.wow.libre.domain.ports.out.character_social;

import com.wow.libre.domain.model.CharacterSocial;

import java.util.List;

public interface ObtainCharacterSocial {
  List<CharacterSocial> getFriends(Long guid, String transactionId);
}
