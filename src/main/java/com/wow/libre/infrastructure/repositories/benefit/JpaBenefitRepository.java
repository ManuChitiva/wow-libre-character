package com.wow.libre.infrastructure.repositories.benefit;

import org.springframework.stereotype.Repository;

@Repository
public class JpaBenefitRepository {

    private final BenefitRepository benefitRepository;

    public JpaBenefitRepository(BenefitRepository benefitRepository) {
        this.benefitRepository = benefitRepository;
    }
}
