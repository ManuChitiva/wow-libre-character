package com.wow.libre.domain.ports.in.character_social;

import com.wow.libre.domain.model.CharacterSocial;

import java.util.List;

public interface CharacterSocialPort {
  List<CharacterSocial> getFriends(Long guid, Long accountId, String transactionId);

  void deleteFriend(Long accountId, Long guid, Long friendGuid, String transactionId);

}
