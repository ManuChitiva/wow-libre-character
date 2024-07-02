package com.wow.libre.application.service.characters;

import com.wow.libre.domain.dto.CharacterFactionDto;
import com.wow.libre.domain.dto.CharacterSocialDto;
import com.wow.libre.domain.dto.CharactersDto;
import com.wow.libre.domain.enums.FactionWow;
import com.wow.libre.domain.enums.WowRace;
import com.wow.libre.domain.model.Character;
import com.wow.libre.domain.model.CharacterDetail;
import com.wow.libre.domain.model.CharacterSocial;
import com.wow.libre.domain.model.CharacterSocialDetail;
import com.wow.libre.domain.ports.in.character_social.CharacterSocialPort;
import com.wow.libre.domain.ports.in.characters.CharactersPort;
import com.wow.libre.domain.ports.out.characters.ObtainCharacters;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CharactersService implements CharactersPort {
    private final ObtainCharacters obtainCharacters;

    private final CharacterSocialPort characterSocialPort;

    public CharactersService(ObtainCharacters obtainCharacters, CharacterSocialPort characterSocialPort) {
        this.obtainCharacters = obtainCharacters;
        this.characterSocialPort = characterSocialPort;
    }

    @Override
    public CharactersDto getCharacters(Long accountId, Long accountWebId, String transactionId) {

        final List<Character> characterAccount = obtainCharacters.getCharacters(accountId, transactionId);

        CharactersDto charactersDto = new CharactersDto();
        charactersDto.setCharacters(characterAccount.stream()
                .map(CharacterDetail::new)
                .toList());
        charactersDto.setTotalQuantity(characterAccount.size());

        return charactersDto;
    }

    @Override
    public CharacterDetail getCharacter(Long guid, Long accountId, String transactionId) {
        return obtainCharacters.getCharacter(guid, accountId, transactionId)
                .map(CharacterDetail::new)
                .orElse(null);
    }

    @Override
    public CharacterFactionDto getCharactersOnline(String transactionId) {
        CharacterFactionDto characterFactionDto = new CharacterFactionDto();

        List<Character> characters = obtainCharacters.getCharactersOnline(transactionId);
        if (Objects.nonNull(characters)) {

            long allianceCount = characters.stream()
                    .filter(character -> FactionWow.ALLIANCE.getDescription().equals(WowRace.getById(character.race).getFaction()))
                    .count();

            long hordeCount = characters.size() - allianceCount;
            characterFactionDto.setAlliance(allianceCount);
            characterFactionDto.setHorde(hordeCount);
        }

        return characterFactionDto;
    }

    @Override
    public CharacterSocialDto getFriends(Long guid, Long accountId, String transactionId) {
        CharacterSocialDto characterSocialDto = new CharacterSocialDto();

        List<CharacterSocial> characterSocials = characterSocialPort.getFriends(guid, accountId, transactionId);

        if (Objects.nonNull(characterSocials)) {

            List<CharacterSocialDetail> friends = characterSocials.stream()
                    .map(characterSocial -> obtainCharacters.getCharacter(characterSocial.friend, transactionId)
                            .map(character -> new CharacterSocialDetail(character, characterSocial)).orElse(null))
                    .collect(Collectors.toList());
            characterSocialDto.setFriends(friends);
            characterSocialDto.setTotalQuantity(friends.size());
        }

        return characterSocialDto;
    }

    @Override
    public CharacterDetail getCharacter(Long guid, String transactionId) {
        return obtainCharacters.getCharacter(guid, transactionId).map(CharacterDetail::new).orElse(null);
    }

}
