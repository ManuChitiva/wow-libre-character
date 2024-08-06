package com.wow.libre.infrastructure.repositories.character_service;

import com.wow.libre.infrastructure.entities.CharacterServicesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterServiceRepository extends CrudRepository<CharacterServicesEntity, Long> {

    Optional<CharacterServicesEntity> findByCharacterIdAndSkillId(Long characterId, Long skillId);
    List<CharacterServicesEntity> findByCharacterId(Long characterId);

}
