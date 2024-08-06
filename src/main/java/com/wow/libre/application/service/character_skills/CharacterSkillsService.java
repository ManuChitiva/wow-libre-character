package com.wow.libre.application.service.character_skills;

import com.wow.libre.domain.dto.ProfessionServicesDto;
import com.wow.libre.domain.enums.ProfessionsWow;
import com.wow.libre.domain.exception.BadRequestException;
import com.wow.libre.domain.model.CharacterProfessionsModel;
import com.wow.libre.domain.model.ProfessionServicesModel;
import com.wow.libre.domain.ports.in.auth.AuthPort;
import com.wow.libre.domain.ports.in.character_services.CharacterServicesPort;
import com.wow.libre.domain.ports.in.character_skills.CharacterSkillsPort;
import com.wow.libre.domain.ports.out.character_skills.ObtainCharacterSkills;
import com.wow.libre.infrastructure.entities.CharacterSkillsEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterSkillsService implements CharacterSkillsPort {
    private final ObtainCharacterSkills obtainCharacterSkills;
    private final CharacterServicesPort characterServicesPort;
    private final AuthPort authPort;

    public CharacterSkillsService(ObtainCharacterSkills obtainCharacterSkills,
                                  CharacterServicesPort characterServicesPort, AuthPort authPort) {
        this.obtainCharacterSkills = obtainCharacterSkills;
        this.characterServicesPort = characterServicesPort;
        this.authPort = authPort;
    }

    @Override
    public List<CharacterProfessionsModel> getProfessions(Long characterId, String transactionId) {
        return obtainCharacterSkills.getCharacterIdSkills(characterId).stream().filter(filterProfession -> ProfessionsWow.getById(filterProfession.getSkill().intValue()) != null).map(characterSkills -> mapToModel(characterSkills, transactionId)).toList();
    }

    @Override
    public void assignService(Long accountWebId, ProfessionServicesDto professionServicesDto, String transactionId) {
        final Long characterId = professionServicesDto.getCharacterId();
        final Long skillId = professionServicesDto.getSkillId();

        authPort.verifyAccount(professionServicesDto.getAccountId(), accountWebId, transactionId);

        CharacterProfessionsModel profession =
                getProfessions(characterId, transactionId).stream().filter(professionSkill -> professionSkill.id() == skillId).findAny().orElse(null);

        if (profession == null) {
            throw new BadRequestException("The assigned profession is not available or learned.", transactionId);
        }

        ProfessionServicesModel professionServicesModel = characterServicesPort.professionService(characterId,
                skillId, transactionId);

        if (professionServicesModel != null) {
            throw new BadRequestException("There is already a professional service created", transactionId);
        }

        characterServicesPort.create(new ProfessionServicesModel(null, professionServicesDto.getCharacterId(),
                professionServicesDto.getSkillId(), profession.name(), professionServicesDto.getDescription(), 0D,
                professionServicesDto.isPublic()), transactionId);
    }

    @Override
    public void updateService(Long accountWebId, ProfessionServicesDto professionServicesDto, String transactionId) {

        authPort.verifyAccount(professionServicesDto.getAccountId(), accountWebId, transactionId);

        final Long characterId = professionServicesDto.getCharacterId();
        final Long skillId = professionServicesDto.getSkillId();

        CharacterProfessionsModel profession =
                getProfessions(characterId, transactionId).stream().filter(professionSkill -> professionSkill.id() == skillId).findAny().orElse(null);

        if (profession == null) {
            throw new BadRequestException("The assigned profession is not available or learned.", transactionId);
        }

        characterServicesPort.update(professionServicesDto.isPublic(), profession.name(),
                professionServicesDto.getDescription(), characterId, skillId, transactionId);
    }


    private CharacterProfessionsModel mapToModel(CharacterSkillsEntity characterSkills, String transactionId) {
        ProfessionServicesModel professionServices =
                characterServicesPort.professionService(characterSkills.getCharacterId(), characterSkills.getSkill(),
                        transactionId);
        ProfessionsWow professionsWow = ProfessionsWow.getById(characterSkills.getSkill().intValue());

        return new CharacterProfessionsModel(professionsWow.getId(), professionsWow.getLogo(), professionsWow.name(),
                characterSkills.getValue(), characterSkills.getMax(), professionServices);
    }
}
