package com.wow.libre.domain.ports.in.character_benefit;

public interface CharacterBenefitPort {
    void save(Long characterId, String benefitCode, String transactionId);

    boolean canReceiveBenefit(Long characterId, String benefitCode, String transactionId);
}
