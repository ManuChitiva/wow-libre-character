package com.wow.libre.domain.ports.out.character_skills;

import com.wow.libre.infrastructure.entities.CharacterSkillsEntity;

import java.util.List;

public interface ObtainCharacterSkills {
    List<CharacterSkillsEntity> getCharacterIdSkills(Long characterId);
}
