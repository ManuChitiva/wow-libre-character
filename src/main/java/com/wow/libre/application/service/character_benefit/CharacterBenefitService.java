package com.wow.libre.application.service.character_benefit;

import com.wow.libre.domain.ports.in.character_benefit.CharacterBenefitPort;
import com.wow.libre.domain.ports.out.character_benefit.ObtainCharacterBenefit;
import com.wow.libre.domain.ports.out.character_benefit.SaveCharacterBenefit;
import com.wow.libre.infrastructure.entities.CharacterBenefitEntity;
import org.springframework.stereotype.Service;

@Service
public class CharacterBenefitService implements CharacterBenefitPort {
    private final SaveCharacterBenefit saveCharacterBenefit;
    private final ObtainCharacterBenefit obtainCharacterBenefit;

    public CharacterBenefitService(SaveCharacterBenefit saveCharacterBenefit,
                                   ObtainCharacterBenefit obtainCharacterBenefit) {
        this.saveCharacterBenefit = saveCharacterBenefit;
        this.obtainCharacterBenefit = obtainCharacterBenefit;
    }

    @Override
    public void save(Long characterId, String benefitCode, String transactionId) {
        CharacterBenefitEntity characterBenefit = new CharacterBenefitEntity();
        characterBenefit.setBenefitCode(benefitCode);
        characterBenefit.setCharacterId(characterId);
        characterBenefit.setTransactionId(transactionId);
        saveCharacterBenefit.save(characterBenefit);
    }

    @Override
    public boolean canReceiveBenefit(Long characterId, String benefitCode, String transactionId) {
        return obtainCharacterBenefit.claimedBenefit(characterId, benefitCode, transactionId).isEmpty();
    }
}
