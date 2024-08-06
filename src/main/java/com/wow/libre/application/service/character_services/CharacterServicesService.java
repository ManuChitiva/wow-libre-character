package com.wow.libre.application.service.character_services;

import com.wow.libre.domain.exception.InternalException;
import com.wow.libre.domain.model.ProfessionServicesModel;
import com.wow.libre.domain.ports.in.character_services.CharacterServicesPort;
import com.wow.libre.domain.ports.out.character_services.ObtainCharacterServices;
import com.wow.libre.domain.ports.out.character_services.SaveCharacterServices;
import com.wow.libre.infrastructure.entities.CharacterServicesEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServicesService implements CharacterServicesPort {

    private final ObtainCharacterServices obtainCharacterServices;
    private final SaveCharacterServices saveCharacterServices;

    public CharacterServicesService(ObtainCharacterServices obtainCharacterServices,
                                    SaveCharacterServices saveCharacterServices) {
        this.obtainCharacterServices = obtainCharacterServices;
        this.saveCharacterServices = saveCharacterServices;
    }

    @Override
    public List<ProfessionServicesModel> professionServices(Long characterId, String transactionId) {
        return obtainCharacterServices.getServices(characterId, transactionId).stream().map(this::mapToModel).toList();
    }

    @Override
    public ProfessionServicesModel professionService(Long characterId, Long skillId, String transactionId) {
        return obtainCharacterServices.getService(characterId, skillId).map(this::mapToModel).orElse(null);
    }

    @Override
    public void create(ProfessionServicesModel professionServicesModel, String transactionId) {
        CharacterServicesEntity characterServices = new CharacterServicesEntity();
        characterServices.setScore(professionServicesModel.score());
        characterServices.setCharacterId(professionServicesModel.characterId());
        characterServices.setName(professionServicesModel.name());
        characterServices.setDescription(professionServicesModel.description());
        characterServices.setSkillId(professionServicesModel.skillId());
        characterServices.setPublic(professionServicesModel.isPublic());
        saveCharacterServices.save(characterServices, transactionId);
    }

    @Override
    public void update(boolean isPublic, String name, String description, Long characterId, Long skillId,
                       String transactionId) {

        Optional<CharacterServicesEntity> professionService = obtainCharacterServices.getService(characterId, skillId);

        if (professionService.isEmpty()) {
            throw new InternalException("The assigned profession is not available or learned.", transactionId);
        }

        CharacterServicesEntity updateCharacterServices = professionService.get();
        updateCharacterServices.setPublic(isPublic);
        updateCharacterServices.setName(name);
        updateCharacterServices.setDescription(description);
        saveCharacterServices.save(updateCharacterServices, transactionId);
    }


    private ProfessionServicesModel mapToModel(CharacterServicesEntity characterServicesEntity) {
        return new ProfessionServicesModel(characterServicesEntity.getId(), characterServicesEntity.getCharacterId(),
                characterServicesEntity.getSkillId(), characterServicesEntity.getName(),
                characterServicesEntity.getDescription(), characterServicesEntity.getScore(),
                characterServicesEntity.isPublic());
    }
}
