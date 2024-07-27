package com.wow.libre.domain.ports.in.character_skills;

import com.wow.libre.domain.model.CharacterProfessionsModel;

import java.util.List;

public interface CharacterSkillsPort {
    List<CharacterProfessionsModel> getProfessions(Long characterId, String transactionId);
}
