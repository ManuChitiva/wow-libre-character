package com.wow.libre.infrastructure.repositories.character_benefit;

import com.wow.libre.domain.ports.out.character_benefit.ObtainCharacterBenefit;
import com.wow.libre.domain.ports.out.character_benefit.SaveCharacterBenefit;
import com.wow.libre.infrastructure.entities.CharacterBenefitEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaCharacterBenefitAdapter implements ObtainCharacterBenefit, SaveCharacterBenefit {
    private final CharacterBenefitRepository characterBenefitRepository;

    public JpaCharacterBenefitAdapter(CharacterBenefitRepository characterBenefitRepository) {
        this.characterBenefitRepository = characterBenefitRepository;
    }

    @Override
    public Optional<CharacterBenefitEntity> claimedBenefit(Long characterId, String benefitCode, String transactionId) {
        return characterBenefitRepository.findByCharacterIdAndBenefitCode(characterId, benefitCode);
    }

    @Override
    public void save(CharacterBenefitEntity characterBenefit) {
        characterBenefitRepository.save(characterBenefit);
    }
}
