package com.wow.libre.infrastructure.repositories.benefit;

import com.wow.libre.domain.ports.out.benefit.ObtainBenefit;
import com.wow.libre.infrastructure.entities.BenefitEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaBenefitRepository implements ObtainBenefit {

    private final BenefitRepository benefitRepository;

    public JpaBenefitRepository(BenefitRepository benefitRepository) {
        this.benefitRepository = benefitRepository;
    }

    @Override
    public List<BenefitEntity> benefitsActive(String transactionId) {
        return benefitRepository.findByStatusIsTrue();
    }
}
