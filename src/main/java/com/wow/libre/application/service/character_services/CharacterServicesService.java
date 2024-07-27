package com.wow.libre.application.service.character_services;

import com.wow.libre.domain.model.ProfessionServicesModel;
import com.wow.libre.domain.ports.in.character_services.CharacterServicesPort;
import com.wow.libre.domain.ports.out.character_services.ObtainCharacterServices;
import com.wow.libre.infrastructure.entities.CharacterServicesEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServicesService implements CharacterServicesPort {

    private final ObtainCharacterServices obtainCharacterServices;

    public CharacterServicesService(ObtainCharacterServices obtainCharacterServices) {
        this.obtainCharacterServices = obtainCharacterServices;
    }

    @Override
    public List<ProfessionServicesModel> professionServices(Long characterId, String transactionId) {
        return obtainCharacterServices.getServices(characterId, transactionId).stream().map(this::mapToModel).toList();
    }

    @Override
    public ProfessionServicesModel professionService(Long characterId, Long skillId, String transactionId) {
        return obtainCharacterServices.getService(characterId, skillId).map(this::mapToModel).orElse(null);
    }

    private ProfessionServicesModel mapToModel(CharacterServicesEntity characterServicesEntity) {
        return new ProfessionServicesModel(characterServicesEntity.getId(), characterServicesEntity.getCharacterId(),
                characterServicesEntity.getSkillId(), characterServicesEntity.getName(),
                characterServicesEntity.getDescription(), characterServicesEntity.getScore(),
                characterServicesEntity.isPublic());
    }
}
