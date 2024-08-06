package com.wow.libre.infrastructure.repositories.character_service;

import com.wow.libre.domain.ports.out.character_services.ObtainCharacterServices;
import com.wow.libre.domain.ports.out.character_services.SaveCharacterServices;
import com.wow.libre.infrastructure.entities.CharacterServicesEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCharacterServiceAdapter implements ObtainCharacterServices, SaveCharacterServices {

    private final CharacterServiceRepository characterServiceRepository;

    public JpaCharacterServiceAdapter(CharacterServiceRepository characterServiceRepository) {
        this.characterServiceRepository = characterServiceRepository;
    }

    @Override
    public List<CharacterServicesEntity> getServices(Long characterId, String transactionId) {
        return characterServiceRepository.findByCharacterId(characterId);
    }

    @Override
    public Optional<CharacterServicesEntity> getService(Long characterId, Long skillId) {
        return characterServiceRepository.findByCharacterIdAndSkillId(characterId, skillId);
    }

    @Override
    public void save(CharacterServicesEntity characterServices, String transactionId) {
        characterServiceRepository.save(characterServices);
    }
}
