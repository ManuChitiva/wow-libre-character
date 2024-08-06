package com.wow.libre.application.service.guild_benefits;

import com.wow.libre.domain.model.GuildBenefitModel;
import com.wow.libre.domain.ports.in.guild_benefits.GuildBenefitsPort;
import com.wow.libre.domain.ports.out.guild_benefit.ObtainGuildBenefit;
import com.wow.libre.infrastructure.entities.GuildBenefitEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuildBenefitsService implements GuildBenefitsPort {

    private final ObtainGuildBenefit obtainGuildBenefit;

    public GuildBenefitsService(ObtainGuildBenefit obtainGuildBenefit) {
        this.obtainGuildBenefit = obtainGuildBenefit;
    }


    @Override
    public List<GuildBenefitModel> getBenefits(Long guildId, String transactionId) {
        return obtainGuildBenefit.getBenefitsAndGuildId(guildId, transactionId).stream().map(this::mapToModel).toList();
    }

    private GuildBenefitModel mapToModel(GuildBenefitEntity benefit) {
        return new GuildBenefitModel(benefit.getId(), benefit.getGuildId(), benefit.getAcquisitionDate(),
                benefit.getExpirationDate(), benefit.getBenefitId().getTitle(), benefit.getBenefitId().getSubTitle(),
                benefit.getBenefitId().getDescription(), benefit.getBenefitId().getLogo(),
                benefit.getBenefitId().getItemId(), benefit.getBenefitId().getQuantity());
    }
}
