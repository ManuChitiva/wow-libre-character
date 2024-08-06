package com.wow.libre.domain.ports.out.character_services;

import com.wow.libre.infrastructure.entities.CharacterServicesEntity;

public interface SaveCharacterServices {
    void save(CharacterServicesEntity characterServices, String transactionId);
}
