package com.wow.libre.application.service.character_skills;

import com.wow.libre.domain.enums.ProfessionsWow;
import com.wow.libre.domain.model.CharacterProfessionsModel;
import com.wow.libre.domain.model.ProfessionServicesModel;
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

    public CharacterSkillsService(ObtainCharacterSkills obtainCharacterSkills,
                                  CharacterServicesPort characterServicesPort) {
        this.obtainCharacterSkills = obtainCharacterSkills;
        this.characterServicesPort = characterServicesPort;
    }

    @Override
    public List<CharacterProfessionsModel> getProfessions(Long characterId, String transactionId) {
        return obtainCharacterSkills.getCharacterIdSkills(characterId).stream()
                .filter(filterProfession ->
                        ProfessionsWow.getById(filterProfession.getSkill().intValue()) != null
                ).map(characterSkills -> mapToModel(characterSkills, transactionId)).toList();
    }

    private CharacterProfessionsModel mapToModel(CharacterSkillsEntity characterSkills, String transactionId) {
        ProfessionServicesModel professionServices =
                characterServicesPort.professionService(characterSkills.getCharacterId(), characterSkills.getSkill(),
                        transactionId);
        ProfessionsWow professionsWow = ProfessionsWow.getById(characterSkills.getSkill().intValue());

        return new CharacterProfessionsModel(professionsWow.getLogo(), professionsWow.name(),
                characterSkills.getValue(),
                characterSkills.getMax(), professionServices);
    }
}
