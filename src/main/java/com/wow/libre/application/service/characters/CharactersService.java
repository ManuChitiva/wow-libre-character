package com.wow.libre.application.service.characters;

import com.wow.libre.domain.dto.CharacterDetail;
import com.wow.libre.domain.dto.CharactersDto;
import com.wow.libre.domain.model.Character;
import com.wow.libre.domain.ports.in.characters.CharactersPort;
import com.wow.libre.domain.ports.out.characters.ObtainCharacters;
import com.wow.libre.domain.ports.out.gmTicket.ObtainGmTicket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharactersService implements CharactersPort {
  private final ObtainCharacters obtainCharacters;

  private final ObtainGmTicket obtainGmTicket;

  public CharactersService(ObtainCharacters obtainCharacters, ObtainGmTicket obtainGmTicket) {
    this.obtainCharacters = obtainCharacters;
    this.obtainGmTicket = obtainGmTicket;
  }

  @Override
  public CharactersDto getCharacters(Long accountId, String transactionId) {

    List<Character> characterAccount = obtainCharacters.getCharacters(accountId, transactionId);
    CharactersDto charactersDto = new CharactersDto();
    charactersDto.setCharacters(characterAccount.stream()
            .map(character ->
                    new CharacterDetail(
                            character, obtainGmTicket.getTheNumberOfUnresolvedTickets(
                            character.guid, transactionId)
                    ))
            .collect(Collectors.toList()));
    charactersDto.setTotalQuantity(characterAccount.size());

    return charactersDto;
  }

  @Override
  public CharacterDetail getCharacter(Long guid, Long accountId, String transactionId) {
    return obtainCharacters.getCharacter(guid, accountId, transactionId)
            .map(character -> new CharacterDetail(
                    character,
                    obtainGmTicket.getTheNumberOfUnresolvedTickets(guid, transactionId)
            ))
            .orElse(null);
  }

}
