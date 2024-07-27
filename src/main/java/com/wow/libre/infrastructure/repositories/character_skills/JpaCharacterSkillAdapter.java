package com.wow.libre.infrastructure.repositories.character_skills;

import com.wow.libre.domain.ports.out.character_skills.ObtainCharacterSkills;
import com.wow.libre.infrastructure.entities.CharacterSkillsEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaCharacterSkillAdapter implements ObtainCharacterSkills {

    private final CharacterSkillsRepository characterSkillsRepository;

    public JpaCharacterSkillAdapter(CharacterSkillsRepository characterSkillsRepository) {
        this.characterSkillsRepository = characterSkillsRepository;
    }

    @Override
    public List<CharacterSkillsEntity> getCharacterIdSkills(Long characterId) {
        return characterSkillsRepository.findByCharacterId(characterId);
    }
}
