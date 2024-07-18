package com.wow.libre.domain.ports.out.benefit;

import com.wow.libre.infrastructure.entities.BenefitEntity;

import java.util.List;

public interface ObtainBenefit {
        List<BenefitEntity> benefitsActive(String transactionId);
}
