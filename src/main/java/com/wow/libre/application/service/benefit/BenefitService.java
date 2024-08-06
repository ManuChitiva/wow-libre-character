package com.wow.libre.application.service.benefit;

import com.wow.libre.domain.model.BenefitModel;
import com.wow.libre.domain.ports.in.benefit.BenefitPort;
import com.wow.libre.domain.ports.out.benefit.ObtainBenefit;
import com.wow.libre.infrastructure.entities.BenefitEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenefitService implements BenefitPort {
    private final ObtainBenefit obtainBenefit;

    public BenefitService(ObtainBenefit obtainBenefit) {
        this.obtainBenefit = obtainBenefit;
    }

    @Override
    public List<BenefitModel> benefits(String transactionId) {
        return obtainBenefit.benefitsActive(transactionId).stream().map(this::mapToModel).toList();
    }

    private BenefitModel mapToModel(BenefitEntity benefit) {
        return new BenefitModel(benefit.getId(), benefit.getTitle(), benefit.getSubTitle(), benefit.getDescription(),
                benefit.getLogo(), benefit.getItemId(), benefit.getQuantity(), benefit.isStatus(),
                benefit.getLinkWowHead());
    }
}
