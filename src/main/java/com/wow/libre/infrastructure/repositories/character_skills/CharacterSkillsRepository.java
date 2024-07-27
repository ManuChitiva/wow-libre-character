package com.wow.libre.infrastructure.repositories.character_skills;

import com.wow.libre.infrastructure.entities.CharacterSkillsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CharacterSkillsRepository extends CrudRepository<CharacterSkillsEntity, Long> {
    List<CharacterSkillsEntity> findByCharacterId(Long characterId);
}
