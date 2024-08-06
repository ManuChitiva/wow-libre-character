package com.wow.libre.domain.ports.in.character_services;

import com.wow.libre.domain.model.ProfessionServicesModel;

import java.util.List;

public interface CharacterServicesPort {

    List<ProfessionServicesModel> professionServices(Long characterId, String transactionId);

    ProfessionServicesModel professionService(Long characterId, Long skillId, String transactionId);

    void create(ProfessionServicesModel professionServicesModel, String transactionId);

    void update(boolean isPublic, String name, String description, Long characterId, Long skillId,
                String transactionId);

}
