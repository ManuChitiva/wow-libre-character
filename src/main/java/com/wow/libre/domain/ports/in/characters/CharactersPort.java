package com.wow.libre.domain.ports.in.characters;

import com.wow.libre.domain.dto.CharacterDetail;
import com.wow.libre.domain.dto.CharactersDto;

public interface CharactersPort {
  CharactersDto getCharacters(Long accountId, String transactionId);

  CharacterDetail getCharacter(Long guid, Long accountId, String transactionId);
}
