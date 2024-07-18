package com.wow.libre.domain.ports.in.benefit;

import com.wow.libre.domain.model.BenefitModel;

import java.util.List;

public interface BenefitPort {
    List<BenefitModel> benefits(String transactionId);
}
