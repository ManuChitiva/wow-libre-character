package com.wow.libre.domain.ports.in.guild_benefits;

import com.wow.libre.domain.model.GuildBenefitModel;

import java.util.List;

public interface GuildBenefitsPort {
    List<GuildBenefitModel> getBenefits(Long guildId, String transactionId);

}
