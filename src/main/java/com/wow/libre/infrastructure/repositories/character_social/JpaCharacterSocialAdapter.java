package com.wow.libre.infrastructure.repositories.character_social;

import com.wow.libre.domain.model.CharacterSocial;
import com.wow.libre.domain.ports.out.character_social.ObtainCharacterSocial;
import com.wow.libre.infrastructure.entities.CharacterSocialEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JpaCharacterSocialAdapter implements ObtainCharacterSocial {
  private final CharacterSocialRepository characterSocialRepository;

  public JpaCharacterSocialAdapter(CharacterSocialRepository characterSocialRepository) {
    this.characterSocialRepository = characterSocialRepository;
  }

  @Override
  public List<CharacterSocial> getFriends(Long guid, String transactionId) {

    return characterSocialRepository.findByGuid(guid).stream().map(this::mapToModel).collect(Collectors.toList());
  }

  private CharacterSocial mapToModel(CharacterSocialEntity characterSocialEntity) {
    return new CharacterSocial(characterSocialEntity.getGuid(),
        characterSocialEntity.getFriend(), characterSocialEntity.getFlags(),
        characterSocialEntity.getNote());
  }
}
