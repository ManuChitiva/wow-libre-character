package com.wow.libre.domain.ports.in.characters;

import com.wow.libre.domain.dto.CharacterFactionDto;
import com.wow.libre.domain.dto.CharacterSocialDto;
import com.wow.libre.domain.dto.CharactersDto;
import com.wow.libre.domain.model.CharacterDetail;

public interface CharactersPort {
    CharactersDto getCharacters(Long accountId, Long accountWebId, String transactionId);

    CharacterDetail getCharacter(Long characterId, Long accountId,Long accountWebId, String transactionId);

    CharacterFactionDto getCharactersOnline(String transactionId);

    CharacterSocialDto getFriends(Long characterId, Long accountId, String transactionId);

    CharacterDetail getCharacter(Long characterId, String transactionId);

}
