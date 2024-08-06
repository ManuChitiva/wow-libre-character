package com.wow.libre.domain.ports.out.character_benefit;

import com.wow.libre.infrastructure.entities.CharacterBenefitEntity;

public interface SaveCharacterBenefit {
    void save(CharacterBenefitEntity characterBenefit);
}
