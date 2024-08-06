package com.wow.libre.domain.ports.out.character_benefit;

import com.wow.libre.infrastructure.entities.CharacterBenefitEntity;

import java.util.Optional;

public interface ObtainCharacterBenefit {
    Optional<CharacterBenefitEntity> claimedBenefit(Long characterId, String benefitCode, String transactionId);
}
