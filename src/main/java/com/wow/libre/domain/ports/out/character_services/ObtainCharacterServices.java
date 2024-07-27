package com.wow.libre.domain.ports.out.character_services;

import com.wow.libre.infrastructure.entities.CharacterServicesEntity;

import java.util.List;
import java.util.Optional;

public interface ObtainCharacterServices {
    List<CharacterServicesEntity> getServices(Long characterId, String transactionId);

    Optional<CharacterServicesEntity> getService(Long characterId, Long skillId);
}
